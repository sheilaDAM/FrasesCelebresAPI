package com.sheilajnieto.frasescelebresapi.modelos.adaptadores;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 02
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheilajnieto.frasescelebresapi.R;
import com.sheilajnieto.frasescelebresapi.actividades.ActividadMostrarFrasesPorCategoria;
import com.sheilajnieto.frasescelebresapi.modelos.Autor;
import com.sheilajnieto.frasescelebresapi.modelos.Categoria;

import java.util.List;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ListViewHolder> {

    private final List<Categoria> categorias;
    //private final IOnClickListener listener;


    public AdaptadorCategoria(List<Categoria> categorias) {
        this.categorias = categorias;

    }

    @NonNull
    @Override
    public AdaptadorCategoria.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new AdaptadorCategoria.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCategoria.ListViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.bindCategory(categoria, position);
    }


    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvNombreCategoria;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            this.tvNombreCategoria = itemview.findViewById(R.id.tvNombreCategoria);

            itemview.setOnClickListener(this);

        }

        public void bindCategory(Categoria categoria, int position) {
            tvNombreCategoria.setText(categoria.getNombre());

        }

        @Override
        public void onClick(View v) {
            int idCategoriaSeleccionada = categorias.get(getAdapterPosition()).getId();
            Intent intent = new Intent(context, ActividadMostrarFrasesPorCategoria.class);
            intent.putExtra("idCategoriaSeleccionada", idCategoriaSeleccionada);
            context.startActivity(intent);

        }

    }
}