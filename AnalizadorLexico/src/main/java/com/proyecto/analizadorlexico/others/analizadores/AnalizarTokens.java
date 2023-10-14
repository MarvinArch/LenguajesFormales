/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others.analizadores;

import com.proyecto.analizadorlexico.model.DeclaracionAsignacion;
import com.proyecto.analizadorlexico.model.Token;
import com.proyecto.analizadorlexico.others.DevolverValores;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author david
 */
public class AnalizarTokens {
    private List<Token> arreglo;
    List<DeclaracionAsignacion> declaraciones;

    public AnalizarTokens(List<Token> arreglo) {
        this.arreglo = arreglo;
        declaraciones= new ArrayList<>();
    }
    
    public void Analisis(){
        int estado =0;
        for (int i = 0; i < arreglo.size(); i++) {
            if (arreglo.get(i).getGrupo().equals("Identificador") && estado==0) {
                i=declaracion(i, arreglo.get(i));
                i--;
            }
            if (i==arreglo.size()-2) {
                i+=2;
            }
        }
    }
    
    public int declaracion(int posicion, Token tok){
        Stack<Token> pila = new Stack();
        DeclaracionAsignacion temp = new DeclaracionAsignacion();
        pila.push(tok);
        int estado=1;
        for (int i = posicion+1; i < arreglo.size(); i++) {
            if (estado==1 && arreglo.get(i).getGrupo().equalsIgnoreCase("asignacion")) {
                pila.push(arreglo.get(i));
                estado=2;
            }else if(estado==2){
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("Constante")) {
                    pila.push(arreglo.get(i));
                    estado=3;
                }else if (arreglo.get(i).getGrupo().equalsIgnoreCase("Identificador")) {
                    if (!declaraciones.isEmpty()) {
                        for (int j = declaraciones.size()-1; j >=0 ; j--) {
                            if (arreglo.get(i).getLexema().equalsIgnoreCase(declaraciones.get(j).getVariable())) {
                                Token temp2= new Token(0, 0, declaraciones.get(j).getValor(), "", "");
                                pila.push(temp2);
                                estado=3;
                            }
                        }
                    }else{
                        i--;
                        estado=50;
                    }
                }else if (arreglo.get(i).equals("[")) {//arreglo
                    estado=4;
                }
            }else if(estado==3){
                if (arreglo.get(i).getFila()>arreglo.get(i-1).getFila()) {
                    while(!pila.empty()){
                        Token tokk= pila.pop();
                        if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {
                            temp.setVariable(pila.pop().getLexema());
                        }else{
                            temp.setValor(temp.getValor()+tokk.getLexema());
                        }
                    }
                    declaraciones.add(temp);
                    return i;
                }else if (arreglo.get(i).getGrupo().equalsIgnoreCase("Aritmetico")) {
                    pila.push(arreglo.get(i));
                    estado=2;
                }
            }else{
                System.out.println("Error");
                if (i==arreglo.size()) {
                    return i;
                }
                for (int j = i; j < arreglo.size(); j++) {
                    if (arreglo.get(j).getFila()>arreglo.get(j-1).getFila()) {
                        return i;
                    }
                }
                return i;
            }
            if (i==arreglo.size()-1) {
                while(!pila.empty()){
                        Token tokk= pila.pop();
                        if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {
                            temp.setVariable(pila.pop().getLexema());
                            temp.setReferencias(tokk.getFila());
                        }else{
                        temp.setValor(temp.getValor()+tokk.getLexema());
                        }
                    }
                    temp.setValor(DevolverValores.unirValores(temp.getValor()));
                    declaraciones.add(temp);
                    System.out.println(declaraciones.get(declaraciones.size()-1));
                return i;
            }
        }
        return 0;
    }
    
    public int arreglos(){
        return 0;
    }
}
