package com.example.training.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productDescription;
    private Float price;
    private String productFeatures;
    private String productStatus;

    public Product(String productName, String productDescription, Float price, String productFeatures, String productStatus) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.productFeatures = productFeatures;
        this.productStatus = productStatus;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Float getPrice() {
        return price;
    }

    public String getProductFeatures() {
        return productFeatures;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setProductFeatures(String productFeatures) {
        this.productFeatures = productFeatures;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

}
