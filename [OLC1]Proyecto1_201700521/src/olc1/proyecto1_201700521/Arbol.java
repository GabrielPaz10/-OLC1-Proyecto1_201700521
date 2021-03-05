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
public class Arbol {
    int max=0;
    public String nombreExp;
    int cantidadNodos;
    
    int cantEstados;
    ArrayList<String> nodoHijos;
    String[][] tabla = new String [100][100];
    ArrayList<ListaSiguiente> siguientes;
    ArrayList<ListaTransiciones> transiciones;
    public ArrayList<String> tokens;
    NodoArbol raiz;
    public String cadenaImprimir;

    public Arbol() {
        raiz=null;
        tokens= new ArrayList<>();
        siguientes =  new ArrayList<>();
        transiciones = new ArrayList<>();
        nodoHijos= new ArrayList<>();
        cantidadNodos=1;
        
    }
    public boolean insertar(Boolean bandera,NodoArbol raiz,NodoArbol nuevo){
        if (raiz.izq!=null) {
            bandera= insertar(bandera, raiz.izq,nuevo);
        }
        if (!bandera) {
            if (raiz.identificador.equals("*")||raiz.identificador.equals("?")||raiz.identificador.equals("+")||raiz.identificador.equals("|")||raiz.identificador.equals(".")) {
                if (raiz.izq==null&&raiz.dere==null) {
                    raiz.izq=nuevo;
                    bandera=true;
                }else if((raiz.identificador.equals("+")||raiz.identificador.equals("*")||raiz.identificador.equals("?"))&&raiz.izq==null){
                    raiz.izq=nuevo;
                    bandera=true;
                }else if(raiz.identificador.equals("|")||raiz.identificador.equals(".")){
                    if (raiz.izq!=null&&raiz.dere==null) {
                        raiz.dere=nuevo;
                        bandera=true;
                    }
                }
            }
        }
        if (raiz.dere!=null) {
            bandera= insertar(bandera,raiz.dere,nuevo);
            
        }
        return bandera;
    }
    public void anulables(NodoArbol raiz){
        if (raiz.izq!=null) {
            anulables(raiz.izq);
        }
        if (raiz.dere!=null) {
            anulables(raiz.dere);
        }
        
        if (raiz.izq==null && raiz.dere==null) {
            raiz.anulable=false;
        }
        if (raiz.izq!=null&&raiz.dere==null) {
            if (raiz.identificador.equals("*")||raiz.identificador.equals("?")) {
                raiz.anulable=true;
            }
            if (raiz.identificador.equals("+")) {
                raiz.anulable=raiz.izq.anulable;
            }
        }
        if (raiz.izq!=null&&raiz.dere!=null) {
            if (raiz.identificador.equals("|")) {
                if (raiz.izq.anulable||raiz.dere.anulable) {
                    raiz.anulable=true;
                }else{
                    raiz.anulable=false;
                }
            }
            if (raiz.identificador.equals(".")) {
                if (raiz.izq.anulable||raiz.dere.anulable) {
                    raiz.anulable=true;
                }else{
                    raiz.anulable=false;
                }
            }
        }
    }
    public void primeros(NodoArbol raiz){
        if (raiz.izq!=null) {
            primeros(raiz.izq);
        }
        if (raiz.dere!=null) {
            primeros(raiz.dere);
        }
        if (raiz.izq==null&&raiz.dere==null) {
            raiz.primeros.add(cantidadNodos);
            siguientes.add(new ListaSiguiente(raiz.identificador,cantidadNodos));
            raiz.id_hoja=cantidadNodos;
            cantidadNodos++;
            if (raiz.identificador.equals("#")) {
                max=raiz.id_hoja;
            }else{
                nodoHijos.add(raiz.identificador);
            }
        }
        if (raiz.identificador.equals("*")||raiz.identificador.equals("+")||raiz.identificador.equals("?")) {
            for (int i = 0; i < raiz.izq.primeros.size(); i++) {
                raiz.primeros.add(raiz.izq.primeros.get(i));
            }
        }
        if (raiz.identificador.equals("|")) {
            for (int i = 0; i < raiz.izq.primeros.size(); i++) {
                raiz.primeros.add(raiz.izq.primeros.get(i));
            }
            for (int i = 0; i < raiz.dere.primeros.size(); i++) {
                raiz.primeros.add(raiz.dere.primeros.get(i));
            }
        }
        if (raiz.identificador.equals(".")) {
            if (raiz.izq.anulable) {
                for (int i = 0; i < raiz.izq.primeros.size(); i++) {
                    raiz.primeros.add(raiz.izq.primeros.get(i));
                }
                for (int i = 0; i < raiz.dere.primeros.size(); i++) {
                    raiz.primeros.add(raiz.dere.primeros.get(i));
                }
            }else{
                for (int i = 0; i < raiz.izq.primeros.size(); i++) {
                    raiz.primeros.add(raiz.izq.primeros.get(i));
                }
            }
        }
    }
    public void ultimos(NodoArbol raiz){
        if (raiz.izq!=null) {
            ultimos(raiz.izq);
        }
        if (raiz.dere!=null) {
            ultimos(raiz.dere);
        }
        if (raiz.izq==null&&raiz.dere==null) {
            raiz.ultimos.add(cantidadNodos);
            cantidadNodos++;
        }
        if (raiz.identificador.equals("*")||raiz.identificador.equals("+")||raiz.identificador.equals("?")) {
            for (int i = 0; i < raiz.izq.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.izq.ultimos.get(i));
            }
        }
        if (raiz.identificador.equals("|")) {
            for (int i = 0; i < raiz.izq.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.izq.ultimos.get(i));
            }
            for (int i = 0; i < raiz.dere.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.dere.ultimos.get(i));
            }
        }
        if (raiz.identificador.equals(".")) {
            if (raiz.izq.anulable) {
                for (int i = 0; i < raiz.izq.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.izq.ultimos.get(i));
                }
                for (int i = 0; i < raiz.dere.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.dere.ultimos.get(i));
                }
            }else{
                for (int i = 0; i < raiz.izq.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.izq.ultimos.get(i));
                }
            }
        }
    }
    public void listaSiguientes(NodoArbol raiz){
        if (raiz.izq!=null) {
            listaSiguientes(raiz.izq);
        }
        if (raiz.dere!=null) {
            listaSiguientes(raiz.dere);
        }
        if(raiz.identificador.equals(".")){
            //ultimos de c1 sus siguientes son los primeros de c2
            for(int i=0;i<raiz.izq.ultimos.size();i++){
                for(int i2=0;i2<raiz.dere.primeros.size();i2++){
                    //posicion del valor
                    int valor=raiz.izq.ultimos.get(i);
                    valor--;
                    siguientes.get(valor).setValor(raiz.dere.primeros.get(i2));
                }
            }
        }
        if(raiz.identificador.equals("+") || raiz.identificador.equals("*")){
            for(int i=0;i<raiz.izq.ultimos.size();i++){
                //ultimos de c1 sus siguientes son los primeros de c1
                for(int i2=0;i2<raiz.izq.primeros.size();i2++){
                    //posicion del valor
                    int valor=raiz.izq.ultimos.get(i);
                    siguientes.get(valor-1).setValor(raiz.izq.primeros.get(i2));
                }
            }
        }
    }
    public void insertarToken(String dato){
        tokens.add(dato);
    }
    public void analizando(){
        raiz=new NodoArbol(0,".");
        raiz.dere=new NodoArbol(1,"#");
        raiz.izq=new NodoArbol(2,tokens.get(0));
        for(int i=0;i<tokens.size();i++){
            NodoArbol nuevo= new NodoArbol(i+3,tokens.get(i));
            insertar(false,raiz,nuevo);
        }
        
    }
}
