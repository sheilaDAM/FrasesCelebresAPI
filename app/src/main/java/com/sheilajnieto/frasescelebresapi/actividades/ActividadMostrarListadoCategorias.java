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
import com.sheilajnieto.frasescelebresapi.modelos.Categoria;
import com.sheilajnieto.frasescelebresapi.modelos.adaptadores.AdaptadorCategoria;

import java.util.List;

public class ActividadMostrarListadoCategorias extends AppCompatActivity {

    private RecyclerView recView;
    private AdaptadorCategoria adaptadorCategoria;
    private int idCategoriaSeleccionada;
    private List<Categoria> listaCategorias;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        listaCategorias = (List<Categoria>) getIntent().getSerializableExtra("listaCategorias");
        mostrarCategorias(listaCategorias);
    }

    public void mostrarCategorias(List<Categoria> listaCategorias) {
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);

        AdaptadorCategoria adaptador = new AdaptadorCategoria(listaCategorias);
        recView.setAdapter(adaptador);
    }
}
