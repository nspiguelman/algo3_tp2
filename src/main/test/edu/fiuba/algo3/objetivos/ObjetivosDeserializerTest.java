package edu.fiuba.algo3.objetivos;

import com.google.gson.stream.JsonReader;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ObjetivosDeserializerTest {

    @Test
    public void ObjetivosDeserializerCantidadCorrecta() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("archivos/objetivos.json"));
        ArrayList<Objetivo> objetivos = new ObjetivosDeserializer(reader).getObjetivos();
        assertEquals(objetivos.size(), 15);
    }

    @Test
    public void ObjetivosDeserilazerPrimerObjetivoIgualAlJSON() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("archivos/objetivos.json"));
        ArrayList<Objetivo> objetivos = new ObjetivosDeserializer(reader).getObjetivos();
        Objetivo objetivo = objetivos.get(0);
        assertEquals(objetivo.obtenerDescripcion(), "Ocupar África, 5 países de América del Norte y 4 países de Europa.");
        ArrayList<ObjetivoContinente> continentes = objetivo.obtenerContinentes();
        ObjetivoContinente continente = continentes.get(0);
        assertEquals(objetivo.obtenerContinente(continente), "Africa");
        assertEquals(objetivo.obtenerCantidadDePaises(continente), 6);
    }
}