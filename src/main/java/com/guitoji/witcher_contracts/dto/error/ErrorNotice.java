package com.guitoji.witcher_contracts.dto.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorNotice(Integer status, String message, List<ErrorFields> errorFields) {

    public static ErrorNotice notFound(String message) {
        return new ErrorNotice(HttpStatus.NOT_FOUND.value(), message, List.of());
    }

    public static ErrorNotice conflict(String message) {
        return new ErrorNotice(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
