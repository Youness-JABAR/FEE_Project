package com.example.fee_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OffreAdapter extends RecyclerView.Adapter<OffreAdapter.MyViewHolder>{
    private Context context;
    private ArrayList titre,description;
    OffreAdapter(Context context, ArrayList titre, ArrayList description){
        this.context = context;
        this.titre = titre;
        this.description = description;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.offre_rec_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titre.setText(String.valueOf(titre.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
    }

    @Override
    public int getItemCount(){
        return titre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titre,description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.tv_titre);
            description = itemView.findViewById(R.id.tv_description);
        }

    }
}
