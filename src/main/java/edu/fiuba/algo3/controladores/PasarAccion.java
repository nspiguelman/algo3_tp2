package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;


public class PasarAccion implements EventHandler<ActionEvent> {

    private Button botonAccionar;
    private Juego juego;
    private ComboBox boxPaisesOrigen;
    private ComboBox ejercitosOrigen;
    private ComboBox boxPaisesDestino;

    public PasarAccion(Juego juego, Button ejecutarAccion, ComboBox box, ComboBox boxDestino, ComboBox cbx){
        this.juego = juego;
        this.boxPaisesOrigen = box;
        this.ejercitosOrigen = cbx;
        this.boxPaisesDestino = boxDestino;
        this.botonAccionar = ejecutarAccion;
    }

    @Override
    public void handle(ActionEvent event) {
        // Pasar a siguiente fase
        Jugador jugador = juego.esElTurnoDe();
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        //Si es el ultimo jugador y no es fase de juego
        if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor()) && !juego.obtenerFase().equals("Juego")){
            try {
                AtacarAccion handler = new AtacarAccion(this.juego, this.boxPaisesOrigen, this.boxPaisesDestino);
                botonAccionar.setOnAction(handler);
                juego.siguienteFase();
                juego.siguienteTurno();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            this.juego.siguienteAccion();
            if (juego.obtenerAccion() == 2){
                Reagrupar handler = new Reagrupar(this.juego, this.boxPaisesOrigen, this.boxPaisesDestino, this.ejercitosOrigen);
                botonAccionar.setOnAction(handler);
            }
            else{
                ColocarEjercitos handler = new ColocarEjercitos(this.juego, this.boxPaisesOrigen, this.ejercitosOrigen);
                botonAccionar.setOnAction(handler);
            }
        }
        ContenedorMapa.actualizarVista();
    }
}