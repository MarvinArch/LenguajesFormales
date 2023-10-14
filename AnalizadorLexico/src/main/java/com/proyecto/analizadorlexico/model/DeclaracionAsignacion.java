/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.model;


/**
 *
 * @author david
 */
public class DeclaracionAsignacion {
    private String variable;
    private String Tipo;
    private String valor;
    private String referencias;
    private int posToken;

    public DeclaracionAsignacion(String variable, String Tipo, String valor, String referencias, int posToken) {
        this.variable = variable;
        this.Tipo = Tipo;
        this.valor = valor;
        this.referencias = referencias;
        this.posToken = posToken;
    }

    public DeclaracionAsignacion() {
        this.variable = "";
        this.Tipo = "";
        this.valor = "";
        this.referencias = "";
        this.posToken = 0;
    }

    public int getPosToken() {
        return posToken;
    }

    public void setPosToken(int posToken) {
        this.posToken = posToken;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(int referencias) {
        this.referencias +=""+ referencias;
    }

    @Override
    public String toString() {
        return "DeclaracionAsignacion{" + "variable=" + variable + ", Tipo=" + Tipo + ", valor=" + valor + ", referencias=" + referencias + ", posToken=" + posToken + '}';
    }
    
    
    
}
