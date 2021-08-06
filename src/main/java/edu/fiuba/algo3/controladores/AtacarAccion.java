package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import edu.fiuba.algo3.vistas.VistaComboBox;
import edu.fiuba.algo3.vistas.VistaLabel;
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
   // private VistaTurno vista;
    private final VistaLabel vistaLabel;


    public AtacarAccion(Juego juego, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos, VistaTurno vista,
                         VistaLabel vistaLabel) {
        this.juego = juego;
        this.boxOrigen = paisesJugadorUno;
        this.boxDestino = paisesJugadorDos;
       // this.vista = vista;
        this.vistaLabel = vistaLabel;
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
        this.vistaLabel.limpiarJugadorDos();
        //this.vista.limpiarJugadorDos();
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
