/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others.analizadores;

import com.proyecto.analizadorlexico.model.DeclaracionAsignacion;
import com.proyecto.analizadorlexico.model.Errores;
import com.proyecto.analizadorlexico.model.Token;
import com.proyecto.analizadorlexico.model.modelIf;
import com.proyecto.analizadorlexico.others.AnalizarAsignacionVariables;
import com.proyecto.analizadorlexico.others.DevolverValores;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author david
 */
public class AnalizarTokens {
    private List<Errores> errores;
    private List<Token> arreglo;
    private int posicionActual;
    List<DeclaracionAsignacion> declaracionesGlobales;

    public AnalizarTokens(List<Token> arreglo) {
        this.arreglo = arreglo;
        declaracionesGlobales= new ArrayList<>();
        errores= new ArrayList<>();
    }
    
    public void Analisis(){
        int estado =0;
        for (int i = 0; i < arreglo.size(); i++) {
            if (arreglo.get(i).getGrupo().equals("Identificador") && estado==0) {
                AnalizarAsignacionVariables vari= new AnalizarAsignacionVariables();
                declaracionesGlobales.add(vari.declaracion(i, arreglo.get(i), arreglo, declaracionesGlobales));
                declaracionesGlobales.get(declaracionesGlobales.size()-1).setTipo("Declaracion Global");
                actualizarErrores(vari.getErrores());
                i=vari.getPosicionActual();
                i--;
                estado=0;
            }else if (arreglo.get(i).getGrupo().equalsIgnoreCase("palabras clave") && estado==0) {
                if (arreglo.get(i).getLexema().equalsIgnoreCase("if")) {
                    posicionActual=i;
                    _ifGlobal();
                }
            }
            if (i==arreglo.size()-2) {
                i+=2;
            }
        }
        imprimirArreglo();
    }
    
    public modelIf _ifGlobal(){
        Stack<Token> pila = new Stack();
        pila.push(arreglo.get(posicionActual));
        posicionActual++;
        int estado=0;
        for (int i = posicionActual; i < arreglo.size(); i++) {
            if (arreglo.get(i).getGrupo().equalsIgnoreCase("Constante")&& estado ==0) {
                if (arreglo.get(i).getLexema().equals("True")||arreglo.get(i).getLexema().equals("False")) {
                    pila.push(arreglo.get(i));
                    estado=3;
                }else{
                    estado=1;
                }
            }
        }
        return null;
    }
    
    //comparacion
    public Stack expresion(){
        Stack<Token> pila = new Stack();
        
        return pila;
    }
    
    
    public void imprimirArreglo(){
        for (int i = 0; i < declaracionesGlobales.size(); i++) {
            System.out.println(declaracionesGlobales.get(i).toString());
        }
    }

    public List<Errores> getErrores() {
        return errores;
    }

    public List<DeclaracionAsignacion> getDeclaracionesGlobales() {
        return declaracionesGlobales;
    }
    
    private void actualizarErrores(List<Errores> lista){
        for (int i = 0; i < lista.size(); i++) {
            errores.add(lista.get(i));
        }
    }
}
