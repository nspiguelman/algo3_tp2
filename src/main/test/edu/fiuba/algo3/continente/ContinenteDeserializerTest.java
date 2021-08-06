package edu.fiuba.algo3.continente;

import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.helper.Helper;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class ContinenteDeserializerTest {

    @Test
    public void deserializaContinentesCorrectamente() throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/continentes.json");
        ArrayList<Continente> continentes = ContinenteDeserializer.deserializarContinentes(reader);
        assertEquals(continentes.size(), 6);
        Continente asia = continentes.get(0);
        assertEquals("Asia", asia.obtenerNombreContinente());
        assertEquals(15, asia.obtenerCantidadDePaises());
    }

}
