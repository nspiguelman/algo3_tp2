package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class ContenedorMapa {
    private VBox contenedorMapa;
    private int botonCordX = 200;
    private int botonCordY = 400;

    public ContenedorMapa() {
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");

        //Image imagen = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/teg.jpeg");
        //BackgroundPosition backgroundImagePosition = new BackgroundPosition(null, 300,false,null, 20, false);

        //BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backgroundImagePosition, BackgroundSize.DEFAULT);
        //final ImageView imagenVista = new ImageView(imagen);

        Label etiqueta = new Label();

        Button botonIniciar = new Button();

        botonIniciar.setText("Pasar Turno");
        botonIniciar.setAlignment(Pos.BOTTOM_CENTER);

        botonIniciar.setTranslateX(botonCordX);
        botonIniciar.setTranslateX(botonCordY);

        //botonIniciar.setOnAction(handler);
        HBox contenedorHorizontal = new HBox(botonIniciar);

        VBox contenedorMapa = new VBox(contenedorHorizontal, etiqueta);

        contenedorMapa.setAlignment(Pos.CENTER);
        contenedorMapa.setSpacing(20);
        contenedorMapa.setPadding(new Insets(25));
        //contenedorMapa.setBackground(new Background(imagenDeFondo));

        this.contenedorMapa = contenedorMapa;
    }

    public VBox obtenerContenedorMapa(){
        return this.contenedorMapa;
    }

}
