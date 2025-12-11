package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilbeadando.data.FavoriteManager;

import java.util.List;

public class PastaFragment extends Fragment {

    private EditText editText_search;
    private Button button_search;
    private Button button_add_test_fav;   // <-- TESZT GOMB
    private TextView textViewPastaTitle;
    private TextView textview_pasta_content;
    private RecyclerView pastaRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pasta, container, false);

        // UI elemek
        editText_search = view.findViewById(R.id.editText_search);
        button_search = view.findViewById(R.id.button_search);
        button_add_test_fav = view.findViewById(R.id.button_add_test_fav); // <-- TESZT GOMB
        textViewPastaTitle = view.findViewById(R.id.textViewPastaTitle);
        textview_pasta_content = view.findViewById(R.id.textview_pasta_content);
        pastaRecyclerView = view.findViewById(R.id.pastaRecyclerView);

        // Mock lista – a többiek később lecserélik API-ra
        List<Pasta> pastaList = MockPastaRepository.getMockPastaList();

        // RecyclerView beállítás
        PastaListAdapter adapter = new PastaListAdapter(pastaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        pastaRecyclerView.setLayoutManager(layoutManager);
        pastaRecyclerView.setAdapter(adapter);

        // Keresés gomb (egyelőre teszt)
        button_search.setOnClickListener(v -> {
            String meal = editText_search.getText().toString();
            String message = "Searching meals: " + meal;
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        // ⭐ TESZT – kedvenc hozzáadása
        button_add_test_fav.setOnClickListener(v -> {
            String idMeal = "12345";
            String mealName = "Test Pasta Meal";
            String mealThumb = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg";

            FavoriteManager.addToFavorites(getContext(), idMeal, mealName, mealThumb);
        });

        return view;
    }
}
