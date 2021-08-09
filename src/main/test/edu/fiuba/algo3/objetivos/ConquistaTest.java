package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConquistaTest {

    private Jugador jugadorNegro;

    public ConquistaTest() {
        this.jugadorNegro = mock(Jugador.class);
    }

    @Test
    public void jugadorCumpleObjetivos() {
        TipoObjetivoDeserializer primerObjetivo = mock(TipoObjetivoDeserializer.class);
        when(primerObjetivo.obtenerContinente()).thenReturn("Europa");
        when(primerObjetivo.obtenerCantidadDePaises()).thenReturn(2);

        TipoObjetivoDeserializer segundoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(segundoObjetivo.obtenerContinente()).thenReturn("Asia");
        when(segundoObjetivo.obtenerCantidadDePaises()).thenReturn(1);

        jugadorNegro.agregarPais(new Pais("A", "Europa", ""));
        jugadorNegro.agregarPais(new Pais("A", "Europa", ""));
        jugadorNegro.agregarPais(new Pais("A", "Asia", ""));

        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(primerObjetivo);
        tipoObjetivos.add(segundoObjetivo);

        Conquista objetivoConquista = new Conquista(tipoObjetivos);
        assertTrue(objetivoConquista.obtenerTipo() == "Conquista");

        when(jugadorNegro.tieneNPaisesEnContinente(2, "Europa")).thenReturn(true);
        when(jugadorNegro.tieneNPaisesEnContinente(1, "Asia")).thenReturn(true);

        assertTrue(objetivoConquista.validarObjetivo(jugadorNegro));
    }

    @Test
    public void jugadorNoCumpleObjetivos() {
        TipoObjetivoDeserializer primerObjetivo = mock(TipoObjetivoDeserializer.class);
        when(primerObjetivo.obtenerContinente()).thenReturn("Europa");
        when(primerObjetivo.obtenerCantidadDePaises()).thenReturn(2);

        TipoObjetivoDeserializer segundoObjetivo = mock(TipoObjetivoDeserializer.class);
        when(segundoObjetivo.obtenerContinente()).thenReturn("Asia");
        when(segundoObjetivo.obtenerCantidadDePaises()).thenReturn(1);

        jugadorNegro.agregarPais(new Pais("A", "Europa", ""));
        jugadorNegro.agregarPais(new Pais("A", "Europa", ""));
        jugadorNegro.agregarPais(new Pais("A", "Asia", ""));

        ArrayList<TipoObjetivoDeserializer> tipoObjetivos = new ArrayList<>();
        tipoObjetivos.add(primerObjetivo);
        tipoObjetivos.add(segundoObjetivo);

        Conquista objetivoConquista = new Conquista(tipoObjetivos);
        objetivoConquista.actualizarObjetivo(jugadorNegro);
        assertTrue(objetivoConquista.obtenerTipo() == "Conquista");

        when(jugadorNegro.tieneNPaisesEnContinente(2, "Europa")).thenReturn(true);
        when(jugadorNegro.tieneNPaisesEnContinente(0, "Asia")).thenReturn(true);

        assertFalse(objetivoConquista.validarObjetivo(jugadorNegro));
    }
}
