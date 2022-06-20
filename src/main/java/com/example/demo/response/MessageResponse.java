package com.example.demo.response;

import com.example.demo.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageResponse {

    private User user;
    private String id;
    private String message;
    private String token;
}
