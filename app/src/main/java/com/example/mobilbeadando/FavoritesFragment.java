package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilbeadando.adapters.FavoriteAdapter;
import com.example.mobilbeadando.data.AppDatabase;
import com.example.mobilbeadando.models.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recyclerFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FavoriteAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        db = AppDatabase.getInstance(requireContext());

        // Observe database changes
        db.favoriteDao().getAllFavorites().observe(getViewLifecycleOwner(), new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                adapter.updateList(favorites);
            }
        });

        return view;
    }
}
