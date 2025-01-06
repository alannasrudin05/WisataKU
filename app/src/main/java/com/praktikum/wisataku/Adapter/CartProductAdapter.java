package com.praktikum.wisataku.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.praktikum.wisataku.Dao.CartProductDao;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.Repository.CartProductRepository;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityCartBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCartBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {

    private List<CartWithProduct> products;
    private Context context;
    private MainViewModel mainViewModel;
    private ActivityCartBinding activityCartBinding;
    private Map<Integer, Double> cartTotalPrices = new HashMap<>();
    private double globalTotalPrice = 0.0;

    public CartProductAdapter(List<CartWithProduct> products, MainViewModel mainViewModel, ActivityCartBinding activityCartBinding) {
        this.products = products;
        this.mainViewModel = mainViewModel;
        this.activityCartBinding = activityCartBinding;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context=parent.getContext();
        ViewholderItemCartBinding binding = ViewholderItemCartBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartWithProduct product = products.get(position);
        holder.binding.titleTxt.setText(product.productTitle);
        holder.binding.priceTxt.setText(String.valueOf(product.price));
        holder.binding.numberItemTxt.setText(String.valueOf(product.quantity));

        if (product.picProduct != null) {
            Glide.with(holder.itemView.getContext())
                    .load(product.picProduct)
                    .into(holder.binding.picProduct);
        }

        String picProduct = product.picProduct;
        if (picProduct != null && !picProduct.isEmpty()) {

            int imageResId = context.getResources().getIdentifier(
                    picProduct, "drawable", context.getPackageName()
            );

            Log.i("dataDiAdapter", String.valueOf(product.picProduct));

            if (imageResId != 0) {
                Glide.with(context)
                        .load(imageResId)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picProduct);
            } else {
                Glide.with(context)
                        .load(R.drawable.gc1)  // Placeholder jika resource tidak ditemukan
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picProduct);
            }
        } else {
            Glide.with(context)
                    .load(R.drawable.gc1)  // Placeholder jika picProduct null
                    .apply(new RequestOptions().transform(new CenterCrop()))
                    .into(holder.binding.picProduct);
        }

        if (!product.isSelected){
            holder.binding.checkboxProduct.setChecked(false);
        }else {
            holder.binding.checkboxProduct.setChecked(true);
        }

        holder.binding.checkboxProduct.setOnCheckedChangeListener((buttonView, isChecked) -> {

            double cartTotalPrice = 0.0;

            if (isChecked) {
                toggleSelection(product.cartProductId, true, position);
                Log.i("buttonDiKlik", "kliiik1" + isChecked + buttonView);
                for (CartWithProduct product1 : products) {
                cartTotalPrice += product1.price * product1.quantity;
                }
                globalTotalPrice += cartTotalPrice;

            } else {
                toggleSelection(product.cartProductId, false, position);
                globalTotalPrice -= product.price * product.quantity;

            }

            activityCartBinding.totalPriceTxt.setText(String.format("Total: %.2f", globalTotalPrice));
            Log.i("TotalHarga", "Total: " + globalTotalPrice);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderItemCartBinding binding;

        public ViewHolder(ViewholderItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void toggleSelection(int cartProductId, boolean isSelected, int position) {
        CartWithProduct product = products.get(position);
            if (product.cartProductId == cartProductId) {
                Log.i("CekDulu", String.valueOf(isSelected));
                Log.i("CekDulu", String.valueOf(product.getCartProductProductId()));
                Log.i("CekDulu", String.valueOf(product.id));
                mainViewModel.updateCartProductSelection(product.id, isSelected);


            }
    }


}

