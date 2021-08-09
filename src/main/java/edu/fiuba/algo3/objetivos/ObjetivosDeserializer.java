package edu.fiuba.algo3.objetivos;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.modelo.Jugador;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjetivosDeserializer {
    @SerializedName("Descripcion") private String descripcion;
    @SerializedName("Tipo") private String tipo;
    @SerializedName("Objetivos") private ArrayList<TipoObjetivoDeserializer> objetivo;

    private String obtenerDescripcion() { return descripcion; }
    private ArrayList<TipoObjetivoDeserializer> obtenerObjetivo() { return objetivo; }
    private String obtenerTipo() { return tipo; }

    public static ArrayList<Objetivo> deserializarObjetivos(JsonReader reader, ArrayList<Jugador> jugadores) {
        ArrayList<Objetivo> nuevosObjetivos = new ArrayList<>();

        Type objetivosType = new TypeToken<ArrayList<ObjetivosDeserializer>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<ObjetivosDeserializer> objetivos = gson.fromJson(reader, objetivosType);
         for (ObjetivosDeserializer objetivo : objetivos) {
            Objetivo nuevoObjetivo = new Objetivo(
                    objetivo.obtenerDescripcion(),
                    objetivo.obtenerTipo(),
                    objetivo.obtenerObjetivo(),
                    jugadores
            );
            nuevosObjetivos.add(nuevoObjetivo);
        }
        return nuevosObjetivos;
    }
}
