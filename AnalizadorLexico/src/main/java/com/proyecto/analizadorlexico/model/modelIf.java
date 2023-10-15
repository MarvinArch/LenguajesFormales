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
public class modelIf {
    private String expresion;
    private boolean _else;
    private boolean _elif;
    List<DeclaracionAsignacion> variables;
    List<modelElIf> _ElIfAnidados;
    modelElse _Else;

    public modelIf() {
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public boolean isElse() {
        return _else;
    }

    public void setElse(boolean _else) {
        this._else = _else;
    }

    public boolean isElif() {
        return _elif;
    }

    public void setElif(boolean _elif) {
        this._elif = _elif;
    }

    public List<DeclaracionAsignacion> getVariables() {
        return variables;
    }

    public void setVariables(List<DeclaracionAsignacion> variables) {
        this.variables = variables;
    }

    public List<modelElIf> getElIfAnidados() {
        return _ElIfAnidados;
    }

    public void setElIfAnidados(List<modelElIf> _ElIfAnidados) {
        this._ElIfAnidados = _ElIfAnidados;
    }

    public modelElse getElse() {
        return _Else;
    }

    public void setElse(modelElse _Else) {
        this._Else = _Else;
    }
    
}
