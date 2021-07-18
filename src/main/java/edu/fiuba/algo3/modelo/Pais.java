package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.fase.Fase;

public class Pais {
    private String[] limitrofes;
    private String nombre;
    private String continente;
    private int cantidadEjercitos;

    public Pais(String nombre, String continente, String limitaCon) {
        this.nombre = nombre;
        this.limitrofes = limitaCon.split(",");
        this.continente = continente;
        this.cantidadEjercitos = 1;
    }

    public String obtenerNombrePais() {
        return nombre;
    }
    
    public String obtenerNombreContinente() {
        return this.continente;
    }

    public String[] obtenerNombrePaisesLimitrofes() {
        return this.limitrofes;
    }

    public void agregarEjercitos(int ejercitos) {
        this.cantidadEjercitos += ejercitos;
    }

    public int obtenerEjercitos() {
        return this.cantidadEjercitos;
    }
    public void matarEjercito(int ejercitos) {
        this.cantidadEjercitos = this.cantidadEjercitos - ejercitos;
    }

    public void limitaCon(Pais unPais) throws Exception {
        for (String elemento: limitrofes) {
            if (unPais.obtenerNombrePais().equals(elemento)) {
                return;
            }
        }
        throw new Exception("Los paises no son limitrofes");
    }

    public void reducirEjercitos(int cantidadEjercitos) {
        this.cantidadEjercitos -= cantidadEjercitos;
    }
    //public boolean esAdyacente(Pais unPais){ return true;}
    //public void defiende(Dados unosDados){}
}