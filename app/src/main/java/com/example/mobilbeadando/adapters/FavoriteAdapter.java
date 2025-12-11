package com.example.mobilbeadando.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobilbeadando.R;
import com.example.mobilbeadando.models.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder> {

    private Context context;
    private List<Favorite> favorites;

    public FavoriteAdapter(Context context, List<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    public void updateList(List<Favorite> newList) {
        this.favorites = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Favorite fav = favorites.get(position);

        holder.textMealName.setText(fav.strMeal);

        Glide.with(context)
                .load(fav.strMealThumb)
                .into(holder.imageMealThumb);
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    public static class FavViewHolder extends RecyclerView.ViewHolder {

        ImageView imageMealThumb;
        TextView textMealName;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMealThumb = itemView.findViewById(R.id.imageMealThumb);
            textMealName = itemView.findViewById(R.id.textMealName);
        }
    }
}
