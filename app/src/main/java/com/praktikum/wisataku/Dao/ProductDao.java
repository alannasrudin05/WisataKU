package com.praktikum.wisataku.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.Relations.ProductWithRelations;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductModel productModel);

    @Query("SELECT * FROM products")
    LiveData<List<ProductModel>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<ProductModel> productModels);


    @Transaction
    @Query("SELECT * FROM products")
    LiveData<List<ProductWithRelations>> getProductsWithRelations();

    @Update
    void updateProduct(ProductModel productModel);

    @Delete
    void deleteProduct(ProductModel productModel);
}
