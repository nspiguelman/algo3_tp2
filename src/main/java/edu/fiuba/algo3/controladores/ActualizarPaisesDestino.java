package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActualizarPaisesDestino implements EventHandler<ActionEvent> {
    private VistaTurno vista;
    private Juego juego;

    public ActualizarPaisesDestino (VistaTurno vista, Juego juego) {
        this.vista = vista;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        int accion = this.juego.obtenerAccion();
        String accionAEjecutar;
        if (accion==2) {
            accionAEjecutar = "Reagrupar";
        } else {
            accionAEjecutar="Ataque";
        }
        this.vista.setSeleccionPaisesDestino(accionAEjecutar);
    }
}
