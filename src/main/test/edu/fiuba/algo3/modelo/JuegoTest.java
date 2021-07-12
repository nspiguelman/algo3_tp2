package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JuegoTest {
    @Test
    public void juegoFaseAsignacionPaises() {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Rojo");
        coloresJugadores.add("Azul");
        coloresJugadores.add("Amarillo");
        coloresJugadores.add("Violeta");
        coloresJugadores.add("Verde");

        try {
            Juego juego = new Juego(coloresJugadores);
            ArrayList<Jugador> jugadores = juego.jugadores();
            assertEquals(jugadores.size(), 5);
            for (Jugador jugador : jugadores) {
                assertEquals(jugador.paises(), 10);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void primerFaseAsignarEjercitosAPaisesExitosamente() {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        try {
            Juego juego = new Juego(coloresJugadores);
            ArrayList<Jugador> jugadores = juego.jugadores();
            for (Jugador jugador : jugadores) {
                Random random = new Random();
                int numeroRandomPaisASeleccionar = random.nextInt(24);
                ArrayList<Pais> paisesDeJugador = juego.obtenerPaisesDeJugador(jugador);
                Pais paisAAgregarEjercitos = paisesDeJugador.get(numeroRandomPaisASeleccionar);
                juego.asignarEjercitoAJugador(jugador, paisAAgregarEjercitos);
                assertEquals(paisAAgregarEjercitos.ejercitos(), 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void tableroAsignaDosJugadoresMismoColorFalla() {
        try {
            ArrayList<String> coloresJugadores = new ArrayList<>();
            coloresJugadores.add("Azul");
            coloresJugadores.add("Azul");
            new Juego(coloresJugadores);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No se puede asignar el mismo color a dos jugadores");
        }
    }
}