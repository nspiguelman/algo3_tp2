package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.vistas.ContenedorEntrada;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import edu.fiuba.algo3.modelo.Juego;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {
    private Button unBoton;
    private ArrayList<String> jugadores = new ArrayList<>();
    public Stage stage;

    public BotonIniciarEventHandler(Boolean jugadorAzul, Boolean jugadorRojo, Boolean jugadorVerde, Boolean jugadorNaranja, Boolean jugadorNegro, Boolean jugadorRosa, Stage stage) throws FileNotFoundException, TegException {
        if (!jugadorAzul) {
            jugadores.add("Azul");
        }
        if (!jugadorRojo) {
            jugadores.add("Rojo");
        }
        if (jugadorVerde) {
            jugadores.add("Verde");
        }
        if (jugadorNaranja) {
            jugadores.add("Naranja");
        }
        if (jugadorNegro) {
            jugadores.add("Negro");
        }
        if (jugadorRosa) {
            jugadores.add("Rosa");
        }
        //Juego juego = new Juego(jugadores);
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ContenedorMapa contenedorEntrada = new ContenedorMapa();
        VBox contenedorPrincipal = contenedorEntrada.obtenerContenedorMapa();
        Scene escenaMapa = new Scene(contenedorPrincipal, 800, 600);
        this.stage.setScene(escenaMapa);
    }
}