package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {

    private Pais atacante;
    private String color;

    public Jugador(String color) {
        this.color = color;
    }

    public String color() {
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

}
