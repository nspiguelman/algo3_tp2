package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {

    private Pais atacante;
    private String color;
    private ArrayList<Pais> paises;

    public Jugador(String color) {
        this.paises = new ArrayList<Pais>();
        this.color = color;
    }

    public String asignarColor() {
        return color;
    }

    public void elegirPais(Pais unPaisAtacante) {
        this.atacante = unPaisAtacante;
    }

    public void atacarA(Pais unPaisDefensor){
        ArrayList<Integer> ejercitosPerdidos = new ArrayList<Integer>();

        Dados dado = new Dados();
        ejercitosPerdidos = dado.tirar(this.atacante.ejercitos(), unPaisDefensor.ejercitos());
        this.atacante.matarEjercito(ejercitosPerdidos.get(0));
        unPaisDefensor.matarEjercito(ejercitosPerdidos.get(1));

    }
    public boolean puedeAtacar() {
        return false;
    }

    public void agregarPais(Pais unPais) {
        this.paises.add(unPais);
    }

    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    public int obtenerEjercitos(Pais unPais) {
        return unPais.ejercitos();
    }

    public void agregarEjercitos(Pais unPais, int cantidadEjercitos) {
        unPais.ejercitos(unPais.ejercitos() + cantidadEjercitos);
    }

    public void validarCantidadEjercitos(int ejercitos) throws Exception {
        int cantidadEjercitosPorDefecto = paises.size();
        int cantidadEjercitosAValidar = cantidadEjercitosPorDefecto + ejercitos;
        int cantidadEjercitos = 0;
        for (Pais pais: paises) {
            cantidadEjercitos += pais.ejercitos();
        }
        if (cantidadEjercitosAValidar != cantidadEjercitos) {
            throw new Exception("Cantidad de ejercitos incorrecta");
        }
    }
}
