package com.praktikum.wisataku;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.praktikum.wisataku.Adapter.SliderAdapter;
import com.praktikum.wisataku.Model.SliderModel;
import com.praktikum.wisataku.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_explore);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<SliderModel> sliderList = new ArrayList<>();
        sliderList.add(new SliderModel(R.drawable.image1));
        sliderList.add(new SliderModel(R.drawable.image2));
        sliderList.add(new SliderModel(R.drawable.image3));
        SliderAdapter adapter = new SliderAdapter(sliderList);
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.setOffscreenPageLimit(3);


        // Auto-slide
        sliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = binding.viewPager2.getCurrentItem();
                int nextItem = (currentItem + 1) % sliderList.size(); // Kembali ke awal jika mencapai akhir
                binding.viewPager2.setCurrentItem(nextItem, true);

                sliderHandler.postDelayed(this, 3000); // 3 detik per slide
            }
        }, 3000);



        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(0);

    }

}