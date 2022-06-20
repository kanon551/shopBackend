package com.example.demo.model;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Address {

    private String city;
    private String country;
    private String line1;
    private String postalCode;
    private String state;
}
