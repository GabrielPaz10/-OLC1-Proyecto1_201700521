/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

import analizadores.ASintactico;
import java.util.ArrayList;

/**
 *
 * @author GabrielPaz
 */
public class Manejador {
    private static Manejador instancia;
    private analizadores.ASintactico parse;
    private ArrayList<Error> errores;

    public ArrayList<Error> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<Error> errores) {
        this.errores = errores;
    }

    
    public ASintactico getParse() {
        return parse;
    }

    public void setParse(ASintactico parse) {
        this.parse = parse;
    }
    private static synchronized void instanciar(){
        if (instancia == null) {
            instancia = new Manejador();
        }
    }
    public static Manejador obtenerInstancia(){
        instanciar();
        return instancia;
    }

    public Manejador() {
        errores= new ArrayList();
    }
    public void proceso(){
        ArrayList<Almacenador> expresiones = parse.info.get(1);
        for (Almacenador siu:expresiones) {
            String exp= (String)siu.valor;
            String[] datos=exp.split(",");
            Arbol nuevo= new Arbol();
            for (int i = 0; i < datos.length; i++) {
                nuevo.insertarToken(datos[i]);
            }
            nuevo.analizando();
        }
    }
}
