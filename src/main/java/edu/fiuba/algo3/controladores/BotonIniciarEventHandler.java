package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {
    private Button unBoton;


    public BotonIniciarEventHandler(Boolean jugadorAzul, Boolean jugadorRojo, Boolean jugadorVerde, Boolean jugadorNaranja, Boolean jugadorNegro, Boolean jugadorRosa) {

    }

    public void inicializar(){

    }


    @Override
    public void handle(ActionEvent actionEvent){
        System.out.println("Hola");
    }
}
