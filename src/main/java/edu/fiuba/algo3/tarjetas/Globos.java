package edu.fiuba.algo3.tarjetas;

import java.util.ArrayList;

public class Globos implements TipoTarjeta {
    private ArrayList<TarjetaPais> tarjetas;

    public Globos() {
        this.tarjetas = new ArrayList<>();

    }
    public void agregarTarjeta(TarjetaPais tarjeta){
        this.tarjetas.add(tarjeta);
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
