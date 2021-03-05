/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author GabrielPaz
 */
class Transicion{
    public String transi;
    public String estado;

    public Transicion(String transi, String estado) {
        this.transi = transi;
        this.estado = estado;
    }
    
}
public class ListaTransiciones {
    private String nombreEstado;
    
    public ArrayList<Integer> idEstado;
    public ArrayList<Transicion> transicion;

    public ListaTransiciones(String nombreEstado) {
        this.nombreEstado = nombreEstado;
        idEstado= new ArrayList();
        transicion = new ArrayList();
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public void setIdEstado(int posicion) {
        this.idEstado.add(posicion);
    }
    public void setTrancision(String encabezado, String nuevoEstado){
        transicion.add(new Transicion(encabezado, nuevoEstado));
        Collections.sort(transicion,new Comparator(){
            public int compare(Transicion p1, Transicion p2) {
                        return new String(p1.transi).compareTo(new String(p2.transi));
                }
            @Override
            public int compare(Object o1, Object o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
}
