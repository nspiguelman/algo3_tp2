package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActualizarDestino implements EventHandler<ActionEvent> {
    private VistaTurno vista;

    public ActualizarDestino(VistaTurno vista) {
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent event) {
        this.vista.setCantidadDeEjercitosDestino();
    }
}
