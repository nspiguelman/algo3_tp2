package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {
    @Test
    public void juegoFaseAsignacionPaises() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Rojo");
        coloresJugadores.add("Azul");
        coloresJugadores.add("Amarillo");
        coloresJugadores.add("Violeta");
        coloresJugadores.add("Verde");

        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        assertEquals(jugadores.size(), 5);
        for (Jugador jugador : jugadores) {
            assertEquals(jugador.obtenerPaises().size(), 10);
        }
    }

    @Test
    public void primerFaseAsignarEjercitosAPaisesExitosamente() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        for (Jugador jugador : jugadores) {
            Random random = new Random();
            int numeroRandomPaisASeleccionar = random.nextInt(24);
            ArrayList<Pais> paisesDeJugador = juego.obtenerPaisesDeJugador(jugador);
            Pais paisAAgregarEjercitos = paisesDeJugador.get(numeroRandomPaisASeleccionar);
            juego.agregarEjercitos(jugador, paisAAgregarEjercitos, 1);
            assertEquals(paisAAgregarEjercitos.ejercitos(), 2);
        }
    }


    @Test
    public void tableroAsignaDosJugadoresMismoColorFalla() {
        try {
            ArrayList<String> coloresJugadores = new ArrayList<>();
            coloresJugadores.add("Azul");
            coloresJugadores.add("Azul");
            new Juego(coloresJugadores);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No se puede asignar el mismo color a dos jugadores");
        }
    }

    @Test
    public void asignarEjercitosPrimeraRondaTerminaSatisfactoriamente() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();

        for (Jugador jugador : jugadores) {
            ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
            Pais paisDeJugador = paisesDeJugadorActual.get(5);
            juego.agregarEjercitos(jugador, paisDeJugador, 5);
        }
        juego.siguienteFase();
    }

    @Test
    public void asignarEjercitosADiferentesPaisesPrimeraRondaTerminaSatisfactoriamente() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");

        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();

        for (Jugador jugador : jugadores) {
            ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
            for (int i = 0; i < 5; i++) {
                Pais paisDeJugador = paisesDeJugadorActual.get(i);
                juego.agregarEjercitos(jugador, paisDeJugador, 1);
            }
        }
        juego.siguienteFase();
    }

    @Test
    public void asignarEjercitosIncompletosSiguienteFaseFalla() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();

        for (Jugador jugador : jugadores) {
            ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
            Pais paisDeJugador = paisesDeJugadorActual.get(4);
            juego.agregarEjercitos(jugador, paisDeJugador, 4);
        }
        Exception exception = assertThrows(Exception.class, () -> {
            juego.siguienteFase();
        });

        String expectedMessage = "En esta etapa cada jugador debería agregar 5 ejercitos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosDeMasEnPrimeraRondaFalla() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);
        juego.agregarEjercitos(jugador, paisDeJugador, 6);

        Exception exception = assertThrows(Exception.class, () -> { juego.siguienteFase(); });

        String expectedMessage = "En esta etapa cada jugador debería agregar 5 ejercitos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosSegundaRondaTerminaSatisfactoriamente() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");

        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);
        juego.agregarEjercitos(jugador, paisDeJugador, 5);
        juego.siguienteFase();
        juego.agregarEjercitos(jugador, paisDeJugador, 3);
        juego.siguienteFase();
    }

    @Test
    public void asignarEjercitosIncompletosEnSegundaRondaFalla() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");

        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);
        juego.agregarEjercitos(jugador, paisDeJugador, 5);

        juego.siguienteFase();

        juego.agregarEjercitos(jugador, paisDeJugador, 2);

        Exception exception = assertThrows(Exception.class, () -> {
            juego.siguienteFase();
        });

        String expectedMessage = "En esta etapa cada jugador debería agregar 3 ejercitos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosDeMasEnSegundaRondaFalla() throws Exception {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);
        juego.agregarEjercitos(jugador, paisDeJugador, 5);
        juego.siguienteFase();
        juego.agregarEjercitos(jugador, paisDeJugador, 4);
        Exception exception = assertThrows(Exception.class, () -> { juego.siguienteFase(); });

        String expectedMessage = "En esta etapa cada jugador debería agregar 3 ejercitos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

   /* @Test
    public void paisAAtacaAPaisBGanaPaisB() throws Exception {
        // mocking
        ArrayList<Pais> paises = new ArrayList<>();
        Pais brasil = new Pais("Brasil", "America", "Argentina");
        Pais argentina = new Pais("Argentina", "America", "Brasil");
        Jugador azul = new Jugador("Azul");
        azul.agregarPais(argentina);
        azul.atacarConA(argentina, brasil);
*/
        /*ArrayList<Pais> paises = new ArrayList<>();
        paises.add(new Pais("Argentina", "America", "Brasil"));
        paises.add(new Pais("Brasil", "America", "Argentina"));

        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        ArrayList<Pais> paisesJugadorUno = jugadores.get(0).obtenerPaises();
        ArrayList<Pais> paisesJugadorDos = jugadores.get(1).obtenerPaises();*/
    //}

}