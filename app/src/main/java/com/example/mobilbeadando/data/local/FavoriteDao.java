package com.example.mobilbeadando.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    //  PASTA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritePasta pasta);

    @Delete
    void delete(FavoritePasta pasta);

    @Query("SELECT * FROM favorites")
    List<FavoritePasta> getAllFavorites();

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE idMeal = :id LIMIT 1)")
    boolean isFavorite(String id);



    //Dessert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDessert(FavoriteDessert dessert);

    @Delete
    void deleteDessert(FavoriteDessert dessert);

    @Query("SELECT * FROM favorite_desserts")
    List<FavoriteDessert> getAllFavoriteDesserts();

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_desserts WHERE idMeal = :id LIMIT 1)")
    boolean isFavoriteDessert(String id);
}