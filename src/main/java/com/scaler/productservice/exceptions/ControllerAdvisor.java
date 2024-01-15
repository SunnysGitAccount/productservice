package com.scaler.productservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NoProductFoundForGivenId.class)
    public ResponseEntity<List<Map<String, String>>> handleHttpServerExceptions(NoProductFoundForGivenId restClientException) {
        return getListResponseEntity(restClientException.getMessage());
    }

    @ExceptionHandler(NoCategoryFoundForGivenId.class)
    public ResponseEntity<List<Map<String, String>>> handleHttpServerExceptions(NoCategoryFoundForGivenId restClientException) {
        return getListResponseEntity(restClientException.getMessage());
    }

    private ResponseEntity<List<Map<String, String>>> getListResponseEntity(String message) {
        List<Map<String, String>> errorMsgs = new ArrayList<>();
        errorMsgs.add(new HashMap<>());
        errorMsgs.get(0).put("error", message);
        return new ResponseEntity<>(errorMsgs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Map<String, String>> testArithmeticExceptions(ArithmeticException arithmeticException) {
        Map<String, String> errorMsgs = new HashMap<>();
        errorMsgs.put("error", arithmeticException.getMessage());
        return new ResponseEntity<>(errorMsgs, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<Map<String, String>>> handleBindingErrors(MethodArgumentNotValidException exception) {
        List<Map<String, String>> errorList = exception.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                })
                .toList();

        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler
    ResponseEntity<List<Map<String, String>>> handleJPAViolations(TransactionSystemException exception) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        if (exception.getCause().getCause() instanceof ConstraintViolationException cve) {

            List<Map<String, String>> errors = cve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String, String> errorMap = new HashMap<>();
                        errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                        return errorMap;
                    })
                    .toList();
            return responseEntity.body(errors);
        }

        return responseEntity.build();
    }
}
