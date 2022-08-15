package com.example.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class Category {

    @NotEmpty(message = "ID can not be empty")
    @Min(value = 3 , message = "ID can not be less than 3 digit")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Min(value = 3, message = "Name can not be less than 3 digit")
    private String name;
}
