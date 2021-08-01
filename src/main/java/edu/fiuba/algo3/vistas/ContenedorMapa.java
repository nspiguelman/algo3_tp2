package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import edu.fiuba.algo3.controladores.BotonPasarAccionEventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.VistaTurno;

public class ContenedorMapa {
    private VBox contenedorMapa;
    private static VistaTurno vistaTurno;
    private Juego juego;

    public ContenedorMapa(Juego juego) {
        this.juego = juego;
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");

        //   IMAGEN MAPA
        Label labelMapaTeg = new Label();
        Image imagenGlobo = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/mapaTeg.png");
        ImageView view = new ImageView(imagenGlobo);
        view.setPreserveRatio(true);
        labelMapaTeg.setGraphic(view);

        // LABEL TURNO y FASE
        Label labelTurno = new Label();

        Label labelFase = new Label();

        this.setVistaTurno(labelTurno, labelFase);

        // PASAR ACCION
        Button pasarAccion = new Button();
        pasarAccion.setText("PASAR ACCION");
        pasarAccion.setTextFill(Color.WHITE);
        pasarAccion.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");

        HBox contenedorTurno = new HBox(pasarAccion ,labelTurno, labelFase);
        contenedorTurno.setAlignment(Pos.BOTTOM_LEFT);
        contenedorTurno.setSpacing(300);


        // ELEGIR PAISES
        Button elegirPaisUno = new Button();
        elegirPaisUno.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        elegirPaisUno.setText("Elegir Pais: ATACANTE");

        Label paisUno = new Label();
        paisUno.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-min-width: 30; -fx-min-height: 30 ; -fx-background-color: #000000; -fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        elegirPaisUno.setTextFill(Color.WHITE);
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("1", "2", "3", "4", "5", "6");
        ComboBox<String> cbx = new ComboBox<>(items);

        VBox contenedorPaisUno = new VBox(elegirPaisUno,paisUno, cbx);

        Button elegirPaisDos = new Button();
        elegirPaisDos.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        elegirPaisDos.setText("Elegir Pais: DEFENSOR");
        elegirPaisDos.setTextFill(Color.WHITE);
        Label paisDos = new Label();
        paisDos.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-min-width: 30; -fx-min-height: 30 ; -fx-background-color: #000000; -fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        VBox contenedorPaisDos = new VBox(elegirPaisDos,paisDos);

        contenedorPaisUno.setAlignment(Pos.TOP_LEFT);
        contenedorPaisDos.setAlignment(Pos.TOP_RIGHT);
        contenedorPaisUno.setSpacing(20);
        contenedorPaisDos.setSpacing(20);

        HBox contenedorPaisesElegidos = new HBox(contenedorPaisUno,labelMapaTeg,contenedorPaisDos);

        contenedorPaisesElegidos.setStyle("");


        VBox contenedorMapa = new VBox(contenedorPaisesElegidos,  contenedorTurno);


        contenedorMapa.setAlignment(Pos.TOP_CENTER);
        contenedorMapa.setSpacing(20);
        contenedorMapa.setPadding(new Insets(25));
        contenedorMapa.setStyle("-fx-background-color: #b18151");

        BotonPasarAccionEventHandler handler = new BotonPasarAccionEventHandler(this.juego);
        pasarAccion.setOnAction(handler);

        this.contenedorMapa = contenedorMapa;
    }

    private void setVistaTurno(Label labelTurno, Label labelFase) {
        this.vistaTurno = new VistaTurno(labelTurno, labelFase, juego);
    }

    public VBox obtenerContenedorMapa(){
        return this.contenedorMapa;
    }

    public static void actualizarVista(){
        vistaTurno.actualizarVista();
    }

}
