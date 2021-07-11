package edu.fiuba.algo3.modelo;

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
        this.atacante.atacar(unPaisDefensor);

    }

    public boolean puedeAtacar() {
        return false;
    }

}
