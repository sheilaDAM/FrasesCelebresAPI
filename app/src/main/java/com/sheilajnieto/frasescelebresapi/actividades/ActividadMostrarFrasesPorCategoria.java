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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        idCategoriaSeleccionada = getIntent().getIntExtra("idCategoriaSeleccionada", 0);

        apiService = RestClient.getApiServiceInstance();

        obtenerFrasesPorCategoria(idCategoriaSeleccionada);

    }

    private void obtenerFrasesPorCategoria(int idCategoriaSeleccionada) {
        Call<List<Frase>> call = apiService.obtenerFrasesPorAutor(idCategoriaSeleccionada);
        call.enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaFrasesPorCategoria = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener frases por categoría: " + t.getMessage());
            }
        });

        mostrarFrasesPorCategoria(listaFrasesPorCategoria);
    }

    public void mostrarFrasesPorCategoria(List<Frase> listaFrasesPorAutor) {
        obtenerFrasesPorCategoria(idCategoriaSeleccionada);
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);

        AdaptadorFrase adaptador = new AdaptadorFrase(listaFrasesPorAutor);
        recView.setAdapter(adaptador);
    }
}
