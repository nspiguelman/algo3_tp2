package edu.fiuba.algo3.continente;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class ContinenteTest {

    @Test
    public void contstructorContinenteDevuelveUnContinenteValido() {
        Continente unContinente = new Continente("Continente",10,12);
        assertEquals("Continente", unContinente.obtenerNombreContinente());
    }
}
