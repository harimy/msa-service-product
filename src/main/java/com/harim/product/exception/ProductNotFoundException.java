package com.harim.product.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String s) {
        super(s);
    }

    public ProductNotFoundException() {
    }
}
