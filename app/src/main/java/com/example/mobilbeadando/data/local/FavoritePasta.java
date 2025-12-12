package com.example.mobilbeadando.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoritePasta {

    @PrimaryKey
    @NonNull
    public String idMeal;

    public String strMeal;
    public String strMealThumb;

    public FavoritePasta(@NonNull String idMeal, String strMeal, String strMealThumb) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }
}