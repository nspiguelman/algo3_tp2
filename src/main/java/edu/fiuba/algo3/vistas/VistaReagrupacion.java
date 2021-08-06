package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.excepciones.TegException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class VistaReagrupacion implements VistaAccion{
    private final Label faseActual;
    private final Label ejercitosJugadorUno;
    private final Label ejercitosJugadorDos;
    private final ComboBox<String> paisesJugadorUno;
    private final ComboBox<String> paisesJugadorDos;
    private final ComboBox<String> cantidadEjercitosJugadorUno;
    private final Label vistaJugadorUno;
    private final Label vistaJugadorDos;

    public VistaReagrupacion(Label faseActual,Label ejercitosJugadorUno, Label ejercitosJugadorDos, ComboBox<String> paisesJugadorUno,
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
        this.vistaJugadorUno.setText("ORIGEN");
        this.vistaJugadorDos.setText("DESTINO");
        this.faseActual.setText("FASE: Reagrupar");
        this.ejercitosJugadorUno.setText("");
        this.ejercitosJugadorUno.setVisible(true);
        this.ejercitosJugadorDos.setVisible(true);
        this.ejercitosJugadorDos.setText("");
        this.paisesJugadorUno.getSelectionModel().clearSelection();
        this.paisesJugadorUno.setDisable(false);
        this.paisesJugadorDos.getSelectionModel().clearSelection();
        this.paisesJugadorDos.setDisable(false);
        this.cantidadEjercitosJugadorUno.setDisable(false);
    }
}


