package com.praktikum.wisataku.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "products")
public class ProductModel {
        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "title")
        private String title;

        @ColumnInfo(name = "price")
        private double price;

        @ColumnInfo(name = "rating")
        private double rating;

        @ColumnInfo(name = "pic_product")
        private String picProduct;

        @ColumnInfo(name = "destination_id")
        private int destinationId;

        @ColumnInfo(name = "gallery_id")
        private int galleryId;

        @ColumnInfo(name = "created_at")
        private long createdAt;

        @ColumnInfo(name = "updated_at")
        private long updatedAt;


        public ProductModel(int id, String title, double price, double rating, String picProduct, int destinationId, int galleryId) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.rating = rating;
            this.picProduct = picProduct;
            this.destinationId = destinationId;
            this.galleryId = galleryId;
            long currentTime = System.currentTimeMillis();
            this.createdAt = currentTime;
            this.updatedAt = currentTime;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPicProduct() {
        return picProduct;
    }

    public void setPicProduct(String picProduct) {
        this.picProduct = picProduct;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}


