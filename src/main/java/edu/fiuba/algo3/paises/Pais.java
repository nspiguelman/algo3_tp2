package edu.fiuba.algo3.paises;

import edu.fiuba.algo3.excepciones.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Pais {
    private ArrayList<String> limitrofes;
    private String nombre;
    private String continente;
    private int cantidadEjercitos;
    private EstadoBelico estadoBelico;

    public Pais(String nombre, String continente, String limitaCon) {
        this.nombre = nombre;
        this.limitrofes = new ArrayList<>(Arrays.asList(limitaCon.split(",")));
        this.continente = continente;
        this.cantidadEjercitos = 1;
        this.estadoBelico = new PaisEnPaz(this);
    }

    public String obtenerNombrePais() {
        return nombre;
    }
    public String obtenerNombreContinente() {
        return this.continente;
    }
    public ArrayList<String> obtenerNombrePaisesLimitrofes() {
        return this.limitrofes;
    }
    public void agregarEjercitos(int ejercitos) {
        this.cantidadEjercitos += ejercitos;
    }
    public int obtenerEjercitos() {
        return this.cantidadEjercitos;
    }

    public void limitaCon(Pais unPais) throws Exception {
        if (!limitrofes.stream().anyMatch(paisLimitrofe -> paisLimitrofe == unPais.obtenerNombrePais())) {
            throw new Exception("Los paises no son limitrofes");
        }
    }

    public void trasferirEjercito(int cantidadEjercitos) {

    }

    public void matarEjercitos(int cantidadEjercitos) throws Exception{
        this.estadoBelico.matarEjercitos(this,cantidadEjercitos);
    }


    public void reducirEjercitos(int cantidadEjercitos){
        this.cantidadEjercitos = this.cantidadEjercitos - cantidadEjercitos;
    }

    public void validarPais (ArrayList<Pais> paises) throws PaisInvalidoException {
        if (!esElPais(paises)) return;
        throw new PaisInvalidoException();
    }
    private boolean esElPais(ArrayList<Pais> paises) { return paises.stream().anyMatch(pais -> pais.obtenerNombrePais() != this.obtenerNombrePais()); }

    public void cambiarEstadoDeBatalla(){
        this.estadoBelico = this.estadoBelico.cambiarEstadoDeBatalla();
    }
}

