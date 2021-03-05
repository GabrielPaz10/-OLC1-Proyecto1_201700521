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
public class NodoArbol {
    public int numero;
    public int id_hoja;
    public String identificador;
    public boolean anulable;
    public ArrayList<Integer> primeros;
    public ArrayList<Integer> ultimos;
    public NodoArbol izq;
    public NodoArbol dere;

    public NodoArbol(int numero, String identificador) {
        this.numero = numero;
        this.identificador = identificador;
        primeros= new ArrayList<>();
        ultimos= new ArrayList<>();
    }
    
}
