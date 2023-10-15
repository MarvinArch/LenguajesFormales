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
public class modelElse {
    
    List<DeclaracionAsignacion> variables;
    modelElse _Else;

    public modelElse() {
    }

    
    public List<DeclaracionAsignacion> getVariables() {
        return variables;
    }

    public void setVariables(List<DeclaracionAsignacion> variables) {
        this.variables = variables;
    }

    public modelElse getElse() {
        return _Else;
    }

    public void setElse(modelElse _Else) {
        this._Else = _Else;
    }
    
}
