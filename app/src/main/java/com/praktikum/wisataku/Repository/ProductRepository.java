package com.praktikum.wisataku.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.praktikum.wisataku.Dao.ProductDao;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;

    public ProductRepository(Context context) {
        WisataDatabase db = WisataDatabase.getInstance(context);
        productDao = db.productDao();
    }

    public LiveData<List<ProductModel>> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void insertProduct(ProductModel productModel) {
        new Thread(() -> productDao.insertProduct(productModel)).start();
    }

    public void insertProducts(List<ProductModel> productModels) {
        WisataDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insertProducts(productModels);
        });
    }
}
