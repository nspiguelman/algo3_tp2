package edu.fiuba.algo3.objetivos;

import java.util.ArrayList;

public class Objetivo {
    private String descripcion;
    private ArrayList<ObjetivoContinente> continentes;

    public Objetivo(String descripcion, ArrayList<ObjetivoContinente> continentes) {
        this.descripcion = descripcion;
        this.continentes = continentes;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
    public ArrayList<ObjetivoContinente> obtenerContinentes() { return continentes; }
    public String obtenerContinente(ObjetivoContinente continente) { return continente.obtenerContinente(); }
    public int obtenerCantidadDePaises(ObjetivoContinente continente) { return continente.obtenerCantidadDePaises(); }
}