package com.praktikum.wisataku.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.praktikum.wisataku.Adapter.CartAdapter;
import com.praktikum.wisataku.Adapter.GalleryAdapter;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityCartBinding;
import com.praktikum.wisataku.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding activityCartBinding;
    private Handler sliderHandler = new Handler();
    private MainViewModel mainViewModel;
    private CartAdapter cartAdapter;
    List<CartModel> allCart = new ArrayList<>();
    List<DestinationModel> allDestination = new ArrayList<>();
    private RecyclerView cartListRecyclerView;
    private ChipNavigationBar bottomMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());



        // Inisialisasi ViewModel
        mainViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainViewModel.class);

        // Inisialisasi RecyclerView
        cartListRecyclerView = findViewById(R.id.cartRecycleView);

        // Atur layout manager horizontal
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartListRecyclerView.setLayoutManager(layoutManager1);

//        Log.i("ieuThis", String.valueOf(this));
        LifecycleOwner owner = this;
        mainViewModel.getCartDetails().observe(this, new Observer<List<CartWithProduct>>() {
            @Override
            public void onChanged(List<CartWithProduct> cartWithProducts) {
        Log.i("ieuThis", String.valueOf(this));
                Map<Integer, List<CartWithProduct>> groupedCartProducts = new HashMap<>();
                for (CartWithProduct cartWithProduct : cartWithProducts) {
                    int cartId = cartWithProduct.cartId;
                    Log.i("CekPicProduct", "Pic Product: " + cartWithProduct.picProduct);
                    Log.i("CekPicProduct", "is selected: " + cartWithProduct.isSelected);
                    groupedCartProducts.computeIfAbsent(cartId, k -> new ArrayList<>()).add(cartWithProduct);

                }

                List<Integer> cartIds = new ArrayList<>(groupedCartProducts.keySet());

                Log.i("CekData", String.valueOf(cartIds));
                Log.i("CekData", String.valueOf(groupedCartProducts));
                CartAdapter cartAdapter = new CartAdapter(cartIds, groupedCartProducts, activityCartBinding, mainViewModel, owner);
                cartAdapter.groupedCartProducts = groupedCartProducts;
                activityCartBinding.cartRecycleView.setAdapter(cartAdapter);


            }
        });

        mainViewModel.getAllCarts().observe(this, new Observer<List<CartModel>>() {
            @Override
            public void onChanged(List<CartModel> cartModels) {
                allCart.clear();
                allCart.addAll(cartModels);
                Log.i("dataCart", "Cart data loaded: " + allCart.size());
                checkAndCompareData();
            }
        });

        mainViewModel.getAllDestinations().observe(this, new Observer<List<DestinationModel>>() {
            @Override
            public void onChanged(List<DestinationModel> destinationModels) {
                allDestination.clear();
                allDestination.addAll(destinationModels);
                Log.i("dataDestination", "Destination data loaded: " + allDestination.size());
                checkAndCompareData();
            }
        });


        activityCartBinding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        bottomMenu = findViewById(R.id.bottom_navigation);

    }

    private void checkAndCompareData() {
        if (!allCart.isEmpty() && !allDestination.isEmpty()) {
            List<CartModel> matchedCarts = new ArrayList<>();
            List<Integer> matchedDestinations = new ArrayList<>();
            List<String> nameDestinations = new ArrayList<>();

            for (CartModel cart : allCart) {
                for (DestinationModel destination : allDestination) {
                    if (cart.getDestinationId() == destination.getId()) {
                        Log.i("Comparison", "Match found: Cart ID " + cart.getDestinationId());
                        Log.i("Comparison", "Match found: Destination ID " + destination.getId());
                        matchedCarts.add(cart);
                        matchedDestinations.add(destination.getId());
                        break;
                    }
                }
            }

            mainViewModel.getAllDestinations().observe(this, new Observer<List<DestinationModel>>() {
                        @Override
                        public void onChanged(List<DestinationModel> destinationModels) {
                            for (DestinationModel destination : destinationModels){
                                for (int id : matchedDestinations)
                                    if (id == destination.getId()){
                                        nameDestinations.add(destination.getTitle());
                                        Log.i("namaDestinasi", String.valueOf(nameDestinations));
                                    }
                            }
                        }

                    });
        }
    }

}