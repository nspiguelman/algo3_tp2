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

    public String color() {
        return color;
    }

    public void elegirPais(Pais unPaisAtacante) {
        this.atacante = unPaisAtacante;
    }

    public void atacarA(Pais unPaisDefensor){
        // this.atacante.atacar(unPaisDefensor);
    }
    public boolean puedeAtacar() {
        return false;
    }

    public void pais(Pais unPais) {
        this.paises.add(unPais);
    }

    public ArrayList<Pais> paises() {
        return paises;
    }

    public int ejercitos(Pais unPais) {
        return unPais.ejercitos();
    }

    public void ejercito(Pais unPais) {
        unPais.ejercitos(unPais.ejercitos() + 1);
    }
}
