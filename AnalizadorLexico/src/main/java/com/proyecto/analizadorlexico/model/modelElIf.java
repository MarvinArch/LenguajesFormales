/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.model;

import java.util.List;

/**
 *
 * @author david
 */
public class modelElIf {
    private String expresion;
    List<DeclaracionAsignacion> variables;

    public modelElIf() {
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public List<DeclaracionAsignacion> getVariables() {
        return variables;
    }

    public void setVariables(List<DeclaracionAsignacion> variables) {
        this.variables = variables;
    }
    
}
