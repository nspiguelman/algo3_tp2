package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Locale;

public class VistaTurno {
    private Juego juego;
    private HashMap<String, String> colores = new HashMap<>();
    private HashMap<Integer, String> acciones = new HashMap<>();
    private Label labelFase;
    private Label labelTurno;


    public VistaTurno(Label labelTurno, Label labelFase, Juego juego) {
        this.juego = juego;
        this.labelFase = labelFase;
        this.labelTurno = labelTurno;
        this.inicializarColores();
        this.inicializarAcciones();
        this.setLabelTurno();
        this.setLabelFase();
    }

    private void setLabelFase() {
        int accionActual = juego.obtenerAccion();
        String accion = acciones.get(accionActual);
        this.labelFase.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        this.labelFase.setText("FASE: " + accion);
    }


    private void setLabelTurno(){
        Jugador jugadorActual = this.juego.esElTurnoDe();
        String colorJugador = jugadorActual.obtenerColor();
        String colorLabel = this.colores.get(colorJugador);
        this.labelTurno.setText("TURNO: " + colorJugador);
        this.labelTurno.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color:" + colorLabel + "; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
    }

    public void inicializarColores() {
        this.colores.put("Negro", "#000000");
        this.colores.put("Azul", "#0077BB");
        this.colores.put("Rosa", "#EE3377");
        this.colores.put("Rojo", "#CC3311");
        this.colores.put("Verde", "#009988");
        this.colores.put("Naranja", "#EE7733");
    }

    public void inicializarAcciones() {
        this.acciones.put(1, "Ataque");
        this.acciones.put(2, "Reagrupacion");
        this.acciones.put(3, "Colocacion de Ejercitos");
    }

    public void actualizarVista(){
        System.out.println(this.juego.esElTurnoDe().obtenerColor() + " " +  this.juego.obtenerAccion());
        this.setLabelFase();
        this.setLabelTurno();
    }
}




