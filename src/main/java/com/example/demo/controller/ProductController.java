package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.response.ProductResponse;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.Origin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepo;

    private static final String AUTH_MECHANISM = "bearerAuth";

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Add Products", description = "Save Product", tags = { "Products" })
    public ProductResponse addProduct(@Valid @RequestBody MultipartFile file, @RequestParam String title,
                                      @RequestParam String desc, @RequestParam ArrayList<String> categories,
                                      @RequestParam ArrayList<String> size, @RequestParam ArrayList<String> color,
                                      @RequestParam String price, @RequestParam boolean inStock) throws IOException {
        return productService.saveProduct(file,title,desc,categories,size,color,price,inStock);
    }

    @PutMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update Products", description = "Edit Product", tags = { "Products" })
    public ProductResponse updateProduct(@Valid @RequestBody MultipartFile file, @RequestParam String title,
                                      @RequestParam String desc, @RequestParam ArrayList<String> categories,
                                      @RequestParam ArrayList<String> size, @RequestParam ArrayList<String> color,
                                      @RequestParam String price, @RequestParam boolean inStock, @RequestParam String id) throws IOException {

        Product existingProduct = productRepo.findById(id).orElse(null);

        return productService.editProduct(file,title,desc,categories,size,color,price,inStock,existingProduct);
    }


    @GetMapping("/product")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Product ", description = "Get Product", tags = { "Products" })
    public List<Product> fetchProducts(){
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Product By Id", description = "Find Records of Product By Id", tags = { "Products" })
    public ProductResponse fetchProductById(@PathVariable String id){
        return productService.getProductByID(id);
    }

    @DeleteMapping("/product/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete Product", description = "Delete Product By ID", tags = { "Products" })
    public ProductResponse removeProduct(@PathVariable String id ) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/product/category/{name}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Product By Category ", description = "Get Product By category", tags = { "Products" })
    public List<Product> fetchProductsByCategory(@PathVariable String name){
        return productService.getProductsByCategory(name);
    }

}
