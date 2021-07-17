/*package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.fase.FaseDeJuego;
import org.junit.jupiter.api.Test;

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
        jugadorUno.agregarEjercitos(argentina,5, new FaseDeJuego());
        jugadorUno.agregarEjercitos(brasil,5, new FaseDeJuego());

        jugadorUno.atacarConA(argentina, brasil, jugadorDos);

        assertEquals((argentina.obtenerEjercitos() < 5) || (brasil.obtenerEjercitos() < 5), true);
    }

    @Test
    public void UnJugadorAtacaYHayPerdidaDeEjercitosConAtacante3DadosDefensa2Dados() throws Exception {
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina,3, new FaseDeJuego());
        jugadorUno.agregarEjercitos(brasil,2, new FaseDeJuego());

        jugadorUno.atacarConA(argentina, brasil, jugadorDos);

        assertEquals((argentina.obtenerEjercitos() < 3) || (brasil.obtenerEjercitos() < 2), true);
    }

    @Test
    public void paisAAtacaAPaisBYGanaConquistaElPais() throws Exception{
        jugadorUno.agregarPais(argentina);
        jugadorDos.agregarPais(brasil);
        jugadorUno.agregarEjercitos(argentina,20, new FaseDeJuego());

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
        jugadorUno.agregarEjercitos(argentina,1, new FaseDeJuego());
        jugadorDos.agregarEjercitos(brasil, 20, new FaseDeJuego());

        // TODO: Mockear
        boolean detenerDefensa = false;
        while (!detenerDefensa) {
            jugadorUno.atacarConA(argentina, brasil, jugadorDos);
            if(argentina.obtenerEjercitos() == 1){
                Exception exception = assertThrows(Exception.class, () -> { jugadorUno.atacarConA(argentina, brasil, jugadorDos); });
                detenerDefensa = true;
            }
        }

        assertEquals(jugadorUno.tieneElPais(brasil), false);
        assertEquals(jugadorDos.tieneElPais(brasil), true);
    }

}*/