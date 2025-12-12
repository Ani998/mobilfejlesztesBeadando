package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilbeadando.data.local.AppDatabase;
import com.example.mobilbeadando.data.local.FavoritePasta;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.recycler_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        // Kedvencek lekérése
        AppDatabase db = AppDatabase.getInstance(getContext());
        List<FavoritePasta> favList = db.favoriteDao().getAllFavorites();

        List<Pasta> pastaList = new ArrayList<>();
        for (FavoritePasta fav : favList) {
            pastaList.add(new Pasta(fav.strMeal, fav.strMealThumb, fav.idMeal));
        }


        PastaResult adapter = new PastaResult(pastaList);
        recyclerView.setAdapter(adapter);
    }
}