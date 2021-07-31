package edu.fiuba.algo3.objetivos;

import java.util.ArrayList;

public class Objetivo {
    private String descripcion;

    public Objetivo(String descripcion, ArrayList<ObjetivoContinente> continentes) {
        this.descripcion = descripcion;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
}