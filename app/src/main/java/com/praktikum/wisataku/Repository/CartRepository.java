package com.praktikum.wisataku.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.praktikum.wisataku.Dao.CartDao;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;

import java.util.List;

public class CartRepository {
    private CartDao cartDao;
    private final LiveData<List<CartModel>> allCarts;

    public CartRepository(Context context) {
        WisataDatabase db = WisataDatabase.getInstance(context);
        cartDao = db.cartDao();
        allCarts = cartDao.getAllCarts();
    }
//    baru
    public LiveData<List<CartModel>> getAllCarts() {
        return allCarts;
    }

    public LiveData<List<CartProduct>> getProductsByCartId(int cartId) {
        return cartDao.getProductsByCartId(cartId);
    }

    public void updateProduct(CartProduct cartProduct) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartDao.updateProduct(cartProduct));
    }
//  end baru

    public void insertCart(CartModel cart) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartDao.insertCart(cart));
    }

    public void insertCarts(List<CartModel> cartModels) {
        WisataDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.insertCarts(cartModels);
        });
    }

    public LiveData<List<CartWithProduct>> getCartDetails() {
        return cartDao.getCartDetails();
    }

    public void updateCart(CartModel cart) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartDao.updateCart(cart));
    }

    public void deleteCart(CartModel cart) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartDao.deleteCart(cart));
    }


}
