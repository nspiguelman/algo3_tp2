package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JuegoTest {
    @Test
    public void juegoFaseAsignacionPaises() {
        ArrayList<String> coloresJugadores = new ArrayList<String>{"Rojo", "Azul", "Amarillo", "Violeta", "Verde"};
        Juego juego = new Juego(coloresJugadores);

        assertEquals(juego.jugadores().size(), 5);

    }
}