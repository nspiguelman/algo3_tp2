package edu.fiuba.algo3.objetivos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TipoObjetivoDeserializer {
    @SerializedName("Continente") private String continente;
    @SerializedName("CantidadDePaises") private int cantidadDePaises;
    @SerializedName("Color") private String color;

    public TipoObjetivoDeserializer() {}

    public String obtenerContinente() { return continente; }
    public int obtenerCantidadDePaises() { return cantidadDePaises; }
    public String obtenerColor() { return color; }
}