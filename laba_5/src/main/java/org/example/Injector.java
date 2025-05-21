package org.example;

import java.lang.reflect.Field;
import java.util.Properties;
import java.io.InputStream;

/**
 * Класс Injector отвечает за внедрение зависимостей в объекты.
 * Он использует рефлексию для поиска полей, помеченных аннотацией {@link AutoInjectable},
 * и инициализирует их соответствующими реализациями, указанными в конфигурационном файле.
 */
public class Injector {
    private Properties properties;

    /**
     * Конструктор, который загружает конфигурацию из файла properties.
     *
     * @throws Exception если не удается загрузить файл конфигурации
     */
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

    /**
     * Внедряет зависимости в указанный объект.
     *
     * @param object объект, в который будут внедрены зависимости
     * @param <T> тип объекта
     * @return объект с внедренными зависимостями
     * @throws Exception если возникает ошибка при внедрении зависимостей
     */
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
