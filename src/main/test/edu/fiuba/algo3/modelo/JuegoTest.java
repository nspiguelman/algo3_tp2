package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {
    @Test
    public void juegoFaseAsignacionPaises() throws TegException, FileNotFoundException {
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
            assertEquals(paisAAgregarEjercitos.obtenerEjercitos(), 2);
            juego.siguienteTurno();
        }
    }


    @Test
    public void tableroAsignaDosJugadoresMismoColorFalla() {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Azul");
        Exception exception = assertThrows(JugadorExistenteException.class, () -> { new Juego(coloresJugadores); });
        String expectedMessage = "Ya existe un jugador con el color: Azul";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosPrimeraRondaTerminaSatisfactoriamente() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();

        for (Jugador jugador : jugadores) {
            ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
            Pais paisDeJugador = paisesDeJugadorActual.get(5);
            juego.agregarEjercitos(jugador, paisDeJugador, 5);
            juego.siguienteTurno();
        }
        juego.siguienteFase();
    }

    @Test
    public void asignarEjercitosADiferentesPaisesPrimeraRondaTerminaSatisfactoriamente() throws Exception, FileNotFoundException {
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
            juego.siguienteTurno();
        }
        juego.siguienteFase();
    }

    @Test
    public void asignarEjercitosIncompletosSiguienteFaseFalla() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();

        for (Jugador jugador : jugadores) {
            ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
            Pais paisDeJugador = paisesDeJugadorActual.get(4);
            juego.agregarEjercitos(jugador, paisDeJugador, 4);
            juego.siguienteTurno();
        }
        Exception exception = assertThrows(SiguienteFaseException.class, juego::siguienteFase);

        String expectedMessage = "Para pasar a la siguiente fase cada jugador debe tener 5 ejercitos agregados.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosDeMasEnPrimeraRondaFalla() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);

        Exception exception = assertThrows(ColocarEjercitosException.class, () -> { juego.agregarEjercitos(jugador, paisDeJugador, 6); });

        String expectedMessage = "En la fase actual no es posible tener mas de 5 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosSegundaRondaTerminaSatisfactoriamente() throws Exception, FileNotFoundException {
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
    public void asignarEjercitosIncompletosEnSegundaRondaFalla() throws Exception, FileNotFoundException {
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

        Exception exception = assertThrows(SiguienteFaseException.class, () -> { juego.siguienteFase(); });
        String expectedMessage = "Para pasar a la siguiente fase cada jugador debe tener 8 ejercitos agregados.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asignarEjercitosDeMasEnSegundaRondaFalla() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        Juego juego = new Juego(coloresJugadores);
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        Jugador jugador = jugadores.get(0);
        ArrayList<Pais> paisesDeJugadorActual = juego.obtenerPaisesDeJugador(jugador);
        Pais paisDeJugador = paisesDeJugadorActual.get(4);
        juego.agregarEjercitos(jugador, paisDeJugador, 5);
        juego.siguienteFase();
        Exception exception = assertThrows(ColocarEjercitosException.class, () -> { juego.agregarEjercitos(jugador, paisDeJugador, 4); });
        String expectedMessage = "En la fase actual no es posible tener mas de 8 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void juegoDeUnaRondaCon2JugadoresColocanEjercitosNuevos() throws Exception, FileNotFoundException{
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Verde");
        Juego juego = new Juego(coloresJugadores);

        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        int faseDeColocacion = 0;
        int totalEjercitos = 5;
        // Colocacion de ejercitos fases 1 y 2
        for (int i=0; i<4; i++){
            Jugador jugadorActual = jugadores.get(i%2);
            if(faseDeColocacion > 1) {
                totalEjercitos = 3;
            }
            ArrayList<Pais> paises = juego.obtenerPaisesDeJugador(jugadorActual);
            for (int m=0; m < totalEjercitos; m++){
                Pais paisDeJugador = paises.get(m);
                juego.agregarEjercitos(jugadorActual, paisDeJugador, 1);
            }
            faseDeColocacion++;
            if (faseDeColocacion==2) {
                juego.siguienteFase();
            }
            juego.siguienteTurno();
        }
        juego.siguienteFase();

        for(int i=0; i<2; i++)
        {
            Jugador jugadorActual = jugadores.get(i%2);
            ArrayList<Pais> paises = juego.obtenerPaisesDeJugador(jugadorActual);
            juego.siguienteMovimiento(); // No ataca
            juego.siguienteMovimiento(); // No reagrupa
            juego.agregarEjercitos(jugadorActual, paises.get(3), 4);
            juego.agregarEjercitos(jugadorActual, paises.get(4), 4);
            juego.agregarEjercitos(jugadorActual, paises.get(5), 4);
            juego.siguienteTurno();
        }
    }
}