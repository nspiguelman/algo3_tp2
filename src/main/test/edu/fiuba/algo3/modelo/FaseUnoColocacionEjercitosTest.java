package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class FaseUnoColocacionEjercitosTest {
    @Test
    public void asignarEjercitosEnPrimeraRonda() throws Exception {
        Pais bolivia = new Pais("Bolivia", "America", "a, b, c");
        Jugador jugador = new Jugador("Rojo");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugador.agregarPais(bolivia);
        jugador.agregarEjercitos(bolivia, 5);
        jugadores.add(jugador);
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        fase.siguienteFase(jugadores);
    }
}
