package com.praktikum.wisataku.Dao;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.praktikum.wisataku.Model.CartProduct;

import java.util.List;

@Dao
public interface CartProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartProduct(CartProduct cartProduct);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartProducts(List<CartProduct> cartProducts);

    @Update
    void updateCartProduct(CartProduct cartProduct);

    @Delete
    void deleteCartProduct(CartProduct cartProduct);

    @Query("SELECT * FROM cart_products")
    LiveData<List<CartProduct>> getAllCartProducts();

    @Transaction
    @Query("SELECT * FROM cart_products WHERE cart_id = :cartId")
    LiveData<List<CartProduct>> getProductsByCartId(int cartId);

    @Query("SELECT * FROM cart_products WHERE id = :id")
    CartProduct getCartProductById(int id);

    @Query("UPDATE Cart_Products SET is_selected = :isSelected WHERE id = :cartProductId")
    void updateCartProductSelection(int cartProductId, boolean isSelected);


}



