package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class Reagrupar implements EventHandler<ActionEvent> {
    private Juego juego;
    private ComboBox<String> paisOrigen;
    private ComboBox<String> paisDestino;
    private ComboBox<String> cantidadEjercitos;


    public Reagrupar(Juego juego, ComboBox<String> boxOrigen, ComboBox<String> boxDestino, ComboBox<String> boxCantidad) {
        this.juego = juego;
        this.paisOrigen = boxOrigen;
        this.paisDestino = boxDestino;
        this.cantidadEjercitos = boxCantidad;
    }

    @Override
    public void handle(ActionEvent event) {
        String nombrePais = paisDestino.getValue();
        int ejercitos = Integer.parseInt(cantidadEjercitos.getValue());
        Jugador jugadorActual = juego.esElTurnoDe();
        ArrayList<Pais> paisesJugador = jugadorActual.obtenerPaises();
        for (Pais pais: paisesJugador){
            if (pais.esElPais(nombrePais)){
                pais.agregarEjercitos(ejercitos);
            }
        }
        ContenedorMapa.actualizarVista();
    }
}
