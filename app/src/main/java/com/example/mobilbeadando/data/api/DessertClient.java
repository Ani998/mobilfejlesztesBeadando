package com.example.mobilbeadando.data.api;

import com.example.mobilbeadando.DessertResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DessertClient {

    @GET("search.php")
    Call<DessertResponse> searchDessertsByName(@Query("s") String name);

    @GET("filter.php")
    Call<DessertResponse> getDessertsByCategory(@Query("c") String category);
}