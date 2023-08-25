/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author david
 */
public final class Mapas {
    private Map<String, String> aritmeticos;
    private Map<Character, String> otros;
    private Map<String, String> reservadas; 
    private Map<String, String> comparacion;

    public Mapas() {
        llenarReservadas();
        llenarOtros();
        llenarComparacion();
        
        aritmeticos =new HashMap<>();
        aritmeticos.put("+", "Suma");
        aritmeticos.put("-", "Resta");
        aritmeticos.put("*","Multiplicacion");
        aritmeticos.put("/", "Division");
        aritmeticos.put("**", "Multiplo");
        aritmeticos.put( "%","Modulo");
        aritmeticos.put( "//", "Division");
        
    }

    private void llenarReservadas(){
        reservadas= new HashMap<>();
        String[] reservadasLista={"and", "as", "assert", "break", "class", "continue", "def", "del", "elif", "else", "except",
                              "False", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal", "not", "or", 
                            "pass", "raise", "return", "True", "try", "while", "with", "yield"};
        
        for (int i = 0; i < reservadasLista.length; i++) {
            reservadas.put(reservadasLista[i], "Reservada");
        }
        reservadas.put("True", "Booleano");
        reservadas.put("False", "Booleano");
        reservadas.put("and", "y");
        reservadas.put("or", "o");
        reservadas.put("not", "negacion");
    }
    
    private void llenarOtros(){
        otros= new HashMap<>();
        otros.put('(', "Parentesis");
        otros.put(')', "Parentesis");
        otros.put('{', "Llaves");
        otros.put('}', "Llaves");
        otros.put('[', "Corchetes");
        otros.put(']', "Corchetes");
        otros.put(',', "Coma");
        otros.put(';', "Punto y Coma");
        otros.put(':', "Dos Puntos");
        otros.put('.', "Punto");
    }
    
    private void llenarComparacion(){
        comparacion= new HashMap<>();
        comparacion.put("==", "Igualar");
        comparacion.put("!=", "diferente");
        comparacion.put(">", "Mayor que");
        comparacion.put("<", "Menor que");
        comparacion.put(">=", "Mayor o Igual que");
        comparacion.put("<=", "Menor o Igual que");
        comparacion.put("=", "Asignacion");
    }

    public Map<String, String> getAritmeticos() {
        return aritmeticos;
    }

    public Map<Character, String> getOtros() {
        return otros;
    }

    public Map<String, String> getReservadas() {
        return reservadas;
    }

    public Map<String, String> getComparacion() {
        return comparacion;
    }
    
    
}
