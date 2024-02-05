package com.sheilajnieto.frasescelebresapi.actividades;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 02
*/

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheilajnieto.frasescelebresapi.R;
import com.sheilajnieto.frasescelebresapi.modelos.Autor;
import com.sheilajnieto.frasescelebresapi.modelos.adaptadores.AdaptadorAutor;

import java.util.List;

public class ActividadMostrarListadoAutores extends AppCompatActivity {

    private RecyclerView recView;
    private AdaptadorAutor adaptadorAutor;
    private int idAutorSeleccionado;
    private List<Autor> listaAutores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        listaAutores = (List<Autor>) getIntent().getSerializableExtra("listaAutores");

        mostrarAutores(listaAutores);
    }

    public void mostrarAutores(List<Autor> listaAutores) {
        recView = findViewById(R.id.recView);
        AdaptadorAutor adaptador = new AdaptadorAutor(listaAutores);
        recView.setAdapter(adaptador);
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}