package com.praktikum.wisataku.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityPaymentBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCartBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCkeckoutBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutProductAdapter extends RecyclerView.Adapter<CheckoutProductAdapter.ViewHolder> {

    private List<CartWithProduct> products;
    private Context context;
    private MainViewModel mainViewModel;
    private ActivityPaymentBinding activityPaymentBinding;
    private Map<Integer, Double> cartTotalPrices = new HashMap<>();
    private double globalTotalPrice = 0.0;

    public CheckoutProductAdapter(List<CartWithProduct> products, MainViewModel mainViewModel, ActivityPaymentBinding activityPaymentBinding) {
        this.products = products;
        this.mainViewModel = mainViewModel;
        this.activityPaymentBinding = activityPaymentBinding;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context=parent.getContext();
        ViewholderItemCkeckoutBinding binding = ViewholderItemCkeckoutBinding.inflate(inflater, parent, false);
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
                        .load(R.drawable.gc1)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(holder.binding.picProduct);
            }
        } else {
            Glide.with(context)
                    .load(R.drawable.gc1)
                    .apply(new RequestOptions().transform(new CenterCrop()))
                    .into(holder.binding.picProduct);
        }

        activityPaymentBinding.totalPriceTxt.setText(String.format("Total: %.2f", globalTotalPrice));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderItemCkeckoutBinding binding;

        public ViewHolder(ViewholderItemCkeckoutBinding binding) {
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

