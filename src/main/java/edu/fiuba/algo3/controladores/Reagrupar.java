package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
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
        Jugador jugadorActual = juego.esElTurnoDe();
        Pais origen = buscarPais(paisOrigen.getValue());
        Pais destino = buscarPais(paisDestino.getValue());
        int ejercitosATransferir = Integer.parseInt(cantidadEjercitos.getValue());
        try {
            juego.reagrupar(jugadorActual, origen, destino, ejercitosATransferir);
        } catch (TegException e) {
            e.printStackTrace();
        }
        ContenedorMapa.actualizarVista();
    }

    public Pais buscarPais(String nombre){
        Jugador jugadorActual = juego.esElTurnoDe();
        ArrayList<Pais> paises = jugadorActual.obtenerPaises();
        for (Pais pais: paises){
            if (pais.esElPais(nombre)){
                return pais;
            }
        }
        return new Pais("a", "b", "c");
    }

}