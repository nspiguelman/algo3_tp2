package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DadosTest {
    Dados dados = new Dados();
    @Test
    public void paisConTresPaisesPuedeAtacarAOtroCon3PaisesMata2Ejercitos() throws Exception  {
        ArrayList<Integer> tirada = dados.tirar(3, 3);
        assertEquals(tirada.get(0)+tirada.get(1),2);
    }
    @Test
    public void paisConUnPaisNoPuedeAtacarAOtro() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> { ArrayList tirada =dados.tirar(1,2);});

    }

}

