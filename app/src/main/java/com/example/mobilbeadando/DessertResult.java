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

    private List<Dessert> dessertList;
    private Context context;
    private AppDatabase db;

    public DessertResult(List<Dessert> dessertList) {
        this.dessertList = dessertList;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        db = AppDatabase.getInstance(context);

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

        // Kinyit/Becsuk logika
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

        // --- KEDVENC GOMB LOGIKA ---

        if (db.favoriteDao().isFavorite(currentDessert.getIdMeal())) {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on); // teli csillag
        } else {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off); // üres csillag
        }

        holder.btnFavorite.setOnClickListener(v -> {
            FavoriteDessert fav = new FavoriteDessert( // class kell a FavoritesFragment-be???
                    currentDessert.getIdMeal(),
                    currentDessert.getStrMeal(),
                    currentDessert.getStrMealThumb()
            );

            boolean isFav = db.favoriteDao().isFavorite(currentDessert.getIdMeal());

            if (isFav) {
                db.favoriteDao().delete(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off); //ikon csere
                Toast.makeText(context, "Eltávolítva a kedvencekből", Toast.LENGTH_SHORT).show();
            } else {
                db.favoriteDao().insert(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on); //ikon csere
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
