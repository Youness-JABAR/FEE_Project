package com.example.fee_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CandidatAdapter extends RecyclerView.Adapter<CandidatAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    ArrayList nom,prenom,link;

    public CandidatAdapter(Context context, Activity activity, ArrayList nom, ArrayList prenom) {
        this.context = context;
        this.activity = activity;
        this.nom = nom;
        this.prenom = prenom;
       // this.link = link;
    }

    @NonNull
    @Override
    //public CandidatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.candidat_row,parent,false);
        //return new CandidatAdapter.MyViewHolder(view);
        return new MyViewHolder(view);
    }

    @Override
    //public void onBindViewHolder(@NonNull CandidatAdapter.MyViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nom.setText(String.valueOf(nom.get(position)));
        holder.prenom.setText(String.valueOf(nom.get(position)));
        //holder.link.setText(String.valueOf(link.get(position)));
    }

    @Override
    public int getItemCount() {
        return nom.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nom,prenom,link;
        LinearLayout candidatLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tv_nom);
            prenom = itemView.findViewById(R.id.tv_prenom);
            //link = itemView.findViewById(R.id.tv_link);
            candidatLayout = itemView.findViewById(R.id.candidatLayout);
        }
    }
}
