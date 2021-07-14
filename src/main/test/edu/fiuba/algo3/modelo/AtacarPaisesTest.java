package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AtacarPaisesTest {
    //argentina es de jugador rojo, brasil de otro jugador
    Jugador jugadorUno = new Jugador("rojo");

    Pais argentina = new Pais("Argentina", "America", "Brasil");
    Pais brasil = new Pais("Brasil", "America", "Argentina");

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacanteYDefensaConMaximoDeDados() throws Exception {

        brasil.ejercitos(5);
        argentina.ejercitos(5);
        jugadorUno.agregarPais(argentina);
        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);

        assertEquals((argentina.ejercitos() < 5) || (brasil.ejercitos() < 5), true);
    }

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosAtacante3DadosDefensa2Dados() throws Exception {

        brasil.ejercitos(2);
        argentina.ejercitos(5);
        jugadorUno.agregarPais(argentina);
        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);


        assertEquals((argentina.ejercitos()<5) || (brasil.ejercitos()<5),true);
    }

    @Test
    public void paisAAtacaAPaisBYGanaConquistaElPais() throws Exception{
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Verde");

        Pais argentina = new Pais("Argentina", "America", "Brasil");
        Pais brasil = new Pais("Brasil", "America", "Argentina");

        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarPais(argentina);
        jugadorUno.agregarEjercitos(argentina,20);

        // TODO: Mockear
        boolean detenerAtaque = false;
        while (!detenerAtaque) {
            jugadorUno.atacarConA(argentina, brasil, jugadorDos);
            if (jugadorUno.tieneElPais(brasil)) {
                detenerAtaque = true;
            }
        }

        assertEquals(jugadorUno.tieneElPais(brasil), true);
        assertEquals(jugadorDos.tieneElPais(brasil), false);
    }


    @Test
    public void paisAAtacaAPaisBYGanaLaDefensa() throws Exception{
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Verde");

        Pais argentina = new Pais("Argentina", "America", "Brasil");
        Pais brasil = new Pais("Brasil", "America", "Argentina");

        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarPais(argentina);
        jugadorUno.agregarEjercitos(argentina,1);
        jugadorDos.agregarEjercitos(brasil, 20);

        // TODO: Mockear
        boolean detenerDefensa = false;
        while (!detenerDefensa) {
            jugadorUno.atacarConA(argentina, brasil, jugadorDos);
            if(argentina.ejercitos() == 1){
                Exception exception = assertThrows(Exception.class, () -> { jugadorUno.atacarConA(argentina, brasil, jugadorDos); });
                detenerDefensa = true;
            }
        }

        assertEquals(jugadorUno.tieneElPais(brasil), false);
        assertEquals(jugadorDos.tieneElPais(brasil), true);
    }

}