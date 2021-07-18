package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.fase.FaseDeJuego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        jugadorUno.agregarEjercitos(argentina, 10, new FaseDeJuego());
        jugadorUno.elegirPais(argentina);
        jugadorDos.elegirPais(brasil);
        Batalla unaBatalla = new Batalla();

        while (!jugadorUno.tieneElPais(brasil)) {
            unaBatalla.batallar(jugadorUno, jugadorDos);
        }

        assertEquals(jugadorUno.tieneElPais(brasil), true);
    }

}