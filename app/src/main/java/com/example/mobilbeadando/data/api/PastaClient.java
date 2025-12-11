package com.example.mobilbeadando.data.api;

import com.example.mobilbeadando.PastaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PastaClient {
    //  Név szerinti keresés
    @GET("search.php")
    Call<PastaResponse> searchMealsByName(@Query("s") String name);

    // Minden tészta listázása
    @GET("filter.php")
    Call<PastaResponse> getMealsByCategory(@Query("c") String category);
}