package com.praktikum.wisataku.Model;


public class CartWithProduct {
    public int id;
    public int cartId;
    public String destinationTitle;
    public int productCount;
    public int cartProductId;
    public int cartProductProductId;
    public String productTitle;
    public String picProduct;
    public double price;
    public int quantity;
    public boolean isSelected;

    public CartWithProduct() {
    }

    public CartWithProduct(int id,int cartId, String destinationTitle, int productCount, int cartProductId, int cartProductProductId, String productTitle, String picProduct, double price, int quantity, boolean isSelected) {
        this.id = id;
        this.cartId = cartId;
        this.destinationTitle = destinationTitle;
        this.productCount = productCount;
        this.cartProductId = cartProductId;
        this.cartProductProductId = cartProductProductId;
        this.productTitle = productTitle;
        this.picProduct = picProduct;
        this.price = price;
        this.quantity = quantity;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartProductProductId() {
        return cartProductProductId;
    }

    public void setCartProductProductId(int cartProductProductId) {
        this.cartProductProductId = cartProductProductId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getDestinationTitle() {
        return destinationTitle;
    }

    public void setDestinationTitle(String destinationTitle) {
        this.destinationTitle = destinationTitle;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(int cartProductId) {
        this.cartProductId = cartProductId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getPicProduct() {
        return picProduct;
    }

    public void setPicProduct(String picProduct) {
        this.picProduct = picProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}