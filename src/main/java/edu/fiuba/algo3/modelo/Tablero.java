package edu.fiuba.algo3.modelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.continente.ContinenteDeserializer;
import edu.fiuba.algo3.helper.Helper;
import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.objetivos.Objetivo;
import edu.fiuba.algo3.objetivos.ObjetivosDeserializer;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisDeserializer;

public class Tablero {
    private final int CANTIDAD_DE_PAISES = 49;

    private ArrayList<TarjetaPais> tarjetasPaises;
    private ArrayList<Pais> paises;
    private ArrayList<Objetivo> objetivos;
    private ArrayList<Continente> continentes;

    public Tablero(ArrayList<Jugador> jugadores) throws FileNotFoundException {
        this.inicializarPaises();
        this.hacerRandomElOrdenDePaises();
        this.asignarPaises(jugadores);
        this.inicializarContinentes();
        this.inicializarObjetivos(jugadores);
        this.asignarObjetivos(jugadores);
    }

    private void asignarObjetivos(ArrayList<Jugador> jugadores) {
        Random random = new Random();
        for (Jugador jugador: jugadores){
            jugador.asignarTablero(this);
            int value = random.nextInt(13);
            jugador.asignarObjetivo(objetivos.get(value));
        }
    }

    private void inicializarContinentes() throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/continentes.json");
        this.continentes = ContinenteDeserializer.deserializarContinentes(reader);
    }

    private void inicializarObjetivos (ArrayList<Jugador> jugadores) throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/objetivos.json");
        this.objetivos = ObjetivosDeserializer.deserializarObjetivos(reader, jugadores);
    }

    private void inicializarPaises() throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/paises-fronteras.json");
        this.paises = PaisDeserializer.deserializarPaises(reader);
    }

    private void hacerRandomElOrdenDePaises() {
        ArrayList<Pais> nuevosPaises = new ArrayList<>();
        ArrayList<String> simbolos = new ArrayList<>();
        ArrayList<TarjetaPais> nuevasTarjetas = new ArrayList<>();
        Random random = new Random();

        simbolos.add("barco");
        simbolos.add("canon");
        simbolos.add("globo");

        for (int i = CANTIDAD_DE_PAISES; i > 0; i--) {
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

    public int obtenerExtrasDeJugador(Jugador jugador) {
        int extras = 0;
        for (Continente continente: continentes){
            extras += continente.obtenerExtras(jugador);
        }
        return extras;
    }
}