package com.praktikum.wisataku.Converter;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.praktikum.wisataku.Model.CartProduct;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductCartConverter {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromCartProductList(List<CartProduct> products) {
        if (products == null) {
            return null;
        }
        return gson.toJson(products);
    }

    @TypeConverter
    public static List<CartProduct> toCartProductList(String productsJson) {
        if (productsJson == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<CartProduct>>() {}.getType();
        return gson.fromJson(productsJson, listType);
    }
}
