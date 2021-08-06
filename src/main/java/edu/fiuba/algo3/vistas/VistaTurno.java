package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.paises.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class VistaTurno {
    private final Juego juego;
    private final HashMap<String, String> colores = new HashMap<>();
    private final HashMap<Integer, String> acciones = new HashMap<>();
    private final Label turnoActual;
    private final Label cantidadEjercitosJugadorUno;
    private final Label cantidadEjercitosJugadorDos;
    private final Label objetivoJugador;
    private final ComboBox<String> paisesJugadorUno;
    private final ComboBox<String> paisesJugadorDos;
    private final ComboBox<String> seleccionEjercitosJugadorUno;
    private final ListView<Label> listaPaises;
    private final Label faseActual;
    private final Button pasarAccion;
    private final Button ejecutarAccion;
    private int ejercitosEnFaseDeJuego;

    public VistaTurno(Label turnoActual, Label faseActual, Label cantidadEjercitosJugadorUno, Label cantidadEjercitosJugadorDos, Juego juego, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos,
                      ComboBox<String> seleccionEjercitosJugadorUno, ListView<Label> listaPaisesJuego, Label objetivoJugador, Button ejecutarAccion, Button pasarAccion) throws TegException {
        this.juego = juego;
        this.faseActual = faseActual;
        this.turnoActual = turnoActual;
        this.cantidadEjercitosJugadorUno = cantidadEjercitosJugadorUno;
        this.cantidadEjercitosJugadorDos = cantidadEjercitosJugadorDos;
        this.objetivoJugador = objetivoJugador;
        this.paisesJugadorUno = paisesJugadorUno;
        this.paisesJugadorDos = paisesJugadorDos;
        this.seleccionEjercitosJugadorUno = seleccionEjercitosJugadorUno;
        this.listaPaises = listaPaisesJuego;
        this.ejecutarAccion = ejecutarAccion;
        this.pasarAccion = pasarAccion;
        this.inicializarColores();
        this.inicializarAcciones();
        this.setLabelTurno();
        this.setPaisesJugadorUno();
        this.setColocarEjercitos();
    }

    private void setLabelTurno() {
        Jugador jugadorActual = this.juego.turnoActual();
        String colorJugador = jugadorActual.obtenerColor();
        String colorLabel = this.colores.get(colorJugador);
        this.turnoActual.setText("TURNO: " + colorJugador);
        this.turnoActual.setStyle("-fx-font-size: 20; -fx-font-weight: 800;  -fx-background-color: " + colorLabel + "; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
    }

    public void setPaisesJugadorUno() {
        ObservableList<String> items = FXCollections.observableArrayList();
        Jugador jugadorActual = this.juego.turnoActual();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();

        for (int i=0; i<paisesJugador.size(); i++) {
            Pais paisActual = paisesJugador.get(i);
            items.add(paisActual.obtenerNombrePais());
        }
        paisesJugadorUno.setItems(items);
    }

    public void setPaisesJugadorDos(String accion) {
        ObservableList<String> itemsDestino = FXCollections.observableArrayList();
        Jugador jugadorActual = juego.turnoActual();
        String paisOrigen = (String) paisesJugadorUno.getValue();
        Pais pais = this.buscarPais(paisOrigen);
        ArrayList<String> limitrofes = pais.obtenerNombrePaisesLimitrofes();
        for (String paisLimitrofe: limitrofes) {
            if (accion.equals("Ataque") && !jugadorActual.tieneElPais(paisLimitrofe)) {
                itemsDestino.add(paisLimitrofe);
            } else  {
                if (!accion.equals("Ataque") && jugadorActual.tieneElPais(paisLimitrofe)) {
                    itemsDestino.add(paisLimitrofe);
                }
            }
        }
        paisesJugadorDos.setItems(itemsDestino);
    }

    public void setColocarEjercitos() {
        int ejercitosFaseUno = 5;
        int ejercitosFaseDos = 8;
        Jugador jugadorActual = juego.turnoActual();
        int ejercitosAMostrar;
        String faseActual = juego.obtenerFase();
        if (faseActual.equals("Juego")) {
            ejercitosAMostrar = this.ejercitosEnFaseDeJuego;

        } else {
            ejercitosEnFaseDeJuego = 99;
            if (faseActual.equals("ColocacionUno")){
                ejercitosAMostrar = ejercitosFaseUno - jugadorActual.obtenerCantidadDeEjercitos();
            } else {
                ejercitosAMostrar = ejercitosFaseDos - jugadorActual.obtenerCantidadDeEjercitos();
            }
        }
        ObservableList<String> ejercitos = FXCollections.observableArrayList();
        for (int i=0; i < ejercitosAMostrar; i++) {
            ejercitos.add(String.valueOf(i + 1));
        }
        seleccionEjercitosJugadorUno.setItems(ejercitos);
    }

    public void fijarEjercitosPorFase() {
        this.ejercitosEnFaseDeJuego = juego.obtenerEjercitosPorFase();
    }

    public void actualizarEjercitosPorFase(int cantidadColocados) {
        this.ejercitosEnFaseDeJuego -= cantidadColocados;
    }

    public void setCantidadDeEjercitosOrigen() {
        String nombrePais = paisesJugadorUno.getValue();
        int ejercitos = this.buscarPais(nombrePais).obtenerEjercitos();
        this.cantidadEjercitosJugadorUno.setText("Ejercitos de " + nombrePais + ": " + ejercitos);
    }

    public void setCantidadDeEjercitosDestino() {
        String nombrePais = paisesJugadorDos.getValue();
        int ejercitosDestino = this.buscarPais(nombrePais).obtenerEjercitos();
        this.cantidadEjercitosJugadorDos.setText("Ejercitos de " + nombrePais + ": " + ejercitosDestino);
    }

    public void setEjercitosReagrupar() {
        String nombrePais = paisesJugadorUno.getValue();
        Pais pais = this.buscarPais(nombrePais);
        ObservableList<String> ejercitos = FXCollections.observableArrayList();

        for (int i=0; i < (pais.obtenerEjercitos() - 1); i++) {
            ejercitos.add(String.valueOf(i + 1));
        }
        seleccionEjercitosJugadorUno.setItems(ejercitos);
    }

    public Pais buscarPais(String nombre) {
        ArrayList<Pais> paises = juego.obtenerPaises();
        for (Pais pais: paises) {
            if (pais.esElPais(nombre)) {
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
        this.setLabelTurno();
        this.setPaisesJugadorUno();
        this.setPaisesJugadorDos(accionAEjecutar);
        this.setColocarEjercitos();
        this.mostrarPaises();
        this.objetivoJugador.setText(juego.turnoActual().obtenerDescripcionObjetivo());
    }

    public void mostrarPaises() {
        ArrayList<Pais> paisesJuego = this.juego.obtenerPaises();
        ObservableList<Label> paises = FXCollections.observableArrayList();

        for (Pais pais: paisesJuego) {
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

    public String obtenerJugador(String unPais) {
        for (Jugador n: juego.obtenerJugadores()) {
            if (n.tieneElPais(unPais)) {
                return n.obtenerColor();
            }
        }
        return "Ningun jugador posee el ejercito";
    }

    public void finalizarJuego() {
        this.faseActual.setText("FASE: GAME OVER");
        this.limpiarJugadorUno();
        this.limpiarJugadorDos();
        this.paisesJugadorUno.setDisable(true);
        this.paisesJugadorDos.setDisable(true);
        this.cantidadEjercitosJugadorUno.setDisable(true);
        this.cantidadEjercitosJugadorDos.setDisable(true);
        this.seleccionEjercitosJugadorUno.setDisable(true);
        this.ejecutarAccion.setDisable(true);
        this.pasarAccion.setDisable(true);
        this.paisesJugadorUno.setDisable(false);
        this.paisesJugadorDos.setDisable(false);
        this.objetivoJugador.setStyle("-fx-min-height: 160 ;-fx-wrap-text: true ; -fx-max-width: 240; -fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #CAE33D; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 200; -fx-padding: 6");
        this.objetivoJugador.setText("GANADOR: " + this.juego.turnoActual().obtenerColor());
    }


    public void limpiarJugadorUno(){
        this.cantidadEjercitosJugadorUno.setText("");
    }

    public void limpiarJugadorDos(){
        this.cantidadEjercitosJugadorDos.setText("");
    }
}




