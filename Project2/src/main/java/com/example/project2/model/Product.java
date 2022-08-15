package com.example.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Product {


    @NotEmpty(message = "ID can not be empty")
    @Size(min = 3 , message = "ID can not be less than 3 digit")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3, message = "Name can not be less than 3 digit")
    private String name;

    @NotNull(message = "Price can not be empty")
    @Positive( message = "The value must be positive numbers")
    public Integer price;

    @NotEmpty(message = "CategoryID can not be empty")
    @Size(min = 3 , message = "CategoryID can not be less than 3 digit")
    private String categoryID;

}
