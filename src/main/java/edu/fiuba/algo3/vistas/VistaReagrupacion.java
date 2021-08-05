package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.excepciones.TegException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class VistaReagrupacion implements VistaAccion{
    private Label labelFase;
    private Label labelEjercitosOrigen;
    private Label labelEjercitosDestino;
    private ComboBox boxPaisesOrigen;
    private ComboBox boxPaisesDestino;
    private ComboBox ejercitosOrigen;

    public VistaReagrupacion(Label labelFase,Label ejercitosOrigen, Label ejercitosDestino, ComboBox boxOrigen, ComboBox boxDestino, ComboBox boxEjercitos) {
        this.labelFase = labelFase;
        this.boxPaisesOrigen = boxOrigen;
        this.boxPaisesDestino = boxDestino;
        this.labelEjercitosOrigen = ejercitosOrigen;
        this.labelEjercitosDestino = ejercitosDestino;
        this.ejercitosOrigen = boxEjercitos;
    }

    public void activar(){
        this.labelFase.setText("FASE: Reagrupar");
        this.labelEjercitosOrigen.setText("");
        this.labelEjercitosDestino.setText("");
        this.boxPaisesOrigen.getSelectionModel().clearSelection();
        this.boxPaisesDestino.getSelectionModel().clearSelection();
        this.ejercitosOrigen.setDisable(false);
        this.boxPaisesOrigen.setDisable(false);
        this.boxPaisesDestino.setDisable(false);
        this.labelEjercitosOrigen.setVisible(true);
        this.labelEjercitosDestino.setVisible(true);
    }
}


