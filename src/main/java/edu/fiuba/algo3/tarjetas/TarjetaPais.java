package edu.fiuba.algo3.tarjetas;

import edu.fiuba.algo3.paises.Pais;

public class TarjetaPais {
    private String nombre;
    private String simbolo;

    public TarjetaPais(String nombrePais, String unSimbolo){
        this.nombre = nombrePais;
        this.simbolo = unSimbolo;
    }

    public String obtenerSimbolo() {
        return this.simbolo;
    }

    public boolean esDelPais(Pais unPais){
        return this.nombre.equals(unPais.obtenerNombrePais());
    }
}
