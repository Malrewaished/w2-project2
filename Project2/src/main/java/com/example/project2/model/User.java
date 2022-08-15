package com.example.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class User {

    @NotEmpty(message = "ID can not be empty")
    @Size(min = 3 , message = "ID can not be less than 3 digit")
    private String id;

    @NotEmpty(message = "Username can not be empty")
    @Size(min = 5, message = "Username can not be less than 5 character")
    private String username;


    @NotEmpty(message = "password can not be empty")
    @Size(min = 6, message = "password must be more than 5 character and must have characters and digits")
    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.*[A-Z]).{6,}$",message = "please enter strong password")
    private String password;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "must be valid email")
    private String email;

    @NotEmpty(message = "Role can not be empty")
    @Pattern(regexp = "(Admin|Customer)",message = "Role must be in Admin or Customer only")
    private String role;

    @NotNull(message = "Balance can not be empty")
    @Positive(message = "Balance have to be positive")
    private Integer balance;

}
