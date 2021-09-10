package com.harim.product.exception;

public class StockQuantityException extends RuntimeException{
    public StockQuantityException() {
    }

    public StockQuantityException(String message) {
        super(message);
    }
}
