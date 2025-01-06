package com.praktikum.wisataku.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

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
import com.praktikum.wisataku.Adapter.CheckoutAdapter;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityCartBinding;
import com.praktikum.wisataku.databinding.ActivityIntroBinding;
import com.praktikum.wisataku.databinding.ActivityPaymentBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding activityPaymentBinding;
    protected MainViewModel mainViewModel;
    protected RecyclerView checkoutListRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentBinding = ActivityPaymentBinding.inflate(LayoutInflater.from(this));
        setContentView(activityPaymentBinding.getRoot());

        mainViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainViewModel.class);

        // Inisialisasi RecyclerView
        checkoutListRecyclerView = findViewById(R.id.checkoutRecycleView);

        // Atur layout manager horizontal
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        checkoutListRecyclerView.setLayoutManager(layoutManager1);


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

                CheckoutAdapter checkoutAdapter = new CheckoutAdapter(cartIds, groupedCartProducts, activityPaymentBinding, mainViewModel, owner);
                checkoutAdapter.groupedCheckoutProducts = groupedCartProducts;
                activityPaymentBinding.checkoutRecycleView.setAdapter(checkoutAdapter);
//                cartAdapter.notifyDataSetChanged();


            }
        });



        activityPaymentBinding.buatPesananBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, ProsesActivity.class);
                startActivity(intent);
            }
        });

//        RadioGroup radioGroup = findViewById(R.id.pembayaranRadGroup);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
////                belum
//            }
//        });




        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }
}