package com.harim.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProductResponse {

    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

}
