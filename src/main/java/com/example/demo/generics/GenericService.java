package com.example.demo.generics;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenericService {


    @Autowired
    private ProductRepository productRepo;


    public GenericTest getProductByID(String id) {

        Product product = productRepo.findById(id).orElse(null);

        if (product == null){
            GenericTest<String> generics = new GenericTest<String>();
            generics.setGenericResponse("Coudnt Find Product with ID "+ id);

            return generics;
        }
        else{
            GenericTest<Product> generics = new GenericTest<Product>();
            generics.setGenericResponse(product);
            return generics;
        }
    }
}
