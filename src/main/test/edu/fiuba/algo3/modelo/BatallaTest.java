package edu.fiuba.algo3.modelo;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatallaTest {
    //argentina es de jugador rojo, brasil de otro jugador
    Pais argentina = new Pais("Argentina", "America", "Brasil");
    Pais brasil = new Pais("Brasil", "America", "Argentina");

    Jugador jugadorUno = new Jugador("Rojo");
    Jugador jugadorDos = new Jugador("Verde");

    @Test
    public void UnJugadorAtacaYOtroJugadorDefiendeAmbosCon3DadosYHayPerdidaDeEjercitos() throws Exception {
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina, 10);
        jugadorUno.elegirPais(argentina);
        jugadorDos.elegirPais(brasil);
        Batalla unaBatalla = new Batalla();

        while(!jugadorUno.tieneElPais(brasil)) {
            unaBatalla.batallar(jugadorUno, jugadorDos);
        }

        assertEquals(jugadorUno.tieneElPais(brasil), true);
    }

}