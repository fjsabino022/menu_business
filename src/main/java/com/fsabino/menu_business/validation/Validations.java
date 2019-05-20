package com.fsabino.menu_business.validation;

import java.util.function.Supplier;

import static org.springframework.util.StringUtils.isEmpty;

public class Validations {

    public static void checkNullParam(Object value, Supplier<String> errorMsg) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException(errorMsg.get());
        }
    }

    public static void checkEmptyParam(String value, Supplier<String> errorMsg) throws IllegalArgumentException {
        if (isEmpty(value)) {
            throw new IllegalArgumentException(errorMsg.get());
        }
    }
}
