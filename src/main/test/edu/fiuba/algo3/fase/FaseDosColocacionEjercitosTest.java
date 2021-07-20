package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;
import edu.fiuba.algo3.excepciones.TegException;
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
        jugador.agregarEjercitos(bolivia, 8);
        jugadores.add(jugador);
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        fase.siguienteFase(jugadores);
    }

    @Test
    public void validarCantidadEjercitosSatisfactoriamente() throws Exception {
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        Jugador jugador = new Jugador("Azul");
        Pais brasil = new Pais("Brasil", "America", "a, b, c");
        jugador.agregarPais(brasil);
        jugador.agregarEjercitos(brasil, 8);
        fase.validarCantidadEjercitos(jugador);
    }

    @Test
    public void validarCantidadEjercitosFalla() throws TegException {
        FaseDosColocacionEjercitos fase = new FaseDosColocacionEjercitos();
        Jugador jugador = new Jugador("Azul");
        Pais brasil = new Pais("Brasil", "America", "a, b, c");
        jugador.agregarPais(brasil);
        jugador.agregarEjercitos(brasil, 9);

        Exception exception = assertThrows(Exception.class, () -> { fase.validarCantidadEjercitos(jugador); });

        String expectedMessage = "En la fase actual no es posible tener mas de 8 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
