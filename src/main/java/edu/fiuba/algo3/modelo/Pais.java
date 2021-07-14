package edu.fiuba.algo3.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Pais {
    private String[] limitrofes;
    private String nombre;
    private String continente;
    private int cantidadEjercitos;

    public Pais(String nombre, String continente, String limitaCon){
        this.nombre = nombre;
        this.limitrofes = limitaCon.split(",");
        this.continente = continente;
        this.cantidadEjercitos = 1;
    }

    public String pais() {
        return this.nombre;
    }
    public String continente() {
        return this.continente;
    }
    public String[] limitrofes() {
        return this.limitrofes;
    }

    public void ejercitos(int ejercitos) {
        this.cantidadEjercitos = ejercitos;
    }

    public int ejercitos() {
        return this.cantidadEjercitos;
    }
    public void matarEjercito(int ejercitos){
        this.cantidadEjercitos = this.cantidadEjercitos - ejercitos;
    }

    public void limitaCon(Pais unPais) throws Exception {
        for (String elemento: limitrofes){
            if (unPais.obtenerNombre().equals(elemento)) {
                return;
            }
        }
        throw new Exception("Los paises no son limitrofes");
    }

    private String obtenerNombre() {
        return nombre;
    }
    //public boolean esAdyacente(Pais unPais){ return true;}
    //public void defiende(Dados unosDados){}
}