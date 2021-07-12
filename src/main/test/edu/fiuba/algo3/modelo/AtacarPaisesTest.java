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
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacanteYDefensaConMaximoDeDados() {

        brasil.ejercitos(5);
        argentina.ejercitos(5);

        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);

        assertEquals((argentina.ejercitos() < 5) || (brasil.ejercitos() < 5), true);
    }

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacante3DadosDefensa2Dados() {

        brasil.ejercitos(2);
        argentina.ejercitos(5);

        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);

        assertEquals((argentina.ejercitos()<5) || (brasil.ejercitos()<5),true);


    }
}
