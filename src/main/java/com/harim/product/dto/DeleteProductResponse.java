package com.harim.product.dto;

import lombok.Data;

@Data
public class DeleteProductResponse {

    private Long id;

    public DeleteProductResponse(Long id) {
        this.id = id;
    }
}
