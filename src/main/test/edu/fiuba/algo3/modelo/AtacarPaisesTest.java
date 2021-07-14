package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtacarPaisesTest {
    //argentina es de jugador rojo, brasil de otro jugador
    Jugador jugadorUno = new Jugador("rojo");

    Pais argentina = new Pais("Argentina", "America", "Brasil");
    Pais brasil = new Pais("Brasil", "America", "Argentina");

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacanteYDefensaConMaximoDeDados() throws Exception {

        brasil.ejercitos(5);
        argentina.ejercitos(5);

        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);

        assertEquals((argentina.ejercitos() < 5) || (brasil.ejercitos() < 5), true);
    }

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacante3DadosDefensa2Dados() throws Exception {

        brasil.ejercitos(2);
        argentina.ejercitos(5);

        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);
        // Agregar set up
        juego.verificarConquista(jugadorUno, argentina, brasil);


        assertEquals((argentina.ejercitos()<5) || (brasil.ejercitos()<5),true);
    }
}