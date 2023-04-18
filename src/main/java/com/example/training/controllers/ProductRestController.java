package com.example.training.controllers;

import com.example.training.dto.AddProductRequest;
import com.example.training.dto.UpdateProductRequest;
import com.example.training.entities.AuthRequest;
import com.example.training.entities.Product;
import com.example.training.entities.User;
import com.example.training.exceptions.ProductNotFoundException;
import com.example.training.jwtutility.JwtUtility;
import com.example.training.repository.UserRepository;
import com.example.training.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private IProductService service;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String welcomeToShopping(){
       return "Welcome to Product App !!";
    }

    @PostMapping("/products/add")
    @PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest request){
        Product product = new Product(request.getProductName(),request.getProductDescription(),request.getPrice(),
                request.getProductFeatures(),request.getProductStatus());
        Product added = service.addProduct(product);
        return new ResponseEntity(added, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List> getAllProducts(){
    List list = service.getAllProducts();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer productId){
        Product product = service.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    /*@GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable("name") String productName){
        Product product = service.findProductByName(productName);
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }*/


    @PutMapping("/products/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateStudent(@RequestBody UpdateProductRequest requestProduct){
       //Product product = new Product();
        Integer id = requestProduct.getProductId();
       if(id == null){
           throw new ProductNotFoundException("product Id not Found");
       }
       String prodDesc = requestProduct.getProductDescription();
       String prodFeat= requestProduct.getProductFeatures();
       String prodName = requestProduct.getProductName();
       String prodStatus = requestProduct.getProductStatus();
       Float prodPrice = requestProduct.getPrice();
       Product updatedProduct = new Product(prodName,prodDesc,prodPrice,prodFeat,prodStatus);
       updatedProduct.setProductId(id);
       updatedProduct = service.updateProduct(updatedProduct);
       return new ResponseEntity<>(updatedProduct , HttpStatus.ACCEPTED);
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/products/deletebyid/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable("id") Integer id) {
        service.deleteProduct(id);
    }

}


