package edu.fiuba.algo3.tarjetas;


import edu.fiuba.algo3.modelo.TarjetaPais;

public interface TipoTarjeta {

    public void agregarTarjeta(TarjetaPais tarjeta);
    public boolean tieneTres();
    public boolean tieneUno();
    public void eliminar(int cantidad);
}
