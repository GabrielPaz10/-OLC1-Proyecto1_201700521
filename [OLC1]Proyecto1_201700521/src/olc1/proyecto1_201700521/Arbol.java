/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.proyecto1_201700521;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        this.raiz=new NodoArbol(0,".");
        this.raiz.dere=new NodoArbol(1,"#");
        this.raiz.izq=new NodoArbol(2,tokens.get(0));
        for(int i=0;i<tokens.size();i++){
            NodoArbol nuevo= new NodoArbol(i+3,tokens.get(i));
            insertar(false,this.raiz,nuevo);
        }
        anulables(this.raiz);
        primeros(this.raiz);
        cantidadNodos=1;
        ultimos(this.raiz);
        listaSiguientes(this.raiz);
        Collections.sort(nodoHijos);
        tablaEstados();
        cantidadNodos=1;
        generarReportes();
        
    }
    public void generarReportes(){
        
    }
    public void generarArbolD(NodoArbol raiz){
        String Titulo;
        String Escape="";
        //validaciones para quitar errores de graphviz
        char Caracter=raiz.identificador.charAt(0);
        if(Caracter == (char) 34){
            Titulo="Cadena";
        }else if(Caracter == (char) 123){
            Titulo="Conjunto";
        }else{
            Titulo="Operador";
        }
        String tem=raiz.identificador.replaceAll("\"", "");
        String tem2="";
        for(int i=0;i<tem.length();i++){
            if(tem.charAt(i)==(char)123    ||tem.charAt(i)==(char)125){
            }else{tem2+=tem.charAt(i);}
        }
        Escape="";
        for(int i=0;i<tem2.length();i++){
            if(Character.isDigit(tem2.charAt(i)) ||Character.isLetter(tem2.charAt(i)) ||tem2.charAt(i)==(char)32){
            }else{
                Escape="\\";
                break;
            }
        }
        
        //Fin de errores Graphviz
        //concatenacion de primeros
        String L_Primeros="";
        L_Primeros+=raiz.primeros.get(0);
        for(int i=1;i<raiz.primeros.size();i++){
            L_Primeros+=" ,"+raiz.primeros.get(i);
            
        }
        String L_Ultimos="";
        L_Ultimos+=raiz.ultimos.get(0);
        for(int i=1;i<raiz.ultimos.size();i++){
            L_Ultimos+=" ,"+raiz.ultimos.get(i);
            
        }
        //fin de concatenacion de primeros 
        this.cadenaImprimir += "\"" + raiz.numero  + "\"" + "[label =\"<C0>|P: "+L_Primeros+"|{<C1> Anulable:"+raiz.anulable+"|" +Titulo+": "+Escape+ tem2 + "}|U: "+L_Ultimos+"|<C2>\"]; \n";

        if(raiz.izq !=null ){
            this.generarArbolD(raiz.izq);
            this.cadenaImprimir += "\""+ raiz.numero  +"\":C0->"+"\""+raiz.izq.numero+"\"; \n";
        }
        
        if(raiz.dere !=null ){
            this.generarArbolD(raiz.dere);
            this.cadenaImprimir += "\""+ raiz.numero  +"\":C2->"+"\""+raiz.dere.numero+"\"; \n";
        }
    }
    public void tablaEstados(){
        int fila=1;
        Set<String>hashSet2= new HashSet<String>(nodoHijos);
        nodoHijos.clear();
        nodoHijos.addAll(hashSet2);
        tabla[0][0]=" ";
        for(int i=0;i<nodoHijos.size();i++){
            tabla[0][i+1]=nodoHijos.get(i);
        }
        
        String s="";
        s+="S"+cantEstados+"{";
        ListaTransiciones nuevo=new ListaTransiciones("S"+cantEstados);
        for(int x=0;x<raiz.primeros.size();x++){
            nuevo.setIdEstado(raiz.primeros.get(x));
            if(x==0){
                s+=raiz.primeros.get(x);
            }else{
                s+=","+raiz.primeros.get(x);
            }   
        }
        Collections.sort(nuevo.idEstado);
        s+="}";
        tabla[1][0]=s;
        transiciones.add(nuevo);
        cantEstados++;
        
        int y=1;
        while(y!=0){
            y--;
            for(int x=0;x<transiciones.size();x++){
                for(int x2=0;x2<transiciones.get(x).idEstado.size();x2++){
                    ListaTransiciones nuevo2=new ListaTransiciones("S"+cantEstados);                    
                    int posicionPrimeros=transiciones.get(x).idEstado.get(x2);
                    String Conc="";
                    Conc+="S"+cantEstados+"{";
                    for(int x3=0;x3<siguientes.get(posicionPrimeros-1).siguientes.size();x3++){
                        nuevo2.setIdEstado(siguientes.get(posicionPrimeros-1).siguientes.get(x3));
                        if(x3==0){
                            Conc+=siguientes.get(posicionPrimeros-1).siguientes.get(x3);
                        }else{
                            Conc+=","+siguientes.get(posicionPrimeros-1).siguientes.get(x3);
                        }
                    }
                    Set<Integer> hashSet = new HashSet<Integer>(nuevo2.idEstado);
                    nuevo2.idEstado.clear();
                    nuevo2.idEstado.addAll(hashSet);
                    Collections.sort(nuevo2.idEstado);
                    boolean repetido=false;
                    int posicionrepetido=0;
                    for(int i=0;i<transiciones.size();i++){
                       repetido = transiciones.get(i).idEstado.equals(nuevo2.idEstado);
                       if(repetido){
                           posicionrepetido=i;
                           break;
                       }
                    }
                    if(repetido){
                        for(int i=0;i<nodoHijos.size();i++){
                            if(nodoHijos.get(i).equals(siguientes.get(posicionPrimeros-1).getNombreNodo())){
                                tabla[x+1][i+1]="S"+posicionrepetido;
                            }
                        }
                    }else{
                        if(nuevo2.idEstado.size()==0){
                        }else{
                            tabla[x+2][0]=Conc+"}";
                            for(int i=0;i<nodoHijos.size();i++){
                                if(nodoHijos.get(i).equals(siguientes.get(posicionPrimeros-1).getNombreNodo())){
                                    tabla[x+1][i+1]="S"+cantEstados;
                                }
                            }
                            y++;
                            transiciones.add(nuevo2);
                            cantEstados++;   
                        }     
                    }
                }
            }   
        }
    }
}
