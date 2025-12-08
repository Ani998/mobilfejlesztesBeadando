package com.example.mobilbeadando;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast; // A Toast üzenet a gombok teszteléséhez

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobilbeadando.databinding.FragmentSecondBinding;
// FIGYELEM: Ha az Android Studio nem generálja a FragmentSecondBinding-et,
// a Build -> Clean Project és Build -> Rebuild Project parancsok segíthetnek.

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    // Megjegyzés: Szükséged lesz egy PastaAdapter és egy PastaItem osztályra is a későbbiekben!

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // View Binding inicializálása a fragment_second.xml alapján
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // ----------------------------------------------------------------------

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. RecyclerView beállítása (A lista megjelenítéséhez)
        setupRecyclerView();

        // 2. Gombok eseménykezelése (Navigáció és Keresés)
        setupButtonListeners();
    }

    /**
     * Beállítja a RecyclerView-t (Layout Manager és Adapter).
     */
    private void setupRecyclerView() {
        // A RecyclerView-nak szüksége van egy LayoutManager-re, ami meghatározza,
        // hogyan helyezkedjenek el az elemek (pl. függőleges lista)
        binding.pastaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // IDE KELL MAJD AZ ADAPTER:
        // List<PastaItem> pastaList = loadPastaData(); // Adatok betöltése (Retrofit/Room/mock)
        // PastaAdapter adapter = new PastaAdapter(pastaList);
        // binding.pastaRecyclerView.setAdapter(adapter);
    }

    /**
     * Beállítja a gombokra kattintás eseményeit.
     */
    private void setupButtonListeners() {
        // Vissza gomb (Back)
        binding.btnBack.setOnClickListener(v -> {
            // Például visszanavigál az előző fragmentre vagy a kezdőoldalra (fragment_home)
            // Itt a NavHostFragment-et használjuk navigációra
            // Cseréld ki az R.id.action_SecondFragment_to_FirstFragment-et a tényleges ID-ra!
            Toast.makeText(getContext(), "Vissza a főoldalra", Toast.LENGTH_SHORT).show();
            // NavHostFragment.findNavController(SecondFragment.this)
            //         .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        // Keresés gomb (Search)
        binding.btnSearch.setOnClickListener(v -> {
            // Itt implementáld a keresési funkció logikáját
            Toast.makeText(getContext(), "Keresés indítása...", Toast.LENGTH_SHORT).show();
        });

        // Alulsó navigációs gombok
        binding.btnDesserts.setOnClickListener(v -> {
            // Navigálás a Desserts fragmentre
            Toast.makeText(getContext(), "Navigálás a Desserts-re", Toast.LENGTH_SHORT).show();
        });

        binding.btnPasta.setOnClickListener(v -> {
            // Már a Pasta fragmenten vagyunk, itt általában semmi nem történik
            Toast.makeText(getContext(), "Már a Pasta oldalon vagy!", Toast.LENGTH_SHORT).show();
        });

        binding.btnFavorites.setOnClickListener(v -> {
            // Navigálás a Favourites fragmentre
            Toast.makeText(getContext(), "Navigálás a Kedvencek-re", Toast.LENGTH_SHORT).show();
        });
    }

    // ----------------------------------------------------------------------

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // A binding null-ra állítása a memória szivárgás elkerülése érdekében
        binding = null;
    }
}