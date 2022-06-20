package com.example.demo.generics;


import org.springframework.stereotype.Component;

@Component
public class GenericTest<T> {

    T genericResponse;

    public T getGenericResponse() {
        return genericResponse;
    }

    public void setGenericResponse(T genericResponse) {
        this.genericResponse = genericResponse;
    }
}
