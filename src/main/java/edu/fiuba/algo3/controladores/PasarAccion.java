package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.VistaAccion;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;


public class PasarAccion implements EventHandler<ActionEvent> {

    private VistaTurno vista;
    private Button botonAccionar;
    private Juego juego;
    private ComboBox boxPaisesOrigen;
    private ComboBox ejercitosOrigen;
    private ComboBox boxPaisesDestino;
    private ArrayList<VistaAccion> vistasTablero;

    public PasarAccion(Juego juego, Button ejecutarAccion, ComboBox box, ComboBox boxDestino, ComboBox cbx, VistaTurno vista, ArrayList<VistaAccion> vistasTablero){
        this.juego = juego;
        this.boxPaisesOrigen = box;
        this.ejercitosOrigen = cbx;
        this.boxPaisesDestino = boxDestino;
        this.botonAccionar = ejecutarAccion;
        this.vista = vista;
        this.vistasTablero = vistasTablero;
    }

    @Override
    public void handle(ActionEvent event) {
        // Pasar a siguiente fase
        Jugador jugador = juego.esElTurnoDe();
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        //Si es el ultimo jugador y no es fase de juego
        if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor()) && !juego.obtenerFase().equals("Juego")){
            try {
                ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.boxPaisesOrigen, this.ejercitosOrigen, this.vista);
                AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.boxPaisesOrigen, this.boxPaisesDestino);
                vistasTablero.get(2).activar();
                botonAccionar.setOnAction(handlerColocar);
                juego.siguienteFase();
                juego.siguienteTurno();
                if(juego.obtenerFase().equals("Juego")){
                    vistasTablero.get(0).activar();
                    botonAccionar.setOnAction(handlerAtaque);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            this.juego.siguienteAccion();
            if (juego.obtenerAccion() == 1){
                vistasTablero.get(0).activar();
                AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.boxPaisesOrigen, this.boxPaisesDestino);
                botonAccionar.setOnAction(handlerAtaque);
            }
            else if(juego.obtenerAccion() == 2){
                vistasTablero.get(1).activar();
                Reagrupar handlerReagrupar = new Reagrupar(this.juego, this.boxPaisesOrigen, this.boxPaisesDestino, this.ejercitosOrigen);
                botonAccionar.setOnAction(handlerReagrupar);
            }
            else{
                ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.boxPaisesOrigen, this.ejercitosOrigen, this.vista);
                if(juego.obtenerFase().equals("Juego")){
                    this.vista.fijarEjercitosPorFase();
                }
                vistasTablero.get(2).activar();
                botonAccionar.setOnAction(handlerColocar);
            }
        }
        ContenedorMapa.actualizarVista();
    }
}