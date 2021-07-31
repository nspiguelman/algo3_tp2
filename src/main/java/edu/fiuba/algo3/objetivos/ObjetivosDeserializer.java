package edu.fiuba.algo3.objetivos;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObjetivosDeserializer {
    private JsonReader reader;

    @SerializedName("Descripcion") private String descripcion;
    @SerializedName("Objetivo") private ArrayList<TipoObjetivoDeserializer> objetivo;
    @SerializedName("Tipo") private String tipo;

    public ObjetivosDeserializer(JsonReader reader) {
        this.reader = reader;
    }

    private String obtenerDescripcion() { return descripcion; }
    private ArrayList<TipoObjetivoDeserializer> obtenerObjetivo() { return objetivo; }
    private String obtenerTipo() { return tipo; }

    public ArrayList<Objetivo> getObjetivos() {
        ArrayList<Objetivo> objetivosADevolver = new ArrayList<>();
        Type userListType = new TypeToken<ArrayList<ObjetivosDeserializer>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<ObjetivosDeserializer> objetivos = gson.fromJson(reader, userListType);
        for (ObjetivosDeserializer objetivo : objetivos) {
            objetivosADevolver.add(new Objetivo(objetivo.obtenerDescripcion(), objetivo.obtenerTipo(), objetivo.obtenerObjetivo()));
        }
        return objetivosADevolver;
    }
}
