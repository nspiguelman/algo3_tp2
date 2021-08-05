package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ColocarEjercitos implements EventHandler<ActionEvent> {
    private Juego juego;
    private ComboBox<String> pais;
    private ComboBox<String> cantidadEjercitos;
    private VistaTurno vista;

    public ColocarEjercitos(Juego juego, ComboBox<String> box, ComboBox<String> cbx, VistaTurno vista) {
        this.juego = juego;
        this.pais = box;
        this.cantidadEjercitos = cbx;
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent event) {
        this.handler();
    }

    public void handler() {
        String nombrePais = pais.getValue();
        int ejercitos = Integer.parseInt(cantidadEjercitos.getValue());
        // TODO: validar que pasa cuando el combobox esta vacio.
        Jugador jugadorActual = juego.esElTurnoDe();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        for (Pais pais: paisesJugador){
            if (pais.esElPais(nombrePais)) {
                pais.agregarEjercitos(ejercitos);
            }
        }
        this.vista.actualizarEjercitosPorFase(ejercitos);
        ContenedorMapa.actualizarVista();
    }

}
