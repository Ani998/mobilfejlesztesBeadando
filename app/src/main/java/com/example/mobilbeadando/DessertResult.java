package com.example.mobilbeadando;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobilbeadando.data.local.AppDatabase;
import com.example.mobilbeadando.data.local.FavoriteDessert;

import java.util.List;

public class DessertResult extends RecyclerView.Adapter<DessertResult.DessertViewHolder> {

    private final List<Dessert> dessertList;
    private final Context context;
    private final AppDatabase db;

    public DessertResult(Context context, List<Dessert> dessertList) {
        this.context = context;
        this.dessertList = dessertList;
        this.db = AppDatabase.getInstance(context.getApplicationContext());
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dessert_list_item, parent, false);
        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {
        Dessert currentDessert = dessertList.get(position);

        holder.mealName.setText(currentDessert.getStrMeal());
        holder.mealId.setText("ID: " + currentDessert.getIdMeal());

        Glide.with(context)
                .load(currentDessert.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mealThumb);

        // Kinyitás / Becsukás
        if (currentDessert.isExpanded()) {
            holder.mealThumb.setVisibility(View.VISIBLE);
            holder.mealId.setVisibility(View.VISIBLE);
        } else {
            holder.mealThumb.setVisibility(View.GONE);
            holder.mealId.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = currentDessert.isExpanded();
            currentDessert.setExpanded(!expanded);
            notifyItemChanged(position);
        });

        // --- KEDVENC LOGIKA (DESSZERTRE SPECIFIKUS) ---

        // Ellenőrizzük, hogy kedvenc-e
        if (db.favoriteDao().isFavoriteDessert(currentDessert.getIdMeal())) {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }

        // Gombnyomás
        holder.btnFavorite.setOnClickListener(v -> {
            FavoriteDessert fav = new FavoriteDessert(
                    currentDessert.getIdMeal(),
                    currentDessert.getStrMeal(),
                    currentDessert.getStrMealThumb()
            );

            if (db.favoriteDao().isFavoriteDessert(currentDessert.getIdMeal())) {
                // Törlés
                db.favoriteDao().deleteDessert(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
                Toast.makeText(context, "Eltávolítva a kedvencekből", Toast.LENGTH_SHORT).show();
            } else {
                // Mentés
                db.favoriteDao().insertDessert(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
                Toast.makeText(context, "Hozzáadva a kedvencekhez!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dessertList.size();
    }

    public static class DessertViewHolder extends RecyclerView.ViewHolder {
        ImageView mealThumb;
        TextView mealName;
        TextView mealId;
        ImageButton btnFavorite;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb = itemView.findViewById(R.id.meal_thumb);
            mealName = itemView.findViewById(R.id.meal_name);
            mealId = itemView.findViewById(R.id.meal_id);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }
    }
}