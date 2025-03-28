package com.intprog.project.util.validation;

import java.util.Set;

public class ValidationException extends RuntimeException {
    public <T> ValidationException(Set<String> errors) {
        super(String.join("\n", errors));
    }
}
