package com.praktikum.wisataku.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Relations.DestinationWithGallery;

import java.util.List;

@Dao
public interface DestinationDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDestination(DestinationModel destinationModel);

    @Query("SELECT * FROM destinations")
    LiveData<List<DestinationModel>> getAllDestinations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDestinations(List<DestinationModel> destinationModels);


    @Transaction
    @Query("SELECT * FROM destinations")
    LiveData<List<DestinationWithGallery>> getDestinationsWithGallery();

    @Update
    void updateDestination(DestinationModel destinationModel);

    @Delete
    void deleteDestination(DestinationModel destinationModel);
}
