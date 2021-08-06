package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;


public class PasarAccion implements EventHandler<ActionEvent> {

    private final Button botonAccionar;
    private final Juego juego;
    private final ComboBox<String> paisesJugadorUno;
    private final ComboBox<String> paisesJugadorDos;
    private final ComboBox<String> ejercitosJugadorUno;
    private final ArrayList<VistaAccion> vistasTablero;
    private final VistaComboBox vistaBox;
    private final VistaLabel vistaLabel;

    public PasarAccion(Juego juego, Button ejecutarAccion, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos, ComboBox<String> ejercitosJugadorUno, ArrayList<VistaAccion> vistasTablero,
                       VistaComboBox vistaBox, VistaLabel vistaLabel){
        this.juego = juego;
        this.paisesJugadorUno = paisesJugadorUno;
        this.paisesJugadorDos = paisesJugadorDos;
        this.ejercitosJugadorUno = ejercitosJugadorUno;
        this.botonAccionar = ejecutarAccion;
        this.vistasTablero = vistasTablero;
        this.vistaBox = vistaBox;
        this.vistaLabel = vistaLabel;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Jugador jugador = juego.turnoActual();
            ArrayList<Jugador> jugadores = juego.obtenerJugadores();
            if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor()) && !juego.obtenerFase().equals("Juego")) {
                ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.paisesJugadorUno, this.ejercitosJugadorUno, vistaBox);
                AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, vistaLabel);
                vistasTablero.get(2).activar();
                botonAccionar.setOnAction(handlerColocar);
                juego.siguienteTurno();
                juego.siguienteFase();
                this.juego.turnoActual().setearEjercitosMaximos();
                if (juego.obtenerFase().equals("Juego")) {
                    vistasTablero.get(0).activar();
                    botonAccionar.setOnAction(handlerAtaque);
                }
                ContenedorMapa.actualizarVista();
            } else if (!juego.alguienGano())
            {
                this.juego.siguienteAccion();
                if (juego.obtenerAccion() == 1) {
                    vistasTablero.get(0).activar();
                    AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, vistaLabel);
                    botonAccionar.setOnAction(handlerAtaque);
                } else if (juego.obtenerAccion() == 2) {
                    vistasTablero.get(1).activar();
                    Reagrupar handlerReagrupar = new Reagrupar(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, this.ejercitosJugadorUno, vistaLabel, vistaBox);
                    botonAccionar.setOnAction(handlerReagrupar);
                } else {
                    ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.paisesJugadorUno, this.ejercitosJugadorUno, vistaBox);
                    if (juego.obtenerFase().equals("Juego")) {
                        this.vistaBox.fijarEjercitosPorFase();
                    }
                    vistasTablero.get(2).activar();
                    botonAccionar.setOnAction(handlerColocar);
                }
                ContenedorMapa.actualizarVista();
            }
            else{
                this.vistaLabel.finalizarJuego();
                this.vistaBox.finalizarJuego();
            }
            this.vistaLabel.limpiarJugadorUno();
            this.vistaLabel.limpiarJugadorDos();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}