package com.example.mobilbeadando;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Toolbar beállítása
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hogy ne jelenjen meg a projekt neve (Mobilbeadando) a felső csíkban
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 2. Alsó navigáció (Bottom Navigation) beállítása
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // 3. Kezdőképernyő betöltése (HomeFragment)
        // Csak akkor töltjük be, ha az alkalmazás most indul (savedInstanceState == null)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            bottomNav.setSelectedItemId(R.id.nav_home);
        }

        // 4. Menüpontokra kattintás figyelése
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_pasta) {
                selectedFragment = new PastaFragment();
            } else if (id == R.id.nav_desserts) {
                // FONTOS: Itt a javított DessertFragment-et hívjuk!
                // Győződj meg róla, hogy a fájl neve: DessertFragment.java
                selectedFragment = new DessertFragment();
            } else if (id == R.id.nav_favorites) {
                selectedFragment = new FavoritesFragment();
            }

            // Ha sikerült kiválasztani a fragmentet, betöltjük
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            } else {
                return false;
            }
        });
    }

    // Segédfüggvény a Fragment cseréjéhez
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}