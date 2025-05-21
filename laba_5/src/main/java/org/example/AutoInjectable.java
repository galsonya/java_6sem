package org.example;

import java.lang.annotation.*;

/**
 * Аннотация, используемая для пометки полей, которые должны быть автоматически инициализированы
 * с помощью механизма внедрения зависимостей.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {}
