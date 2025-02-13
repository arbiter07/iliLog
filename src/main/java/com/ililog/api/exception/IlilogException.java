package com.ililog.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class IlilogException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public IlilogException(String message) {
        super(message);
    }

    public IlilogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
