package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;

public class TableroTest {

    @Test
    public void tableroTiene50Paises() throws FileNotFoundException {
        Tablero tablero = new Tablero();
        assertEquals(tablero.obtenerPaises().size(), 50);
    }

    @Test
    public void tableroTieneJugador() {
        try {
            Tablero tablero = new Tablero();
            Jugador jugadorAzul = new Jugador("Azul");
            tablero.asignarJugador(jugadorAzul);
            assertEquals(tablero.obtenerJugadores().size(),1);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void tableroAsignaDosJugadoresMismoColorFalla() {
        try {
            Tablero tablero = new Tablero();
            Jugador jugadorAzul = new Jugador("azul");
            Jugador jugadorAzulDos = new Jugador("azul");
            tablero.asignarJugador(jugadorAzul);
            tablero.asignarJugador(jugadorAzulDos);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No se puede asignar el mismo color a dos jugadores");
        }
    }
    
    @Test
    public void tableroAsignarDosVecesMismoJugadorFalla() {
        try {
            Tablero tablero = new Tablero();
            Jugador jugadorAzul = new Jugador("azul");
            tablero.asignarJugador(jugadorAzul);
            tablero.asignarJugador(jugadorAzul);

        } catch (Exception e) {
            assertEquals(e.getMessage(), "No se puede asignar el mismo color a dos jugadores");
        }
    }
}
