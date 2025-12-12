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
import com.example.mobilbeadando.data.local.FavoritePasta;

import java.util.List;

public class PastaResult extends RecyclerView.Adapter<PastaResult.PastaViewHolder> {

    private List<Pasta> pastaList;
    private Context context;
    private AppDatabase db;

    public PastaResult(List<Pasta> pastaList) {
        this.pastaList = pastaList;
    }

    @NonNull
    @Override
    public PastaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        db = AppDatabase.getInstance(context);

        View view = LayoutInflater.from(context).inflate(R.layout.pasta_list_item, parent, false);
        return new PastaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastaViewHolder holder, int position) {
        Pasta currentPasta = pastaList.get(position);

        holder.mealName.setText(currentPasta.getStrMeal());
        holder.mealId.setText("ID: " + currentPasta.getIdMeal());

        Glide.with(context)
                .load(currentPasta.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mealThumb);


        if (currentPasta.isExpanded()) {
            holder.mealThumb.setVisibility(View.VISIBLE);
            holder.mealId.setVisibility(View.VISIBLE);
        } else {
            holder.mealThumb.setVisibility(View.GONE);
            holder.mealId.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = currentPasta.isExpanded();
            currentPasta.setExpanded(!expanded);
            notifyItemChanged(position);
        });


        if (db.favoriteDao().isFavorite(currentPasta.getIdMeal())) {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on); // Teli csillag
        } else {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off); // Üres csillag (vagy sima btn_star)
        }


        holder.btnFavorite.setOnClickListener(v -> {
            FavoritePasta fav = new FavoritePasta(
                    currentPasta.getIdMeal(),
                    currentPasta.getStrMeal(),
                    currentPasta.getStrMealThumb()
            );

            // Megnézzük, hogy kedvenc-e már
            boolean isFav = db.favoriteDao().isFavorite(currentPasta.getIdMeal());

            if (isFav) {

                db.favoriteDao().delete(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off); // Ikon csere
                Toast.makeText(context, "Eltávolítva a kedvencekből", Toast.LENGTH_SHORT).show();



            } else {

                db.favoriteDao().insert(fav);
                holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on); // Ikon csere
                Toast.makeText(context, "Hozzáadva a kedvencekhez!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pastaList.size();
    }

    public static class PastaViewHolder extends RecyclerView.ViewHolder {
        ImageView mealThumb;
        TextView mealName;
        TextView mealId;
        ImageButton btnFavorite;

        public PastaViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb = itemView.findViewById(R.id.meal_thumb);
            mealName = itemView.findViewById(R.id.meal_name);
            mealId = itemView.findViewById(R.id.meal_id);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }
    }
}