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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class ContenedorMapa {
    private VBox contenedorMapa;

    public ContenedorMapa() {
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");

        Label labelMapaTeg = new Label();
        Image imagenGlobo = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/mapaTeg.png");
        ImageView view = new ImageView(imagenGlobo);
        view.setPreserveRatio(true);
        labelMapaTeg.setGraphic(view);

        VBox contenedorMapa = new VBox(labelMapaTeg);

        contenedorMapa.setAlignment(Pos.CENTER);
        contenedorMapa.setSpacing(20);
        contenedorMapa.setPadding(new Insets(25));
        contenedorMapa.setStyle("-fx-background-color: #b18151");

        this.contenedorMapa = contenedorMapa;
    }

    public VBox obtenerContenedorMapa(){
        return this.contenedorMapa;
    }

}
