package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilbeadando.data.api.PastaClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PastaFragment extends Fragment {

    private EditText editText_search;
    private Button button_search;
    private RecyclerView pastaRecyclerView;

    // FONTOS: Ebben a listában tároljuk az ÖSSZES tésztát.
    // Ebből fogunk válogatni kereséskor.
    private List<Pasta> allPastaList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pasta, container, false);

        editText_search = view.findViewById(R.id.editText_search);
        button_search = view.findViewById(R.id.button_search);
        pastaRecyclerView = view.findViewById(R.id.pastaRecyclerView);

        pastaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //  Letöltjük az összes tésztát a szerverről
        fetchPastaByCategory("Pasta");

        // Keresés gomb logika
        button_search.setOnClickListener(v -> {
            String query = editText_search.getText().toString().trim(); // Levágjuk a felesleges szóközöket

            // Itt hívjuk meg a helyi szűrést, nem a szerveres keresést!
            filterLocalData(query);
        });

        return view;
    }

    // API Hívás: Csak a "Pasta" kategória letöltése
    private void fetchPastaByCategory(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PastaClient client = retrofit.create(PastaClient.class);

        client.getMealsByCategory(category).enqueue(new Callback<PastaResponse>() {
            @Override
            public void onResponse(Call<PastaResponse> call, Response<PastaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pasta> meals = response.body().meals;

                    if (meals != null) {
                        // ELMENTJÜK a teljes listát a memóriába
                        allPastaList.clear();
                        allPastaList.addAll(meals);

                        // Megjelenítjük az egészet alapból
                        pastaRecyclerView.setAdapter(new PastaResult(allPastaList));
                    }
                }
            }

            @Override
            public void onFailure(Call<PastaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Hiba: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helyi SZŰRÉS: Ez a függvény végzi a válogatást a már letöltött tészták között
    private void filterLocalData(String query) {

        if (allPastaList.isEmpty()) {
            Toast.makeText(getContext(), "Adatok betöltése folyamatban...", Toast.LENGTH_SHORT).show();
            return;
        }


        List<Pasta> filteredList = new ArrayList<>();


        for (Pasta item : allPastaList) {

            if (item.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }


        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "Nincs ilyen tészta.", Toast.LENGTH_SHORT).show();
        }


        pastaRecyclerView.setAdapter(new PastaResult(filteredList));
    }
}