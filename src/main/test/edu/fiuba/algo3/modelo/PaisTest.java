package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaisTest {
    @Test
    public void colocarEjercitos() {
        Pais pais = new Pais();
        pais.colocarEjercitos(5);
        assertEquals(pais.cantidadEjercitos(), 5);
    }
}
