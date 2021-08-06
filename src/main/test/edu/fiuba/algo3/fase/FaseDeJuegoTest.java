package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FaseDeJuegoTest {

    @Test
    public void constructorFaseDeJuegoDevuelveUnaFaseDeJuegoValida(){
        FaseDeJuego faseDeJuego = new FaseDeJuego();
        assertEquals("Juego", faseDeJuego.obtenerFase());
        assertEquals(1, faseDeJuego.accionActual());
    }

    @Test
    public void siguienteAccionCambiaAReagrupar() {
        FaseDeJuego faseDeJuego = new FaseDeJuego();
        Jugador unJugador = new Jugador("Azul");
        faseDeJuego.siguienteAccion(unJugador);
        assertEquals(2, faseDeJuego.accionActual());
    }

    @Test
    public void reiniciarAccionCambiaAAtacar() {
        FaseDeJuego faseDeJuego = new FaseDeJuego();
        Jugador unJugador = new Jugador("Azul");
        faseDeJuego.siguienteAccion(unJugador);
        assertEquals(2, faseDeJuego.accionActual());

        faseDeJuego.reiniciarAcciones();
        assertEquals(1, faseDeJuego.accionActual());
    }

    @Test
    public void siguienteFaseDevuelveFaseColocacionEjercitos() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        FaseDeJuego faseDeJuego = new FaseDeJuego();
        Fase nuevaFase = faseDeJuego.siguienteFase(jugadores);

        assertEquals("ColocacionDos", nuevaFase.obtenerFase());

    }

}
