package com.praktikum.wisataku.Adapter;
import com.praktikum.wisataku.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.databinding.ViewholderListDestinationBinding;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private List<DestinationModel> items;
    private Context context;


    public DestinationAdapter(List<DestinationModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        ViewholderListDestinationBinding binding=ViewholderListDestinationBinding.inflate(
                LayoutInflater.from(context), parent, false
        );
        return new ViewHolder(binding);
    }


    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DestinationModel item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
        DestinationModel item = items.get(position);

        holder.binding.titleTxt.setText(item.getTitle());
        holder.binding.ratingTxt.setText(String.valueOf(item.getRating()));

        String picDestination = item.getPicDestination();
        if (picDestination != null && !picDestination.isEmpty()) {
            int imageResId = context.getResources().getIdentifier(
                    picDestination, "drawable", context.getPackageName()
            );

            if (imageResId != 0) {
                Glide.with(context)
                        .load(imageResId)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picListDestination);
            } else {
                Glide.with(context)
                        .load(R.drawable.gc1)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picListDestination);
            }
        } else {
            Glide.with(context)
                    .load(R.drawable.gc1)
                    .apply(new RequestOptions().transform(new CenterCrop()))
                    .into(holder.binding.picListDestination);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderListDestinationBinding binding;
        public ViewHolder(ViewholderListDestinationBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
