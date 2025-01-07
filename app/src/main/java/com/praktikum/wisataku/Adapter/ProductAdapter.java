package com.praktikum.wisataku.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.View.DetailDestinationActivity;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.databinding.CustomDialogBoxBinding;
import com.praktikum.wisataku.databinding.ViewholderPopularEventBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> items;
    private Context context;
    private ProductAdapter.OnItemClickListener listener;
    public Dialog dialog;

    public ProductAdapter(List<ProductModel> items) {
        this.items = items;
    }

    public interface OnItemClickListener {
        void onItemClick(ProductModel item);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        ViewholderPopularEventBinding binding=ViewholderPopularEventBinding.inflate(
                LayoutInflater.from(context), parent, false
        );
        return new ViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        ProductModel item = items.get(position);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        String priceFormatted = decimalFormat.format(item.getPrice());

        holder.binding.titleTxt.setText(item.getTitle());
        holder.binding.priceTxt.setText("Rp. "+priceFormatted);
        holder.binding.ratingTxt.setText(String.valueOf(item.getRating()));

        String picProduct = item.getPicProduct();
        if (picProduct != null && !picProduct.isEmpty()) {
            int imageResId = context.getResources().getIdentifier(
                    picProduct, "drawable", context.getPackageName()
            );

            if (imageResId != 0) {
                Glide.with(context)
                        .load(imageResId)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picPopularEvent);
            } else {
                Glide.with(context)
                        .load(R.drawable.gc1)  // Placeholder jika resource tidak ditemukan
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picPopularEvent);
            }
        } else {
            Glide.with(context)
                    .load(R.drawable.gc1)  // Placeholder jika picProduct null
                    .apply(new RequestOptions().transform(new CenterCrop()))
                    .into(holder.binding.picPopularEvent);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });

        holder.binding.btnAddToCart.setOnClickListener(v -> {
            CustomDialogBoxBinding customDialogBoxBinding = CustomDialogBoxBinding.inflate(
                    LayoutInflater.from(context), null, false
            );
            Dialog dialog = new Dialog(context);
            dialog.setContentView(customDialogBoxBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            customDialogBoxBinding.tambahDialogBtn.setOnClickListener(view -> {
                Toast.makeText(context, "Produk berhasil ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            });

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderPopularEventBinding binding;
        public ViewHolder(ViewholderPopularEventBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}


