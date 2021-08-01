package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.vistas.ContenedorEntrada;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import edu.fiuba.algo3.modelo.Juego;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {
    private Button unBoton;
    private ArrayList<String> jugadores = new ArrayList<>();
    private ArrayList<String> colores;
    private ArrayList<CheckBox> checkboxes;
    public Stage stage;
    public Label validacion;

    public BotonIniciarEventHandler(ArrayList<CheckBox> checkboxes, Stage stage, Label validacion) {
        this.colores = new ArrayList<>(Arrays.asList("Azul", "Rojo", "Verde", "Naranja", "Negro", "Rosa"));
        this.checkboxes = checkboxes;

        this.stage = stage;
        this.validacion = validacion;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.agregarJugadores();
        if (!this.arrancarJuego()) {
            this.validacion.setText("Cantidad de Jugadores invalida. ");
            System.out.println("asd");
        }

        stage.setX(100);
        stage.setY(10);
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
    }


    private void agregarJugadores() {
        for (int i = 0; i < this.checkboxes.size(); i++) {
            if (this.checkboxes.get(i).isSelected()) {
                this.jugadores.add(colores.get(i));
            }
        }
    }

    public boolean arrancarJuego() {
        if (this.jugadores.size() > 1) {
            try {
                Juego juego = new Juego(this.jugadores);
                ContenedorMapa contenedorEntrada = new ContenedorMapa(juego);
                VBox contenedorPrincipal = contenedorEntrada.obtenerContenedorMapa();
                Scene escenaMapa = new Scene(contenedorPrincipal);
                this.stage.setScene(escenaMapa);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }
}