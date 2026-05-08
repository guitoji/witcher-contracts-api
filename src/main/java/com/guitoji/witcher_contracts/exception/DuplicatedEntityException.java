package com.guitoji.witcher_contracts.exception;

public class DuplicatedEntityException extends RuntimeException {
    public DuplicatedEntityException(String message) {
        super(message);
    }
}
