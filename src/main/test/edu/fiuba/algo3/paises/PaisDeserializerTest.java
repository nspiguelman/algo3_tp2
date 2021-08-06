package edu.fiuba.algo3.paises;

import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PaisDeserializerTest {

    private ArrayList<Pais> paises;

    @Test
    public void deserializaLosPaisesCorrectamente() throws FileNotFoundException {
        JsonReader reader = Helper.crearJsonReader("archivos/paises-fronteras.json");
        this.paises = PaisDeserializer.deserializarPaises(reader);

        assertTrue(this.paises.size() == 50);
        assertTrue(this.paises.get(0).obtenerNombrePais().equals("Francia"));
    }
}
