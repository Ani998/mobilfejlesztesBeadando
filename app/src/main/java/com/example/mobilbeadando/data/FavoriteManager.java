package com.example.mobilbeadando.data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mobilbeadando.models.Favorite;

public class FavoriteManager {

    public static void addToFavorites(Context context, String idMeal, String mealName, String thumbUrl) {

        Favorite favorite = new Favorite(idMeal, mealName, thumbUrl);
        AppDatabase db = AppDatabase.getInstance(context);

        AsyncTask.execute(() -> {
            db.favoriteDao().insert(favorite);
        });

        Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
    }
}
