package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.ActualizarDestino;
import edu.fiuba.algo3.controladores.ActualizarOrigen;
import edu.fiuba.algo3.controladores.ColocarEjercitos;
import edu.fiuba.algo3.controladores.PasarAccion;
import edu.fiuba.algo3.excepciones.TegException;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import edu.fiuba.algo3.modelo.Juego;

public class ContenedorMapa {
    private VBox contenedorMapa;
    private static VistaTurno vistaTurno;
    private Juego juego;

    public ContenedorMapa(Juego juego) {
        this.juego = juego;
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");

        Label labelMapaTeg = this.setImagen();

        Button elegirPaisUno = new Button();
        Button elegirPaisDos = new Button();
        Button pasarAccion = new Button();
        Button ejecutarAccion = new Button();

        Label labelTurno = new Label();
        Label labelFase = new Label();

        ComboBox<String> boxOrigen = new ComboBox<>();

        ComboBox<String> boxDestino = new ComboBox<>();
        ComboBox<String> cbx = new ComboBox<>();

        Label labelCantidadEjercitosDestino = new Label();
        Label labelCantidadEjercitosOrigen = new Label();

        PasarAccion handler = new PasarAccion(this.juego, ejecutarAccion, boxOrigen, boxDestino, cbx);

        VBox contenedorPaisUno = new VBox(elegirPaisUno, boxOrigen, cbx, labelCantidadEjercitosOrigen);
        VBox contenedorPaisDos = new VBox(elegirPaisDos, boxDestino, labelCantidadEjercitosDestino);
        HBox contenedorTurno = new HBox(pasarAccion, labelTurno, labelFase, ejecutarAccion);

        HBox contenedorPaisesElegidos = new HBox(contenedorPaisUno, labelMapaTeg, contenedorPaisDos);

        VBox contenedorMapa = new VBox(contenedorPaisesElegidos,  contenedorTurno);

        ejecutarAccion.setCursor(Cursor.HAND);
        pasarAccion.setCursor(Cursor.HAND);

        this.contenedorMapa = contenedorMapa;
        this.setVisualBotones(elegirPaisUno, elegirPaisDos);
        this.setVisualContenedores(pasarAccion, ejecutarAccion, contenedorTurno, contenedorPaisUno, contenedorPaisDos, handler);
        this.setVistaTurno(labelTurno, labelFase, labelCantidadEjercitosOrigen, labelCantidadEjercitosDestino, boxOrigen, boxDestino, cbx);

        ActualizarOrigen actualizarPaisesDestinoHandler = new ActualizarOrigen(this.vistaTurno, juego);
        boxOrigen.setOnAction(actualizarPaisesDestinoHandler);
        ActualizarDestino actualizarEjercitosDestinoHandler = new ActualizarDestino(this.vistaTurno);
        boxDestino.setOnAction(actualizarEjercitosDestinoHandler);
    }

    private void setVistaTurno(Label labelTurno, Label labelFase, Label labelEjercitosOrigen, Label labelEjercitosDestino, ComboBox box, ComboBox boxDestino, ComboBox cbx) {
        try {
            this.vistaTurno = new VistaTurno(labelTurno, labelFase, labelEjercitosOrigen, labelEjercitosDestino, juego, box, boxDestino, cbx);
        } catch (TegException e) {
            e.printStackTrace();
        }
    }

    private void setVisualBotones(Button elegirPaisUno, Button elegirPaisDos){

        elegirPaisUno.setStyle("-fx-font-size: 20; -fx-min-width: 230; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        elegirPaisUno.setText("ATACANTE");
        elegirPaisUno.setTextFill(Color.WHITE);
        elegirPaisDos.setStyle("-fx-font-size: 20; -fx-min-width: 230;  -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        elegirPaisDos.setText("DEFENSOR");
        elegirPaisDos.setTextFill(Color.WHITE);
        }

    private void setVisualContenedores(Button pasarAccion, Button ejecutarAccion, HBox contenedorTurno, VBox contenedorPaisUno, VBox contenedorPaisDos, PasarAccion handler){
        pasarAccion.setText("PASAR ACCION");
        pasarAccion.setTextFill(Color.WHITE);
        pasarAccion.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        pasarAccion.setOnAction(handler);
        ejecutarAccion.setText("EJECUTAR ACCION");
        ejecutarAccion.setTextFill(Color.WHITE);
        ejecutarAccion.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        contenedorTurno.setAlignment(Pos.BOTTOM_LEFT);
        contenedorTurno.setSpacing(160);
        contenedorPaisUno.setSpacing(20);
        contenedorPaisDos.setSpacing(20);
        contenedorPaisUno.setAlignment(Pos.TOP_LEFT);
        contenedorPaisUno.setPadding(new Insets(0,15,0,0));
        contenedorPaisDos.setPadding(new Insets(0,0,0,15));
        contenedorPaisDos.setAlignment(Pos.TOP_RIGHT);
        contenedorMapa.setAlignment(Pos.TOP_CENTER);
        contenedorMapa.setSpacing(20);
        contenedorMapa.setPadding(new Insets(25));
        contenedorMapa.setStyle("-fx-background-color: #b18151");
    }

    private Label setImagen(){
        Label labelMapaTeg = new Label();
        Image imagenGlobo = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/mapaTeg.png");
        ImageView view = new ImageView(imagenGlobo);
        view.setPreserveRatio(true);
        labelMapaTeg.setGraphic(view);
        return labelMapaTeg;
    }

    public VBox obtenerContenedorMapa(){
        return this.contenedorMapa;
    }

    public static void actualizarVista(){
        vistaTurno.actualizarVista();
    }

}
