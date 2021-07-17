package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.TegException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TableroTest {
    @Test
    public void tableroTiene50Paises() throws FileNotFoundException {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Rojo"));
        Tablero tablero = new Tablero(jugadores);
        assertEquals(tablero.obtenerPaises().size(), 50);
    }

    @Test
    public void tableroAsignaPaisesAJugador() throws FileNotFoundException {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador("Rojo");
        jugadores.add(jugador);

        Tablero tablero = new Tablero(jugadores);
        assertEquals(jugador.obtenerPaises().size(), 50);
    }
}
