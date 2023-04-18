package com.example.training.services;
import com.example.training.entities.Product;
import java.util.List;

public interface IProductService {
    public Product addProduct(Product product);
    public Product getProductById(Integer productId);
    public List<Product> getAllProducts();
    public Product updateProduct(Product product);
    public void deleteProduct(Integer productId);
    //public Product findProductByName(String productName);
}
