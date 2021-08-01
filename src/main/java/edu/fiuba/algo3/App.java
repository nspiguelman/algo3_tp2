package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.BotonIniciarEventHandler;
import edu.fiuba.algo3.vistas.ContenedorEntrada;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TEG");
        stage.setResizable(false);

        ContenedorEntrada contenedorEntrada = new ContenedorEntrada(stage);

        VBox contenedorPrincipal = contenedorEntrada.obtenerContenedorEntrada();
        Scene escenaJuego = new Scene(contenedorPrincipal, 800, 600);
        stage.setScene(escenaJuego);
        contenedorEntrada.iniciarJuego(stage);


        stage.show();

    }

    public static void main(String[] args) { launch(); }

}
