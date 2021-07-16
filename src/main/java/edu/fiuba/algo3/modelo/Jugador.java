package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class
Jugador {

    private Pais paisEnBatalla;
    private String color;
    private ArrayList<Pais> paises;
    private ArrayList<TarjetaPais> tarjetas;

    public Jugador(String color) {
        this.paises = new ArrayList<Pais>();
        this.color = color;
    }

    public String asignarColor() {
        return color;
    }

    /* COMENTAR
    public void atacarConA(Pais atacante, Pais defensor, Jugador jugadorDefensor) throws Exception {
        this.elegirPais(atacante);
        defensor.limitaCon(atacante);
        this.tirarDados(defensor);
        this.verificarConquista(jugadorDefensor, atacante, defensor);
    }*/

    /* COMENTAR
    public void tirarDados(Pais unPaisDefensor) throws Exception{
        ArrayList<Integer> ejercitosPerdidos = new ArrayList<Integer>();
        Dados dado = new Dados();
        ejercitosPerdidos = dado.tirar(this.atacante.ejercitos(), unPaisDefensor.ejercitos());
        this.atacante.matarEjercito(ejercitosPerdidos.get(0));
        unPaisDefensor.matarEjercito(ejercitosPerdidos.get(1));
    }*/


    public void elegirPais(Pais unPais) throws Exception {
        if (!paises.contains(unPais)){
            throw new Exception("El atacante no contiene el pais");
        }
        this.paisEnBatalla = unPais;
    }

    public void agregarPais(Pais unPais) {
        this.paises.add(unPais);
    }

    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.ejercitos();
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

    public void eliminarPais() {
        paises.remove(paisEnBatalla);
        paisEnBatalla = null;
    }

    public boolean tieneElPais(Pais paisDefensor) {
        return paises.contains(paisDefensor);
    }

    public void conquistar(Pais unPais){
        this.paisEnBatalla.reducirEjercitos(1);
        unPais.ejercitos(1);
        this.agregarPais(unPais);
    }

    public void matarEjercito(int cantidadEjercitos){
        this.paisEnBatalla.reducirEjercitos(cantidadEjercitos);
    }

    public Pais paisEnBatalla(){
        return this.paisEnBatalla;
    }
    /* COMENTAR
    public void verificarConquista(Jugador jugadorDefensor, Pais paisAtacante, Pais paisDefensor){
        if (paisDefensor.ejercitos() == 0){
            jugadorDefensor.eliminarPais(paisDefensor);

            paisAtacante.reducirEjercitos(1);
            paisDefensor.ejercitos(1);
            this.agregarPais(paisDefensor);
        }
    }*/
}
