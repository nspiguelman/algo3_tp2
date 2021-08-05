package edu.fiuba.algo3.paises;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PaisDeserializer {
    @SerializedName("Limita con") private String limitaCon;
    @SerializedName("Pais") private String nombre;
    @SerializedName("Continente") private String continente;

    private String obtenerPais() { return nombre; }
    private String obtenerContinente() { return continente; }
    private String obtenerPaisesLimitrofes() { return limitaCon; }

    public static ArrayList<Pais> deserializarPaises(JsonReader reader) {
        ArrayList<Pais> nuevosPaises = new ArrayList<>();

        Type paisesType = new TypeToken<ArrayList<PaisDeserializer>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<PaisDeserializer> paises = gson.fromJson(reader, paisesType);

        for (PaisDeserializer pais : paises) {
            Pais nuevoPais = new Pais(pais.obtenerPais(), pais.obtenerContinente(), pais.obtenerPaisesLimitrofes());
            nuevosPaises.add(nuevoPais);
        }

        return nuevosPaises;
    }
}