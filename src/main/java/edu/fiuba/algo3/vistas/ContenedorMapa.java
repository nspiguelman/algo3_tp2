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

import java.util.ArrayList;

public class ContenedorMapa {
    private VBox contenedorMapa;
    private static VistaTurno vistaTurno;
    private Juego juego;
    private ArrayList<VistaAccion> vistasTablero;
    private static VistaLabel vistaLabel;
    private static VistaComboBox vistaComboBox;

    public ContenedorMapa(Juego juego) {
        this.juego = juego;
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #FFECB9");
        Label mapaTEG = this.setImagen();

        Label elegirPaisUno = new Label();
        Label elegirPaisDos = new Label();

        Label cantidadEjercitosJugadorUno = new Label();
        Label cantidadEjercitosJugadorDos = new Label();
        Label turnoActual = new Label();
        Label faseActual = new Label();
        Label objetivoJugador = new Label();
        Button pasarAccion = new Button();
        Button ejecutarAccion = new Button();
        ComboBox<String> paisesJugadorUno = new ComboBox<>();
        ComboBox<String> paisesJugadorDos = new ComboBox<>();
        ComboBox<String> ejercitosJugadorUno = new ComboBox<>();
        ListView<Label> paisesJuego = new ListView<>();
        VBox contenedorJugadorUno = new VBox(elegirPaisUno, paisesJugadorUno, ejercitosJugadorUno, cantidadEjercitosJugadorUno, paisesJuego);
        VBox contenedorJugadorDos = new VBox(elegirPaisDos, paisesJugadorDos, cantidadEjercitosJugadorDos, objetivoJugador);
        HBox contenedorVistas = new HBox(pasarAccion, turnoActual, faseActual, ejecutarAccion);
        HBox contenedorTEG = new HBox(contenedorJugadorUno, mapaTEG, contenedorJugadorDos);
        VBox contenedorMapa = new VBox(contenedorTEG, contenedorVistas);

        this.contenedorMapa = contenedorMapa;

        this.vistasTablero = this.setVistasTablero(faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno, elegirPaisUno, elegirPaisDos);
        this.setEstiloComboBox(paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno);
        this.setEstiloLabel(cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, faseActual, objetivoJugador);
        this.setVisualBotones(elegirPaisUno, elegirPaisDos, ejecutarAccion, pasarAccion);
        this.setVistaTurno(turnoActual, faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno, paisesJuego, objetivoJugador, ejecutarAccion, pasarAccion);
        PasarAccion handler = new PasarAccion(this.juego, ejecutarAccion, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno, this.vistaTurno, vistasTablero, this.vistaComboBox, this.vistaLabel);
        this.setVisualContenedores(pasarAccion, ejecutarAccion, contenedorVistas, contenedorJugadorUno, contenedorJugadorDos, handler);

        this.establecerParametrosIniciales(paisesJugadorUno, paisesJugadorDos, ejecutarAccion, ejercitosJugadorUno);
    }


    private ArrayList<VistaAccion> setVistasTablero(Label faseActual, Label cantidadEjercitosJugadorUno, Label cantidadEjercitosJugadorDos, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos, ComboBox<String> ejercitosJugadorUno, Label vistaJugadorUno, Label vistaJugadorDos){
        faseActual.setText("FASE: Colocacion Ejercitos");
        VistaAccion colocacion = new VistaColocacion(faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno, vistaJugadorUno, vistaJugadorDos);
        colocacion.activar();
        ArrayList<VistaAccion> vistas = new ArrayList<>();
        vistas.add(new VistaAtaque(faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno,vistaJugadorUno,vistaJugadorDos));
        vistas.add(new VistaReagrupacion(faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno,vistaJugadorUno,vistaJugadorDos));
        vistas.add(new VistaColocacion(faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno,vistaJugadorUno,vistaJugadorDos));

        return vistas;
    }

    private void setVistaTurno(Label turnoActual, Label faseActual, Label cantidadEjercitosJugadorUno, Label cantidadEjercitosJugadorDos, ComboBox<String> paisesJugadorUno,
                               ComboBox<String> paisesJugadorDos, ComboBox<String> ejercitosJugadorUno, ListView<Label> listaPaises, Label objetivoJugador, Button ejecutarAccion, Button pasarAccion) {
        try {
            this.vistaLabel= new VistaLabel(
                     turnoActual,
                     faseActual,
                     cantidadEjercitosJugadorUno,
                     cantidadEjercitosJugadorDos,
                     juego,
                     listaPaises,
                     objetivoJugador,
                     ejecutarAccion,
                     pasarAccion);
            this.vistaComboBox= new VistaComboBox(juego, paisesJugadorUno, paisesJugadorDos,
                    ejercitosJugadorUno, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos);

            this.vistaTurno = new VistaTurno(turnoActual, faseActual, cantidadEjercitosJugadorUno, cantidadEjercitosJugadorDos, juego, paisesJugadorUno, paisesJugadorDos, ejercitosJugadorUno, listaPaises, objetivoJugador, ejecutarAccion, pasarAccion);
        } catch (TegException e) {
            e.printStackTrace();
        }
    }

    private void setVisualBotones(Label vistaJugadorUno, Label vistaJugadorDos, Button ejecutarAccion, Button pasarAccion){
        vistaJugadorUno.setStyle("-fx-font-size: 20; -fx-min-width: 230; -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6;");
        vistaJugadorUno.setText("PAIS A COLOCAR");
        vistaJugadorUno.setTextFill(Color.WHITE);
        vistaJugadorDos.setStyle("-fx-font-size: 20; -fx-min-width: 230;  -fx-font-weight: 800; -fx-background-color: #283618; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        vistaJugadorDos.setText("");
        vistaJugadorDos.setTextFill(Color.WHITE);
        ejecutarAccion.setCursor(Cursor.HAND);
        pasarAccion.setCursor(Cursor.HAND);
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

    private void setEstiloLabel(Label ejercitosJugadorUno, Label ejercitosJugadorDos, Label faseActual, Label objetivosJugador){
        ejercitosJugadorUno.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-min-width: 150;-fx-fill-height: 240; -fx-padding: 6");
        ejercitosJugadorDos.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-min-width: 150;-fx-fill-height: 240; -fx-padding: 6");
        faseActual.setStyle("-fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
        objetivosJugador.setStyle("-fx-min-height: 160 ;-fx-wrap-text: true ; -fx-max-width: 240; -fx-font-size: 18; -fx-font-weight: 800; -fx-background-color: #dda15e; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 200; -fx-padding: 6");
    }

    private void setEstiloComboBox(ComboBox paisesJugadorUno, ComboBox paisesJugadorDos, ComboBox ejercitoJugadorUno){
        paisesJugadorUno.setStyle("-fx-font-size: 14; -fx-min-width: 150");
        paisesJugadorDos.setStyle("-fx-font-size: 14; -fx-min-width: 150");
        ejercitoJugadorUno.setStyle("-fx-font-size: 14; -fx-min-width: 150");
        paisesJugadorDos.setDisable(true);

    }

    private Label setImagen(){
        Label labelMapaTeg = new Label();
        Image imagenGlobo = new Image("file:src/main/java/edu/fiuba/algo3/imagenes/mapaTeg.png");
        ImageView view = new ImageView(imagenGlobo);
        view.setPreserveRatio(true);
        labelMapaTeg.setGraphic(view);

        return labelMapaTeg;
    }

    private void establecerParametrosIniciales(ComboBox paisesJugadorUno, ComboBox paisesJugadorDos, Button ejecutarAccion, ComboBox ejercitosJugadorUno) {
        ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, paisesJugadorUno, ejercitosJugadorUno, this.vistaTurno, vistaComboBox);
        ejecutarAccion.setOnAction(handlerColocar);
        ActualizarOrigen actualizarPaisesDestinoHandler = new ActualizarOrigen(this.vistaTurno, juego, vistaComboBox);
        paisesJugadorUno.setOnAction(actualizarPaisesDestinoHandler);
        ActualizarDestino actualizarEjercitosDestinoHandler = new ActualizarDestino(this.vistaTurno, vistaComboBox);
        paisesJugadorDos.setOnAction(actualizarEjercitosDestinoHandler);
        this.juego.turnoActual().setearEjercitosMaximos();
       // this.vistaTurno.mostrarPaises();
        vistaLabel.mostrarPaises();
        vistaLabel.actualizar();
        vistaComboBox.actualizar();
    //    this.vistaTurno.actualizarVista();
    }

    public VBox obtenerContenedorMapa(){
        return this.contenedorMapa;
    }

    public static void actualizarVista(){
        vistaLabel.actualizar();
        vistaComboBox.actualizar();
        //vistaTurno.actualizarVista();
    }

}
