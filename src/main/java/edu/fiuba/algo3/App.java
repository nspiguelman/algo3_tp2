package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.ContenedorEntrada;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

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
