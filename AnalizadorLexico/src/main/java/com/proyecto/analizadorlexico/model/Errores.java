/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.model;

/**
 *
 * @author david
 */
public class Errores {
    
    private int numError;
    private int columna;
    private int fila;
    private String descripcion;
    private String token;

    public Errores() {
    }

    public Errores(int numError, int columna, int fila, String descripcion, String token) {
        this.numError = numError;
        this.columna = columna;
        this.fila = fila;
        this.descripcion = descripcion;
        this.token = token;
    }

    public int getNumError() {
        return numError;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "  Numero de Error=" + numError + ",: \n\tcolumna=" + columna + ", fila=" + fila + ", descripcion=" + descripcion + ", \n\ttoken=" + token + "\n\tSe esperaba";
    }
    
    
    
    
}
