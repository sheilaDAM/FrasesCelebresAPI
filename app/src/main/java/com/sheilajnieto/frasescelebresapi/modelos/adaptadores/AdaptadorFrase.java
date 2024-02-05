package com.sheilajnieto.frasescelebresapi.modelos.adaptadores;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 02
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheilajnieto.frasescelebresapi.R;
import com.sheilajnieto.frasescelebresapi.modelos.Autor;
import com.sheilajnieto.frasescelebresapi.modelos.Frase;

import java.util.List;

public class AdaptadorFrase extends RecyclerView.Adapter<AdaptadorFrase.ListViewHolder> {

    private final List<Frase> frases;
    //private final IOnClickListener listener;


    public AdaptadorFrase(List<Frase> frases) {
        this.frases = frases;

    }

    @NonNull
    @Override
    public AdaptadorFrase.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frase, parent, false);
        return new AdaptadorFrase.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFrase.ListViewHolder holder, int position) {
        Frase frase = frases.get(position);
        holder.bindCategory(frase, position);
    }


    @Override
    public int getItemCount() {
        return frases.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvFrase;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            this.tvFrase = itemview.findViewById(R.id.tvFrase);

            itemview.setOnClickListener(this);

        }

        public void bindCategory(Frase frase, int position) {
            tvFrase.setText(frases.get(position).getTexto());

        }

        @Override
        public void onClick(View v) {
            /*
            if (listener != null) {
                listener.onCategoryClicked(getAdapterPosition());
            }

             */

        }

    }
}
