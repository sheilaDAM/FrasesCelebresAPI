package com.sheilajnieto.frasescelebresapi.modelos;/*
@author sheila j. nieto 
@version 0.1 2024 -01 - 31
*/

import android.os.Parcel;
import android.os.Parcelable;

public class Categoria implements Parcelable {

    private int id;
    private String nombre;

    public Categoria(int id, String name) {
        this.id = id;
        this.nombre = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + nombre + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // ------- CÓDIGO PARA IMPLEMENTAR PARCELABLE -------

    protected Categoria(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
    }

}
