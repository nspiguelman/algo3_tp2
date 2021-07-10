package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void tableroTienePaises() {
        Tablero tablero = new Tablero();
        tablero.obtenerPaises();
    }
}
