package com.sheilajnieto.frasescelebresapi.actividades;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 05
*/

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheilajnieto.frasescelebresapi.R;
import com.sheilajnieto.frasescelebresapi.api.IApiService;
import com.sheilajnieto.frasescelebresapi.api.RestClient;
import com.sheilajnieto.frasescelebresapi.modelos.Categoria;
import com.sheilajnieto.frasescelebresapi.modelos.Frase;
import com.sheilajnieto.frasescelebresapi.modelos.adaptadores.AdaptadorCategoria;
import com.sheilajnieto.frasescelebresapi.modelos.adaptadores.AdaptadorFrase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadMostrarFrasesPorAutor extends AppCompatActivity {

    private RecyclerView recView;
    private AdaptadorFrase adaptadorFrase;
    private int idAutorSeleccionado;
    private List<Frase> listaFrasesPorAutor;
    private IApiService apiService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        idAutorSeleccionado = getIntent().getIntExtra("idAutorSeleccionado", 0);

        Log.d("ACTIVIDAD FRASE POR AUTOR", "idAutorSeleccionado: " + idAutorSeleccionado);

        apiService = RestClient.getApiServiceInstance();

        obtenerFrasesPorAutor(idAutorSeleccionado);

    }

    private void obtenerFrasesPorAutor(int idAutorSeleccionado) {
        Call<List<Frase>> call = apiService.obtenerFrasesPorAutor(idAutorSeleccionado);
        call.enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaFrasesPorAutor = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener frases por autor: " + t.getMessage());
            }
        });

        mostrarFrasesPorAutor(listaFrasesPorAutor);
    }

    public void mostrarFrasesPorAutor(List<Frase> listaFrasesPorAutor) {
        obtenerFrasesPorAutor(idAutorSeleccionado);
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);

        AdaptadorFrase adaptador = new AdaptadorFrase(listaFrasesPorAutor);
        recView.setAdapter(adaptador);
    }
}