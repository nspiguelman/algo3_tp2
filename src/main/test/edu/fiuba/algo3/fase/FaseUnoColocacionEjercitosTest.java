package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaseUnoColocacionEjercitosTest {
    @Test
    public void asignarEjercitosEnPrimeraRonda() throws SiguienteFaseException, ColocarEjercitosException, PaisNoPerteneceAJugadorException {
        Pais bolivia = new Pais("Bolivia", "America", "a, b, c");
        Jugador jugador = new Jugador("Rojo");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugador.agregarPais(bolivia);
        jugador.agregarEjercitos(bolivia, 5, new FaseUnoColocacionEjercitos());
        jugadores.add(jugador);
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        fase.siguienteFase(jugadores);
    }

    @Test
    public void validarCantidadEjercitosSatisfactoriamente() throws ColocarEjercitosException {
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        for (int i = 1; i <= 5; i++) {
            fase.validarCantidadEjercitos(i);
        }
    }

    @Test
    public void validarCantidadEjercitosFalla() {
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        Exception exception = assertThrows(ColocarEjercitosException.class, () -> { fase.validarCantidadEjercitos(6); });

        String expectedMessage = "No se puede agregar mas de 5 ejercitos en la actual fase de colocación";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
