package edu.fiuba.algo3.vistas;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class VistaAtaque implements VistaAccion {
    private final Label faseActual;
    private final Label ejercitosJugadorUno;
    private final Label ejercitosJugadorDos;
    private final ComboBox<String> paisesJugadorUno;
    private final ComboBox<String> paisesJugadorDos;
    private final ComboBox<String> cantidadEjercitosJugadorUno;
    private final Label vistaJugadorUno;
    private final Label vistaJugadorDos;

    public VistaAtaque(Label faseActual,Label ejercitosJugadorUno, Label ejercitosJugadorDos, ComboBox<String> paisesJugadorUno,
                       ComboBox<String> paisesJugadorDos, ComboBox<String> cantidadEjercitosJugadorUno, Label vistaJugadorUno, Label vistaJugadorDos) {
        this.faseActual = faseActual;
        this.paisesJugadorUno = paisesJugadorUno;
        this.paisesJugadorDos = paisesJugadorDos;
        this.ejercitosJugadorUno = ejercitosJugadorUno;
        this.ejercitosJugadorDos = ejercitosJugadorDos;
        this.cantidadEjercitosJugadorUno = cantidadEjercitosJugadorUno;
        this.vistaJugadorUno = vistaJugadorUno;
        this.vistaJugadorDos = vistaJugadorDos;
    }

    public void activar() {
        this.vistaJugadorUno.setText("ATACANTE");
        this.vistaJugadorDos.setText("DEFENSOR");
        this.faseActual.setText("FASE: Ataque");
        this.ejercitosJugadorUno.setText("");
        this.ejercitosJugadorDos.setText("");
        this.cantidadEjercitosJugadorUno.setDisable(true);
        this.paisesJugadorUno.getSelectionModel().clearSelection();
        this.paisesJugadorDos.getSelectionModel().clearSelection();
        this.paisesJugadorUno.setDisable(false);
        this.paisesJugadorDos.setDisable(false);
        this.ejercitosJugadorUno.setVisible(true);
        this.ejercitosJugadorDos.setVisible(true);
    }
}
