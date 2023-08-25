/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Graphviz {

        private ProcessBuilder proceso;
        
    public Graphviz() {
    }

    public void Dibujar(String direccionDot, String direccionPng) {
        
        try {
            /* CREAMOS PROCESOS DEL SISTEMA OPERATIVO */
            

            /* CONSTRUIMOS EL SIGUIENTE COMANDO PARA GENERAR EL AUTOMATA PLANTEADO */
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            proceso.redirectErrorStream(true);

            /* EJECUTA EL PROCESO*/
            proceso.start();

        } catch (Exception e) {
            e.printStackTrace();
            Dibujar(direccionDot, direccionPng);
        }
    }
    
    public void crearGrafico(String lexema, String direccionDot, String tipo){
        char[] caracteres= lexema.toCharArray();
        
        String cuerpo="digraph finite_state_machine {\n" +
                "	rankdir=LR;\n"
                +"node [shape = doublecircle]; S" +(caracteres.length) +";\n"
                +"node [shape = circle];\n"
                +"S0->S1 [label = \""+caracteres[0]+"\"]; \n";
        
        for (int i = 1; i < caracteres.length; i++) {
            cuerpo+="S"+i+"-> S"+(i+1)+" [label= \""+caracteres[i]+"\"]\n";
        }
        cuerpo+="}";
        try {
            
            File file = new File(direccionDot+"src/main/java/com/proyecto/analizadorlexico/Resource/Graficos/"+tipo+".dot");
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cuerpo);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
