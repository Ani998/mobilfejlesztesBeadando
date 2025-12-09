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

        // Toolbar beállítása
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //hogy ne jelenjen meg a projekt neve
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        // Alsó navigáció
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // Kezdőképernyő, főoldal (FirstFragment)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            bottomNav.setSelectedItemId(R.id.nav_home);
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;

            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Főoldal (FirstFragment )
                selected = new HomeFragment();
            } else if (id == R.id.nav_pasta) {
                // Itt SecondFragmentet használjuk a Pasta menüponthoz, mert a SecondFragmentet módosítottuk és nem j fragmentet hoztunk létre
                selected = new PastaFragment();
            } else if (id == R.id.nav_desserts) {
                selected = new DessertsFragment();
            } else if (id == R.id.nav_favorites) {
                selected = new FavoritesFragment();
            }

            if (selected != null) {
                loadFragment(selected);
                return true;
            } else {
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
