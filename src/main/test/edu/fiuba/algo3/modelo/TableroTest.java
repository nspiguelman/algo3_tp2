package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TableroTest {
    @Test
    public void tableroTiene50Paises() throws FileNotFoundException {
        try {
            Tablero tablero = new Tablero(new ArrayList<Jugador>());
            assertEquals(tablero.obtenerPaises().size(), 50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void tableroAsignaPaisesAJugador() {
        try {
            ArrayList<Jugador> jugadores = new ArrayList<>();
            Jugador jugador = new Jugador("Rojo");
            jugadores.add(jugador);

            Tablero tablero = new Tablero(jugadores);
            assertEquals(jugador.paises(), 50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
