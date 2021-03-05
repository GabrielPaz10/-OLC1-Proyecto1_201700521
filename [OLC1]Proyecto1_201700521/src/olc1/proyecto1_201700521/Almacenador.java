/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

/**
 *
 * @author GabrielPaz
 */
public class Almacenador {
    String nombre;
    Object valor;

    public Almacenador(String nombre, Object valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Almacenador{" + "nombre=" + nombre + ", valor=" + valor + '}';
    }
    
    
}
