package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaseUnoColocacionEjercitosTest {
    @Test
    public void asignarEjercitosEnPrimeraRonda() throws SiguienteFaseException, Exception {
        Pais bolivia = new Pais("Bolivia", "America", "a, b, c");
        Jugador jugador = new Jugador("Rojo");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugador.agregarPais(bolivia);
        jugador.agregarEjercitos(bolivia, 5);
        jugadores.add(jugador);
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        fase.siguienteFase(jugadores);
    }

    @Test
    public void validarCantidadEjercitosSatisfactoriamente() throws Exception {
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        Jugador jugador = new Jugador("Azul");
        Pais brasil = new Pais("Brasil", "America", "a, b, c");
        jugador.agregarPais(brasil);
        jugador.agregarEjercitos(brasil, 5);
        fase.validarCantidadEjercitos(jugador);
    }

    @Test
    public void validarCantidadEjercitosFalla() throws Exception {
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        Jugador jugador = new Jugador("Azul");
        Pais brasil = new Pais("Brasil", "America", "a, b, c");
        jugador.agregarPais(brasil);
        jugador.agregarEjercitos(brasil, 6);
        Exception exception = assertThrows(ColocarEjercitosException.class, () -> { fase.validarCantidadEjercitos(jugador); });

        String expectedMessage = "En la fase actual no es posible tener mas de 5 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
