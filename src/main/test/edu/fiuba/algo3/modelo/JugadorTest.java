package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.fase.FaseUnoColocacionEjercitos;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void inicioUnJugadorConColor() {
        Jugador unJugador = new Jugador("Azul");
        assertEquals(unJugador.obtenerColor(), "Azul");
    }

    @Test
    public void agregarPaisAJugador() {
        Jugador unJugador = new Jugador("Azul");
        Pais pais = new Pais("Argentina", "America", "Uruguay,Paraguay");
        unJugador.agregarPais(pais);
        ArrayList<Pais> paisesJugador = unJugador.obtenerPaises();
        assertEquals(paisesJugador.contains(pais), true);
    }

    // TODO: implementar fase de juego
    @Test
    public void agregarUnEjercitoAUnPais() throws Exception {
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        jugador.agregarEjercitos(pais, 1);
        jugador.elegirPais(pais);
        assertEquals(jugador.obtenerEjercitosEnBatalla(), 2);
    }

    @Test
    public void agregarEjercitosAUnPaisNoPropioFalla() {
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Azul");

        Pais paisJugadorUno = new Pais("pais1", "continente", "pais10");
        Pais paisJugadorDos = new Pais("pais2", "continente", "pais11");

        jugadorUno.agregarPais(paisJugadorUno);
        jugadorDos.agregarPais(paisJugadorDos);

        Exception exception = assertThrows(Exception.class, () -> {
            jugadorUno.agregarEjercitos(paisJugadorDos, 1);
        });

        String expectedMessage = "pais2 no pertenece al jugador: Rojo";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validarCantidadEjercitosAAgregarEnFaseUnoExitosamente() throws Exception {
        FaseUnoColocacionEjercitos faseUno = new FaseUnoColocacionEjercitos();
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        // Se valida que la cantidad (1) de ejercitos a agregar, sea valida en el entorno de la Fase Uno
        jugador.validarCantidadEjercitos(1, faseUno.ejercitosPorFase(jugador));
        jugador.agregarEjercitos(pais, 1);
    }

    @Test
    public void validarCantidadEjercitosAAgregarEnFaseUnoFalla() throws Exception {
        FaseUnoColocacionEjercitos faseUno = new FaseUnoColocacionEjercitos();
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        jugador.agregarEjercitos(pais, 4);
        //Exception exception = assertThrows(ColocarEjercitosException.class, () -> {
          //  jugador.validarCantidadEjercitos(2, faseUno.ejercitosPorFase(jugador));
        //});
        //String expectedMessage = "En la fase actual no es posible tener mas de 5 ejercitos.";
        //String actualMessage = exception.getMessage();
        //assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void jugadorTieneUnPaisEnContinente() throws Exception {
        Continente continente = new Continente("America",1,0);
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Argentina", "America", "a,b");
        jugador.agregarPais(pais);
        assertTrue(jugador.tieneNPaisesEnContinente(1, continente.obtenerNombreContinente()));
    }

    @Test
    public void jugadorSinPaisesNoEstaEnJuego() {
        Jugador jugador = new Jugador("Rojo");
        assertFalse(jugador.sigueEnJuego());
    }

    @Test
    public void jugadorConPaisesEstaEnJuego() {
        Jugador jugador = new Jugador("Rojo");
        jugador.agregarPais(new Pais("pais", "continente", "a,b"));
        assertTrue(jugador.sigueEnJuego());
    }


    // FALTA AGREGAR TEST DE VALIDACIONES PARA FASE DOS EXITOSO, Y FASE DOS FALLA

}