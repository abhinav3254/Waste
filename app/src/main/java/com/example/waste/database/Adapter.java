package com.example.waste.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waste.ExtraStuffs;
import com.example.waste.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Pojo> list;

    public Adapter(Context context, List<Pojo> list) {
        this.context = context;
        this.list = list;
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
        holder.desc.setText(list.get(position).getDesc());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
        holder.price.setText(list.get(position).getPrice());
        holder.id.setText(list.get(position).getId());
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
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.desc);
            id = itemView.findViewById(R.id.idApp);
            cardView = itemView.findViewById(R.id.cardViewCard);
        }
    }
}
