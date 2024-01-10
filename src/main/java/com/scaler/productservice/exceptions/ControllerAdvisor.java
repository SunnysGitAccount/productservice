package com.scaler.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Map<String, String>> errorMsgs = new ArrayList<>();
        errorMsgs.add(new HashMap<>());
        errorMsgs.get(0).put("error", restClientException.getMessage());
        return new ResponseEntity<>(errorMsgs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Map<String, String>> testArithmeticExceptions(ArithmeticException arithmeticException) {
        Map<String, String> errorMsgs = new HashMap<>();
        errorMsgs.put("error", arithmeticException.getMessage());
        return new ResponseEntity<>(errorMsgs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
