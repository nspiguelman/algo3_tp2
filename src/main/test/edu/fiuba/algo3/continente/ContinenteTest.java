package edu.fiuba.algo3.continente;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class ContinenteTest {

    @Test
    public void contstructorContinenteDevuelveUnContinenteValido() {
        Continente unContinente = new Continente("Continente",10,12);
        assertEquals("Continente", unContinente.obtenerNombreContinente());
        assertEquals(10, unContinente.obtenerCantidadDePaises());
    }

    @Test
    public void esConquistadoPorDevuelveTrue() {
        Continente america = new Continente("America",1,12);
        Jugador unJugador = new Jugador("Azul");
        Pais pais = new Pais("Argentina", "America", "Uruguay,Paraguay");
        unJugador.agregarPais(pais);
        assertEquals(true, america.esConquistadoPor(unJugador));
    }
}
