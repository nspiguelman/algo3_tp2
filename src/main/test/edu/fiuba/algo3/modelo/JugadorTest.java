package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class JugadorTest {

    @Test
    public void inicioUnJugadorConColor() {
        Jugador unJugador = new Jugador("Azul");
        assertEquals(unJugador.color(),"Azul");
    }
    @Test
    public void agregarPaisAJugador() {
        Jugador unJugador = new Jugador("Azul");
        Pais pais = new Pais("Argentina", "America", "Uruguay,Paraguay");
        unJugador.pais(pais);
        ArrayList<Pais> paisesJugador = unJugador.paises();
        assertEquals(paisesJugador.contains(pais), true);
    }

    @Test
    public void agregarUnEjercitoAUnPais() {
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.pais(pais);
        jugador.ejercito(pais);
        assertEquals(jugador.ejercitos(pais), 1);
    }
}