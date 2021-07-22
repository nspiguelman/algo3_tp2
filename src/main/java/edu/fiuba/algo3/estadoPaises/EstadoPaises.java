package edu.fiuba.algo3.estadoPaises;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.paises.*;


import java.util.ArrayList;

public class EstadoPaises {

    private final ArrayList<Pais> paises;
    private Pais paisEnBatalla;

    public EstadoPaises() {
        this.paisEnBatalla = null;
        this.paises = new ArrayList<>();
    }

    public void agregarPais(Pais unPais) {
        paises.add(unPais);
    }
    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    public int obtenerCantidadTotalDeEjercitos() {
        return paises.stream()
            .map(pais -> pais.obtenerEjercitos())
            .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public int obtenerCantidadDeEjercitosAgregados() {
        return obtenerCantidadTotalDeEjercitos() - paises.size();
    }

    public void agregarEjercitos(Pais unPais, int cantidadDeEjercitos) throws Exception {
        this.tieneElPais(unPais);
        unPais.agregarEjercitos(cantidadDeEjercitos);
    }

    public void validarCantidadEjercitos(int cantidadEjercitosMaximos, int ejercitosASumar, int ejercitosPorFase) throws ColocarEjercitosException {

        int ejercitosJugador = obtenerCantidadTotalDeEjercitos();
        int diferencia = cantidadEjercitosMaximos + ejercitosPorFase - ejercitosASumar - ejercitosJugador;

        if (diferencia < 0) {
            throw new ColocarEjercitosException(ejercitosPorFase);
        }
    }

    public void tieneElPais(Pais unPais) throws TegException{
        if (!paises.contains(unPais)){
            throw new PaisInvalidoException();
        }
    }

    public void eliminarPaisEnBatalla() {
        paisEnBatalla.cambiarEstadoDeBatalla();
        paises.remove(paisEnBatalla);
        paisEnBatalla = null;
    }


    public void elegirPaisEnBatalla(Pais unPais) throws Exception{
        if (paisEnBatalla!=null){
            paisEnBatalla.cambiarEstadoDeBatalla();
        }
        this.tieneElPais(unPais);
        this.paisEnBatalla = unPais;
        unPais.cambiarEstadoDeBatalla();
    }

    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.obtenerEjercitos();
    }

    public void reducirEjercitos(Pais unPais, int ejercitos) throws Exception{
        this.tieneElPais(unPais);
        unPais.matarEjercitos(ejercitos);
    }

    public Pais obtenerPaisEnBatalla() {
        return this.paisEnBatalla;
    }

    public boolean tieneElPaisARREGLAR(Pais paisDefensor) {
        return paises.contains(paisDefensor);
    }

    public int obtenerEjercitosExtraAColocar(){
        int paisesAsiaticos = 0;
        int ejercitosExtra = 0;
        for (int i = 0; i< paises.size(); i++){
            if (paises.get(i).obtenerNombreContinente().equals("Asia")){
                paisesAsiaticos += 1;
            }
        }
        if (paisesAsiaticos == 15){
            ejercitosExtra = 7;
        }
        return ejercitosExtra;
    }
    public boolean domina(String continente){
        int paisesAsiaticos = 0;
        for (int i = 0; i< paises.size(); i++){
            if (paises.get(i).obtenerNombreContinente().equals("Asia")){
                paisesAsiaticos += 1;
            }
        }
        if (paisesAsiaticos == 15){
            return true ;
        }
        return false;
    }

    public int obtenerPaisesEnAsia() {
        int paisesAsiaticos = 0;
        for (int i = 0; i< paises.size(); i++){
            if (paises.get(i).obtenerNombreContinente().equals("Asia")){
                paisesAsiaticos += 1;
            }
        }
        return paisesAsiaticos;
    }

}
