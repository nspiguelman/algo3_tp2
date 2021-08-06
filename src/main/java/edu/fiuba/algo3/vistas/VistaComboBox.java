package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.paises.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class VistaComboBox {
    private final Juego juego;
    private final ComboBox<String> paisesJugadorUno;
    private final ComboBox<String> paisesJugadorDos;
    private final ComboBox<String> seleccionEjercitosJugadorUno;
    private final Label cantidadEjercitosJugadorUno;
    private final Label cantidadEjercitosJugadorDos;
    private int ejercitosEnFaseDeJuego;

    public VistaComboBox(Juego juego, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos,
                      ComboBox<String> seleccionEjercitosJugadorUno, Label cantidadEjercitosJugadorUno, Label cantidadEjercitosJugadorDos) {
        this.juego = juego;
        this.paisesJugadorUno = paisesJugadorUno;
        this.paisesJugadorDos = paisesJugadorDos;
        this.cantidadEjercitosJugadorUno = cantidadEjercitosJugadorUno;
        this.cantidadEjercitosJugadorDos = cantidadEjercitosJugadorDos;
        this.seleccionEjercitosJugadorUno = seleccionEjercitosJugadorUno;
        this.setPaisesJugadorUno();
        this.setColocarEjercitos();
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

    public void actualizar() {
        int accion = this.juego.obtenerAccion();
        String accionAEjecutar;
        if (accion == 2) {
            accionAEjecutar = "Reagrupar";
        } else{
            accionAEjecutar="Ataque";
        }
        this.setPaisesJugadorUno();
        this.setPaisesJugadorDos(accionAEjecutar);
        this.setColocarEjercitos();
    }

    public void finalizarJuego() {
        this.paisesJugadorUno.setDisable(true);
        this.paisesJugadorDos.setDisable(true);
        this.cantidadEjercitosJugadorUno.setDisable(true);
        this.cantidadEjercitosJugadorDos.setDisable(true);
        this.seleccionEjercitosJugadorUno.setDisable(true);
        this.paisesJugadorUno.setDisable(false);
        this.paisesJugadorDos.setDisable(false);
    }
}




