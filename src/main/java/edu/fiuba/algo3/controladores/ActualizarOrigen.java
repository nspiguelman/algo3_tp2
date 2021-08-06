package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.VistaComboBox;
import edu.fiuba.algo3.vistas.VistaLabel;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActualizarOrigen implements EventHandler<ActionEvent> {
    private VistaTurno vista;
    private VistaLabel vistaLabel;
    private VistaComboBox vistaBox;

    private final Juego juego;

    public ActualizarOrigen(VistaTurno vista, Juego juego, VistaComboBox vistaBox) {
        //this.vista = vista;
        this.vistaBox = vistaBox;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        int accion = this.juego.obtenerAccion();
        String accionAEjecutar;
        if (accion==2) {
            accionAEjecutar = "Reagrupar";
            //this.vista.setEjercitosReagrupar();
            this.vistaBox.setEjercitosReagrupar();
        } else {
            accionAEjecutar="Ataque";
        }
        this.vistaBox.setCantidadDeEjercitosOrigen();
        this.vistaBox.setPaisesJugadorDos(accionAEjecutar);
        //this.vista.setCantidadDeEjercitosOrigen();
        //this.vista.setPaisesJugadorDos(accionAEjecutar);
    }
}
