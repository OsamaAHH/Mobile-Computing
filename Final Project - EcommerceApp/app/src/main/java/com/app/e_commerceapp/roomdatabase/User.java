package com.app.e_commerceapp.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "productName")
    public String productName;

    @ColumnInfo(name = "productPrice")
    public String productPrice;

    @ColumnInfo(name= "productImage")
    public String productImage;

    @ColumnInfo(name= "productQuantity")
    public int productQuantity;



    public User(int uid, String productName, String productPrice, int productId, String productImage, int productQuantity) {
        this.uid = uid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = productId;
        this.productImage = productImage;
        this.productQuantity = productQuantity;
    }

    public int getUid() {
        return uid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


}
