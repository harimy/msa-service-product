package com.harim.product.dto;

import lombok.Data;

@Data
public class CreateProductResponse {

    private Long id;

    public CreateProductResponse(Long id) {
        this.id = id;
    }
}
