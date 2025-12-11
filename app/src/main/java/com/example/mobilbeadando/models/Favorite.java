package com.example.mobilbeadando.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {

    @PrimaryKey
    @NonNull
    public String idMeal;

    public String strMeal;
    public String strMealThumb;

    public Favorite(@NonNull String idMeal, String strMeal, String strMealThumb) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }
}
