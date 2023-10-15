
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
    private String tipo;

    public Token(int columna, int fila, String lexema, String token, String grupo) {
        this.columna = columna;
        this.fila = fila;
        this.token = token;
        this.lexema = lexema;
        this.grupo=grupo;
        fijarPatron();
    }
    
    private void fijarPatron(){
        char espe=40;
        String[] patrones= {"(a-zA-z|1-9|_)*", "(1-9)+", "(1-9)+(.)(1-9)+", "(#)(a-zA-Z|1-9|)*","(\")(a-zA-Z|1-9|)*(\")"};
        if (this.token.equals("Identificador")) {
            this.patron=patrones[0];
        }else if(this.token.equals("Entero")){
            this.patron=patrones[1];
        }else if(this.token.equals("Decimal")){
            this.patron=patrones[2];
        }else if(this.token.equals("Comentario")){
            this.patron=patrones[3];
        }else if(this.token.equals("Cadena")){
            this.patron=patrones[4];
        }else{
            this.patron="("+this.lexema+")";
        }
        
        if (this.lexema.equals("True") || this.lexema.equals("False")) {
            this.token="Boolenas";
            this.grupo="Constante";
        }
        if (this.lexema.equals("and") || this.lexema.equals("or") || this.lexema.equals("not")) {
            this.grupo="Constante";
        }
        if (this.token.equals("Asignacion")) {
            this.grupo="Asignacion";
        }
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    
}
