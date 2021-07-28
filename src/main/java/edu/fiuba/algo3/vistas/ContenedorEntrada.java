package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class ContenedorEntrada {
    private VBox contenedorEntrada;

    public ContenedorEntrada() {
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");


        //Image imagen = new Image("teg.jpeg");
        //final ImageView imagenVista = new ImageView(imagen);

        Label etiqueta = new Label();

        TextField texto = new TextField();
        texto.setPromptText("Jugador Uno");
        texto.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #0077bb; -fx-font-weight: 900; -fx-max-width: 200px");

        TextField texto2 = new TextField();
        texto2.setPromptText("Jugador Dos");
        texto2.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #cc3311;-fx-font-weight: 900; -fx-max-width: 200px");

        TextField texto3 = new TextField();
        texto3.setPromptText("Jugador Tres");
        texto3.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #ee7733;-fx-font-weight: 900; -fx-max-width: 200px");


        TextField texto4 = new TextField();
        texto4.setPromptText("Jugador Cuatro");
        texto4.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white  ; -fx-background-color: #009988;-fx-font-weight: 900; -fx-max-width: 200px");

        TextField texto5 = new TextField();
        texto5.setPromptText("Jugador Cinco");
        texto5.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white  ; -fx-background-color: #ee3377;-fx-font-weight: 900; -fx-max-width: 200px");

        TextField texto6 = new TextField();
        texto6.setPromptText("Jugador Seis");
        texto6.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #000000;-fx-font-weight: 900 ; -fx-max-width: 200px");

        Button botonIniciar = new Button();

        botonIniciar.setText("Iniciar Juego");
        botonIniciar.setAlignment(Pos.BOTTOM_CENTER);

        botonIniciar.setTranslateX(200);
        botonIniciar.setTranslateX(400);

        HBox contenedorHorizontal = new HBox(botonIniciar);

        VBox contenedorPrincipal = new VBox(texto, texto2, texto3, texto4, texto5, texto6, contenedorHorizontal, etiqueta);

        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setSpacing(20);
        contenedorPrincipal.setPadding(new Insets(25));

        this.contenedorEntrada = contenedorPrincipal;
    }

    public VBox obtenerContenedorEntrada(){
        return this.contenedorEntrada;
    }

}
