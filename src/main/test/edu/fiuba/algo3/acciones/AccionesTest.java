package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.PaisNoEstaEnBatallaException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccionesTest {

    @Test
    public void obtenerNumeroAccionCorrecto() {
        Atacar atacar = new Atacar();
        Reagrupar reagrupar = new Reagrupar();
        Colocar colocar = new Colocar();
        assertTrue(atacar.numeroAccion() == 1);
        assertTrue(reagrupar.numeroAccion() == 2);
        assertTrue(colocar.numeroAccion() == 3);
    }

    @Test
    public void validarEstaEnAtacarEsCorrecto() throws AccionesException {
        Atacar accion = new Atacar();
        accion.estaEnAtacar();
    }

    @Test
    public void validarEstaEnReagruparFalla() throws AccionesException {
        Atacar accion = new Atacar();
        Exception exception = assertThrows(AccionesException.class, () -> { accion.estaEnReagrupar(); });

        String expectedMessage = "No está en reagrupar";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validarEstaEnColocarFalla() throws AccionesException {
        Atacar accion = new Atacar();
        Exception exception = assertThrows(AccionesException.class, () -> { accion.estaEnColocar(); });

        String expectedMessage = "No está en colocar";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void atacarSiguienteAccionEsCorrecto() throws AccionesException {
        Atacar atacar = new Atacar();
        Reagrupar reagrupar = atacar.siguienteAccion(new Jugador(""));

        Exception exceptionPrimera = assertThrows(AccionesException.class, () -> { reagrupar.estaEnColocar(); });
        assertTrue(exceptionPrimera.getMessage().contains("No está en colocar"));

        Exception exceptionSegunda = assertThrows(AccionesException.class, () -> { reagrupar.estaEnAtacar(); });
        assertTrue(exceptionSegunda.getMessage().contains("No está en atacar"));

        reagrupar.estaEnReagrupar();
    }

    @Test
    public void reagruparSiguienteAccionEsCorrecto() throws AccionesException {
        Reagrupar reagrupar = new Reagrupar();
        Colocar colocar = reagrupar.siguienteAccion(new Jugador(""));

        Exception exceptionPrimera = assertThrows(AccionesException.class, () -> { colocar.estaEnReagrupar(); });
        assertTrue(exceptionPrimera.getMessage().contains("No está en reagrupar"));

        Exception exceptionSegunda = assertThrows(AccionesException.class, () -> { colocar.estaEnAtacar(); });
        assertTrue(exceptionSegunda.getMessage().contains("No está en atacar"));

        colocar.estaEnColocar();
    }
}
