package edu.fiuba.algo3.paises;

import edu.fiuba.algo3.excepciones.PaisNoEstaEnBatallaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaisEnPazTest {

    @Test
    public void matarEjercitosEnPaisEnPazArrojaExcepcion() {
        Pais pais = new Pais("Argentina", "America", "");
        PaisEnPaz estado = new PaisEnPaz(pais);

        Exception exception = assertThrows(PaisNoEstaEnBatallaException.class, () -> { estado.matarEjercitos(new Pais("Uruguay", "America", ""), 10); });

        String expectedMessage = "No se puede matar ejércitos de un Pais en paz.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cambiarEstadoDeBatallaCorrectamente() {
        Pais pais = new Pais("Argentina", "America", "");
        PaisEnPaz estado = new PaisEnPaz(pais);

        PaisEnBatalla nuevoEstado = estado.cambiarEstadoDeBatalla();
        assertTrue(nuevoEstado instanceof PaisEnBatalla);
    }
}
