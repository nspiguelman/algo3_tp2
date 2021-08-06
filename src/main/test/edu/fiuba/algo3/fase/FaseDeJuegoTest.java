package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;
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

}
