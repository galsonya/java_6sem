package org.example;

import java.lang.reflect.Field;
import java.util.Properties;
import java.io.InputStream;

public class Injector {
    private Properties properties;

    public Injector() throws Exception {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        }
    }

    public <T> T inject(T object) throws Exception {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());

                if (implementationClassName == null) {
                    throw new RuntimeException("No implementation defined for " +
                            fieldType.getName() + " in config.properties");
                }

                Class<?> implementationClass = Class.forName(implementationClassName);
                Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();

                field.setAccessible(true);
                field.set(object, implementationInstance);
            }
        }
        return object;
    }
}