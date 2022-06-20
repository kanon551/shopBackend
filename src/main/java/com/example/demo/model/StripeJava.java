package com.example.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StripeJava {
        private String description;
        private String name;
        private String line1;
        private String postalCode;
        private String city;
        private String state;
        private String country;
        private String amount;
        private String receiptMail;
        private String tokenID;

}
