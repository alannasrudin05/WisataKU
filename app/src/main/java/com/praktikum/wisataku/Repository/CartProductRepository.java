package com.praktikum.wisataku.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.praktikum.wisataku.Dao.CartProductDao;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.CartProduct;

import java.util.List;

public class CartProductRepository {
    private CartProductDao cartProductDao;

    public CartProductRepository(Application application) {
        WisataDatabase db = WisataDatabase.getInstance(application);
        cartProductDao = db.cartProductDao();
    }

    public LiveData<List<CartProduct>> getAllCartProducts() {
        return cartProductDao.getAllCartProducts();
    }

    public LiveData<List<CartProduct>> getProductsByCartId(int cartId) {
        return cartProductDao.getProductsByCartId(cartId);
    }

    public void insertCartProduct(CartProduct cartProduct) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartProductDao.insertCartProduct(cartProduct));
    }
 public void insertCartProducts(List<CartProduct> cartProduct) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartProductDao.insertCartProducts(cartProduct));
    }

    public void updateCartProduct(CartProduct cartProduct) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartProductDao.updateCartProduct(cartProduct));
    }
    public void updateCartProductSelected(int cartProduct, boolean isSelected) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartProductDao.updateCartProductSelection(cartProduct, isSelected));
    }

    public void deleteCartProduct(CartProduct cartProduct) {
        WisataDatabase.databaseWriteExecutor.execute(() -> cartProductDao.deleteCartProduct(cartProduct));
    }
}
