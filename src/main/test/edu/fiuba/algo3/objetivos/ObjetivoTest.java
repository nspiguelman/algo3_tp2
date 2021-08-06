package edu.fiuba.algo3.objetivos;

import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.continente.ContinenteDeserializer;
import edu.fiuba.algo3.helper.Helper;
import edu.fiuba.algo3.modelo.Batalla;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisDeserializer;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjetivoTest {

    private ArrayList<Objetivo> objetivos;
    private ArrayList<Continente> continentes;
    private ArrayList<Pais> paises;

    private Jugador jugadorAzul;
    private Jugador jugadorNegro;

    private Pais argentina;
    private Pais uruguay;

    public ObjetivoTest() throws FileNotFoundException {
        this.jugadorAzul = new Jugador("Azul");
        this.jugadorNegro = new Jugador("Negro");

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(this.jugadorAzul);
        jugadores.add(this.jugadorNegro);

        JsonReader readerContinente = Helper.crearJsonReader("archivos/continentes.json");
        this.continentes = ContinenteDeserializer.deserializarContinentes(readerContinente);
        JsonReader readerObjetivo = Helper.crearJsonReader("archivos/objetivos.json");
        this.objetivos = ObjetivosDeserializer.deserializarObjetivos(readerObjetivo, jugadores);
        JsonReader readerPaises = Helper.crearJsonReader("archivos/paises-fronteras.json");
        this.paises = PaisDeserializer.deserializarPaises(readerPaises);

        this.argentina = obtenerPais("Argentina");
        this.uruguay = obtenerPais("Uruguay");
    }

    @Test
    public void creaObjetivosCorrectamente() {
        assertEquals(13, objetivos.size());
        Objetivo objetivo = objetivos.get(0);
        assertEquals("Ocupar África, 5 países de América del Norte y 4 países de Europa.", objetivo.obtenerDescripcion());
        assertEquals("Conquista", objetivo.obtenerTipo());
    }

    @Test
    public void validarObjetivoConquistaEsTruePorqueEstaCumplido() {
        List<Pais> paisesAAgregar = paises
                .stream()
                .filter(pais -> pais.obtenerNombrePais().equals("Argentina") || pais.obtenerNombrePais().equals("Uruguay") || pais.obtenerNombreContinente().equals("Asia"))
                .collect(Collectors.toList());
        for (Pais pais : paisesAAgregar) { jugadorAzul.agregarPais(pais); }

        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Ocupar Asia y 2 países de América del Sur."))
                .collect(Collectors.toList())
                .get(0);

        assertTrue(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    @Test
    public void validarObjetivoConquistaEsFalsePorqueNoEstaCumplido() {
        jugadorAzul.agregarPais(paises.get(0));
        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Ocupar Asia y 2 países de América del Sur."))
                .collect(Collectors.toList())
                .get(0);
        assertFalse(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    @Test
    public void validarObjetivoDestruccionEsTruePorqueEstaCumplido() throws Exception {
        jugadorAzul.agregarPais(argentina);
        jugadorNegro.agregarPais(uruguay);

        jugadorAzul.agregarEjercitos(argentina, 10);
        jugadorAzul.elegirPais(argentina);
        jugadorNegro.elegirPais(uruguay);

        Batalla unaBatalla = new Batalla();

        while (!jugadorAzul.tieneElPais(uruguay)) {
            unaBatalla.batallar(jugadorAzul, jugadorNegro);
        }

        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Destruir al ejército negro de ser imposible al jugador de la derecha."))
                .collect(Collectors.toList())
                .get(0);

        assertTrue(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    @Test
    public void validarObjetivoDestruccionEsFalsePorqueNoEstaCumplido() {
        jugadorAzul.agregarPais(argentina);
        jugadorNegro.agregarPais(uruguay);

        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Destruir al ejército negro de ser imposible al jugador de la derecha."))
                .collect(Collectors.toList())
                .get(0);

        assertFalse(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    @Test
    public void validarObjetivoCuandoElJugadorAVencerNoExisteSeActualizaYEsValido() throws Exception {
        // Hay que terminar de corregir esto, hay que actualizar el objetivo llamando al metodo de tipoObjetivo en Objetivo
        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Destruir al ejército rojo de ser imposible al jugador de la derecha."))
                .collect(Collectors.toList())
                .get(0);
        jugadorAzul.agregarPais(argentina);
        jugadorNegro.agregarPais(uruguay);

        jugadorAzul.agregarEjercitos(argentina, 10);
        jugadorAzul.elegirPais(argentina);
        jugadorNegro.elegirPais(uruguay);

        Batalla unaBatalla = new Batalla();

        while (!jugadorAzul.tieneElPais(uruguay)) {
            unaBatalla.batallar(jugadorAzul, jugadorNegro);
        }

        assertTrue(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    @Test
    public void validarObjetivoCuandoElJugadorAVencerNoExisteSeActualizaYNoEsValido() {
        jugadorAzul.agregarPais(argentina);
        jugadorNegro.agregarPais(uruguay);

        Objetivo objetivoACumplir = objetivos
                .stream()
                .filter(objetivo -> objetivo.obtenerDescripcion().equals("Destruir al ejército rojo de ser imposible al jugador de la derecha."))
                .collect(Collectors.toList())
                .get(0);

        assertFalse(objetivoACumplir.validarObjetivo(jugadorAzul));
    }

    public Pais obtenerPais(String nombrePaisABuscar) {
        return paises
                .stream()
                .filter(pais -> pais.obtenerNombrePais().equals(nombrePaisABuscar))
                .collect(Collectors.toList()).get(0);
    }

}