package com.example.training.dto;

public class UpdateProductRequest {
        private Integer productId;
        private String productName;
        private String productDescription;
        private Float price;
        private String productFeatures;
        private String productStatus;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getProductFeatures() {
            return productFeatures;
        }

        public void setProductFeatures(String productFeatures) {
            this.productFeatures = productFeatures;
        }

        public String getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

}
