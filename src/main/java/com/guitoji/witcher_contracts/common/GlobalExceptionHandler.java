package com.guitoji.witcher_contracts.common;

import com.guitoji.witcher_contracts.dto.error.ErrorFields;
import com.guitoji.witcher_contracts.dto.error.ErrorNotice;
import com.guitoji.witcher_contracts.exception.DuplicatedEntityException;
import com.guitoji.witcher_contracts.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorNotice handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorFields> errorFields = fieldErrors
                .stream()
                .map(fe -> new ErrorFields(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorNotice(HttpStatus.BAD_REQUEST.value(), "Validation Error", errorFields);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorNotice handleIllegalArgumenteException(IllegalArgumentException e) {
        return new ErrorNotice(HttpStatus.BAD_REQUEST.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorNotice handleNotFoundException(NotFoundException e) {
        return ErrorNotice.notFound(e.getMessage());
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorNotice handleDuplicatedEntityException(DuplicatedEntityException e) {
        return ErrorNotice.conflict(e.getMessage());
    }
}
