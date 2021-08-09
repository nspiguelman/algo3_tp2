package edu.fiuba.algo3.tarjetas;


public interface TipoTarjeta {

    public void agregarTarjeta(TarjetaPais tarjeta);
    public boolean tieneTres();
    public boolean tieneUno();
    public void eliminar(int cantidad);
}
