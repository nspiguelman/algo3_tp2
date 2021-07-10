package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Tablero {

    private final ArrayList<Pais> paises;

    public Tablero() {
        this.inicializarPaises();
    }

    private void inicializarPaises() {
        
    }

    public ArrayList<Pais> obtenerPaises () {
        return paises;
    }
}
