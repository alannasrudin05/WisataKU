package com.praktikum.wisataku.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.praktikum.wisataku.Dao.GalleryDao;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.GalleryModel;

import java.util.List;

public class GalleryRepository {
    private GalleryDao galleryDao;

    public GalleryRepository(Context context) {
        WisataDatabase db = WisataDatabase.getInstance(context);
        galleryDao = db.galleryDao();
    }

    public LiveData<List<GalleryModel>> getAllGalleries() {
        return galleryDao.getAllGalleries();
    }

    public void insertGallery(GalleryModel galleryModel) {
        new Thread(() -> galleryDao.insertGallery(galleryModel)).start();
    }

    public void insertGalleries(List<GalleryModel> galleries) {
        WisataDatabase.databaseWriteExecutor.execute(() -> {
            galleryDao.insertGalleries(galleries);
        });
    }

}
