package com.praktikum.wisataku.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.praktikum.wisataku.Adapter.DestinationAdapter;
import com.praktikum.wisataku.Adapter.GalleryAdapter;
import com.praktikum.wisataku.Adapter.ProductAdapter;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityDetailDestinationBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailDestinationActivity extends AppCompatActivity {

    private ActivityDetailDestinationBinding binding;
    private MainViewModel mainViewModel;
    private Handler sliderHandler = new Handler();
    private RecyclerView popularEventRecyclerView;
    private ProductAdapter productAdapter;
    private GalleryAdapter galleryAdapter;

    private List<String> filteredGalleries = new ArrayList<>();
    private List<ProductModel> filteredProducts = new ArrayList<>();
    private List<DestinationModel> isDestination = new ArrayList<>();
    private ChipNavigationBar bottomMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDestinationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainViewModel.class);


        getBundleExtra();

        bottomMenu = findViewById(R.id.bottom_navigation);

        int selectedItem = getIntent().getIntExtra("selected_item", R.id.explore);
            bottomMenu.setItemSelected(selectedItem, true);
            bottomMenu.setOnItemSelectedListener(item -> {
            setMode(item);

        });



        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(0);
    }


    private void getBundleExtra() {
        int id = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");


            if (name.equals("product_id")){
                Log.i("DetailDestination", "Product");
                Log.i("id", String.valueOf(id));

                mainViewModel.getAllProducts().observe(this, products -> {
                    for (ProductModel product : products) {
                        if (product.getId() == id) {
                                int destinationId = Integer.parseInt(String.valueOf(product.getDestinationId()));
                                getByDestination(destinationId);
                        }
                    }
                });
            }else if (name.equals("destination_id")){
                Log.i("SayaDi", name);
                Log.i("SayaDi", String.valueOf(id));
                getByDestination(id);
            }

    }

    private void getByDestination(int id) {

        Log.i("idDestinasiDariProduk", String.valueOf(id));
        mainViewModel.getAllDestinations().observe(this, destinations -> {

            for (DestinationModel destination : destinations) {
                if (destination.getId() == id) {
                    isDestination.add(destination);
                    Log.i("artributDestinasi", "id destinasi intent: "+ id + " : id destinasi :" + destination.getId());
                    destination.getGalleryId(); //1

                    binding.titleAppBar.setText(destination.getTitle());
                    binding.titleTxt.setText(destination.getTitle());
                    binding.descriptionTxt.setText(destination.getDescription());

                    mainViewModel.getAllGalleries().observe(this, new Observer<List<GalleryModel>>() {
                        @Override
                        public void onChanged(List<GalleryModel> galleryModels) {

                            for (GalleryModel gallery : galleryModels) {
                                if (gallery.getId() == destination.getGalleryId()) {
                                    filteredGalleries.addAll(gallery.getGalleryUrls());
                                    Log.i("artributGallery", "id gallery: "+ gallery.getId() + " : destination.galleryId :" + destination.getGalleryId());
                                    Log.i("artributGalleryUrls", "id gallery: "+ filteredGalleries);

                                }

                            }
                            galleryAdapter = new GalleryAdapter(DetailDestinationActivity.this, filteredGalleries);
                            binding.viewPager2.setAdapter(galleryAdapter);

                            startAutoSlide();
                        }
                    });
                }
            }

        });

        productInDestination(id);
    }

    private void productInDestination(int id) {
        mainViewModel.getAllProducts().observe(this, products -> {

            for (ProductModel product : products) {
                if (product.getDestinationId() == id) {
                    filteredProducts.add(product);
                    Log.i("artributProduk", "Product with destinationId 2: " + product.toString());
                }
            }
            if (!filteredProducts.isEmpty()) {
                popularEventRecyclerView = findViewById(R.id.popularEventRecyclerView);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                popularEventRecyclerView.setLayoutManager(layoutManager);

                productAdapter = new ProductAdapter(filteredProducts);
                popularEventRecyclerView.setAdapter(productAdapter);
            }
        });
    }

    // Fungsi untuk memulai auto-slide
    private void startAutoSlide() {
        sliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = binding.viewPager2.getCurrentItem();
                int nextItem = (currentItem + 1) % filteredGalleries.size();
                binding.viewPager2.setCurrentItem(nextItem, true);

                sliderHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }


    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(DetailDestinationActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(DetailDestinationActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(DetailDestinationActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(DetailDestinationActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }


}

