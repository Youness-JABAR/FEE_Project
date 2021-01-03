package com.example.fee_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OffreEtdAdapter extends RecyclerView.Adapter<OffreEtdAdapter.MyViewHolder> {


    private Context context;
    private ArrayList titre,type,remuneration,description,periode;
    OffreEtdAdapter(Context context, ArrayList titre,ArrayList type,ArrayList remuneration,ArrayList description,ArrayList periode){
        this.context = context;
        this.titre = titre;
        this.type = type;
        this.remuneration = remuneration;
        this.description = description;
        this.periode = periode;
    }
    @NonNull
    @Override
    public OffreEtdAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.offre_etd_row,parent,false);
        return new OffreEtdAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreEtdAdapter.MyViewHolder holder, int position) {
        holder.titre.setText(String.valueOf(titre.get(position)));
        holder.type.setText(String.valueOf(type.get(position)));
        holder.remuneration.setText(String.valueOf(remuneration.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
        holder.periode.setText(String.valueOf(periode.get(position)));
    }

    @Override
    public int getItemCount() {
        return periode.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titre,type,remuneration,description,periode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.tv_titre_etdrow);
            type = itemView.findViewById(R.id.tv_type);
            remuneration = itemView.findViewById(R.id.tv_remuneration);
            description = itemView.findViewById(R.id.tv_desc_etdrow);
            periode = itemView.findViewById(R.id.tv_periode);
        }
    }
}
