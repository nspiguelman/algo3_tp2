package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vistas.VistaComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActualizarDestino implements EventHandler<ActionEvent> {
    private final VistaComboBox vistaBox;

    public ActualizarDestino(VistaComboBox vistaBox) {
        this.vistaBox = vistaBox;
    }

    @Override
    public void handle(ActionEvent event) {
        this.vistaBox.setCantidadDeEjercitosDestino();
    }
}
