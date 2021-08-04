package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.paises.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

public class VistaTurno {
    private Juego juego;
    private HashMap<String, String> colores = new HashMap<>();
    private HashMap<Integer, String> acciones = new HashMap<>();
    private Label labelFase;
    private Label labelTurno;
    private ComboBox boxPaisesOrigen;
    private ComboBox boxPaisesDestino;
    private ComboBox boxEjercitos;
    private int ejercitosFaseUno = 5;
    private int ejercitosFaseDos = 8;

    public VistaTurno(Label labelTurno, Label labelFase, Juego juego, ComboBox boxOrigen, ComboBox boxDestino, ComboBox cbx) throws TegException {
        this.juego = juego;
        this.labelFase = labelFase;
        this.labelTurno = labelTurno;
        this.boxPaisesOrigen = boxOrigen;
        this.boxPaisesDestino = boxDestino;
        this.boxEjercitos = cbx;
        this.inicializarColores();
        this.inicializarAcciones();
        this.setLabelTurno();
        this.setLabelFase();
        this.setSeleccionPaisesOrigen();
        this.setColocarEjercitos();
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

    public void setSeleccionPaisesOrigen(){
        ObservableList<String> items = FXCollections.observableArrayList();

        Jugador jugadorActual = this.juego.esElTurnoDe();

        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        for (int i=0; i<paisesJugador.size(); i++){
            Pais paisActual = paisesJugador.get(i);
            items.add(paisActual.obtenerNombrePais());
        }
        boxPaisesOrigen.setItems(items);
    }

    public void setSeleccionPaisesDestino(){
        ObservableList<String> itemsDestino = FXCollections.observableArrayList();
        Jugador jugadorActual = juego.esElTurnoDe();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        String paisOrigen = (String) boxPaisesOrigen.getValue();
        for (Pais pais: paisesJugador){
            if (pais.esElPais(paisOrigen)){
                ArrayList<String> limitrofes = pais.obtenerNombrePaisesLimitrofes();
                for (String paisLimitrofe: limitrofes){
                    itemsDestino.add(paisLimitrofe);
                }
            }
        }
        boxPaisesDestino.setItems(itemsDestino);
    }

    public void setColocarEjercitos() {
        Jugador jugadorActual = juego.esElTurnoDe();
        int ejercitosAMostrar;
        String faseActual = juego.obtenerFase();
        if (faseActual.equals("Juego")){
            ejercitosAMostrar = juego.obtenerEjercitosPorFase();
        }
        else{
            if (faseActual.equals("ColocacionUno")){
                ejercitosAMostrar = ejercitosFaseUno - jugadorActual.obtenerCantidadDeEjercitos();
            }
            else{
                ejercitosAMostrar = ejercitosFaseDos - jugadorActual.obtenerCantidadDeEjercitos();
            }
        }
        System.out.println(jugadorActual.obtenerColor() + " " + ejercitosAMostrar);
        ObservableList<String> ejercitos = FXCollections.observableArrayList();
        for (int i=0; i<ejercitosAMostrar; i++){
            ejercitos.add(String.valueOf(i + 1));
        }
        boxEjercitos.setItems(ejercitos);
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
        this.setLabelFase();
        this.setLabelTurno();
        this.setSeleccionPaisesOrigen();
        this.setColocarEjercitos();
    }
}




