package edu.fiuba.algo3.continente;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.objetivos.Objetivo;
import edu.fiuba.algo3.objetivos.ObjetivosDeserializer;
import edu.fiuba.algo3.objetivos.TipoObjetivoDeserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ContinenteDeserializer {
    @SerializedName("Continente") private String continente;
    @SerializedName("CantidadPaises") private int cantidadDePaises;
    @SerializedName("FichasExtra") private int cantidadDeFichasExtra;

    private String obtenerContinente() { return continente; }
    private int obtenerCantidadDePaises() { return cantidadDePaises; }
    private int obtenerCantidadDeFichasExtra() { return cantidadDeFichasExtra; }

    public static ArrayList<Continente> deserializarContinentes(JsonReader reader) {
        ArrayList<Continente> continentes = new ArrayList<>();

        Type continenteType = new TypeToken<ArrayList<ContinenteDeserializer>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<ContinenteDeserializer> continentesDeserializados = gson.fromJson(reader, continenteType);

        continentesDeserializados.stream().forEach(continenteDeserializado -> {
            Continente unContinente = new Continente(continenteDeserializado.obtenerContinente(), continenteDeserializado.obtenerCantidadDePaises(), continenteDeserializado.obtenerCantidadDeFichasExtra());
            continentes.add(unContinente);
        });

        return continentes;
    }

}
