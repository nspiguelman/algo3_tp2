package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AtacarPaisesTest {
    //argentina es de jugador rojo, brasil de otro jugador
    Pais argentina = new Pais("Argentina", "America", "Brasil");
    Pais brasil = new Pais("Brasil", "America", "Argentina");

    Jugador jugadorUno = new Jugador("Rojo");
    Jugador jugadorDos = new Jugador("Verde");


    @Test
    public void UnJugadorAtacaYOtroJugadorDefiendeAmbosCon3DadosYHayPerdidaDeEjercitos() throws Exception {
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina,5);
        jugadorUno.agregarEjercitos(brasil,5);

        jugadorUno.atacarConA(argentina, brasil, jugadorDos);

        assertEquals((argentina.ejercitos() < 5) || (brasil.ejercitos() < 5), true);
    }

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosConAtacante3DadosDefensa2Dados() throws Exception {
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina,3);
        jugadorUno.agregarEjercitos(brasil,2);

        jugadorUno.atacarConA(argentina, brasil, jugadorDos);

        assertEquals((argentina.ejercitos() < 3) || (brasil.ejercitos() < 2), true);
    }

    @Test
    public void paisAAtacaAPaisBYGanaConquistaElPais() throws Exception{
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
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
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
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