package com.example.demo.controller;
import com.example.demo.model.StripeJava;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "https://shopperfront.herokuapp.com")
@RequestMapping("/api/checkout")
@RestController
public class StripePaymentController {
    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentIntent payments(@Valid @RequestBody StripeJava stripeJava) throws StripeException {
        Stripe.apiKey = "sk_test_51L5jWUSF3kjGYdaFsHC7PLiHtPMYxyhfy2DBAuAJW6PajjBTMNUFuBywnfxsGCJdWjAIS3dLXRUbyFxdN7zfxdiU00WlKVMolB";


        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setDescription(stripeJava.getDescription())
                        .setShipping(
                                PaymentIntentCreateParams.Shipping
                                        .builder()
                                        .setName(stripeJava.getName())
                                        .setTrackingNumber(stripeJava.getTokenID())
                                        .setAddress(
                                                PaymentIntentCreateParams.Shipping.Address
                                                        .builder()
                                                        .setLine1(stripeJava.getLine1())
                                                        .setPostalCode(stripeJava.getPostalCode())
                                                        .setCity(stripeJava.getCity())
                                                        .setState(stripeJava.getState())
                                                        .setCountry(stripeJava.getCountry())
                                                        .build()
                                        )
                                        .build()
                        )
                        .setAmount(Long.parseLong(stripeJava.getAmount(),10))
                        .setReceiptEmail(stripeJava.getReceiptMail())
                        .setCurrency("INR")
                        .addPaymentMethodType("card")
                        .build();



        PaymentIntent paymentIntent = PaymentIntent.create(params);

        String paymentIntentJson = paymentIntent.toJson();

        Gson g = new Gson();
        PaymentIntent paymentIntent1 = g.fromJson(paymentIntentJson, PaymentIntent.class);
        return paymentIntent1;
    }
}
