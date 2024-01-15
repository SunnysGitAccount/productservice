package com.scaler.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Given Category ID not found!")
public class NoCategoryFoundForGivenId extends Throwable {
    public NoCategoryFoundForGivenId(String message) {
        super(message);
    }
}
