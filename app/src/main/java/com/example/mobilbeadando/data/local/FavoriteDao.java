package com.example.mobilbeadando.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    // Mentés / felülírás
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritePasta pasta);

    // Törlés
    @Delete
    void delete(FavoritePasta pasta);

    // Összes kedvenc lekérése
    @Query("SELECT * FROM favorites")
    List<FavoritePasta> getAllFavorites();

    // Ellenőrizzük, hogy kedvenc-e már
    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE idMeal = :id LIMIT 1)")
    boolean isFavorite(String id);
}