package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.paises.Pais;
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

        juego.siguienteAccion();
        juego.siguienteAccion();

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
    }

    @Test
    public void juegoDeUnaRondaCon2JugadoresColocanEjercitosNuevos() throws Exception{
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
            juego.siguienteAccion(); // No ataca
            juego.siguienteAccion(); // No reagrupa
            juego.agregarEjercitos(jugadorActual, paises.get(3), 4);
            juego.agregarEjercitos(jugadorActual, paises.get(4), 4);
            juego.agregarEjercitos(jugadorActual, paises.get(5), 4);
            juego.siguienteTurno();
        }
    }
    @Test
    public void juegoDeUnaRondaCon2JugadoresJugadorUnoAtacaYConquista2PaisesDelJugadorDos() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Verde");
        Juego juego = new Juego(coloresJugadores);

        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        int faseDeColocacion = 0;
        int totalEjercitos = 5;
        // Colocacion de ejercitos fases 1 y 2
        for (int i = 0; i < 4; i++) {
            Jugador jugadorActual = jugadores.get(i % 2);
            if (faseDeColocacion > 1) {
                totalEjercitos = 3;
            }
            ArrayList<Pais> paises = juego.obtenerPaisesDeJugador(jugadorActual);
            for (int m = 0; m < totalEjercitos; m++) {
                Pais paisDeJugador = paises.get(m);
                juego.agregarEjercitos(jugadorActual, paisDeJugador, 1);
            }
            faseDeColocacion++;
            if (faseDeColocacion == 2) {
                juego.siguienteFase();
            }
            juego.siguienteTurno();
        }
        juego.siguienteFase();

        Jugador jugadorUno = jugadores.get(0);
        Jugador jugadorDos = jugadores.get(1);
        ArrayList<Pais> paisesUno = juego.obtenerPaisesDeJugador(jugadorUno);
        ArrayList<Pais> paisesDos = juego.obtenerPaisesDeJugador(jugadorDos);

        for (int i = 0; i < 2; i++) {
            Pais paisActualJugadorUno = paisesUno.get(i);
            int contador = 0;
            Pais paisActualJugadorDos = paisesDos.get(contador);
            boolean frenar = false;
            while (contador < paisesUno.size() && !frenar) {
                paisActualJugadorUno = paisesUno.get(contador);
                int contador2 = 0;
                while (!paisActualJugadorUno.limitaCon(paisActualJugadorDos)) {
                    contador2++;
                    if (contador2 < paisesDos.size()) {
                        paisActualJugadorDos = paisesDos.get(contador2);
                    } else {
                        break;
                    }

                    if (paisActualJugadorUno.limitaCon(paisActualJugadorDos)) {
                        frenar = true;
                    } else {
                        if (contador == 23) {
                            frenar = true;
                        }
                    }
                    contador++;
                }
            }
            jugadorUno.elegirPais(paisActualJugadorUno);
            paisActualJugadorUno.agregarEjercitos(10);
            jugadorDos.elegirPais(paisActualJugadorDos);
            while (!jugadorUno.tieneElPais(paisActualJugadorDos)) {
                juego.ataqueDeA(jugadorUno, jugadorDos);
            }
        }
    }

    @Test
    public void JuegoDeUnaRondaCon3JugadoresEl2ControlaAsiaColocanNuevosEjercitos() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Verde");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);

        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        int faseDeColocacion = 0;
        int totalEjercitos = 5;
        // Colocacion de ejercitos fases 1 y 2
        for (int i = 0; i < 6; i++) {
            Jugador jugadorActual = jugadores.get(i % 3);
            if (faseDeColocacion > 2) {
                totalEjercitos = 3;
            }
            ArrayList<Pais> paises = juego.obtenerPaisesDeJugador(jugadorActual);
            for (int m = 0; m < totalEjercitos; m++) {
                Pais paisDeJugador = paises.get(m);
                juego.agregarEjercitos(jugadorActual, paisDeJugador, 1);
            }
            faseDeColocacion++;
            if (faseDeColocacion == 3) {
                juego.siguienteFase();
            }
            juego.siguienteTurno();
        }
        juego.siguienteFase();
        // Termina colocación de ejercitos inicial, y arranca la Fase de Juego

        Jugador jugadorUno = jugadores.get(0);
        Jugador jugadorDos = jugadores.get(1);
        Jugador jugadorTres = jugadores.get(2);
        ArrayList<Pais> paisesUno = juego.obtenerPaisesDeJugador(jugadorUno);
        ArrayList<Pais> paisesDos = juego.obtenerPaisesDeJugador(jugadorDos);
        ArrayList<Pais> paisesTres = juego.obtenerPaisesDeJugador(jugadorTres);
        int contador =0;

        ArrayList<Pais> paisesDeAtaque = new ArrayList<>();
        // Paso de turno para que el Jugador Dos realice los ataques a Asia
        juego.siguienteTurno();
        while (contador < paisesDos.size()){
            Pais paisActualDos = paisesDos.get(contador);

            if (paisActualDos.obtenerNombreContinente().equals("Asia")){
                jugadorDos.elegirPais(paisActualDos);
                // Nos fijamos los paises del jugador Uno hasta encontrar alguno que limite con el nuestro
                for (int i = 0; i < paisesUno.size();i++){
                    Pais paisActualUno = paisesUno.get(i);
                    if (paisActualDos.limitaCon(paisActualUno) && paisActualUno.obtenerNombreContinente().equals("Asia")){
                        paisesDeAtaque.add(paisActualDos);
                        paisActualDos.agregarEjercitos(100);
                        jugadorUno.elegirPais(paisActualUno);
                        while (!jugadorDos.tieneElPais(paisActualUno)){
                            juego.ataqueDeA(jugadorDos, jugadorUno);
                        }
                    }
                }

                for (int i = 0; i < paisesTres.size();i++){
                    Pais paisActualTres = paisesTres.get(i);

                    if (paisActualDos.limitaCon(paisActualTres) && paisActualTres.obtenerNombreContinente().equals("Asia")){
                        paisesDeAtaque.add(paisActualDos);
                        paisActualDos.agregarEjercitos(100);
                        jugadorTres.elegirPais(paisActualTres);
                        while (!jugadorDos.tieneElPais(paisActualTres)){
                            juego.ataqueDeA(jugadorDos, jugadorTres);
                        }
                    }
                }

            }
                contador++;
        }
        /// Actualmente es el turno del Jugador Dos.
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso de ataque a reagrupamiento, y de reagrupamiento a colocacion de ejercitos

        juego.siguienteTurno(); // Turno del Jugador Tres
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso de ataque a reagrupamiento, y de reagrupamiento a colocacion de ejercitos

        juego.siguienteTurno();

        // Reseteo los ejercitos de los paises atacantes, porque habiamos agregado exceso para poder conquistar todo Asia
        for (int m=0; m<paisesDeAtaque.size(); m++){
            Pais paisActual = paisesDeAtaque.get(m);
            paisActual.reducirEjercitos(paisActual.obtenerEjercitos()-1);
        }


        // Turno del Jugador Uno
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso directamente a la fase de colocar ejercitos

        int resto = jugadorUno.obtenerPaises().size() % 2;
        int cantidadPaisesJugador = (jugadorUno.obtenerPaises().size() - resto)/ 2;
        juego.agregarEjercitos(jugadorUno, paisesUno.get(2), cantidadPaisesJugador);

        juego.siguienteTurno();
        juego.siguienteAccion();
        juego.siguienteAccion();

        int restoDos = jugadorDos.obtenerPaises().size() % 2;
        int cantidadPaisesJugadorDos = (jugadorDos.obtenerPaises().size() - restoDos)/ 2;
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), cantidadPaisesJugadorDos+2);
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), 2);
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), 3);


        juego.siguienteTurno();
        juego.siguienteAccion();
        juego.siguienteAccion();

        int restoTres = jugadorTres.obtenerPaises().size() % 2;
        int cantidadPaisesJugadorTres = (jugadorTres.obtenerPaises().size() - restoTres)/ 2;
        juego.agregarEjercitos(jugadorTres, paisesTres.get(3), cantidadPaisesJugadorTres);
    }

   @Test
    public void JuegoDeUnaRondaCon3JugadoresEl2ControlaEuropaColocanNuevosEjercitos() throws Exception, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Azul");
        coloresJugadores.add("Verde");
        coloresJugadores.add("Rojo");
        Juego juego = new Juego(coloresJugadores);

        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        int faseDeColocacion = 0;
        int totalEjercitos = 5;
        // Colocacion de ejercitos fases 1 y 2
        for (int i = 0; i < 6; i++) {
            Jugador jugadorActual = jugadores.get(i % 3);
            if (faseDeColocacion > 2) {
                totalEjercitos = 3;
            }
            ArrayList<Pais> paises = juego.obtenerPaisesDeJugador(jugadorActual);
            for (int m = 0; m < totalEjercitos; m++) {
                Pais paisDeJugador = paises.get(m);
                juego.agregarEjercitos(jugadorActual, paisDeJugador, 1);
            }
            faseDeColocacion++;
            if (faseDeColocacion == 3) {
                juego.siguienteFase();
            }
            juego.siguienteTurno();
        }
        juego.siguienteFase();
        // Termina colocación de ejercitos inicial, y arranca la Fase de Juego

        Jugador jugadorUno = jugadores.get(0);
        Jugador jugadorDos = jugadores.get(1);
        Jugador jugadorTres = jugadores.get(2);
        ArrayList<Pais> paisesUno = juego.obtenerPaisesDeJugador(jugadorUno);
        ArrayList<Pais> paisesDos = juego.obtenerPaisesDeJugador(jugadorDos);
        ArrayList<Pais> paisesTres = juego.obtenerPaisesDeJugador(jugadorTres);
        int contador =0;

        ArrayList<Pais> paisesDeAtaque = new ArrayList<>();
        // Paso de turno para que el Jugador Dos realice los ataques a Asia
        juego.siguienteTurno();
        while (contador < paisesDos.size()){
            Pais paisActualDos = paisesDos.get(contador);

            if (paisActualDos.obtenerNombreContinente().equals("Europa")){
                jugadorDos.elegirPais(paisActualDos);
                // Nos fijamos los paises del jugador Uno hasta encontrar alguno que limite con el nuestro
                for (int i = 0; i < paisesUno.size();i++){
                    Pais paisActualUno = paisesUno.get(i);
                    if (paisActualDos.limitaCon(paisActualUno) && paisActualUno.obtenerNombreContinente().equals("Europa")){
                        paisesDeAtaque.add(paisActualDos);
                        paisActualDos.agregarEjercitos(100);
                        jugadorUno.elegirPais(paisActualUno);
                        while (!jugadorDos.tieneElPais(paisActualUno)){
                            juego.ataqueDeA(jugadorDos, jugadorUno);
                        }
                    }
                }

                for (int i = 0; i < paisesTres.size();i++){
                    Pais paisActualTres = paisesTres.get(i);

                    if (paisActualDos.limitaCon(paisActualTres) && paisActualTres.obtenerNombreContinente().equals("Europa")){
                        paisesDeAtaque.add(paisActualDos);
                        paisActualDos.agregarEjercitos(100);
                        jugadorTres.elegirPais(paisActualTres);
                        while (!jugadorDos.tieneElPais(paisActualTres)){
                            juego.ataqueDeA(jugadorDos, jugadorTres);
                        }
                    }
                }

            }
            contador++;
        }
        /// Actualmente es el turno del Jugador Dos.
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso de ataque a reagrupamiento, y de reagrupamiento a colocacion de ejercitos

        juego.siguienteTurno(); // Turno del Jugador Tres
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso de ataque a reagrupamiento, y de reagrupamiento a colocacion de ejercitos

        juego.siguienteTurno();

        // Reseteo los ejercitos de los paises atacantes, porque habiamos agregado exceso para poder conquistar todo Asia
        for (int m=0; m<paisesDeAtaque.size(); m++){
            Pais paisActual = paisesDeAtaque.get(m);
            paisActual.reducirEjercitos(paisActual.obtenerEjercitos()-1);
        }

        // Turno del Jugador Uno
        juego.siguienteAccion();
        juego.siguienteAccion(); // Paso directamente a la fase de colocar ejercitos

        int resto = jugadorUno.obtenerPaises().size() % 2;
        int cantidadPaisesJugador = (jugadorUno.obtenerPaises().size() - resto)/ 2;
        juego.agregarEjercitos(jugadorUno, paisesUno.get(2), cantidadPaisesJugador);

        juego.siguienteTurno();
        juego.siguienteAccion();
        juego.siguienteAccion();

        int restoDos = jugadorDos.obtenerPaises().size() % 2;
        int cantidadPaisesJugadorDos = (jugadorDos.obtenerPaises().size() - restoDos)/ 2;
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), cantidadPaisesJugadorDos+2);
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), 2);
        juego.agregarEjercitos(jugadorDos, paisesDos.get(10), 1);

        juego.siguienteTurno();
        juego.siguienteAccion();
        juego.siguienteAccion();

        int restoTres = jugadorTres.obtenerPaises().size() % 2;
        int cantidadPaisesJugadorTres = (jugadorTres.obtenerPaises().size() - restoTres)/ 2;
        juego.agregarEjercitos(jugadorTres, paisesTres.get(3), cantidadPaisesJugadorTres);
    }
}