package com.sheilajnieto.frasescelebresapi.actividades;/*
@author sheila j. nieto 
@version 0.1 2024 -02 - 02
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
import com.sheilajnieto.frasescelebresapi.modelos.Frase;
import com.sheilajnieto.frasescelebresapi.modelos.adaptadores.AdaptadorFrase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadMostrarFrasesPorCategoria extends AppCompatActivity {

    private RecyclerView recView;
    private AdaptadorFrase adaptadorFrase;
    private int idCategoriaSeleccionada;
    private List<Frase> listaFrasesPorCategoria;
    private IApiService apiService;
    private AdaptadorFrase adaptador;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        idCategoriaSeleccionada = getIntent().getIntExtra("idCategoriaSeleccionada", 0);
        Log.d("CATEGORIA", "idCategoriaSeleccionada: " + idCategoriaSeleccionada);
        apiService = RestClient.getApiServiceInstance();

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);
        adaptador = new AdaptadorFrase(listaFrasesPorCategoria);
        recView.setAdapter(adaptador);

        obtenerFrasesPorCategoria(idCategoriaSeleccionada);

    }

    private void obtenerFrasesPorCategoria(int idCategoriaSeleccionada) {
       apiService.obtenerFrasesPorCategoria(idCategoriaSeleccionada).enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                if (response.isSuccessful()) {
                    listaFrasesPorCategoria = response.body();
                    Log.d("FRASES OBTENIDAS DE CATEGORIA", "Cantidad: " + listaFrasesPorCategoria.size());
                    adaptador.setData(listaFrasesPorCategoria);
                } else {
                    Log.d("FRASES OBTENIDAS DE CATEGORIA", "Error al obtener las frases de la categoria: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {
                Log.d("FRASES OBTENIDAS DE CATEGORIA", "Error al obtener las frases de la categoria: " + t.getMessage());
            }
        });

    }
}
