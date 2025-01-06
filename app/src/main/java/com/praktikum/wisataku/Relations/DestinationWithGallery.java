package com.praktikum.wisataku.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;

public class DestinationWithGallery {
    @Embedded
    private DestinationModel destination;

    @Relation(
            parentColumn = "gallery_id",
            entityColumn = "id"
    )
    private GalleryModel gallery;

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
