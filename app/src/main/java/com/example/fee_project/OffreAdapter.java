package com.example.fee_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
// cette page nous aide à adapter le format de l'item qui contient les informations des offres qui s'affichent au recruteur

public class OffreAdapter extends RecyclerView.Adapter<OffreAdapter.MyViewHolder>{


    private Context context;
    Activity activity;
    private ArrayList idoff,titre,type,remuneration,description,periode;

    public OffreAdapter(Context context, Activity activity, ArrayList idoff, ArrayList titre, ArrayList type, ArrayList remuneration, ArrayList description, ArrayList periode) {
        this.context = context;
        this.activity = activity;
        this.idoff = idoff;
        this.titre = titre;
        this.type = type;
        this.remuneration = remuneration;
        this.description = description;
        this.periode = periode;
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

        holder.idoff.setText(String.valueOf(idoff.get(position)));
        holder.titre.setText(String.valueOf(titre.get(position)));
        holder.type.setText(String.valueOf(type.get(position)));
        holder.remuneration.setText(String.valueOf(remuneration.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
        holder.periode.setText(String.valueOf(periode.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, UpdateOffre.class);
                intent.putExtra("idoff", String.valueOf(idoff.get(position)));
                intent.putExtra("title", String.valueOf(titre.get(position)));
                intent.putExtra("type", String.valueOf(type.get(position)));
                intent.putExtra("remuneration", String.valueOf(remuneration.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                intent.putExtra("periode", String.valueOf(periode.get(position)));
                activity.startActivityForResult(intent,1);
                //when we click in our reclycle view item, on transfere ses donnees a updateoffre
            }
        });
    }

    @Override
    public int getItemCount(){
        return titre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idoff,titre,type,remuneration,description,periode;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idoff = itemView.findViewById(R.id.tv_id);
            titre = itemView.findViewById(R.id.tv_titre);
            type = itemView.findViewById(R.id.tv_typeoff);
            remuneration = itemView.findViewById(R.id.tv_remunerationoff);
            description = itemView.findViewById(R.id.tv_description);
            periode = itemView.findViewById(R.id.tv_periodeoff);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }
}
