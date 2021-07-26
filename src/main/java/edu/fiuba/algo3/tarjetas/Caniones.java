package edu.fiuba.algo3.tarjetas;
import edu.fiuba.algo3.modelo.TarjetaPais;

import java.util.ArrayList;

public class Caniones implements TipoTarjeta{
    private ArrayList<TarjetaPais> tarjetas;

    public Caniones(){
        this.tarjetas = new ArrayList<>();
    }

    public void agregarTarjeta(TarjetaPais unaTarjeta){
        this.tarjetas.add(unaTarjeta);
    }
    public boolean tieneTres(){
        return (tarjetas.size() >= 3);
    }
    public boolean tieneUno(){
        return (tarjetas.size() >= 1);
    }
    public void eliminar(int cantidad) {
        for (int i=0; i<cantidad;i++){
            tarjetas.remove(0);
        }
    }
}
