package com.praktikum.wisataku.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityCartBinding;
import com.praktikum.wisataku.databinding.ActivityPaymentBinding;
import com.praktikum.wisataku.databinding.ViewholderCartBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCartBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCkeckoutBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<Integer> cartIds;
    public Map<Integer, List<CartWithProduct>> groupedCartProducts;
    private final MainViewModel mainViewModel;
    private final ActivityCartBinding activityCartBinding;
    private LifecycleOwner lifecycleOwner;

    private Map<Integer, Double> cartTotalPrices = new HashMap<>();
    private double globalTotalPrice = 0.0;


    public CartAdapter(List<Integer> cartIds, Map<Integer, List<CartWithProduct>> groupedCartProducts, ActivityCartBinding activityCartBinding, MainViewModel mainViewModel, LifecycleOwner lifecycleOwner) {
        this.cartIds = cartIds;
        this.groupedCartProducts = groupedCartProducts;
        this.activityCartBinding = activityCartBinding;
        this.mainViewModel = mainViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(inflater, parent, false);
        ActivityCartBinding activityCartBinding = ActivityCartBinding.inflate(inflater, parent, false);
        ViewholderItemCartBinding itemCartBinding = ViewholderItemCartBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding, activityCartBinding, itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        int cartId = cartIds.get(position);
        List<CartWithProduct> products = groupedCartProducts.get(cartId);

        Log.i("getProductsByCartId", "CartIdDiOnBind" + cartId);
        String destinationTitle = products.get(0).destinationTitle;
        holder.binding.titleTxt.setText(destinationTitle);

        holder.binding.itemCartRecycleView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        CartProductAdapter productAdapter = new CartProductAdapter(products, mainViewModel, activityCartBinding);
        holder.binding.itemCartRecycleView.setAdapter(productAdapter);


        updateTotalPrice(position);
        holder.binding.checkBoxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            double cartTotalPrice = 0.0;

            if (isChecked) {
                for (CartWithProduct product : products) {
                    cartTotalPrice += product.price * product.quantity;
                }
                globalTotalPrice += cartTotalPrice;
                cartTotalPrices.put(cartId, cartTotalPrice);
                Log.i("Lihat ", String.valueOf(holder.itemCartBinding.checkboxProduct));
                holder.itemCartBinding.checkboxProduct.setChecked(true);


            } else {
                if (cartTotalPrices.containsKey(cartId)) {
                    globalTotalPrice -= cartTotalPrices.get(cartId);
                    cartTotalPrices.remove(cartId);
                }
            }

            activityCartBinding.totalPriceTxt.setText(String.format("Total: %.2f", globalTotalPrice));
            Log.i("GlobalTotalPrice", String.format("Updated Total: %.2f", globalTotalPrice));
        });

    }

    private void updateTotalPrice(int position) {
        globalTotalPrice = 0.0;
        int cartId = cartIds.get(position);
        mainViewModel.getProductsByCartId(cartId).observe(lifecycleOwner, products -> {
            if (products != null) {
                for (CartProduct product : products) {
                    Log.i("getProductsByCartId", "Product ID: " + product.getProductId());
                }
            } else {
                Log.i("getProductsByCartId", "No products found for cartId: " + cartId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartIds.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ViewholderCartBinding binding;
        ActivityCartBinding activityCartBinding;
        ViewholderItemCartBinding itemCartBinding;

        public CartViewHolder(ViewholderCartBinding binding, ActivityCartBinding activityCartBinding, ViewholderItemCartBinding itemCartBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.activityCartBinding = activityCartBinding;
            this.itemCartBinding = itemCartBinding;
        }

        public CartViewHolder(ViewholderCartBinding binding, ActivityCartBinding activityCartBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.activityCartBinding = activityCartBinding;
        }

        public CartViewHolder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}






