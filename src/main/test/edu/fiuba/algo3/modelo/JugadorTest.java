package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void inicioUnJugadorConColor() {
        Jugador unJugador = new Jugador("Azul");
        assertEquals(unJugador.asignarColor(),"Azul");
    }

    @Test
    public void agregarPaisAJugador() {
        Jugador unJugador = new Jugador("Azul");
        Pais pais = new Pais("Argentina", "America", "Uruguay,Paraguay");
        unJugador.agregarPais(pais);
        ArrayList<Pais> paisesJugador = unJugador.obtenerPaises();
        assertEquals(paisesJugador.contains(pais), true);
    }

    @Test
    public void agregarUnEjercitoAUnPais() {
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        jugador.agregarEjercitos(pais, 1);
        assertEquals(jugador.obtenerEjercitos(pais), 2);
    }

    @Test
    public void validarCantidadEjercitos() throws Exception {
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        jugador.agregarEjercitos(pais, 1);
        jugador.validarCantidadEjercitos(1);
    }

    @Test
    public void validarCantidadEjercitosFalla() throws Exception {
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        jugador.agregarEjercitos(pais, 1);
        Exception exception = assertThrows(Exception.class, () -> { jugador.validarCantidadEjercitos(0); });

        String expectedMessage = "Cantidad de ejercitos incorrecta";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}