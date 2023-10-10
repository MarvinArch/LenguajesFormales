package com.proyecto.analizadorlexico.others;

import com.proyecto.analizadorlexico.model.Errores;
import com.proyecto.analizadorlexico.model.Token;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class AnalizadorLetras {

    private ArrayList<Token> token;
    private ArrayList<Errores> errores;
    private String textoAnalizar;
    private ArrayList<String> lineas;
    Mapas reservados;

    public AnalizadorLetras(String texto) {
        token = new ArrayList<Token>();
        this.lineas = new ArrayList<String>();
        this.textoAnalizar = texto;
        reservados = new Mapas();
    }

    private void separaLineas() {
        String linea = "";
        char[] letras = textoAnalizar.toCharArray();
        for (int i = 0; i < letras.length; i++) {
            linea+=letras[i];
            if (letras[i] == '\n' || i == letras.length - 1) {
                String linea2 = linea.replaceAll("\n", " ");
                lineas.add(linea2 + " ");
                linea = "";

            }
        }

        
        lineas.add("  ");
    }

    public boolean analizar() {
        separaLineas();
        errores = new ArrayList<>();
        int fila = 0;
        int columna = 0;
        int estado = 0;
        String cadena = "";
        char tipoComilla=' ';
        /*
        estado 0 es de entrada clasifica dependiendo del caracter que recibe
        estado 1 comprueba si se cumplen las reglas para ser identificador
        estado 2 comprueba que sean numero enteros
        estado 3 Entra si exite un entero y un punto declarando que ahora es un decimal
        estado 5 Comillas lee todo lo que esta entre commillas
        estado 6 Comentario lee toda la linea
         */
        for (int i = 0; i < lineas.size(); i++) {
            fila++;
            char letra[] = lineas.get(i).toCharArray();
            cadena="";
            for (int j = 0; j < letra.length; j++) {
                if (i == lineas.size() - 1 && j == letra.length - 1) {
                    fila = lineas.size() - 1;
                }
                if (estado == 0) {
                    columna = j + 1;
                    cadena += letra[j];
                    if (identificador(letra[j]) == true) {//envio de identificadores
                        estado = 1;
                    } else if (numero(letra[j])) {//Numeros Enteros o Decimales
                        estado = 2;
                    } else if (reservados.getComparacion().containsKey("" + letra[j]) || letra[j]=='!' ) {//Asignacion y Comparacion
                        j += comprobarComparacion(letra[j], letra[j + 1], fila, columna, cadena);
                        cadena = "";
                        estado = 0;
                    } else if (letra[j] == 34 || letra[j] == 39) {// comillas
                        tipoComilla=letra[j];
                        estado = 5;
                    } else if (letra[j] == 35) {//comentario
                        estado = 6;
                    } else if (comprobarAdjuntos(letra[j]) == 1) {//aritmeticos
                        j += comprobarAritmeticos(letra[j], letra[j + 1], fila, columna, cadena);
                        cadena = "";
                        estado = 0;
                    } else if (reservados.getOtros().containsKey(letra[j])) {//Agrupacion y puntos
                        cadena = tokenLista(cadena, fila, columna, reservados.getOtros().get(letra[j]), "Otros");
                        estado = 0;
                    }else if(letra[j]==' '|| letra[j]=='\t' ){
                        estado=0;
                    }else if(letra[j]==92){
                    }else{
                        errores.add(new Errores(errores.size(), columna, fila, "Error de Inicio", cadena));
                        estado=0;
                    }
                } else if (estado == 1) {//Identificadores
                    boolean seguir = identificador(letra[j]) == false ? numero(letra[j]) : identificador(letra[j]);//comprueba si es letra numero o guion bajo

                    if (seguir) {
                        estado = 1;
                        cadena += letra[j];
                    } else {
                        cadena = tokenLista(cadena, fila, columna, "Identificador", "Identificador");
                        j--;
                        estado=0;
                        cadena="";
                        
                    }
                } else if (estado == 2) {//Enteros
                    cadena += letra[j];
                    if (numero(letra[j])) {
                        estado = 2;
                    } else if (letra[j] == '.') {
                        estado = 3;
                    } else if (letra[j] == ' ') {
                        cadena = tokenLista(cadena.trim(), fila, columna, "Entero", "Constante");
                        estado = 0;
                    } else if (reservados.getAritmeticos().containsKey("" + letra[j])) {
                        cadena = tokenLista(cadena.replace(letra[j], ' ').trim(), fila, columna, "Entero", "Constante");
                        j--;
                        estado = 0;
                    } else if (comprobarAdjuntos(letra[j]) != 0) {
                        estado = 0;
                        cadena = tokenLista(cadena.replace(letra[j], ' '), fila, columna, "Entero", "Constante");
                        j--;
                    } else {
                        j+=salirError(cadena, "Caracter Invalido", columna, fila, letra, j);
                        cadena="";
                        estado=0;                       
                    }
                } else if (estado == 3) {//decimales
                    cadena += letra[j];
                    if (numero(letra[j])) {
                        estado = 3;
                    } else if (letra[j] == ' ') {
                        cadena = tokenLista(cadena.trim(), fila, columna, "Decimal", "Constante");
                        estado = 0;
                    } else if (reservados.getAritmeticos().containsKey("" + letra[j])) {
                        cadena = tokenLista(cadena.replace(letra[j], ' ').trim(), fila, columna, "Decimal", "Constante");
                        j--;
                        estado = 0;
                    } else if (comprobarAdjuntos(letra[j]) != 0) {
                        estado = 0;
                        cadena = tokenLista(cadena.replace(letra[j], ' '), fila, columna, "Decimal", "Constante");
                        j--;
                    } else {
                        j+=salirError(cadena, "Caracter Invalido", columna, fila, letra, j);
                        cadena="";
                        estado=0;                        
                    }
                } else if (estado == 5) {//Comillas cierre
                    cadena += letra[j];
                    if ((letra[j] == 34 || letra[j] == 39) && tipoComilla==letra[j]) {
                        cadena = tokenLista(cadena, fila, columna, "Cadena", "Constante");
                        estado = 0;
                    } else if (j == letra.length - 1) {
                        estado = 0;
                        salirError(cadena, "No se encontro cierre de cadena", columna, fila, letra, j);
                        cadena = "";
                    }
                } else if (estado == 6) {//Comentario
                    cadena += letra[j];
                    if (j == letra.length - 1) {
                        //cadena = tokenLista(cadena, fila, columna, "Comentario", "Comentario");
                        estado = 0;
                    }
                } else if (letra[j] == ' ') {
                    columna++;
                }
            }
        }        
        if (errores.size()>0) {
            return true;
        }
        return false;

    }
    
    private int salirError(String cadena, String descripcion, int columna, int fila, char[] texto, int index){
        errores.add(new Errores(errores.size()+1, columna+cadena.length()-1, fila, descripcion, cadena));
        for (int i = index; i < texto.length; i++) {
            if (texto[i]==' ') {
                return i-index;
            }
        }
        return 0;
    }
    
    //private void 

    private boolean identificador(char letra) {
        return ((letra < 123 && letra > 96) || (letra < 91 && letra > 64)
                || (letra == '_')); 
    }

    private boolean numero(char letra) {
        if (letra < 58 && letra >= 48) {
            return true;
        } else {
            return false;
        }
    }

    private int comprobarAdjuntos(char letra) {
        if (reservados.getAritmeticos().containsKey("" + letra)) {
            return 1;
        } else if (reservados.getComparacion().containsKey("" +letra)) {//falta definir bien
            return 2;
        } else if(reservados.getOtros().containsKey(letra)){
            return 3;
        }else {
            return 0;
        }
    }

    private String tokenLista(String cadena, int fila, int columan, String tipo, String grupo) {
        cadena = cadena.trim();
        if (tipo!=null && tipo.equals("Identificador")) {
            if (reservados.getReservadas().containsKey(cadena)) {
                token.add(new Token(columan, fila, cadena, reservados.getReservadas().get(cadena), "Palabras clave"));
            } else {
                token.add(new Token(columan, fila, cadena, tipo, "Identificador"));
            }
        } else {
            token.add(new Token(columan, fila, cadena, tipo, grupo));
        }
        if(cadena.equals("print")){
            return "print";
        }
        return "";
    }

    public void imprimiLista() {
        for (int i = 0; i < token.size(); i++) {
            System.out.println(token.get(i).toString());
        }
    }

    private int comprobarAritmeticos(char actual, char future, int fila, int columna, String cadena) {
        if (reservados.getAritmeticos().containsKey(actual + "" + future)) {
            tokenLista(actual + "" + future, fila, columna, reservados.getAritmeticos().get(actual + "" + future), "Aritmetico");
            return 1;
        } else {
            tokenLista(cadena, fila, columna, reservados.getAritmeticos().get(cadena.trim()), "Aritmetico");
            return 0;
        }
    }

    private int comprobarComparacion(char actual, char future, int fila, int columna, String cadena) {
        if (reservados.getComparacion().containsKey(actual + "" + future)) {
            tokenLista(actual + "" + future, fila, columna, reservados.getComparacion().get(actual + "" + future), "Comparacion");
            return 1;
        } else if(actual==33){
            char[] fuc={'s','y'};
            salirError(cadena, "no se reconoce caracter", columna, fila, fuc, fila);
            return 100;
        }else {
            tokenLista(cadena, fila, columna, reservados.getComparacion().get("" + actual), "Comparacion");
            return 0;
        }
    }

    public ArrayList<Errores> getErrores() {
        return errores;
    }

    public ArrayList<Token> getToken() {
        return token;
    }

}
