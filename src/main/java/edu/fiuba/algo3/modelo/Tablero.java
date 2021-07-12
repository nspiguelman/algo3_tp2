package edu.fiuba.algo3.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.lang.Integer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Tablero {

    private ArrayList<Pais> paises;

    public Tablero(ArrayList<Jugador> jugadores) throws FileNotFoundException {
        this.inicializarPaises();
        this.asignarPaises(jugadores);
    }

    private HashMap<Integer,Pais> inicializarMapaDePaises(ArrayList<Pais> paises) {
        HashMap<Integer,Pais> mapaDePaises = new HashMap<Integer,Pais>();
        Integer contador = 1;
        for (Pais unPais : paises) {
            mapaDePaises.put(contador, unPais);
            contador++;
        }
        return mapaDePaises;
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
        /*for (Jugador jugador : jugadores) {
            if (jugador.color() == unJugador.color()) {
                throw new Exception("No se puede asignar el mismo color a dos jugadores");
            }
        }
        jugadores.add(unJugador);
         */
    }

    public ArrayList<Jugador> obtenerJugadores() {
       /* return jugadores;*/
    }

    public void asignarPaises(ArrayList<Jugador> jugadores) {
        HashMap<Integer,Pais> hashDePaises= this.inicializarMapaDePaises(this.paises);
        ArrayList<Integer> contador = new ArrayList<Integer>();
        for (Integer i; i < 50; i++) {
            contador.add(i);
        }

        Collections.shuffle(contador);
        Integer numeroJugador = 1;

        for (Integer paisesRestantes = 50; paisesRestantes > 0; paisesRestantes--){
            Pais unPais = hashDePaises.get(contador[paisesRestantes]);
            jugadores[numeroJugador].asignarPais(unPais);
            if (numeroJugador == 6) {
                numeroJugador = 1;
            }
            else{
                numeroJugador++;
            }
            paisesRestantes--;
        }
    }
}