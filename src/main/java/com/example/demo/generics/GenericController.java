package com.example.demo.generics;

import com.example.demo.response.ProductResponse;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/generics")
@RestController
public class GenericController {

    @Autowired
    private GenericTest genericTest;

    @Autowired
    private GenericService genericService;

    private static final String AUTH_MECHANISM = "bearerAuth";

    @GetMapping("/product/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Product By Id", description = "Find Records of Product By Id", tags = { "Products" })
    public GenericTest fetchProductById(@PathVariable String id){
        return genericService.getProductByID(id);
    }
}
