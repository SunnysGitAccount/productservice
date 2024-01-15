package com.scaler.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Given Product ID not found!")
public class NoProductFoundForGivenId extends Throwable {
    public NoProductFoundForGivenId(String s) {
        super(s);
    }
}
