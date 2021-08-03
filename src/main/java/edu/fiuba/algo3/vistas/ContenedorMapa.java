package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controladores.ColocarEjercitos;
import edu.fiuba.algo3.controladores.PasarAccion;
import edu.fiuba.algo3.excepciones.TegException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import edu.fiuba.algo3.modelo.Juego;
import org.w3c.dom.events.MouseEvent;

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
        Label paisDos = new Label();

        ComboBox<String> box = new ComboBox<>();
        ComboBox<String> cbx = new ComboBox<>();

        PasarAccion handler = new PasarAccion(this.juego);
        ColocarEjercitos handlerDos = new ColocarEjercitos(this.juego, box, cbx);

        VBox contenedorPaisUno = new VBox(elegirPaisUno, box, cbx);
        VBox contenedorPaisDos = new VBox(elegirPaisDos,paisDos);
        HBox contenedorTurno = new HBox(pasarAccion, labelTurno, labelFase, ejecutarAccion);

        HBox contenedorPaisesElegidos = new HBox(contenedorPaisUno, labelMapaTeg, contenedorPaisDos);

        VBox contenedorMapa = new VBox(contenedorPaisesElegidos,  contenedorTurno);

        ejecutarAccion.setCursor(Cursor.HAND);
        pasarAccion.setCursor(Cursor.HAND);

        this.contenedorMapa = contenedorMapa;
        this.setVisualBotones(elegirPaisUno, elegirPaisDos, paisDos);
        this.setVisualContenedores(pasarAccion, ejecutarAccion, contenedorTurno, contenedorPaisUno, contenedorPaisDos, handler, handlerDos);
        this.setVistaTurno(labelTurno, labelFase, box, cbx);
    }

    private void setVistaTurno(Label labelTurno, Label labelFase, ComboBox box, ComboBox cbx) {
        try {
            this.vistaTurno = new VistaTurno(labelTurno, labelFase, juego, box, cbx);
        } catch (TegException e) {
            e.printStackTrace();
        }
    }

    private void setVisualBotones(Button elegirPaisUno, Button elegirPaisDos, Label paisDos){

        elegirPaisUno.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        elegirPaisUno.setText("Elegir Pais: ATACANTE");
        elegirPaisUno.setTextFill(Color.WHITE);
        elegirPaisDos.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        elegirPaisDos.setText("Elegir Pais: DEFENSOR");
        elegirPaisDos.setTextFill(Color.WHITE);
        paisDos.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-min-width: 30; -fx-min-height: 30 ; -fx-background-color: #000000; -fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");



        }

    private void setVisualContenedores(Button pasarAccion, Button ejecutarAccion, HBox contenedorTurno, VBox contenedorPaisUno, VBox contenedorPaisDos, PasarAccion handler, ColocarEjercitos handlerDos){
        pasarAccion.setText("PASAR ACCION");
        pasarAccion.setTextFill(Color.WHITE);
        pasarAccion.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        pasarAccion.setOnAction(handler);
        ejecutarAccion.setOnAction(handlerDos);
        ejecutarAccion.setText("EJECUTAR ACCION");
        ejecutarAccion.setTextFill(Color.WHITE);
        ejecutarAccion.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        contenedorTurno.setAlignment(Pos.BOTTOM_LEFT);
        contenedorTurno.setSpacing(200);
        contenedorPaisUno.setSpacing(20);
        contenedorPaisDos.setSpacing(20);
        contenedorPaisUno.setAlignment(Pos.TOP_LEFT);
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
