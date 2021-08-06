package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.paises.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class VistaLabel {
    private final Juego juego;
    private final HashMap<String, String> colores = new HashMap<>();
    private final Label turnoActual;
    private final Label cantidadEjercitosJugadorUno;
    private final Label cantidadEjercitosJugadorDos;
    private final Label objetivoJugador;
    private final ListView<Label> listaPaises;
    private final Label faseActual;
    private final Button ejecutarAccion;
    private final Button pasarAccion;

    public VistaLabel(
            Label turnoActual,
            Label faseActual,
            Label cantidadEjercitosJugadorUno,
            Label cantidadEjercitosJugadorDos,
            Juego juego,
            ListView<Label> listaPaisesJuego,
            Label objetivoJugador,
            Button ejecutarAccion,
            Button pasarAccion
    )
    {
        this.juego = juego;
        this.faseActual = faseActual;
        this.turnoActual = turnoActual;
        this.cantidadEjercitosJugadorUno = cantidadEjercitosJugadorUno;
        this.cantidadEjercitosJugadorDos = cantidadEjercitosJugadorDos;
        this.objetivoJugador = objetivoJugador;
        this.listaPaises = listaPaisesJuego;
        this.inicializarColores();
        this.setLabelTurno();
        this.ejecutarAccion = ejecutarAccion;
        this.pasarAccion = pasarAccion;
    }

    public void setLabelTurno() {
        Jugador jugadorActual = this.juego.turnoActual();
        String colorJugador = jugadorActual.obtenerColor();
        String colorLabel = this.colores.get(colorJugador);
        this.turnoActual.setText("TURNO: " + colorJugador);
        this.turnoActual.setStyle("-fx-font-size: 20; -fx-font-weight: 800;  -fx-background-color: " + colorLabel + "; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 300; -fx-padding: 6");
    }

    public void limpiarJugadorUno(){
        this.cantidadEjercitosJugadorUno.setText("");
    }

    public void limpiarJugadorDos(){
        this.cantidadEjercitosJugadorDos.setText("");
    }

    public void inicializarColores() {
        this.colores.put("Negro", "#000000");
        this.colores.put("Azul", "#0077BB");
        this.colores.put("Rosa", "#EE3377");
        this.colores.put("Rojo", "#CC3311");
        this.colores.put("Verde", "#009988");
        this.colores.put("Naranja", "#EE7733");
    }

    public void mostrarPaises() {
        ArrayList<Pais> paisesJuego = this.juego.obtenerPaises();
        ObservableList<Label> paises = FXCollections.observableArrayList();

        for (Pais pais: paisesJuego) {
            String nombrePais = pais.obtenerNombrePais();
            Label texto = new Label();
            texto.setText(nombrePais);
            String color = colores.get(this.obtenerJugador(nombrePais));
            texto.setStyle("-fx-font-size: 14; -fx-font-weight: 800; -fx-background-color: " + color + "; -fx-border-color: #000000;-fx-border-radius: 0.3; -fx-padding: 3; -fx-min-width:210");
            texto.setTextFill(Color.WHITE);
            paises.add(texto);
        }
        listaPaises.setItems(paises);
        listaPaises.setStyle("-fx-control-inner-background: #b18151; -fx-border-color: #b18151");
    }

    public String obtenerJugador(String unPais) {
        for (Jugador n: juego.obtenerJugadores()) {
            if (n.tieneElPais(unPais)) {
                return n.obtenerColor();
            }
        }
        return "Ningun jugador posee el ejercito";
    }

    public void actualizar() {
        this.setLabelTurno();
        this.mostrarPaises();
        this.objetivoJugador.setText(juego.turnoActual().obtenerDescripcionObjetivo());
    }

    public void finalizarJuego() {
        this.faseActual.setText("FASE: GAME OVER");
        this.limpiarJugadorUno();
        this.limpiarJugadorDos();
        this.cantidadEjercitosJugadorUno.setDisable(true);
        this.cantidadEjercitosJugadorDos.setDisable(true);
        this.ejecutarAccion.setDisable(true);
        this.pasarAccion.setDisable(true);
        this.objetivoJugador.setStyle("-fx-min-height: 160 ;-fx-wrap-text: true ; -fx-max-width: 240; -fx-font-size: 20; -fx-font-weight: 800; -fx-background-color: #CAE33D; -fx-border-color: #000000;-fx-border-radius: 0.3;-fx-fill-height: 200; -fx-padding: 6");
        this.objetivoJugador.setText("GANADOR: " + this.juego.turnoActual().obtenerColor());
    }

}




