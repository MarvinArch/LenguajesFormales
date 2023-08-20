/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author david
 */
public class CargarArchivo extends JFileChooser{
    FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Archivos", "txt", "py"); 
    String titulo = "sin titulo";
    
    
    public List<String> cargarMiArchivo(Component parent) {
        BufferedReader br = null;
        List<String> texto = new ArrayList<>();
        this.setFileFilter(imgFilter);
        int result = this.showOpenDialog(parent);
        if (result != JFileChooser.CANCEL_OPTION) {
            try{
                String linea;
                File fileName = this.getSelectedFile();
                titulo=fileName.getAbsolutePath();
                FileReader fr = new FileReader (fileName);
                br = new BufferedReader(fr);
                while((linea=br.readLine())!=null) texto.add(linea);

            }catch(Exception e){
                System.out.println(e);
            }
            
        }
        return texto;
    }
    
    public String devolverTitulo(){
        char[] title = titulo.toCharArray();
        String newTitle="";
        ArrayList<Character> finalTitle= new ArrayList<>();
        for (char c : title) {
            finalTitle.add(new Character(c));
            if (c=='/') {
                finalTitle.clear();
            }
        }
        for (Character character : finalTitle) {
            newTitle+=character;
        }
        
        return newTitle;
    }
    
}
