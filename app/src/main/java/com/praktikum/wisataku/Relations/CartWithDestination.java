package com.praktikum.wisataku.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.DestinationModel;

public class CartWithDestination {

    @Embedded
    private CartModel cart;

    @Relation(
            parentColumn = "destination_id",
            entityColumn = "id"
    )
    private DestinationModel destination;



    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public DestinationModel getDestination() {
        return destination;
    }

    public void setDestination(DestinationModel destination) {
        this.destination = destination;
    }
}
