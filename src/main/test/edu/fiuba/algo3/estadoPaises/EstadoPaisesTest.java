package edu.fiuba.algo3.estadoPaises;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisInvalidoException;
import edu.fiuba.algo3.paises.Pais;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EstadoPaisesTest {
    @Test
    public void paisesAgregadosCorrectamente() throws PaisInvalidoException {
        EstadoPaises estado = new EstadoPaises();
        estado.agregarPais(new Pais("Argentina", "America", "Brasil"));
        ArrayList<Pais> paises = estado.obtenerPaises();
        assertEquals(paises.size(), 1);
        assertEquals(paises.get(0).obtenerNombrePais(), "Argentina");
        assertEquals(estado.tieneElPais(paises.get(0)), true);
    }

    @Test
    public void creaUnPaisYAgregaEjercitosCorrectamente() throws PaisInvalidoException {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);
        estado.agregarEjercitos(pais, 10);
        assertEquals(estado.obtenerCantidadTotalDeEjercitos(), 11);
        assertEquals(estado.obtenerCantidadDeEjercitosAgregados(), 10);
    }

    @Test
    public void creaUnPaisYAgregoEjercitosAOtroFallaCorrectamente() {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);

        Exception exception = assertThrows(PaisInvalidoException.class, () -> {
            estado.agregarEjercitos(new Pais("a", "a", "a"), 10);
        });
        String expectedMessage = "";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void creaUnPaisYAgregoEjercitosDeMasFallaCorrectamente() throws ColocarEjercitosException {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);

        Exception exception = assertThrows(ColocarEjercitosException.class, () -> {
            estado.validarCantidadEjercitos(10, 5);
        });
        String expectedMessage = "En la fase actual no es posible tener mas de 5 ejercitos.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void creaUnPaisYLoBorroEjercitosFunciona() {
        EstadoPaises estado = new EstadoPaises();
        Pais pais = new Pais("Argentina", "America", "Brasil");
        estado.agregarPais(pais);
        estado.eliminarPaisEnBatalla();
        assertEquals(estado.obtenerPaises().size(), 0);
    }
}
