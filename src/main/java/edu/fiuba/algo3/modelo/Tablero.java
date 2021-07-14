package edu.fiuba.algo3.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.stream.JsonReader;

public class Tablero {

    private ArrayList<Pais> paises;

    public Tablero(ArrayList<Jugador> jugadores) throws FileNotFoundException {
        this.inicializarPaises();
        this.hacerRandomElOrdenDePaises();
        this.asignarPaises(jugadores);
    }

    private void inicializarPaises() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("archivos/paises-fronteras.json"));
        this.paises = new PaisDeserializer(reader).getPaises();
    }

    private void hacerRandomElOrdenDePaises() {
        ArrayList<Pais> nuevosPaises = new ArrayList<>();
        Random random = new Random();
        for (int i = 49; i > 0; i--) {
            int value = random.nextInt(i);
            Pais pais = paises.get(value);
            nuevosPaises.add(pais);
            paises.remove(value);
        }
        Pais pais = paises.get(0);
        nuevosPaises.add(pais);
        this.paises = nuevosPaises;
    }

    public void asignarPaises(ArrayList<Jugador> jugadores) {
        int numeroJugador = 0;
        int cantidadJugadores = jugadores.size();
        for (Pais pais : paises) {
            Jugador jugadorActual = jugadores.get(numeroJugador % cantidadJugadores);
            jugadorActual.agregarPais(pais);
            numeroJugador++;
        }
    }

    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }
}