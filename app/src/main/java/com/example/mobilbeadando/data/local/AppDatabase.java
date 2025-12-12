package com.example.mobilbeadando.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Mindkettő adatbázis a kedvencek tárolásához
@Database(entities = {FavoritePasta.class, FavoriteDessert.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app-database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration() // Ez engedi, hogy frissüljön az adatbázis
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}