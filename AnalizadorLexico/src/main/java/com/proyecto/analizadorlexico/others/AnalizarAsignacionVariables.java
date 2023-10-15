/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others;

import com.proyecto.analizadorlexico.model.DeclaracionAsignacion;
import com.proyecto.analizadorlexico.model.Errores;
import com.proyecto.analizadorlexico.model.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author david
 */
public class AnalizarAsignacionVariables {
    private int posicionActual;
    private List<Errores> errores = new ArrayList<>();
    
    public DeclaracionAsignacion declaracion(int posicion, Token tok, List<Token> arreglo, List<DeclaracionAsignacion> declaracionesGlobales){//Debe de analizar solo una linea;
        Stack<Token> pila = new Stack();
        DeclaracionAsignacion temp = new DeclaracionAsignacion();
        pila.push(tok);
        Mapas map = new Mapas();
        int estado=1;
        for (int i = posicion+1; i < arreglo.size(); i++) {
            if (estado==1  ) {//apila el signo igual
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("asignacion")) {
                    pila.push(arreglo.get(i));
                    estado=2;
                }else if (i<=arreglo.size()-3 && (map.getAsignacioncorta().containsKey(arreglo.get(i).getLexema()+arreglo.get(i+1).getLexema()) 
                        || "**=".equalsIgnoreCase(arreglo.get(i).getLexema()+arreglo.get(i+1).getLexema()+arreglo.get(i+2).getLexema())) ) {
                    String valor = "";

                    valor = arreglo.get(i).getLexema() + arreglo.get(i + 1).getLexema();
                    i += 1;
                    String _val = map.getAsignacioncorta().get(valor);
                    System.out.println("el valor que devuelve es " + valor+_val);
                    switch (_val) {
                        case "suma Corta":
                            valor = "+";
                            break;
                        case "resta Corta":
                            valor = "-";
                            break;
                        case "multiplicacion Corta":
                            valor = "*";
                            break;
                        case "division Corta":
                            valor = "/";
                            break;
                        case "residuo Corta":
                            valor = "%";
                            break;
                        case "potencia Corta":
                            valor = "**";
                            break;
                        default:
                            System.out.println("no encontro nada" + valor + _val);
                    }

                    Token temp3 = new Token(0, 0, "=", "", "Asignacion");
                    pila.push(temp3);
                    if (!declaracionesGlobales.isEmpty()) {
                        boolean encontrado = false;
                        for (int j = declaracionesGlobales.size() - 1; j >= 0; j--) {
                            if (tok.getLexema().equalsIgnoreCase(declaracionesGlobales.get(j).getVariable())) {//buscar la variable
                                Token temp2 = new Token(0, 0, declaracionesGlobales.get(j).getValor(), "", "");
                                pila.push(temp2);
                                estado = 2;
                                encontrado = true;
                                pila.push(new Token(0,0,valor,"",""));
                                j=0;
                            }
                            if (j == 0 && !encontrado) {
                                estado = 50;
                                errores.add(new Errores(0, tok.getColumna(), tok.getFila(), "La variable no ha sido declarada", tok.getLexema()));
                            }
                        }
                    } else {//aca agregar Error por arreglo vacillo
                        i--;
                        estado = 50;
                    }
                }
                
            }else if(estado==2){//verifica si lo que sigue al igual es una constante otro identificador o arreglo
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("Constante")) {
                    if (arreglo.get(i).getLexema().equals("True")||arreglo.get(i).getLexema().equals("False")
                            || arreglo.get(i).getLexema().equals("and")|| arreglo.get(i).getLexema().equals("or") || arreglo.get(i).getLexema().equals("not")) {
                        arreglo.get(i).setLexema("\""+arreglo.get(i).getLexema()+"\"");
                    }
                    pila.push(arreglo.get(i));
                    estado=3;
                }  else if (arreglo.get(i).getGrupo().equalsIgnoreCase("Identificador")) {//comprueba que la variable exista par poder ser llamada
                    if (!declaracionesGlobales.isEmpty()) {
                        boolean encontrado = false;
                        for (int j = declaracionesGlobales.size() - 1; j >= 0; j--) {
                            if (arreglo.get(i).getLexema().equalsIgnoreCase(declaracionesGlobales.get(j).getVariable())) {
                                Token temp2 = new Token(0, 0, declaracionesGlobales.get(j).getValor(), "", "");
                                pila.push(temp2);
                                estado = 3;
                                encontrado = true;
                                j=0;
                            }
                            if (j == 0 && !encontrado) {
                                estado = 50;
                                System.out.println("la variable no existe");
                            }
                        }
                    } else {//aca agregar Error por arreglo vacillo
                        i--;
                        estado = 50;
                    }

                } else if (arreglo.get(i).getLexema().equalsIgnoreCase("[")) {//arreglo
                    pila.push(arreglo.get(i));
                    estado = 4;
                }
            }else if(estado==3){// verifica el fin de linea
                if (arreglo.get(i).getFila()>arreglo.get(i-1).getFila()) {
                    while(!pila.empty()){
                        Token tokk= pila.pop();
                        if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {//si es fin de linea almacena la variable
                            temp.setVariable(pila.pop().getLexema());
                        }else{
                            temp.setValor(temp.getValor()+tokk.getLexema());
                        }
                    }
                    posicionActual=i;
                    
                    temp.setValor(DevolverValores.unirValores(temp.getValor()));
                    temp.setReferencias(arreglo.get(i).getFila()-1);
                    return temp;
                }else if (arreglo.get(i).getGrupo().equalsIgnoreCase("Aritmetico")) {//si encuentra una operacion almacena el signo y lo regresa al estado 2
                    pila.push(arreglo.get(i));
                    estado=2;
                }else if (arreglo.get(i).getGrupo().equalsIgnoreCase("comparacion")) {
                    pila.push(arreglo.get(i));
                    estado=2;
                } 
            }else if (estado==4) {
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("constante")) {
                    pila.push(arreglo.get(i));
                    estado=6;//buscar la coma
                }else if (arreglo.get(i).getLexema().equalsIgnoreCase("{")) {
                    pila.push(arreglo.get(i));
                    posicionActual=i+1;
                    pila=diccionario(pila, arreglo);
                    List<String> tem= new ArrayList<>();
                    while(!pila.empty()){
                        
                        Token tokk= pila.pop();
                        if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {//si es fin de linea almacena la variable
                            temp.setVariable(pila.pop().getLexema());
                            temp.setValor(DevolverValores.voltearArreglo(tem));
                            temp.setReferencias(arreglo.get(i).getFila());
                        }else{
                            tem.add(tokk.getLexema());
                        }
                    }
                    posicionActual=i;
                    return temp;
                }
            } else if (estado==6) {
                if (arreglo.get(i).getLexema().equals(",")) {
                    pila.push(arreglo.get(i));
                    estado=4;
                }else if (arreglo.get(i).getLexema().equals("]")) {
                    pila.push(arreglo.get(i));
                    List<String> tem= new ArrayList<>();
                    while(!pila.empty()){
                        
                        Token tokk= pila.pop();
                        if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {//si es fin de linea almacena la variable
                            temp.setVariable(pila.pop().getLexema());
                            temp.setValor(DevolverValores.voltearArreglo(tem));
                            temp.setReferencias(arreglo.get(i).getFila());
                        }else{
                            tem.add(tokk.getLexema());
                        }
                    }
                    posicionActual=i;
                    return temp;
                }
            } else{// llamar a mas errores
                System.out.println("Error linea 126 Clase analizar Asignacion");
                if (i==arreglo.size()-1) {
                    posicionActual=arreglo.size();
                    return temp;
                }
                for (int j = i; j < arreglo.size(); j++) {
                    if (arreglo.get(j).getFila()>arreglo.get(j-1).getFila()) {
                        posicionActual=j;
                        return temp;
                    }
                }
                return temp;
            }
            
            if (i == arreglo.size() - 1) {//si llega a el ultimo token del arreglo
                while (!pila.empty()) {
                    Token tokk = pila.pop();
                    if (tokk.getGrupo().equalsIgnoreCase("asignacion")) {
                        temp.setVariable(pila.pop().getLexema());
                        temp.setReferencias(tokk.getFila());
                    } else {
                        temp.setValor(temp.getValor() + tokk.getLexema());
                    }
                }
                temp.setValor(DevolverValores.unirValores(temp.getValor()));
                posicionActual = i;
                return temp;
            }
        }
        return new DeclaracionAsignacion();
    }
    
    public Stack diccionario(Stack pila1, List<Token> arreglo){
        Stack<Token> pila = pila1;
        int estado=1;
        for (int i = posicionActual; i < arreglo.size(); i++) {
            if (estado==0) {
                if (arreglo.get(i).getLexema().equalsIgnoreCase("{")) {
                    pila.push(arreglo.get(i));
                    estado=1;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==1) {
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("constante")) {
                    pila.push(arreglo.get(i));
                    estado=2;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==2) {
                if (arreglo.get(i).getLexema().equalsIgnoreCase(":")) {
                    pila.push(arreglo.get(i));
                    estado=3;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==3) {
                if (arreglo.get(i).getGrupo().equalsIgnoreCase("constante")) {
                    pila.push(arreglo.get(i));
                    estado=4;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==4) {
                if (arreglo.get(i).getLexema().equalsIgnoreCase(",")) {
                    pila.push(arreglo.get(i));
                    estado=1;
                }else if (arreglo.get(i).getLexema().equalsIgnoreCase("}")) {
                    pila.push(arreglo.get(i));
                    estado=5;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==5) {
                if (arreglo.get(i).getLexema().equalsIgnoreCase(",")) {
                    pila.push(arreglo.get(i));
                    estado=0;
                }else if (arreglo.get(i).getLexema().equalsIgnoreCase("]")) {
                    pila.push(arreglo.get(i));
                    return pila;
                }else{
                    i--;
                    estado=50;
                }
            }else if (estado==50) {
                System.out.println("Error estado =50");
            }
            if (i==arreglo.size()-1 && estado!=5) {
                System.out.println("Error al finalizar");
            }
        }
        return pila;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public List<Errores> getErrores() {
        return errores;
    }
    
    
}
