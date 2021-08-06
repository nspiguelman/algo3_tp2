package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import edu.fiuba.algo3.vistas.VistaTurno;

import java.util.ArrayList;

public class AtacarAccion implements EventHandler<ActionEvent> {
    private Juego juego;
    private ComboBox<String> boxOrigen;
    private ComboBox<String> boxDestino;
    private Jugador jugadorAtacante;
    private Jugador jugadorDefensor;
    private VistaTurno vista;

    public AtacarAccion(Juego juego, ComboBox<String> boxOrigen, ComboBox<String> boxDestino, VistaTurno vista) {
        this.juego = juego;
        this.boxOrigen = boxOrigen;
        this.boxDestino = boxDestino;
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent event) {
        ArrayList<Jugador> jugadores =  juego.obtenerJugadores();
        this.jugadorAtacante = juego.turnoActual();
        for (Jugador jugador: jugadores) {
            if (jugador.tieneElPais(boxDestino.getValue())) {
                this.jugadorDefensor = jugador;
            }
        }
        try {
            Pais paisAtacante = this.buscarPais(this.boxOrigen.getValue());
            this.jugadorAtacante.elegirPais(paisAtacante);
            this.jugadorDefensor.elegirPais(this.buscarPais(this.boxDestino.getValue()));
            juego.ataqueDeA(jugadorAtacante, jugadorDefensor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContenedorMapa.actualizarVista();
        this.vista.limpiarJugadorDos();
    }

    public Pais buscarPais(String nombre){
        ArrayList<Pais> paises = juego.obtenerPaises();
        for (Pais pais: paises){
            if (pais.esElPais(nombre)){
                return pais;
            }
        }
        return new Pais("a", "b", "c");
    }
}
