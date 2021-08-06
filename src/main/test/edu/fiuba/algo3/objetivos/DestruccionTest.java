package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DestruccionTest {

    private Jugador jugadorNegro;
    private Jugador jugadorRojo;
    private Jugador jugadorAzul;

    ArrayList<Jugador> jugadores = new ArrayList<>();

    public DestruccionTest() {
        this.jugadorAzul = mock(Jugador.class);
        when(this.jugadorAzul.obtenerColor()).thenReturn("Azul");
        this.jugadorRojo = mock(Jugador.class);
        when(this.jugadorRojo.obtenerColor()).thenReturn("Rojo");
        this.jugadorNegro = mock(Jugador.class);
        when(this.jugadorNegro.obtenerColor()).thenReturn("Negro");

        this.jugadores.add(jugadorAzul);
        this.jugadores.add(jugadorRojo);
        this.jugadores.add(jugadorNegro);
    }

    @Test
    public void creaElObjetivoDeDestruccionCorrectamente() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Negro");
        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);
        assertTrue(objetivoDestruccion.obtenerTipo() == "Destrucción");
    }

    @Test
    public void validarObjetivoEsTrue() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Negro");
        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);
        assertTrue(objetivoDestruccion.validarObjetivo(this.jugadorAzul));
    }

    @Test
    public void validarObjetivoJugadorConPaisesEsFalse() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Negro");
        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);
        when(this.jugadorNegro.sigueEnJuego()).thenReturn(true);
        assertFalse(objetivoDestruccion.validarObjetivo(this.jugadorAzul));
    }

    @Test
    public void actualizarObjetivoNoActualizaPorqueYaEstaAsignado() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Negro");
        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);

        when(this.jugadorNegro.sigueEnJuego()).thenReturn(true);
        assertFalse(objetivoDestruccion.validarObjetivo(this.jugadorAzul));

        objetivoDestruccion.actualizarObjetivo(this.jugadorAzul);

        when(this.jugadorNegro.sigueEnJuego()).thenReturn(true);
        assertFalse(objetivoDestruccion.validarObjetivo(this.jugadorAzul));
    }

    @Test
    public void actualizarObjetivoActualizaPorqueNoHabiaSigoAsignado() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Gris");

        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);
        assertFalse(objetivoDestruccion.validarObjetivo(this.jugadorAzul));
        objetivoDestruccion.actualizarObjetivo(this.jugadorAzul);

        when(this.jugadorRojo.sigueEnJuego()).thenReturn(false);
        assertTrue(objetivoDestruccion.validarObjetivo(this.jugadorAzul));
    }

    @Test
    public void actualizarObjetivosAlUltimoJugadorPoneAPrimerJugadorComoNuevoObjetivo() {
        TipoObjetivoDeserializer tipoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(tipoObjetivo.obtenerColor()).thenReturn("Gris");

        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(tipoObjetivo);

        Destruccion objetivoDestruccion = new Destruccion(tipoObjetivos, jugadores);
        assertFalse(objetivoDestruccion.validarObjetivo(this.jugadorNegro));
        objetivoDestruccion.actualizarObjetivo(this.jugadorNegro);

        when(this.jugadorAzul.sigueEnJuego()).thenReturn(false);
        assertTrue(objetivoDestruccion.validarObjetivo(this.jugadorNegro));
    }
}
