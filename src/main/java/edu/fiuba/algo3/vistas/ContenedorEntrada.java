package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import edu.fiuba.algo3.excepciones.TegException;
import javafx.collections.ArrayChangeListener;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContenedorEntrada {
    private VBox contenedorEntrada;
    private int botonCordX = 0;
    private int botonCordY = 0;
    private ArrayList<CheckBox> checkboxes = new ArrayList<>();


    public ContenedorEntrada(Stage stage) throws FileNotFoundException, TegException {
        //StackPane layout = new Stac
        Image imagen = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/tegLogo.png");
        BackgroundPosition backgroundImagePosition = new BackgroundPosition(null, 410,false,null, 20, false);

        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backgroundImagePosition, BackgroundSize.DEFAULT);
        //final ImageView imagenVista = new ImageView(imagen);




        Label etiqueta = new Label();
        CheckBox jugadorAzul = new CheckBox("Azul");
        jugadorAzul.setIndeterminate(false);
        jugadorAzul.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #0077bb; -fx-font-weight: 900; -fx-max-width: 200px");

        CheckBox jugadorRojo = new CheckBox("Rojo");
        jugadorRojo.setIndeterminate(false);
        jugadorRojo.setStyle("-fx-text-box-border: black; -fx-font-scale: 20 ; -fx-text-fill: white ; -fx-background-color: #cc3311; -fx-font-weight: 900; -fx-max-width: 200px");

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

        checkboxes.add(jugadorAzul);
        checkboxes.add(jugadorRojo);
        checkboxes.add(jugadorVerde);
        checkboxes.add(jugadorNaranja);
        checkboxes.add(jugadorNegro);
        checkboxes.add(jugadorRosa);


        //botonIniciar.setOnAction(handler);
        //HBox contenedorHorizontal = new HBox(botonIniciar);
        //contenedorHorizontal.setBackground(new Background(imagenDeFondo));

        Label labelLogoTeg = new Label();
        Image imagenGlobo = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/tegLogo.png");
        ImageView view = new ImageView(imagenGlobo);
        view.setFitHeight(200);      //para achicar la imagen
        view.setPreserveRatio(true);
        labelLogoTeg.setGraphic(view);
        labelLogoTeg.setTranslateX(0);
        labelLogoTeg.setTranslateY(0);


        VBox contenedorPrincipal = new VBox(labelLogoTeg,jugadorAzul, jugadorRojo, jugadorVerde, jugadorNaranja, jugadorNegro, jugadorRosa, etiqueta);

        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setSpacing(20);
        contenedorPrincipal.setPadding(new Insets(25));
        contenedorPrincipal.setStyle("-fx-background-color: #b18151");



        this.contenedorEntrada = contenedorPrincipal;
    }

    public void iniciarJuego(Stage stage) throws TegException, FileNotFoundException{
        Button botonIniciar = new Button();
        Label validacion = new Label();
        validacion.setText("");

        botonIniciar.setText("Iniciar Juego");
        botonIniciar.setAlignment(Pos.CENTER);

        botonIniciar.setTranslateX(botonCordX);
        botonIniciar.setTranslateX(botonCordY);
        BotonIniciarEventHandler handler = new BotonIniciarEventHandler(checkboxes, stage, validacion);
        botonIniciar.setOnAction(handler);

        this.contenedorEntrada.getChildren().add(botonIniciar);
    }

    public VBox obtenerContenedorEntrada(){
        return this.contenedorEntrada;
    }

}
