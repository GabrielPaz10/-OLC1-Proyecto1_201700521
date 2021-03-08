/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

import analizadores.ASintactico;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author GabrielPaz
 */
public class Manejador {
    private static Manejador instancia;
    private analizadores.ASintactico parse;
    private ArrayList<Almacenador> expresiones;
    private ArrayList<Almacenador> evaluaciones;
    private ArrayList<Almacenador> conjuntos;
    private ArrayList<Validacion> validacionesJson;

    public ArrayList<Validacion> getValidacionesJson() {
        return validacionesJson;
    }

    public void setValidacionesJson(ArrayList<Validacion> validacionesJson) {
        this.validacionesJson = validacionesJson;
    }
    

    public ArrayList<Almacenador> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(ArrayList<Almacenador> expresiones) {
        this.expresiones = expresiones;
    }

    public ArrayList<Almacenador> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Almacenador> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public ArrayList<Almacenador> getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(ArrayList<Almacenador> conjuntos) {
        this.conjuntos = conjuntos;
    }
    private ArrayList<ErrorLS> errores;
    private String rutaArbol;
    private String rutaAFD;
    private String rutaAFN;
    private String rutaSiguientes;
    private String rutaTransiciones;
    private String rutaErrores;
    private String rutaSalidas;
    private String dotPath;

    public String getDotPath() {
        return dotPath;
    }
    

    public ArrayList<ErrorLS> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<ErrorLS> errores) {
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

    public String getRutaArbol() {
        return rutaArbol;
    }

    public String getRutaAFD() {
        return rutaAFD;
    }

    public String getRutaAFN() {
        return rutaAFN;
    }

    public String getRutaSiguientes() {
        return rutaSiguientes;
    }

    public String getRutaTransiciones() {
        return rutaTransiciones;
    }

    public String getRutaErrores() {
        return rutaErrores;
    }

    public String getRutaSalidas() {
        return rutaSalidas;
    }

    
    public Manejador() {
        errores= new ArrayList<>();
        rutaArbol="\\ARBOLES_201700521\\";
        rutaAFD="\\AFD_201700521\\";
        rutaAFN="\\AFND_201700521\\";
        rutaSiguientes="\\SIGUIENTES_201700521\\";
        rutaTransiciones="\\TRANSICIONES_201700521\\";
        rutaErrores="\\ERRORES_201700521\\";
        rutaSalidas="\\SALIDAS_201700521\\";
        expresiones= new ArrayList<>();
        conjuntos= new ArrayList<>();
        evaluaciones= new ArrayList<>();
        dotPath="D:\\Graphviz\\bin\\dot.exe";
        validacionesJson= new ArrayList<>();
    }
    public void procesoArbol() throws IOException{
        
        for (Almacenador expresion:expresiones) {
            String exp= (String)expresion.valor;
            String[] datos=exp.split(",");
            Arbol nuevo= new Arbol();
            for (String dato : datos) {
                nuevo.insertarToken(dato);
            }
            nuevo.analizando( expresion.nombre);
        }
    }
}
