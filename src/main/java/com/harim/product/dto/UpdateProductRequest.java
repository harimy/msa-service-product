package com.harim.product.dto;

import lombok.Data;

@Data
public class UpdateProductRequest {

    private String name;
    private int price;
    private int stockQuantity;
}
