package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mobilbeadando.databinding.FragmentPastaBinding;

import java.util.List;

public class PastaFragment extends Fragment {

    private EditText editText_search;
    private Button button_search;
    private TextView textViewPastaTitle;
    private TextView textview_pasta_content;
    private RecyclerView pastaRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment layout betöltése
        View view = inflater.inflate(R.layout.fragment_pasta, container, false);

        // Elemek inicializálása
        editText_search = view.findViewById(R.id.editText_search);
        button_search = view.findViewById(R.id.button_search);
        textViewPastaTitle = view.findViewById(R.id.textViewPastaTitle);
        textview_pasta_content = view.findViewById(R.id.textview_pasta_content);
        pastaRecyclerView = view.findViewById(R.id.pastaRecyclerView);

        List<Pasta> pastaList = MockPastaRepository.getMockPastaList();

        // RecyclerView beállítása
        PastaListAdapter adapter = new PastaListAdapter(pastaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        pastaRecyclerView.setLayoutManager(layoutManager);
        pastaRecyclerView.setAdapter(adapter);

        // Keresés gomb
        button_search.setOnClickListener(v -> {
            String meal = editText_search.getText().toString();
            String message = "Searching meals: " + meal;
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}