package com.praktikum.wisataku.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.praktikum.wisataku.Model.GalleryModel;

import java.util.List;

@Dao
public interface GalleryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGallery(GalleryModel galleryModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGalleries(List<GalleryModel> galleries);

    @Query("SELECT * FROM galleries")
    LiveData<List<GalleryModel>> getAllGalleries();

    @Update
    void updateGallery(GalleryModel galleryModel);

    @Delete
    void deleteGallery(GalleryModel galleryModel);
}
