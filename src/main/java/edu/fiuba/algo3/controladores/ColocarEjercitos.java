package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import edu.fiuba.algo3.vistas.VistaComboBox;
import edu.fiuba.algo3.vistas.VistaLabel;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ColocarEjercitos implements EventHandler<ActionEvent> {
    private final Juego juego;
    private final ComboBox<String> pais;
    private final VistaComboBox vistaBox;
    private ComboBox<String> cantidadEjercitos;
    private final VistaTurno vista;

    public ColocarEjercitos(Juego juego, ComboBox<String> paisesJugador, ComboBox<String> cantidadEjercitos, VistaTurno vista,
                            VistaComboBox vistaBox) {
        this.juego = juego;
        this.pais = paisesJugador;
        this.cantidadEjercitos = cantidadEjercitos;
        this.vista = vista;
        this.vistaBox = vistaBox;
    }

    @Override
    public void handle(ActionEvent event) {
        this.handler();
    }

    public void handler() {
        String nombrePais = pais.getValue();
        int ejercitos = Integer.parseInt(cantidadEjercitos.getValue());
        // TODO: validar que pasa cuando el combobox esta vacio.
        Jugador jugadorActual = juego.turnoActual();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        for (Pais pais: paisesJugador){
            if (pais.esElPais(nombrePais)) {
                pais.agregarEjercitos(ejercitos);
            }
        }
        this.vistaBox.actualizarEjercitosPorFase(ejercitos);
       // this.vista.actualizarEjercitosPorFase(ejercitos);
        ContenedorMapa.actualizarVista();
    }

}
