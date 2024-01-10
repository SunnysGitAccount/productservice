package com.scaler.productservice.exceptions;

public class NoProductFoundForGivenId extends RuntimeException {
    public NoProductFoundForGivenId(String s) {
        super(s);
    }
}
