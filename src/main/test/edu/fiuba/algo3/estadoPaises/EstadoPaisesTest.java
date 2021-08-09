package edu.fiuba.algo3.estadoPaises;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisInvalidoException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.fase.FaseUnoColocacionEjercitos;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EstadoPaisesTest {
    @Test
    public void paisesAgregadosCorrectamente() throws TegException {
        EstadoPaises estado = new EstadoPaises();
        estado.agregarPais(new Pais("Argentina", "America", "Brasil"));
        ArrayList<Pais> paises = estado.obtenerPaises();
        assertEquals(paises.size(), 1);
        assertEquals(paises.get(0).obtenerNombrePais(), "Argentina");
        estado.validarSiTieneElPais(paises.get(0));
    }

    @Test
    public void creaUnPaisYAgregaEjercitosCorrectamente() throws Exception {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);
        estado.agregarEjercitos(pais, 10);
        assertEquals(estado.obtenerCantidadTotalDeEjercitos(), 11);
        assertEquals(estado.obtenerCantidadDeEjercitosAgregados(), 10);
    }

    @Test
    public void creaUnPaisYAgregoEjercitosAOtroFallaCorrectamente(){
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);

        Exception exception = assertThrows(PaisInvalidoException.class, () -> {
            estado.agregarEjercitos(new Pais("a", "a", "a"), 10);
        });
        String expectedMessage = "Pais inválido";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void creaUnPaisYAgregoEjercitosDeMasFallaCorrectamente() {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);
        FaseUnoColocacionEjercitos fase = new FaseUnoColocacionEjercitos();
        int cantidadEjercitosPorFase = fase.ejercitosPorFase(new Jugador("Azul"));

        Exception exception = assertThrows(ColocarEjercitosException.class, () -> {
            estado.validarCantidadEjercitos(1, 6, cantidadEjercitosPorFase);
        });
        String expectedMessage = "En la fase actual no es posible tener mas de 5 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void creaUnPaisYLoBorroEjercitosFunciona() throws Exception{
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);
        estado.elegirPaisEnBatalla(pais);
        estado.eliminarPaisEnBatalla();
        assertEquals(estado.obtenerPaises().size(), 0);
    }
}
