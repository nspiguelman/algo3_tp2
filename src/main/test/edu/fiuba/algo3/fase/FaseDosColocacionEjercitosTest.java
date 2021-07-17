package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaseDosColocacionEjercitosTest {
    @Test
    public void asignarEjercitosEnSegundaRonda() throws Exception {
        Pais bolivia = new Pais("Bolivia", "America", "a, b, c");
        Jugador jugador = new Jugador("Rojo");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugador.agregarPais(bolivia);
        jugador.agregarEjercitos(bolivia, 8, new FaseDosColocacionEjercitos());
        jugadores.add(jugador);
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        fase.siguienteFase(jugadores);
    }

    @Test
    public void validarCantidadEjercitosSatisfactoriamente() throws Exception {
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        for (int i = 1; i <= 8; i++) {
            fase.validarCantidadEjercitos(i);
        }
    }

    @Test
    public void validarCantidadEjercitosFalla() {
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        Exception exception = assertThrows(Exception.class, () -> { fase.validarCantidadEjercitos(9); });

        String expectedMessage = "No se puede agregar mas de 8 ejercitos en la primer fase de colocación";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
