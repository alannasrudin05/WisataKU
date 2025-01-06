package com.praktikum.wisataku.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.praktikum.wisataku.Converter.StringListConverter;

import java.util.List;


@Entity(tableName = "galleries")
public class GalleryModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(StringListConverter.class)
    @ColumnInfo(name = "gallery_urls")
    private List<String> galleryUrls;



    public GalleryModel() {
    }

    public GalleryModel(List<String> galleryUrls) {
        this.galleryUrls = galleryUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGalleryUrls() {
        return galleryUrls;
    }

    public void setGalleryUrls(List<String> galleryUrls) {
        this.galleryUrls = galleryUrls;
    }



}
