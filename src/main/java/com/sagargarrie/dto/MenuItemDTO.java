package com.sagargarrie.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemDTO {
    private Long id;
    private String category;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean veg;
    private String imageUrl;
}
