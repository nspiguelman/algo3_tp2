package edu.fiuba.algo3.modelo;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PaisDeserializer {
    private JsonReader reader;

    @SerializedName("Limita con") private String limitaCon;
    @SerializedName("Pais") private String nombre;
    @SerializedName("Continente") private String continente;

    public PaisDeserializer(JsonReader reader) {
        this.reader = reader;
    }

    private String pais() { return nombre; }
    private String continente() { return continente; }
    private String limitrofes() { return limitaCon; }

    public ArrayList<Pais> getPaises() {
        ArrayList<Pais> paisesADevolver = new ArrayList<>();
        Type userListType = new TypeToken<ArrayList<PaisDeserializer>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<PaisDeserializer> paises = gson.fromJson(reader, userListType);
        for (PaisDeserializer pais : paises) {
            paisesADevolver.add(new Pais(pais.pais(), pais.continente(), pais.limitrofes()));
        }
        return paisesADevolver;
    }
}