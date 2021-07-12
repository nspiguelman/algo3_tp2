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
        this.cantidadEjercitos = 0;
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

    //public void atacar(Pais defensor){
        /*Dados dado = new Dados();
        this.esAdyacente();
        defensor.defiende(dado);
        ArrayList<> dadosActuales = dado.atacarConEjercitos(cantidadEjercitos)
         */
    //}

    //public boolean esAdyacente(Pais unPais){ return true;}
    //public void defiende(Dados unosDados){}
}