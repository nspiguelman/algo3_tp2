package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.fase.FaseUnoColocacionEjercitos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaisTest {
    @Test
    public void inicioUnPaisConNombreContinenteYLimitrofes() {
        Pais pais = new Pais("Argentina", "America", "a,b,c");
        assertEquals(pais.obtenerEjercitos(), 1);
        assertEquals(pais.obtenerNombrePais(), "Argentina");
        assertEquals(pais.obtenerNombreContinente(), "America");
        String[] limitrofes = { "a", "b", "c" };
        assertArrayEquals(pais.obtenerNombrePaisesLimitrofes(), limitrofes);
        pais.agregarEjercitos(5);
        assertEquals(pais.obtenerEjercitos(), 6);
    }

    @Test
    public void inicioUnPaisConNombreContinenteYLimitrofe() {
        Pais pais = new Pais("Argentina", "America", "a");
        assertEquals(pais.obtenerEjercitos(), 1);
        assertEquals(pais.obtenerNombrePais(), "Argentina");
        assertEquals(pais.obtenerNombreContinente(), "America");
        String[] limitrofes = { "a" };
        assertArrayEquals(pais.obtenerNombrePaisesLimitrofes(), limitrofes);
    }
}
