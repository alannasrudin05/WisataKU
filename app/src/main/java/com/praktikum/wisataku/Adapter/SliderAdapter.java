package com.praktikum.wisataku.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.wisataku.Model.SliderModel;
import com.praktikum.wisataku.R;


import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewholder> {


    private List<SliderModel> sliderModels;

    public SliderAdapter(List<SliderModel> sliderModels) {
        this.sliderModels = sliderModels;
    }

    @NonNull
    @Override
    public SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_image_container, parent, false);
        return new SliderViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewholder holder, int position) {
        SliderModel slider = sliderModels.get(position);
        holder.imageView.setImageResource(slider.getImageResId());
    }

    @Override
    public int getItemCount() {
        return sliderModels.size();
    }

    public class SliderViewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlide);
        }

    }
}
