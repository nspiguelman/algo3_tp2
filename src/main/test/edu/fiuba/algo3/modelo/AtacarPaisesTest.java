package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtacarPaisesTest {
    @Test
    public void UnJugadorAtacaYGanaLaDefensa() {
        // argentina es de jugador rojo, brasil de otro jugador
        Jugador jugadorUno = new Jugador(rojo);
        Pais argentina = new Pais();
        Pais brasil = new Pais();

        jugadorUno.elegirPais(argentina);
        jugadorUno.atacarA(brasil);

        assertEquals(jugadorUno.puedeAtacar(),false);

    }
}
