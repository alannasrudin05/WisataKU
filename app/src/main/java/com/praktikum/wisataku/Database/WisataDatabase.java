package com.praktikum.wisataku.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.praktikum.wisataku.Converter.ProductCartConverter;
import com.praktikum.wisataku.Converter.StringListConverter;
import com.praktikum.wisataku.Dao.CartDao;
import com.praktikum.wisataku.Dao.CartProductDao;
import com.praktikum.wisataku.Dao.DestinationDao;
import com.praktikum.wisataku.Dao.GalleryDao;
import com.praktikum.wisataku.Dao.ProductDao;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {ProductModel.class, DestinationModel.class, GalleryModel.class, CartModel.class, CartProduct.class},
        version = 1,
        exportSchema = true
)
@TypeConverters({StringListConverter.class, ProductCartConverter.class})
public abstract class WisataDatabase extends RoomDatabase {
    private static volatile WisataDatabase INSTANCE;
    private static final Object LOCK = new Object();

    // DAO instances
    public abstract GalleryDao galleryDao();
    public abstract DestinationDao destinationDao();
    public abstract ProductDao productDao();
    public abstract CartDao cartDao();
    public abstract CartProductDao cartProductDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    // Singleton pattern
    public static WisataDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WisataDatabase.class, "wisata_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
