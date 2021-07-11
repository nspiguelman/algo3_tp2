package edu.fiuba.algo3.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Tablero {

    private ArrayList<Pais> paises;
    private ArrayList<Jugador> jugadores;

    public Tablero() throws FileNotFoundException {
        this.inicializarPaises();
        this.jugadores = new ArrayList<>();
    }

    private void inicializarPaises() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("archivos/paises-fronteras.json"));
        this.paises = new PaisDeserializer(reader).getPaises();
    }

    public ArrayList<Pais> obtenerPaises () {
        return paises;
    }

    public void asignarJugador(Jugador unJugador) throws Exception {
        // TODO: pasarlo a otra clase, investigar como nombrar las clases de validación de inputs
        for (Jugador jugador : jugadores) {
            if (jugador.color() == unJugador.color()) {
                throw new Exception("No se puede asignar el mismo color a dos jugadores");
            }
        }
        jugadores.add(unJugador);
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }
}
