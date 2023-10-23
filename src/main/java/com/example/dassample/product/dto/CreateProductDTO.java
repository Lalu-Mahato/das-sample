package com.example.dassample.product.dto;

import lombok.Data;

@Data
public class CreateProductDTO {
    private Integer code;
    private String name;
    private String description;
}
