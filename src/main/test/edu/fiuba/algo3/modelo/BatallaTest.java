package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
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
        jugadorUno.agregarEjercitos(argentina, 10);
        jugadorUno.elegirPais(argentina);
        jugadorDos.elegirPais(brasil);
        Batalla unaBatalla = new Batalla();

        while (!jugadorUno.tieneElPais(brasil)) {
            unaBatalla.batallar(jugadorUno, jugadorDos);
        }

        assertTrue(jugadorUno.tieneElPais(brasil));
    }


    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosConAtacante3DadosDefensa2Dados() throws Exception {
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina, 10);
        jugadorUno.agregarEjercitos(brasil, 2);
        jugadorUno.elegirPais(argentina);
        jugadorDos.elegirPais(brasil);

        Batalla unaBatalla = new Batalla();

        while (!jugadorUno.tieneElPais(brasil)) {
            unaBatalla.batallar(jugadorUno, jugadorDos);
        }
        assertTrue(jugadorUno.tieneElPais(brasil));
        assertFalse(jugadorDos.tieneElPais(brasil));
    }
}