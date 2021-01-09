package com.example.fee_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// cette page nous aide à adapter le format de l'item qui contient les informations des offres et s'affcihe pour l'étudiant
public class OffreEtdAdapter extends RecyclerView.Adapter<OffreEtdAdapter.MyViewHolder> {


    private Context context;
    private ArrayList titre,type,remuneration,description,periode,id;

    public OffreEtdAdapter(Context context, ArrayList titre, ArrayList type, ArrayList remuneration, ArrayList description, ArrayList periode, ArrayList id) {
        this.context = context;
        this.titre = titre;
        this.type = type;
        this.remuneration = remuneration;
        this.description = description;
        this.periode = periode;
        this.id = id;
        System.out.println(id.get(0));
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
        holder.id.setText(id.get(position).toString());
        holder.etdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DescriptionOffers.class);
                intent.putExtra("idOffer", id.get(position).toString());
                intent.putExtra("title", String.valueOf(titre.get(position)));
                intent.putExtra("type", String.valueOf(type.get(position)));
                intent.putExtra("remuneration", String.valueOf(remuneration.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                intent.putExtra("periode", String.valueOf(periode.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return periode.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titre,type,remuneration,description,periode,id;
        LinearLayout etdLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.tv_titre_etdrow);
            type = itemView.findViewById(R.id.tv_type);
            remuneration = itemView.findViewById(R.id.tv_remuneration);
            description = itemView.findViewById(R.id.tv_desc_etdrow);
            periode = itemView.findViewById(R.id.tv_periode);
            id = itemView.findViewById(R.id.tv_id);
            etdLayout=itemView.findViewById(R.id.etdLayout);
        }
    }
}
