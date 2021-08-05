package edu.fiuba.algo3.continente;

import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.helper.Helper;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContinenteDeserializerTest {

    @Test
    public void DeserializaContinentesCorrectamente() throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/continentes.json");
        HashMap<String, HashMap> continentes = ContinenteDeserializer.deserializarContinentes(reader);
        assertEquals(continentes.size(), 6);
        HashMap<String, Integer> value = continentes.get("Asia");
        assertEquals((int)value.get("cantidadDeFichasGanadas"), 7);
        assertEquals((int)value.get("cantidadDePaises"), 15);
    }
}
