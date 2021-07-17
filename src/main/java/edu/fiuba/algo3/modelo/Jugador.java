package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.CantidadDeEjercitosInvalida;
import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.fase.Fase;
import edu.fiuba.algo3.fase.FaseDeJuego;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class
Jugador {

    private Pais atacante;
    private String color;
    private ArrayList<Pais> paises;

    public Jugador(String color) {
        this.paises = new ArrayList<Pais>();
        this.color = color;
    }

    public String obtenerColor() {
        return color;
    }

    public void atacarConA(Pais atacante, Pais defensor, Jugador jugadorDefensor) throws Exception {
        this.elegirPais(atacante);
        defensor.limitaCon(atacante);
        this.tirarDados(defensor);
        this.verificarConquista(jugadorDefensor, atacante, defensor);
    }

    public void tirarDados(Pais unPaisDefensor) throws Exception {
        ArrayList<Integer> ejercitosPerdidos = new ArrayList<Integer>();

        Dados dado = new Dados();
        ejercitosPerdidos = dado.tirar(this.atacante.obtenerEjercitos(), unPaisDefensor.obtenerEjercitos());
        this.atacante.matarEjercito(ejercitosPerdidos.get(0));
        unPaisDefensor.matarEjercito(ejercitosPerdidos.get(1));
    }

    public void elegirPais(Pais unPaisAtacante) throws Exception {
        if (!paises.contains(unPaisAtacante)){
            throw new Exception("El atacante no contiene el pais");
        }
        this.atacante = unPaisAtacante;
    }

    public void agregarPais(Pais unPais) {
        this.paises.add(unPais);
    }

    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    public int obtenerEjercitos(Pais unPais) {
        return unPais.obtenerEjercitos();
    }

    private int obtenerCantidadTotalDeEjercitos() {
        return paises.stream()
                .map(pais -> pais.obtenerEjercitos())
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public void agregarEjercitos(Pais unPais, int cantidadEjercitos, Fase fase) throws PaisNoPerteneceAJugadorException, ColocarEjercitosException {
        String nombrePais = unPais.obtenerNombrePais();
        List<Pais> paisSeleccionado = paises
                .stream()
                .filter(pais -> nombrePais.equals(pais.obtenerNombrePais()))
                .collect(Collectors.toList());;
        Pais paisX = paisSeleccionado.size() == 1 ? paisSeleccionado.get(0) : null;
        if (paisX == null) {
            throw new PaisNoPerteneceAJugadorException(nombrePais, this.obtenerColor());
        }
        int cantidadTotalDeEjercitos = obtenerCantidadTotalDeEjercitos();

        fase.validarCantidadEjercitos(cantidadTotalDeEjercitos +  cantidadEjercitos - paises.size());
        unPais.agregarEjercitos(cantidadEjercitos);
    }

    public void validarCantidadEjercitos(int ejercitos) throws CantidadDeEjercitosInvalida {
        int cantidadEjercitosPorDefecto = paises.size();
        int cantidadEjercitosAValidar = cantidadEjercitosPorDefecto + ejercitos;
        int cantidadEjercitos = obtenerCantidadTotalDeEjercitos();

        if (cantidadEjercitosAValidar != cantidadEjercitos) {
            throw new CantidadDeEjercitosInvalida(ejercitos, cantidadEjercitos - paises.size());
        }
    }

    public void eliminarPais(Pais paisDefensor) {
        paises.remove(paisDefensor);
    }

    public boolean tieneElPais(Pais paisDefensor) {
        return paises.contains(paisDefensor);
    }

    public void verificarConquista(Jugador jugadorDefensor, Pais paisAtacante, Pais paisDefensor) throws TegException {
        if (paisDefensor.obtenerEjercitos() == 0){
            jugadorDefensor.eliminarPais(paisDefensor);

            paisAtacante.reducirEjercitos(1);
            paisDefensor.agregarEjercitos(1);
            this.agregarPais(paisDefensor);
        }
    }
}
