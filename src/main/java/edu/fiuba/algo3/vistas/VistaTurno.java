package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.excepciones.PaisInvalidoException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.paises.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;

public class VistaTurno {
    private Juego juego;
    private HashMap<String, String> colores = new HashMap<>();
    private HashMap<Integer, String> acciones = new HashMap<>();
    private Label labelFase;
    private Label labelTurno;
    private Label labelEjercitosOrigen;
    private Label labelEjercitosDestino;
    private ComboBox boxPaisesOrigen;
    private ComboBox boxPaisesDestino;
    private ComboBox boxEjercitos;
    private int ejercitosFaseUno = 5;
    private int ejercitosFaseDos = 8;
    private int ejercitosEnFaseDeJuego;
    private ListView<Label> listaPaises;


    public VistaTurno(Label labelTurno, Label labelFase, Label ejercitosOrigen, Label ejercitosDestino, Juego juego, ComboBox boxOrigen, ComboBox boxDestino, ComboBox cbx, ListView<Label> listaPaises) throws TegException {
        this.juego = juego;
        this.labelFase = labelFase;
        this.labelTurno = labelTurno;
        this.boxPaisesOrigen = boxOrigen;
        this.boxPaisesDestino = boxDestino;
        this.labelEjercitosOrigen = ejercitosOrigen;
        this.labelEjercitosDestino = ejercitosDestino;
        this.boxEjercitos = cbx;
        this.inicializarColores();
        this.inicializarAcciones();
        this.setLabelTurno();
        this.setLabelFase();
        this.setLabelEjercitos();
        this.setSeleccionPaisesOrigen();
        this.setColocarEjercitos();
        this.setStyleComboBox();
        this.listaPaises = listaPaises;
    }

    private void setLabelEjercitos() {
        this.labelEjercitosOrigen.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-min-width: 150;-fx-fill-height: 240; -fx-padding: 6");
        this.labelEjercitosDestino.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-min-width: 150;-fx-fill-height: 240; -fx-padding: 6");
    }

    private void setLabelFase() {
        int accionActual = juego.obtenerAccion();
        String accion = acciones.get(accionActual);
        this.labelFase.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
    }

    private void setLabelTurno(){
        Jugador jugadorActual = this.juego.esElTurnoDe();
        String colorJugador = jugadorActual.obtenerColor();
        String colorLabel = this.colores.get(colorJugador);
        this.labelTurno.setText("TURNO: " + colorJugador);
        this.labelTurno.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color:" + colorLabel + "; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
    }

    private void setStyleComboBox(){
        this.boxPaisesOrigen.setStyle("-fx-font-size: 14; -fx-min-width: 150");
        this.boxPaisesDestino.setStyle("-fx-font-size: 14; -fx-min-width: 150");
        this.boxEjercitos.setStyle("-fx-font-size: 14; -fx-min-width: 150");
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

    public void setSeleccionPaisesDestino(String accion) {
        ObservableList<String> itemsDestino = FXCollections.observableArrayList();
        Jugador jugadorActual = juego.esElTurnoDe();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        String paisOrigen = (String) boxPaisesOrigen.getValue();
        Pais pais = this.buscarPais(paisOrigen);
        ArrayList<String> limitrofes = pais.obtenerNombrePaisesLimitrofes();
        for (String paisLimitrofe: limitrofes){
            if (accion.equals("Ataque") && !jugadorActual.tieneElPais(paisLimitrofe)){
                itemsDestino.add(paisLimitrofe);
            } else  {
                if (!accion.equals("Ataque") && jugadorActual.tieneElPais(paisLimitrofe)){
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
            ejercitosAMostrar = this.ejercitosEnFaseDeJuego;
        }
        else{
            ejercitosEnFaseDeJuego = 99;
            if (faseActual.equals("ColocacionUno")){
                ejercitosAMostrar = ejercitosFaseUno - jugadorActual.obtenerCantidadDeEjercitos();
                //jugadorActual.obtenerPaises().get(2).agregarEjercitos(ejercitosFaseUno);
            }
            else{
                ejercitosAMostrar = ejercitosFaseDos - jugadorActual.obtenerCantidadDeEjercitos();
                //jugadorActual.obtenerPaises().get(2).agregarEjercitos(3);
            }
        }
        ObservableList<String> ejercitos = FXCollections.observableArrayList();
        for (int i=0; i<ejercitosAMostrar; i++){
            ejercitos.add(String.valueOf(i + 1));
        }
        boxEjercitos.setItems(ejercitos);
    }

    public void fijarEjercitosPorFase() {
        this.ejercitosEnFaseDeJuego = juego.obtenerEjercitosPorFase();
    }

    public void actualizarEjercitosPorFase(int cantidadColocados) {
        this.ejercitosEnFaseDeJuego -= cantidadColocados;
    }

    public void setCantidadDeEjercitosOrigen() {
        String nombrePais = (String) boxPaisesOrigen.getValue();
        int ejercitos = this.buscarPais(nombrePais).obtenerEjercitos();
        this.labelEjercitosOrigen.setText("Ejercitos de " + nombrePais + ": " + ejercitos);
    }

    public void setCantidadDeEjercitosDestino() {
        String nombrePais = (String) boxPaisesDestino.getValue();
        int ejercitosDestino = this.buscarPais(nombrePais).obtenerEjercitos();
        this.labelEjercitosDestino.setText("Ejercitos de " + nombrePais + ": " + ejercitosDestino);
    }
    public void setEjercitosReagrupar() {
        String nombrePais = (String) boxPaisesOrigen.getValue();
        Pais pais = this.buscarPais(nombrePais);
        ObservableList<String> ejercitos = FXCollections.observableArrayList();
        for (int i=0; i<(pais.obtenerEjercitos() - 1); i++){
            ejercitos.add(String.valueOf(i + 1));
        }
        boxEjercitos.setItems(ejercitos);
    }

    public Pais buscarPais(String nombre){
        ArrayList<Pais> paises = juego.obtenerPaises();
        for (Pais pais: paises){
            if (pais.esElPais(nombre)){
                return pais;
            }
        }
        return new Pais("a", "b", "c");
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

    public void actualizarVista() {
        int accion = this.juego.obtenerAccion();
        String accionAEjecutar;
        if (accion == 2) {
            accionAEjecutar = "Reagrupar";
        } else{
            accionAEjecutar="Ataque";
        }
        this.setLabelFase();
        this.setLabelTurno();
        this.setSeleccionPaisesOrigen();
        this.setSeleccionPaisesDestino(accionAEjecutar);
        this.setColocarEjercitos();
        this.mostrarPaises();
    }

    public void mostrarPaises(){
        ArrayList<Pais> paisesJuego = this.juego.obtenerPaises();
        ObservableList<Label> paises = FXCollections.observableArrayList();

        for (Pais pais: paisesJuego){
            String nombrePais = pais.obtenerNombrePais();
            Label texto = new Label();
            texto.setText(nombrePais);
            String color = colores.get(this.obtenerJugador(nombrePais));
            texto.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: " + color + "; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-padding: 3; -fx-min-width:210");
            texto.setTextFill(Color.WHITE);
            paises.add(texto);
        }
        listaPaises.setItems(paises);
        listaPaises.setStyle("-fx-control-inner-background: #b18151; -fx-border-color: #b18151");
    }

    public String obtenerJugador(String unPais){
        for (Jugador n: juego.obtenerJugadores()){
            if (n.tieneElPais(unPais)) {
                return n.obtenerColor();
            }
        }
        return "Ningun jugador posee el ejercito";
    }
}




