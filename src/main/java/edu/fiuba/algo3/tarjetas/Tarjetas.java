package edu.fiuba.algo3.tarjetas;

import edu.fiuba.algo3.modelo.*;

import java.util.ArrayList;

public class Tarjetas {

    private final ArrayList<String> nombres = new ArrayList<>();
    private final ArrayList<TipoTarjeta> tipos = new ArrayList<>();
    private int canjeActual;

    public Tarjetas() {
        Barcos barcos = new Barcos();
        Caniones caniones = new Caniones();
        Globos globos = new Globos();
        this.tipos.add(barcos);
        this.tipos.add(caniones);
        this.tipos.add(globos);
        this.nombres.add("barco");
        this.nombres.add("canon");
        this.nombres.add("globo");
        this.canjeActual = 0;
    }

    public void agregarTarjeta(TarjetaPais tarjeta) {
        for (int i=0; i<nombres.size(); i++) {
            if (tarjeta.obtenerSimbolo().equals(nombres.get(i))) {
                tipos.get(i).agregarTarjeta(tarjeta);
            }
        }
    }

    public int obtenerCanjeActual() {
        for (int i=0; i<3; i++){
            TipoTarjeta tarjetasActuales = tipos.get(i);
            if (tarjetasActuales.tieneTres()){
                tarjetasActuales.eliminar(3);
                this.canjeActual++;
                return this.calcularCanje();
            }
        }
        if (tipos.get(0).tieneUno() && tipos.get(1).tieneUno() && tipos.get(2).tieneUno()) {
            tipos.get(0).eliminar(1);
            tipos.get(1).eliminar(1);
            tipos.get(2).eliminar(1);
            this.canjeActual++;
            return this.calcularCanje();
        }
        return 0;
    }

    private int calcularCanje() {
        if (this.canjeActual==1){
            return 4;
        }
        if (this.canjeActual==2){
            return 7;
        }
        else{
            return (canjeActual-1)*5;
        }
    }
}
