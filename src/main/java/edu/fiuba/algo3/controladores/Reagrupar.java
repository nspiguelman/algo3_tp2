package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import edu.fiuba.algo3.vistas.VistaComboBox;
import edu.fiuba.algo3.vistas.VistaLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class Reagrupar implements EventHandler<ActionEvent> {
    private final VistaComboBox vistaBox;
    private final Juego juego;
    private final ComboBox<String> paisOrigen;
    private final ComboBox<String> paisDestino;
    private final ComboBox<String> cantidadEjercitos;
    private final VistaLabel vistaLabel;

    public Reagrupar(Juego juego, ComboBox<String> paisesOrigen, ComboBox<String> paisesDestino, ComboBox<String> seleccionEjercitos, VistaLabel vistaLabel, VistaComboBox vistaBox) {
        this.juego = juego;
        this.paisOrigen = paisesOrigen;
        this.paisDestino = paisesDestino;
        this.cantidadEjercitos = seleccionEjercitos;
        this.vistaLabel = vistaLabel;
        this.vistaBox = vistaBox;
    }

    @Override
    public void handle(ActionEvent event) {
        Jugador jugadorActual = juego.turnoActual();
        Pais origen = buscarPais(paisOrigen.getValue());
        Pais destino = buscarPais(paisDestino.getValue());
        int ejercitosATransferir = Integer.parseInt(cantidadEjercitos.getValue());
        try {
            juego.reagrupar(jugadorActual, origen, destino, ejercitosATransferir);
        } catch (TegException e) {
            e.printStackTrace();
        }
        ContenedorMapa.actualizarVista();
        this.vistaLabel.limpiarJugadorDos();
    }

    public Pais buscarPais(String nombre){
        Jugador jugadorActual = juego.turnoActual();
        ArrayList<Pais> paises = jugadorActual.obtenerPaises();
        for (Pais pais: paises){
            if (pais.esElPais(nombre)){
                return pais;
            }
        }
        return new Pais("a", "b", "c");
    }

}
