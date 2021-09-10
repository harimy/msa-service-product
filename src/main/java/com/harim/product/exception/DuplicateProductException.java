package com.harim.product.exception;

public class DuplicateProductException extends RuntimeException{
    public DuplicateProductException() {
    }

    public DuplicateProductException(String message) {
        super(message);
    }
}
