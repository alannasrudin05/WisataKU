package com.praktikum.wisataku.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.ProductModel;

public class CartProductWithRelations {

    @Embedded
    private CartProduct cartProduct;

    @Relation(
            parentColumn = "cartId",
            entityColumn = "id"
    )
    private CartModel cart;

    @Relation(
            parentColumn = "product_id",
            entityColumn = "id"
    )
    private ProductModel product;

    public CartProduct getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
