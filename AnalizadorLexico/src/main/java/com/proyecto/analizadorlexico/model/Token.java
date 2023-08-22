
package com.proyecto.analizadorlexico.model;

/**
 *
 * @author david
 */
public class Token {
    private int columna;
    private int fila;
    private String token;
    private String lexema;
    private String patron;
    private String grupo;
    private int posicionReal;

    public Token(int columna, int fila, String lexema, String token, String grupo) {
        this.columna = columna;
        this.fila = fila;
        this.token = token;
        this.lexema = lexema;
        this.grupo=grupo;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    @Override
    public String toString() {
        
        return "Token{" + "columna=" + columna + ", fila=" + fila + ", token=" + token + " tipo= "+patron+'}';
    }

    public int getPosicionReal() {
        return posicionReal;
    }

    public void setPosicionReal(int posicionReal) {
        this.posicionReal = posicionReal;
    }

    public String getLexema() {
        return lexema;
    }

    public String getGrupo() {
        return grupo;
    }
    
    
}
