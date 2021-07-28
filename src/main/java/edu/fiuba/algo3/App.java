package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import edu.fiuba.algo3.vista.ContenedorEntrada;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TEG");
        StackPane layout = new StackPane();

        Label label = new Label();
        label.setText("Bienvenido");
        layout.getChildren().add(label);

        Button button = new Button();
        button.setText("2 Jugadores");

        Button button2 = new Button();
        button2.setText("3 Jugadores");

        Button button3 = new Button();
        button3.setText("4 Jugadores");

        Button button4 = new Button();
        button4.setText("5 Jugadores");

        Button button5 = new Button();
        button5.setText("6 Jugadores");

        Button botonIniciar = new Button();
        botonIniciar.setText("Iniciar Juego");

        BotonIniciarEventHandler botonIniciarEventHandler = new BotonIniciarEventHandler(botonIniciar);
        botonIniciar.setOnAction(botonIniciarEventHandler);

        HBox contenedorPrincipal = new HBox(button, button2, button3, button4, button5, botonIniciar);
        
        contenedorPrincipal.setSpacing(10);

        /*layout.getChildren().add(button);
        layout.getChildren().add(button2);
        layout.getChildren().add(button3);
         */



        Scene escenaJuego = new Scene(contenedorPrincipal, 720, 220);
        stage.setScene(escenaJuego);
        stage.show();
    }

    public static void main(String[] args) { launch(); }

}
