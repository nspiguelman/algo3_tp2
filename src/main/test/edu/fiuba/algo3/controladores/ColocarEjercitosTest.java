package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorEntrada;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ColocarEjercitosTest {

    @Test
    public void handleColocarEjercitos() throws TegException, FileNotFoundException {
        ArrayList<String> coloresJugadores = new ArrayList<>();
        coloresJugadores.add("Rojo");
        coloresJugadores.add("Azul");
        coloresJugadores.add("Amarillo");
        coloresJugadores.add("Violeta");
        coloresJugadores.add("Verde");

        Juego juego = new Juego(coloresJugadores);
        ComboBox<String> paises = new ComboBox<>();

        /* ComboBox<String> cantidadEjercitos = new ComboBox<>();
        ObservableList<String> ejercitos = FXCollections.observableArrayList();

        for (int i=0; i < 5; i++) {
            ejercitos.add(String.valueOf(i + 1));
        }

        cantidadEjercitos.setItems(ejercitos);

        Stage stage = new Stage();

        ContenedorEntrada contenedorEntrada = new ContenedorEntrada(stage);
        contenedorEntrada.iniciarJuego(stage);
        ContenedorMapa contenedorMapa = new ContenedorMapa(juego);
        ColocarEjercitos colocarEjercitosController = new ColocarEjercitos(juego, paises, cantidadEjercitos);
        colocarEjercitosController.handler();

         */
    }

}
