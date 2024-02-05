package com.sheilajnieto.frasescelebresapi.modelos.adaptadores;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 02
*/

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheilajnieto.frasescelebresapi.R;
import com.sheilajnieto.frasescelebresapi.actividades.ActividadMostrarFrasesPorAutor;
import com.sheilajnieto.frasescelebresapi.modelos.Autor;

import java.util.List;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.ListViewHolder> {

    private final List<Autor> autores;
    //private final IOnClickListener listener;


    public AdaptadorAutor(List<Autor> autores) {
        this.autores = autores;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor, parent, false);
        return new ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Autor autor = autores.get(position);
        holder.bindCategory(autor);
    }


    @Override
    public int getItemCount() {
        return autores.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvNombreAutor;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            this.tvNombreAutor = itemview.findViewById(R.id.tvNombreAutor);

            itemview.setOnClickListener(this);

        }

        public void bindCategory(Autor autor) {
            tvNombreAutor.setText(autor.getNombre());
        }

        @Override
        public void onClick(View v) {
            int idAutorSeleccionado = autores.get(getAdapterPosition()).getId();
            Log.d("MOSTRAR DATOS", "idAutorSeleccionado: " + idAutorSeleccionado);
            Intent intent = new Intent(context, ActividadMostrarFrasesPorAutor.class);
            intent.putExtra("idAutorSeleccionado", idAutorSeleccionado);
            context.startActivity(intent);
        }

    }
}