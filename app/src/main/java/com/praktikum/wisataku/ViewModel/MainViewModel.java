//package com.praktikum.wisataku.ViewModel;
//
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;
//import com.praktikum.wisataku.Model.SliderModel;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainViewModel {
//
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private MutableLiveData<List<SliderModel>> _slider = new MutableLiveData<>();
//
//    public LiveData<List<SliderModel>> getSlider() {
//
//        return _slider;
//    }
//    public void loadSlider() {
//        DatabaseReference ref = firebaseDatabase.getReference("banner");
//        ref.addValueEventListener((new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                List<SliderModel> lists = new ArrayList<>();
//                for(DataSnapshot chilSnapshot:snapshot.getChildren()){
//                    SliderModel list=chilSnapshot.getValue(SliderModel.class);
//                    if(list != null){
//                        lists.add(list);
//                    }
//                        _slider.setValue(lists);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        }));
//
//    }
//}
