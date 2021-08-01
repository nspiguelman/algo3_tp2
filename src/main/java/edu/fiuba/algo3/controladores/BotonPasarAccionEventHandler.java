package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;

import java.util.ArrayList;


public class BotonPasarAccionEventHandler implements EventHandler<ActionEvent> {

    private Juego juego;

    public BotonPasarAccionEventHandler(Juego juego){
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        int ej = juego.obtenerEjercitosPorFase();
        Jugador jugador = juego.esElTurnoDe();
        ArrayList<Pais> paises = jugador.obtenerPaises();
        try {
            juego.agregarEjercitos(jugador, paises.get(2), ej);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        this.juego.siguienteAccion();
        if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor())){
            try {
                juego.siguienteFase();
            } catch (TegException e) {
                e.printStackTrace();
            }
        }
        ContenedorMapa.actualizarVista();
    }
}
