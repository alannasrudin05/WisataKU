package com.praktikum.wisataku.ViewModel;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.praktikum.wisataku.Model.CartModel;
import com.praktikum.wisataku.Model.CartProduct;
import com.praktikum.wisataku.Model.CartWithProduct;
import com.praktikum.wisataku.Model.DestinationModel;
import com.praktikum.wisataku.Model.GalleryModel;
import com.praktikum.wisataku.Model.ProductModel;
import com.praktikum.wisataku.Repository.CartProductRepository;
import com.praktikum.wisataku.Repository.CartRepository;
import com.praktikum.wisataku.Repository.DestinationRepository;
import com.praktikum.wisataku.Repository.GalleryRepository;
import com.praktikum.wisataku.Repository.ProductRepository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;


public class MainViewModel extends AndroidViewModel {
    private GalleryRepository galleryRepository;
    private ProductRepository productRepository;
    private DestinationRepository destinationRepository;
    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;

    private LiveData<List<GalleryModel>> galleries;
    private LiveData<List<ProductModel>> products;
    private LiveData<List<DestinationModel>> destinations;
    private LiveData<List<CartModel>> carts;
    private LiveData<List<CartProduct>> cart_products;
    private LiveData<List<CartWithProduct>> cartDetails;
    private final LiveData<List<CartModel>> allCarts;


    public MainViewModel(@NonNull Application application) {
        super(application);
        galleryRepository = new GalleryRepository(application);
        productRepository = new ProductRepository(application);
        destinationRepository = new DestinationRepository(application);
        cartRepository = new CartRepository(application);
        cartProductRepository = new CartProductRepository(application);

//        // LiveData dari Room
        galleries = galleryRepository.getAllGalleries();
        products = productRepository.getAllProducts();
        destinations = destinationRepository.getAllDestinations();
        carts = cartRepository.getAllCarts();
        cart_products = cartProductRepository.getAllCartProducts();
        cartDetails = cartRepository.getCartDetails();
        allCarts = cartRepository.getAllCarts();
    }




    public LiveData<List<GalleryModel>> getAllGalleries() {
        return galleries;
    }

    public LiveData<List<ProductModel>> getAllProducts() {
        return products;
    }

    public LiveData<List<DestinationModel>> getAllDestinations() {
        return destinations;
    }


//    baru
    public LiveData<List<CartModel>> getAllCarts() {
        return allCarts;
    }
    public LiveData<List<CartProduct>> getProductsByCartId(int cartId) {
        return cartRepository.getProductsByCartId(cartId);
    }
    public void updateProductSelection(CartProduct product) {
        cartRepository.updateProduct(product);
    }

//    end baru
    public LiveData<List<CartProduct>> getAllCartProducts() {
        return cart_products;
    }

    public LiveData<List<CartWithProduct>> getCartDetails() {
        return cartDetails;
    }

    public void updateCartProductSelection(int cartProductId, boolean isSelected) {
        Executors.newSingleThreadExecutor().execute(() -> {
            cartProductRepository.updateCartProductSelected(cartProductId, isSelected);
        });
    }

    public void loadDataAndInsert() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                // Baca JSON dari assets
                Context context = getApplication();
                InputStream is = context.getAssets().open("wisata.json");
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                String jsonData = new String(buffer, StandardCharsets.UTF_8);

                // Parsing JSON
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

                List<GalleryModel> galleries = gson.fromJson(
                        jsonObject.getAsJsonArray("Galleries"),
                        new TypeToken<List<GalleryModel>>() {}.getType()
                );
                List<DestinationModel> destinations = gson.fromJson(
                        jsonObject.getAsJsonArray("Destinations"),
                        new TypeToken<List<DestinationModel>>() {}.getType()
                );

                for (DestinationModel destination : destinations) {
                    Log.d("Destination", "Title: " + destination.getTitle() + ", Pic: " + destination.getPicDestination());
                }
                List<ProductModel> products = gson.fromJson(
                        jsonObject.getAsJsonArray("Products"),
                        new TypeToken<List<ProductModel>>() {}.getType()
                );
                List<CartModel> carts = gson.fromJson(
                        jsonObject.getAsJsonArray("Carts"),
                        new TypeToken<List<CartModel>>() {}.getType()
                );
                List<CartProduct> cart_products = gson.fromJson(
                        jsonObject.getAsJsonArray("Cart_Products"),
                        new TypeToken<List<CartProduct>>() {}.getType()
                );


                // Simpan ke database melalui repository
                galleryRepository.insertGalleries(galleries);
                destinationRepository.insertDestinations(destinations);
                productRepository.insertProducts(products);
                cartRepository.insertCarts(carts);
                cartProductRepository.insertCartProducts(cart_products);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
