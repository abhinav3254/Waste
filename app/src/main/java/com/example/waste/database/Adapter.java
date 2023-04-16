package com.example.waste.database;

import static android.graphics.Color.alpha;
import static android.graphics.Color.argb;
import static android.graphics.Color.rgb;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waste.ExtraStuffs;
import com.example.waste.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Collections;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Pojo> list;

    Animation animation;

    public Adapter(Context context, List<Pojo> list) {
        this.context = context;
        this.list = list;
        Collections.reverse(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_one);
        holder.cardView.startAnimation(animation);
//        holder.desc.setText(list.get(holder.getAdapterPosition()).getDesc());
        holder.title.setText(list.get(holder.getAdapterPosition()).getTitle());
//        holder.date.setText(list.get(holder.getAdapterPosition()).getDate());
        holder.price.setText(list.get(holder.getAdapterPosition()).getPrice());
//        holder.id.setText(list.get(holder.getAdapterPosition()).getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtraStuffs.showBottomSheet(list.get(position),context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,price,date,desc,id;
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCard);
            price = itemView.findViewById(R.id.price1);
//            date = itemView.findViewById(R.id.date);
//            desc = itemView.findViewById(R.id.desc);
//            id = itemView.findViewById(R.id.idApp);
            cardView = itemView.findViewById(R.id.cardViewCard);
        }
    }
}
