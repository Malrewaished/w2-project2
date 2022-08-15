package com.example.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Merchant {
    @NotEmpty(message = "ID can not be empty")
    @Size(min = 3 , message = "ID can not be less than 3 digit")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3, message = "Name can not be less than 3 digit")
    private String name;
}
