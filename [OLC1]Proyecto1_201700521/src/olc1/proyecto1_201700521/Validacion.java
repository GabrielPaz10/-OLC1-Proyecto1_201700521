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
public class Validacion {
    private String valor;
    private String expresion;
    private String resultado;

    public Validacion(String valor, String expresion) {
        this.valor = valor;
        this.expresion = expresion;
        this.resultado="";
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
