package com.app.e_commerceapp.models;

import org.json.JSONObject;

public class Products {
    String ProductImage;
    String ProductName;
    String ProductPrice;

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }


    public Products parsingObject(JSONObject jsonObject) {
        this.setProductImage(jsonObject.optString("src"));
        this.setProductName(jsonObject.optString("name"));
        this.setProductPrice(jsonObject.optString("price"));
        return this;
    }
}
