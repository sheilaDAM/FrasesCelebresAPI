package com.sheilajnieto.frasescelebresapi.modelos;/*
@author sheila j. nieto 
@version 0.1 2024 -01 - 31
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Frase implements Parcelable {

    private int id;
    private String texto;
    private String fechaProgramada;
    private Autor autor;
    private Categoria categoria;
    private List<Frase> frases;


    public Frase(int id, String texto, String fechaProgramada, Autor autor, Categoria categoria) {
        this.id = id;
        this.texto = texto;
        this.fechaProgramada = fechaProgramada;
        this.autor = autor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public Autor getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Frase> getFrases() {
        return frases;
    }

    // ------- CÓDIGO PARA IMPLEMENTAR PARCELABLE -------

    protected Frase(Parcel in) {
        id = in.readInt();
        texto = in.readString();
        fechaProgramada = in.readString();
        autor = in.readParcelable(Autor.class.getClassLoader());
        categoria = in.readParcelable(Categoria.class.getClassLoader());
    }

    public static final Creator<Frase> CREATOR = new Creator<Frase>() {
        @Override
        public Frase createFromParcel(Parcel in) {
            return new Frase(in);
        }

        @Override
        public Frase[] newArray(int size) {
            return new Frase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(texto);
        dest.writeString(fechaProgramada);
        dest.writeParcelable(autor, flags);
        dest.writeParcelable(categoria, flags);
    }
}
