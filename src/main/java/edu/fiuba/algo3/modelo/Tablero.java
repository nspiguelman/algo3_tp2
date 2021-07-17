package edu.fiuba.algo3.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.stream.JsonReader;

public class Tablero {

    private ArrayList<Pais> paises;
    private ArrayList<TarjetaPais> tarjetasPaises;

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
        ArrayList<String> simbolos = new ArrayList<>();
        ArrayList<TarjetaPais> nuevasTarjetas = new ArrayList<>();
        Random random = new Random();

        simbolos.add("barco");
        simbolos.add("canon");
        simbolos.add("globo");

        for (int i = 49; i > 0; i--) {
            int value = random.nextInt(i);
            Pais pais = paises.get(value);
            nuevosPaises.add(pais);
            TarjetaPais tarjeta = new TarjetaPais(pais.obtenerNombrePais(), simbolos.get(i % 3));
            nuevasTarjetas.add(tarjeta);

            paises.remove(value);
        }
        Pais pais = paises.get(0);
        nuevosPaises.add(pais);
        this.paises = nuevosPaises;
        this.tarjetasPaises = nuevasTarjetas;
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