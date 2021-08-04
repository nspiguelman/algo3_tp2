package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;

import java.util.ArrayList;


public class PasarAccion implements EventHandler<ActionEvent> {

    private Juego juego;

    public PasarAccion(Juego juego){
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        // Pasar a siguiente fase
        Jugador jugador = juego.esElTurnoDe();
        ArrayList<Jugador> jugadores = juego.obtenerJugadores();
        //Si es el ultimo jugador y no es fase de juego
        if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor()) && !juego.obtenerFase().equals("Juego")){
            try {
                juego.siguienteFase();
                juego.siguienteTurno();
            } catch (TegException e) {
                e.printStackTrace();
            }
        }
        else{
            this.juego.siguienteAccion();
        }

        ContenedorMapa.actualizarVista();
    }
}
