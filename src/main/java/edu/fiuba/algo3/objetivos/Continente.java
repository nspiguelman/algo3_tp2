package edu.fiuba.algo3.objetivos;
import edu.fiuba.algo3.modelo.Jugador;

public class Continente {

    private final int cantidadDePaises;
    private final String nombre;
    private final int extras;

    public Continente(String nombre,int cantidadDePaises, int paisesExtras){
        this.cantidadDePaises = cantidadDePaises;
        this.nombre = nombre;
        this.extras = paisesExtras;
    }
    public boolean esConquistadoPor(Jugador unJugador){
        int paisesDominados = 0;
        for (int i = 0; i< unJugador.obtenerPaises().size(); i++) {
            if (unJugador.obtenerPaises().get(i).obtenerNombreContinente().equals(this.nombre)){
                paisesDominados += 1;
            }
        }
        if (paisesDominados == this.cantidadDePaises){
            return true;
        }
        return false;
    }
    public int obtenerExtras(Jugador unJugador){
        if (this.esConquistadoPor(unJugador)){
            return this.extras;
        }
        return 0;
    }
}
