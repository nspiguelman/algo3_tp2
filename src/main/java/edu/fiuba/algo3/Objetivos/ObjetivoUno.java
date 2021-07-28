package edu.fiuba.algo3.Objetivos;
import edu.fiuba.algo3.modelo.Jugador;



public class ObjetivoUno implements Objetivo{
    private final Jugador jugador;

    public ObjetivoUno(Jugador jugador){
        this.jugador = jugador;
    }

    public boolean objetivoCumplido(){
        return (jugador.domina("Asia", 15) && jugador.domina("America del Sur", 2));
    }
}
