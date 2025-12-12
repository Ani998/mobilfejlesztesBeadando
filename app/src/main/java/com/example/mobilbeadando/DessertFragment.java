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

import com.example.mobilbeadando.data.api.DessertClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class DessertFragment extends Fragment {

    private EditText editText_search;
    private Button button_search;
    private RecyclerView dessertRecyclerView;

    private List<Dessert> allDessertList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dessert, container, false);

        editText_search = view.findViewById(R.id.editText_search);
        button_search = view.findViewById(R.id.button_search);
        dessertRecyclerView = view.findViewById(R.id.dessertRecyclerView);

        dessertRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //összes desszert letöltése a szerverről
        fetchDessertByCategory("Dessert");

        //Keresés gomb
        button_search.setOnClickListener(v ->{
            String query = editText_search.getText().toString().trim(); //levágjuk a felesleges szóközöket
            // itt hívjuk meg a helyi szűrést, nem a szerveres keresést!
            filterLocalData(query);
        });

        return view;
    }

    //API hívás Dessert kategória letöltésére

    private void fetchDessertByCategory(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DessertClient client = retrofit.create(DessertClient.class);

        client.getDessertsByCategory(category).enqueue(new Callback<DessertResponse>() {
            @Override
            public void onResponse(Call<DessertResponse> call, Response<DessertResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Dessert> desserts = response.body().desserts;

                    if (desserts != null) {
                        // ELMENTJÜK a teljes listát a memóriába
                        allDessertList.clear();
                        allDessertList.addAll(desserts);

                        // Megjelenítjük az egészet alapból
                        dessertRecyclerView.setAdapter(new DessertResult(allDessertList));
                    }
                }
            }

            @Override
            public void onFailure(Call<DessertResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Hiba: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helyi szűrés: letöltött desszertek között

    private void filterLocalData(String query) {

        if (allDessertList.isEmpty()) {
            Toast.makeText(getContext(), "Adatok betöltése folyamatban.", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Dessert> filteredList = new ArrayList<>();

        for (Dessert item : allDessertList) {
            if (item.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "Nincs ilyen desszert.", Toast.LENGTH_SHORT).show();
        }

        dessertRecyclerView.setAdapter(new DessertResult(filteredList));
    }
}
