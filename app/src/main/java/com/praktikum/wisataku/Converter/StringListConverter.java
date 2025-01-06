package com.praktikum.wisataku.Converter;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListConverter {

    @TypeConverter
    public String fromList(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public List<String> toList(String json) {
        Type type = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

}
