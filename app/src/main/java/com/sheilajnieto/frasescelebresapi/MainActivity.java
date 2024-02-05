package com.sheilajnieto.frasescelebresapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sheilajnieto.frasescelebresapi.actividades.ActividadMostrarListadoAutores;
import com.sheilajnieto.frasescelebresapi.actividades.ActividadMostrarListadoCategorias;
import com.sheilajnieto.frasescelebresapi.api.IApiService;
import com.sheilajnieto.frasescelebresapi.api.RestClient;
import com.sheilajnieto.frasescelebresapi.modelos.Autor;
import com.sheilajnieto.frasescelebresapi.modelos.Categoria;
import com.sheilajnieto.frasescelebresapi.modelos.Frase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Autor> listaAutores;
    private List<Categoria> listaCategorias;
    private IApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bMostrarPorAutor = findViewById(R.id.btMostrarPorAutor);
        Button bMostrarPorCategoria = findViewById(R.id.btMostrarPorCategoria);
        Button bFraseDelDia = findViewById(R.id.btFraseDelDia);

        bMostrarPorAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerAutoresDeAPI();
              /*
                try {
                    listaAutores = obtenerListadoAutores();
                    mostrarListadoAutores(listaAutores);
                } catch (IOException e) {
                    e.printStackTrace();
                        Log.d("MOSTRAR DATOS", "Error al obtener los autores de la api rest: " + e.getMessage());
                }
               */
            }
        });

        bMostrarPorCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerCategoriasDeAPI();
            }
        });

        bFraseDelDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFraseDelDia();
            }
        });

        apiService = RestClient.getApiServiceInstance();

    } //fin onCreate

    //---- MÉTODO PARA OBTENER EL LISTADO DE AUTORES DE LA API Y GUARDARLOS EN UNA COLECCION ----
    private void obtenerAutoresDeAPI() {
        apiService.obtenerAutores().enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if (response.isSuccessful()) {
                    listaAutores = response.body();
                    Log.d("RETROFIT", "Autores obtenidos de la api rest: " + listaAutores.size());
                    mostrarListadoAutores(listaAutores);
                }
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener los autores de la api rest: " + t.getMessage());
            }
        });
    }

    //---- MÉTODO PARA OBTENER EL LISTADO DE CATEGORÍAS DE LA API Y GUARDARLAS EN UNA COLECCION ----
    private void obtenerCategoriasDeAPI() {
        apiService.obtenerCategorias().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    listaCategorias = response.body();
                    Log.d("RETROFIT", "Categorías obtenidas de la api rest: " + listaCategorias.size());
                    mostrarListadoCategorias(listaCategorias);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener las categorías de la api rest: " + t.getMessage());
            }
        });
    }


    //---- MANEJO PARA CAMBIAR DE ACTIVIDAD Y MOSTRAR EL LISTADO DE AUTORES ----
    private void mostrarListadoAutores(List<Autor> listaAutores) {

        Intent intent = new Intent(MainActivity.this, ActividadMostrarListadoAutores.class);
        intent.putExtra("listaAutores", (ArrayList<Autor>) listaAutores);
        startActivity(intent);
    }

    //---- MANEJO PARA CAMBIAR DE ACTIVIDAD Y MOSTRAR EL LISTADO DE CATEGORÍAS ----
    private void mostrarListadoCategorias(List<Categoria> listaCategorias) {
        Intent intent = new Intent(MainActivity.this, ActividadMostrarListadoCategorias.class);
        intent.putExtra("listaCategorias", (ArrayList<Categoria>) listaCategorias);
        startActivity(intent);
    }

    //---- MÉTODO PARA OBTENER LA FRASE DEL DÍA DESDE LA API----
    public void obtenerFraseDelDia() {
        String fecha = obtenerFechaActual();
        Log.d("FECHA", "Fecha actual: " + fecha);
        apiService.obtenerFraseDelDia(fecha).enqueue(new Callback<Frase>() {
            @Override
            public void onResponse(Call<Frase> call, Response<Frase> response) {
                if (response.isSuccessful()) {
                    Log.d("FRASE DEL DÍA", "Frase del día obtenida de la api rest: " + response.body().getTexto());
                    mostrarDialogoFraseDelDia(response.body());
                }
            }

            @Override
            public void onFailure(Call<Frase> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener la frase del día: " + t.getMessage());
                String url = call.request().url().toString();
                Log.d("RETROFIT", "URL de la llamada: " + url);
            }
        });
    }

    //------- MÉTODO PARA MOSTRAR UN CUADRO DE DIÁLOGO CON LA FRASE DEL DÍA -------
    public void mostrarDialogoFraseDelDia(Frase frase) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("La frase del día :)");
        SimpleDateFormat formatoFechaApi = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat nuevoFormatoFecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        String fechaCambiada = "";

        try {
            Date date = formatoFechaApi.parse(frase.getFechaProgramada());
            if (date != null) {
                fechaCambiada = nuevoFormatoFecha.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        builder.setMessage("'" + frase.getTexto() +"'" + "\n\n" + frase.getAutor().getNombre() + "\n\n" + fechaCambiada);
        builder.setPositiveButton("Aceptar", null);
        builder.show();
    }

    //------- MÉTODO PARA OBTENER LA FECHA ACTUAL -------
    public String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

}