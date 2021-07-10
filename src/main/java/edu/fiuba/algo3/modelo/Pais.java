package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Pais {
    private int cantidadEjercitos;
    private Map adyacentes;

    public Pais() {
        this.cantidadEjercitos = 0;
        this.adyacentes = new HashMap<String,Pais>();
    }

    public void colocarEjercitos(int ejercitos) {
        this.cantidadEjercitos = ejercitos;
    }

    public int cantidadEjercitos() {
        return this.cantidadEjercitos;
    }

    public void atacar(Pais defensor){
        Dados dado = new Dados();

        this.esAdyacente();
        dado.atacarConEjercitos(cantidadEjercitos);
    }
    public boolean esAdyacente(Pais unPais){

    }
}
