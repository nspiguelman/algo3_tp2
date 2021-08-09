package edu.fiuba.algo3.tarjetas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarjetasTest {

    @Test
    public void constructorTarjetasDevuelveTarjetasValido() {
        Tarjetas tarjetas = new Tarjetas();
        assertEquals(0, tarjetas.obtenerCanjeActual());
    }
    
    @Test
    public void agregarTarjetaAgregaTarjetaATarjetas() {
        Tarjetas tarjetas = new Tarjetas();
        TarjetaPais tarjetaBarco = new TarjetaPais("Argentina", "barco");
        TarjetaPais tarjetaGlobo = new TarjetaPais("Buenos Aires", "globo");
        TarjetaPais tarjetaCanion = new TarjetaPais("Brasil", "canon");

        tarjetas.agregarTarjeta(tarjetaBarco);
        tarjetas.agregarTarjeta(tarjetaGlobo);
        tarjetas.agregarTarjeta(tarjetaCanion);
        assertEquals(4, tarjetas.obtenerCanjeActual());
    }

}
