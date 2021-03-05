/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

import java.util.ArrayList;

/**
 *
 * @author GabrielPaz
 */
public class ListaSiguiente {
    private String nombreNodo;
    private int numeroNodo;
    public ArrayList<Integer> siguientes;

    public ListaSiguiente(String nombreNodo, int numeroNodo) {
        this.nombreNodo = nombreNodo;
        this.numeroNodo = numeroNodo;
        siguientes= new ArrayList<>();
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public int getNumeroNodo() {
        return numeroNodo;
    }
    public void setValor(int dato){
        siguientes.add(dato);
    }
}
