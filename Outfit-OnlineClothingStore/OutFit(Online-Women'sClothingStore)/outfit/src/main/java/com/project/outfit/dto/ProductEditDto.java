package com.project.outfit.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ProductEditDto {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
}
