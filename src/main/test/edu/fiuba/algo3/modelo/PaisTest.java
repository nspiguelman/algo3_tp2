package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaisTest {
    @Test
    public void inicioUnPaisConNombreContinenteYLimitrofes() {
        Pais pais = new Pais("Argentina", "America", "a,b,c");
        assertEquals(pais.ejercitos(), 0);
        assertEquals(pais.pais(), "Argentina");
        assertEquals(pais.continente(), "America");
        String[] limitrofes = { "a", "b", "c" };
        assertArrayEquals(pais.limitrofes(), limitrofes);
        pais.ejercitos(5);
        assertEquals(pais.ejercitos(), 5);
    }

    @Test
    public void inicioUnPaisConNombreContinenteYLimitrofe() {
        Pais pais = new Pais("Argentina", "America", "a");
        assertEquals(pais.ejercitos(), 0);
        assertEquals(pais.pais(), "Argentina");
        assertEquals(pais.continente(), "America");
        String[] limitrofes = { "a" };
        assertArrayEquals(pais.limitrofes(), limitrofes);
    }
}
