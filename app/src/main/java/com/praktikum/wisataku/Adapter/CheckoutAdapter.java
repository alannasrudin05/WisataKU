package com.praktikum.wisataku.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.ViewModel.MainViewModel;
import com.praktikum.wisataku.databinding.ActivityCartBinding;
import com.praktikum.wisataku.databinding.ActivityPaymentBinding;
import com.praktikum.wisataku.databinding.ViewholderCartBinding;
import com.praktikum.wisataku.databinding.ViewholderCheckoutBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCartBinding;
import com.praktikum.wisataku.databinding.ViewholderItemCkeckoutBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CartViewHolder> {
    private final List<Integer> cartIds;
    public Map<Integer, List<CartWithProduct>> groupedCheckoutProducts;
    private final MainViewModel mainViewModel;
    private final ActivityPaymentBinding activityPaymentBinding;
    private LifecycleOwner lifecycleOwner;

    private double globalTotalPrice = 0.0;


    public CheckoutAdapter(List<Integer> cartIds, Map<Integer, List<CartWithProduct>> groupedCheckoutProducts, ActivityPaymentBinding activityPaymentBinding, MainViewModel mainViewModel, LifecycleOwner lifecycleOwner) {
        this.cartIds = cartIds;
        this.groupedCheckoutProducts = groupedCheckoutProducts;
        this.activityPaymentBinding = activityPaymentBinding;
        this.mainViewModel = mainViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewholderCheckoutBinding binding = ViewholderCheckoutBinding.inflate(inflater, parent, false);
        ActivityPaymentBinding activityPaymentBinding = ActivityPaymentBinding.inflate(inflater, parent, false);
        ViewholderItemCkeckoutBinding itemCkeckoutBinding = ViewholderItemCkeckoutBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding, activityPaymentBinding, itemCkeckoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        int cartId = cartIds.get(position);
        List<CartWithProduct> products = groupedCheckoutProducts.get(cartId);

        Log.i("getProductsByCartId", "CartIdDiOnBind" + cartId);
        String destinationTitle = products.get(0).destinationTitle;
        holder.binding.titleTxt.setText(destinationTitle);

        holder.binding.itemCheckoutRecycleView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        CheckoutProductAdapter productAdapter = new CheckoutProductAdapter(products, mainViewModel, activityPaymentBinding);
        holder.binding.itemCheckoutRecycleView.setAdapter(productAdapter);


    }

    private void updateTotalPrice(int position) {
        globalTotalPrice = 0.0;
        int cartId = cartIds.get(position);

        mainViewModel.getProductsByCartId(cartId).observe(lifecycleOwner, products -> {
            if (products != null) {
                for (CartProduct product : products) {
                    Log.i("getProductsByCartId", "Product ID: " + product.getProductId()); //di dapatkan product id yang ada dalam cart 100, 101, 103
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
        ViewholderCheckoutBinding binding;
        ActivityPaymentBinding activityPaymentBinding;
        ViewholderItemCkeckoutBinding itemCartBinding;

        public CartViewHolder(ViewholderCheckoutBinding binding, ActivityPaymentBinding activityPaymentBinding, ViewholderItemCkeckoutBinding itemCartBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.activityPaymentBinding = activityPaymentBinding;
            this.itemCartBinding = itemCartBinding;
        }

        public CartViewHolder(ViewholderCheckoutBinding binding, ActivityPaymentBinding activityPaymentBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.activityPaymentBinding = activityPaymentBinding;
        }

        public CartViewHolder(ViewholderCheckoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}





