package com.praktikum.wisataku.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.Relations.CartWithDestination;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCart(CartModel cartModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCarts(List<CartModel> cartModel);

    @Query("SELECT * FROM carts")
    LiveData<List<CartModel>> getAllCarts();

    @Transaction
    @Query("SELECT * FROM carts")
    LiveData<List<CartWithDestination>> getCartWithDestination();

    @Update
    void updateCart(CartModel cartModel);

    @Delete
    void deleteCart(CartModel cartModel);

    @Query("SELECT " +
            "Cart_Products.id AS id, " +
            "Carts.id AS cartId, " +
            "Destinations.title AS destinationTitle, " +
            "COUNT(Cart_Products.id) AS productCount, " +
            "Cart_Products.cart_id AS cartProductId, " +
            "Cart_Products.product_id AS cartProductProductId, " +
            "Products.title AS productTitle, " +
            "Products.pic_product As picProduct, " +
            "Products.price AS price, " +
            "Cart_Products.is_selected AS isSelected, " +
            "Cart_Products.quantity " +
            "FROM Carts " +
            "JOIN Destinations ON Carts.destination_id = Destinations.id " +
            "JOIN Cart_Products ON Carts.id = Cart_Products.cart_id " +
            "JOIN Products ON Cart_Products.product_id = Products.id " +
            "GROUP BY Carts.id, Cart_Products.id , Products.id")
    LiveData<List<CartWithProduct>> getCartDetails();


    @Query("SELECT * FROM cart_products WHERE cart_id = :cartId")
    LiveData<List<CartProduct>> getProductsByCartId(int cartId);

    @Update
    void updateProduct(CartProduct product);

}
