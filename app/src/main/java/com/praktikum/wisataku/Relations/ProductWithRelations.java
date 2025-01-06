package com.praktikum.wisataku.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;

public class ProductWithRelations {
    @Embedded
    private ProductModel product;

    @Relation(
            parentColumn = "destination_id",
            entityColumn = "id"
    )
    private DestinationModel destination;

    @Relation(
            parentColumn = "gallery_id",
            entityColumn = "id"
    )
    private GalleryModel gallery;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public DestinationModel getDestination() {
        return destination;
    }

    public void setDestination(DestinationModel destination) {
        this.destination = destination;
    }

    public GalleryModel getGallery() {
        return gallery;
    }

    public void setGallery(GalleryModel gallery) {
        this.gallery = gallery;
    }
}
