package com.sheilajnieto.frasescelebresapi.api;/*
@author sheila j. nieto 
@version 0.1 2024 -01 - 30
*/

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//API helper
//convertimos en patrón singleton para que solo haya una instancia de esta clase
public class RestClient {

    private static IApiService apiInstance;
    private static final String BASE_URL = "http://192.168.50.145:8082"; //url base de la api

    //constructor privado para que no se puedan tener objetos de este tipo
    private RestClient() {
    }

    public static IApiService getApiServiceInstance() {
        if (apiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInstance = retrofit.create(IApiService.class); //nos devuelve un IApiService ya implemetando que podemos consumir

        }
        return apiInstance;
    }
}
