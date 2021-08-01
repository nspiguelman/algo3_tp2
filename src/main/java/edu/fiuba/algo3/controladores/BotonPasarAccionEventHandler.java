package edu.fiuba.algo3.controladores;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;


public class BotonPasarAccionEventHandler implements EventHandler<ActionEvent> {

    private Juego juego;

    public BotonPasarAccionEventHandler(Juego juego){
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        this.juego.siguienteAccion();
        ContenedorMapa.actualizarVista();
    }
}
