package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {
    private Button unBoton;

    public BotonIniciarEventHandler(Button unBoton) {
        this.unBoton = unBoton;
    }



    @Override
    public void handle(ActionEvent actionEvent){
       System.out.println("Hola");
    }
}
