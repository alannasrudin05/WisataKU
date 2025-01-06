package com.praktikum.wisataku.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.praktikum.wisataku.Adapter.DestinationAdapter;
import com.praktikum.wisataku.Adapter.ProductAdapter;
import com.praktikum.wisataku.Adapter.GalleryAdapter;
import com.praktikum.wisataku.Database.WisataDatabase;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityMainBinding;
import com.praktikum.wisataku.databinding.CustomDialogBoxBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements TextWatcher {

    protected ActivityMainBinding binding;
    protected Handler sliderHandler = new Handler();
    protected MainViewModel mainViewModel;

    private RecyclerView popularEventRecyclerView;
    private RecyclerView listDestinationRecyclerView;

    private ProductAdapter productAdapter;
    private DestinationAdapter destinationAdapter;
    private GalleryAdapter galleryAdapter;

    public String[] saranDestinasi={"Waduk Dharma Kuningan", "Talaga Surian", "Curug Putri"};
    protected List<String> allGalleryUrls = new ArrayList<>();
    protected ChipNavigationBar bottomMenu;
    protected CustomDialogBoxBinding customDialogBoxBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        customDialogBoxBinding = CustomDialogBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomMenu = findViewById(R.id.bottom_navigation);

        mainViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainViewModel.class);

        // Load JSON dan simpan ke database
//        mainViewModel.loadDataAndInsert();

//        clear db
//        WisataDatabase db = WisataDatabase.getInstance(this);
//        db.clearAllTables();

        // Inisialisasi RecyclerView
        listDestinationRecyclerView = findViewById(R.id.listDestinationRecyclerView);

        // Atur layout manager horizontal
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listDestinationRecyclerView.setLayoutManager(layoutManager1);

        // Observasi data dari ViewModel untuk Destination
        mainViewModel.getAllDestinations().observe(this, destinations -> {


            if (destinations != null) {
                destinationAdapter = new DestinationAdapter(destinations);
                listDestinationRecyclerView.setAdapter(destinationAdapter);

                destinationAdapter.setOnItemClickListener(new DestinationAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(DestinationModel item) {
                    Intent intent = new Intent(MainActivity.this, DetailDestinationActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("name", "destination_id");
                    startActivity(intent);

                    }
                });

            }
        });


        // Inisialisasi RecyclerView
        popularEventRecyclerView = findViewById(R.id.popularEventRecyclerView);

        // Atur layout manager horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularEventRecyclerView.setLayoutManager(layoutManager);

        // Observasi data dari ViewModel untuk Product
        mainViewModel.getAllProducts().observe(this, products -> {
            if (products != null) {
                productAdapter = new ProductAdapter(products);
                popularEventRecyclerView.setAdapter(productAdapter);

                productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(ProductModel item) {
                        Intent intent = new Intent(MainActivity.this, DetailDestinationActivity.class);
                        intent.putExtra("id", item.getId());
                        intent.putExtra("name", "product_id");
                        startActivity(intent);

                    }
                });


            }
        });

        // Observasi LiveData dari MainViewModel untuk Galeri
        mainViewModel.getAllGalleries().observe(this, new Observer<List<GalleryModel>>() {
            @Override
            public void onChanged(List<GalleryModel> galleryModels) {
                allGalleryUrls.clear();
                for (GalleryModel gallery : galleryModels) {
                    if (gallery.getGalleryUrls() != null && !gallery.getGalleryUrls().isEmpty()) {
                        allGalleryUrls.addAll(gallery.getGalleryUrls());
                    }
                }
                galleryAdapter = new GalleryAdapter(MainActivity.this, allGalleryUrls);
                binding.viewPager2.setAdapter(galleryAdapter);

                startAutoSlide();
            }
        });

        AutoCompleteTextView searchAC = binding.search;

        searchAC.addTextChangedListener(this);
        searchAC.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, saranDestinasi));


        int selectedItem = getIntent().getIntExtra("selected_item", R.id.explore);
        bottomMenu.setItemSelected(selectedItem, true);
        bottomMenu.setOnItemSelectedListener(item -> {
            setMode(item);

        });


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


    }





    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }

    // Fungsi untuk memulai auto-slide
    private void startAutoSlide() {
        sliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = binding.viewPager2.getCurrentItem();
                int nextItem = (currentItem + 1) % allGalleryUrls.size();
                binding.viewPager2.setCurrentItem(nextItem, true);

                sliderHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacksAndMessages(null); // Menghentikan auto-slide saat activity pause
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}