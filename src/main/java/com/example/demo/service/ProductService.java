package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.response.ProductResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepo;

    public ProductResponse saveProduct(MultipartFile file, String title, String desc, ArrayList<String> categories,
                                       ArrayList<String> size, ArrayList<String> color, String price, boolean inStock) throws IOException {

             Product product = new Product();
               product.setTitle(title);
               product.setDesc(desc);
               product.setCategories(categories);
               product.setSize(size);
               product.setColor(color);
               product.setPrice(price);
               product.setInStock(inStock);
        Date currentDate = new Date();
                product.setCreatedDate(currentDate.toString());
                product.setUpdatedDate(currentDate.toString());


        if(file == null) {
            byte[] data = new byte[] {};
            product.setImg(new Binary(BsonBinarySubType.BINARY, data));
        }
        else {
            product.setImg(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        }

            productRepo.save(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProduct(product);
        productResponse.setMessage("Product Successfully Saved");

            return productResponse;
        }

        public ProductResponse editProduct(MultipartFile file, String title, String desc, ArrayList<String> categories,
                                           ArrayList<String> size, ArrayList<String> color, String price,
                                           boolean inStock, Product existingProduct) throws IOException{

            Date currentDate = new Date();

            existingProduct.setTitle(title);
            existingProduct.setDesc(desc);
            existingProduct.setCategories(categories);
            existingProduct.setSize(size);
            existingProduct.setColor(color);
            existingProduct.setPrice(price);
            existingProduct.setUpdatedDate(currentDate.toString());
            existingProduct.setInStock(inStock);

            if(file == null) {
                byte[] existingImage = existingProduct.getImg().getData();
                existingProduct.setImg(new Binary(BsonBinarySubType.BINARY, existingImage));
            }
            else {
                existingProduct.setImg(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            }
            productRepo.save(existingProduct);

            ProductResponse productResponse = new ProductResponse();
            productResponse.setProduct(existingProduct);
            productResponse.setMessage("Product Successfully Updated");

            return productResponse;

        }

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public ProductResponse getProductByID(String id){
        Product product = productRepo.findById(id).orElse(null);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProduct(product);
        if (product == null){
            productResponse.setId(id);
            productResponse.setMessage("Product Doesnt exist with Id: "+id);

            return productResponse;
        }
        else {

            productResponse.setId(id);
            productResponse.setMessage("Product Found with id"+id);
            return productResponse;
        }
    }

    public ProductResponse deleteProduct(String id) {
        productRepo.deleteById(id);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setMessage("Product Successfully Deleted");

        return productResponse;


    }

    public List<Product> getProductsByCategory(String name){
        return productRepo.findByCategories(name);
    }

}
