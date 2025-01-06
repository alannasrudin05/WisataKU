package com.praktikum.wisataku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.request.RequestOptions;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.R;


import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.SliderViewholder> {

    private List<String> galleryUrls;
    private Context context;

    public GalleryAdapter(Context context, List<String> galleryUrls) {
        this.context = context;
        this.galleryUrls = galleryUrls;
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
        String imageName = galleryUrls.get(position);

        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (imageResId != 0) {
            holder.imageView.setImageResource(imageResId);

        } else {
            holder.imageView.setImageResource(R.drawable.gc3);
        }

    }

    @Override
    public int getItemCount() {
        return galleryUrls.size();
    }

    public class SliderViewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
    }
}




