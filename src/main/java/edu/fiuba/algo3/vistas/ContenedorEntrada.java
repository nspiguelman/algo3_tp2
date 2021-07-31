package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import edu.fiuba.algo3.excepciones.TegException;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ContenedorEntrada {
    private VBox contenedorEntrada;
    private int botonCordX = 200;
    private int botonCordY = 400;

    public ContenedorEntrada(Stage stage) throws FileNotFoundException, TegException {
        //StackPane layout = new Stac
        Image imagen = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/teg.jpeg");
        BackgroundPosition backgroundImagePosition = new BackgroundPosition(null, 410,false,null, 20, false);

        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backgroundImagePosition, BackgroundSize.DEFAULT);
        //final ImageView imagenVista = new ImageView(imagen);

        Label etiqueta = new Label();

        CheckBox jugadorAzul = new CheckBox("Azul");
        jugadorAzul.setIndeterminate(false);
        jugadorAzul.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #0077bb; -fx-font-weight: 900; -fx-max-width: 200px");

        CheckBox jugadorRojo = new CheckBox("Rojo");
        jugadorRojo.setIndeterminate(false);
        jugadorRojo.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #cc3311;-fx-font-weight: 900; -fx-max-width: 200px");

        CheckBox jugadorVerde = new CheckBox("Verde");
        jugadorVerde.setIndeterminate(false);
        jugadorVerde.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white  ; -fx-background-color: #009988;-fx-font-weight: 900; -fx-max-width: 200px");

        CheckBox jugadorNaranja = new CheckBox("Naranja");
        jugadorNaranja.setIndeterminate(false);
        jugadorNaranja.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #ee7733;-fx-font-weight: 900; -fx-max-width: 200px");

        CheckBox jugadorNegro = new CheckBox("Negro");
        jugadorNegro.setIndeterminate(false);
        jugadorNegro.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #000000;-fx-font-weight: 900 ; -fx-max-width: 200px");

        CheckBox jugadorRosa = new CheckBox("Rosa");
        jugadorRosa.setIndeterminate(false);
        jugadorRosa.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white  ; -fx-background-color: #ee3377;-fx-font-weight: 900; -fx-max-width: 200px");


        Button botonIniciar = new Button();

        botonIniciar.setText("Iniciar Juego");
        botonIniciar.setAlignment(Pos.BOTTOM_CENTER);

        botonIniciar.setTranslateX(botonCordX);
        botonIniciar.setTranslateX(botonCordY);
        BotonIniciarEventHandler handler = new BotonIniciarEventHandler(
                jugadorAzul.isSelected(),
                jugadorRojo.isSelected(),
                jugadorVerde.isSelected(),
                jugadorNaranja.isSelected(),
                jugadorNegro.isSelected(),
                jugadorRosa.isSelected(),
                stage
        );
        //botonIniciar.setOnAction(handler);
        HBox contenedorHorizontal = new HBox(botonIniciar);
        contenedorHorizontal.setBackground(new Background(imagenDeFondo));

        VBox contenedorPrincipal = new VBox(contenedorHorizontal, botonIniciar, jugadorAzul, jugadorRojo, jugadorVerde, jugadorNaranja, jugadorNegro, jugadorRosa, etiqueta);

        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setSpacing(20);
        contenedorPrincipal.setPadding(new Insets(25));
        contenedorPrincipal.setStyle("-fx-background-color: #b18151");

        this.contenedorEntrada = contenedorPrincipal;
    }

    public VBox obtenerContenedorEntrada(){
        return this.contenedorEntrada;
    }

}
