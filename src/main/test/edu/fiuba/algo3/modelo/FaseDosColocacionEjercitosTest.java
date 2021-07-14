package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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
}
