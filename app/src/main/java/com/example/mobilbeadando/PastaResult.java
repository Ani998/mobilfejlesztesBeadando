package com.example.mobilbeadando;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide; // Győződj meg róla, hogy a Glide be van húzva a gradle-be!

import java.util.List;

public class PastaResult extends RecyclerView.Adapter<PastaResult.PastaViewHolder> {

    private List<Pasta> pastaList;
    private Context context;

    public PastaResult(List<Pasta> pastaList) {
        this.pastaList = pastaList;
    }

    @NonNull
    @Override
    public PastaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pasta_list_item, parent, false);
        return new PastaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastaViewHolder holder, int position) {
        Pasta currentPasta = pastaList.get(position);

        // Név beállítása
        holder.mealName.setText(currentPasta.getStrMeal());

        // ID beállítása
        holder.mealId.setText("ID: " + currentPasta.getIdMeal());

        // Kép betöltése URL-ből Glide segítségével
        // (ez a feladatleírásban "Nagyfelbontású képi erőforrások betöltése Glide csomag használatával valósuljon meg")
        Glide.with(context)
                .load(currentPasta.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background) // Opcionális: kell egy placeholder kép
                .into(holder.mealThumb);

        // Logika: Ha 'isExpanded' igaz, akkor látható a kép és az ID, egyébként GONE (eltűnik)
        //"Tartalmazzon listás és részletes nézetet/nézeteket "

        if (currentPasta.isExpanded()) {
            holder.mealThumb.setVisibility(View.VISIBLE);
            holder.mealId.setVisibility(View.VISIBLE);
        } else {
            holder.mealThumb.setVisibility(View.GONE);
            holder.mealId.setVisibility(View.GONE);
        }

        // Kattintás esemény az egész elemre - Kattintásra nyílik - csukódik a sor
        holder.itemView.setOnClickListener(v -> {
            // Átállítjuk az állapotot az ellenkezőjére
            boolean expanded = currentPasta.isExpanded();
            currentPasta.setExpanded(!expanded);

            // Értesítjük az adaptert, hogy ez az elem megváltozott, rajzolja újra
            notifyItemChanged(position);
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

        public PastaViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb = itemView.findViewById(R.id.meal_thumb);
            mealName = itemView.findViewById(R.id.meal_name);
            mealId = itemView.findViewById(R.id.meal_id);
        }
    }
}