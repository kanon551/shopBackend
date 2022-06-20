package com.example.demo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Component
@Document(collection = "users")
public class User {

    private String _id;
    private String email;
    private String password;
    private boolean isAdmin;

    private String currentDate;
    private String updatedDate;
}
