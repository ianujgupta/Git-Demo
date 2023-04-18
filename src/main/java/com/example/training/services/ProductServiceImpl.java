package com.example.training.services;

import com.example.training.entities.Product;
import com.example.training.exceptions.InvalidArgumentException;
import com.example.training.exceptions.ProductNotFoundException;
import com.example.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product getProductById(Integer productId) {
        Optional<Product> optional = repository.findById(productId);
        boolean exist = optional.isPresent();
        if (!exist) {
            throw new ProductNotFoundException("Product not found with Id " + productId);
        }
        return optional.get();
    }

    @Override
    public Product updateProduct(Product product) {
        Integer productId = product.getProductId();
        if (product == null) {
            throw new ProductNotFoundException("Product not found with Id " + productId);
        }
        Product updatedProduct = repository.save(product);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (productId == null) {
            throw new ProductNotFoundException("Product not found with Id " + productId);
        }
        repository.deleteById(productId);
    }

    /*@Override
    public Product findProductByName(String productName) {
        Product product = repository.findByName(productName);
        if(product == null){
            throw new ProductNotFoundException("product not found with name " + productName);
        }
        return product;
    }*/

    @Override
    public Product addProduct(Product product) {
        if (product == null) {
            throw new InvalidArgumentException("Product can't be empty!!");
        }
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
