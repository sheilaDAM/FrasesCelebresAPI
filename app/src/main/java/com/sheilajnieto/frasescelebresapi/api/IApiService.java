package com.sheilajnieto.frasescelebresapi.api;/*
@author sheila j. nieto 
@version 0.1 2024 -01 - 30
*/

import com.sheilajnieto.frasescelebresapi.modelos.Autor;
import com.sheilajnieto.frasescelebresapi.modelos.Categoria;
import com.sheilajnieto.frasescelebresapi.modelos.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


//FACHADA DE LA API REST
public interface IApiService {
    //firma de un método que le vamos a decir qué queremos obtener y a través de qué método
    //el @GET tendrá la URL relativa a al api principal /character/1, el 1 se sustituye por un placeholder {id}
    //este id se convertirá en una variable, se parametriza para que al leerlo de la llave lo meta en getCharacter
    @GET("autor/all")
    //anotaciones para que cuando invoquemos a qeste método, retrofit compilará este método y generará un código específico para este método, retrofit implementa esta interfaz automáticamente
    public Call<List<Autor>> obtenerAutores(); //a través del conversor implementado en el gradle se encargará de convertir la llamada que recibe un JSON en un objeto de tipo Character

    @GET("categoria/all")
    public Call<List<Categoria>> obtenerCategorias();

    @GET("frase/autor/1/{id}")
    public Call<List<Frase>> obtenerFrasesPorAutor(@Path("id") int idAutor);


    @GET("categoria/{id}")
    public Call<Categoria> obtenerFrasesPorCategoria(@Path("id") int id);

    @GET("frase/dia/{fecha}")
    Call<Frase> obtenerFraseDelDia(@Path("fecha") String fecha);


}
