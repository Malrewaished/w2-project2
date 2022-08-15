package com.example.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class MerchantStock {

    @NotEmpty(message = "ID can not be empty")
    @Min(value = 3 , message = "ID can not be less than 3 digit")
    private String id;

    @NotEmpty(message = "ProductID can not be empty")
    @Min(value = 3, message = "Name can not be less than 3 character")
    private String productID;

    @NotEmpty(message = "MerchantID can not be empty")
    @Min(value = 3, message = "Name can not be less than 3 character")
    public String merchantID;

    @NotNull(message = "Stock can not be empty")
    private Integer stock;
}