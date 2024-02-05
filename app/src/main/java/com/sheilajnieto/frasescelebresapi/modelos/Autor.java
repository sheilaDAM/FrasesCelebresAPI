package com.sheilajnieto.frasescelebresapi.modelos;/*
@author sheila j. nieto 
@version 0.1 2024 -01 - 31
*/

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Autor implements Parcelable {

    private int id;
    private String nombre;
    private int nacimiento;
    private String muerte;
    private String profesion;

    public Autor(int id, String nombre, int nacimiento, String muerte, String profesion) {
            this.id = id;
            this.nombre = nombre;
            this.nacimiento = nacimiento;
            this.muerte = muerte;
            this.profesion = profesion;
        }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento='" + nacimiento + '\'' +
                ", muerte='" + muerte + '\'' +
                ", profesion='" + profesion + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public String getProfesion() {
        return profesion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Autor(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        nacimiento = in.readInt();
        muerte = in.readString();
        profesion = in.readString();
    }

    public static final Creator<Autor> CREATOR = new Creator<Autor>() {
        @Override
        public Autor createFromParcel(Parcel in) {
            return new Autor(in);
        }

        @Override
        public Autor[] newArray(int size) {
            return new Autor[size];
        }
    };

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeInt(nacimiento);
        dest.writeString(muerte);
        dest.writeString(profesion);
    }
}
