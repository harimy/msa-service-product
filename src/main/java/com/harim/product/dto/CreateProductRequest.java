package com.harim.product.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateProductRequest {

    @NotEmpty
    private String name;

    private int price;
    private int stockQuantity;
}
