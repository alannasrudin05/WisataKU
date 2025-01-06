package com.praktikum.wisataku.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.praktikum.wisataku.Dao.DestinationDao;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;

import java.util.List;

public class DestinationRepository {
    private DestinationDao destinationDao;

    public DestinationRepository(Context context) {
        WisataDatabase db = WisataDatabase.getInstance(context);
        destinationDao = db.destinationDao();
    }

    public LiveData<List<DestinationModel>> getAllDestinations() {
        return destinationDao.getAllDestinations();
    }

    public void insertDestination(DestinationModel destinationModel) {
        new Thread(() -> destinationDao.insertDestination(destinationModel)).start();
    }

    public void insertDestinations(List<DestinationModel> destinationModels) {
        WisataDatabase.databaseWriteExecutor.execute(() -> {
            destinationDao.insertDestinations(destinationModels);
        });
    }
}
