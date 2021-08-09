package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.VistaComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActualizarOrigen implements EventHandler<ActionEvent> {
    private final VistaComboBox vistaBox;

    private final Juego juego;

    public ActualizarOrigen(Juego juego, VistaComboBox vistaBox) {
        this.vistaBox = vistaBox;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        int accion = this.juego.obtenerAccion();
        String accionAEjecutar;
        if (accion==2) {
            accionAEjecutar = "Reagrupar";
            this.vistaBox.setEjercitosReagrupar();
        } else {
            accionAEjecutar="Ataque";
        }
        this.vistaBox.setPaisesJugadorDos(accionAEjecutar);
        this.vistaBox.setCantidadDeEjercitosOrigen();
    }
}
