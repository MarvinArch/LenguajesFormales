/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.proyecto.analizadorlexico.visual;

import com.proyecto.analizadorlexico.model.Token;
import java.util.List;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import com.proyecto.analizadorlexico.others.NumerarLineas;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author david
 */
public class PanelPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form PanelPrincipal
     */
    NumerarLineas numeros;
    public PanelPrincipal() {
        initComponents();
        numeros = new NumerarLineas(jTextAreaEditable);
        
        jScrollPane1.setViewportView(jTextAreaEditable);
        jScrollPane1.setRowHeaderView(numeros);
        this.setToolTipText("sin titulo");
        this.jTextAreaEditable.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                JTextPane editArea = (JTextPane) e.getSource();
                int linea = 1;
                int columna = 1;

                try {
                    int caretpos = editArea.getCaretPosition();
                    int[] posFC=contadorLinea(editArea.getText(), caretpos);
                    linea = posFC[0];
                    columna=posFC[1];
                    

                    // Ya que las líneas las cuenta desde la 0
                    linea += 1;
                } catch (Exception ex) {
                }
                modificarLabelConteo(linea, columna);
            }
        });
    }
    
    public void limpiarErrores(){
        textAreaErrores.setText("");
    }
    
    
    
    public int[] contadorLinea(String texto, int posicion){
        char espacio[]= texto.toCharArray();
        int posiciones[]=new int[2];
        posiciones[0]=0;
        posiciones[1]=0;
        int temp=0;
        for (int i = 0; i <posicion; i++) {
            if (espacio[i]=='\n') {
                posiciones[0]++;
                temp=i;
            }
        }
        posiciones[1]=posicion-temp;
        if(posiciones[0]==0) posiciones[1]++;
        return posiciones;
    }
    
    public void modificarLabelConteo(int fila, int columna){
        this.labelConteo.setText("Linea "+ fila+ " Columna "+ columna);
    }
    
    public void cambiarTextoArea(List<String> agregar) {
        jTextAreaEditable.setText("");
        try {
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setForeground(attrs, Color.WHITE);
            jTextAreaEditable.getDocument().insertString(0, agregar.get(0), attrs);
            for (int i =1 ; i<agregar.size(); i++) {
                int posicion = jTextAreaEditable.getStyledDocument().getLength();
                jTextAreaEditable.getStyledDocument().insertString(posicion, agregar.get(i), attrs);
                jTextAreaEditable.getStyledDocument().insertString(posicion, " \n", null);
            }
        } catch (Exception e) {
        }
    }
    
    public void cambiarTextoReporte(List<String> errores){
        textAreaErrores.setText("");
        for (String error : errores) {
            textAreaErrores.append(error);
            textAreaErrores.append("\n");
        }
    }
    
    public String devolverTexto(){
        return jTextAreaEditable.getText();
    } 
    
    public void remplaceText(List<Token> tokens){
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        String texto= jTextAreaEditable.getText();
        StyleConstants.setForeground(attrs, Color.white);
        jTextAreaEditable.setText("");
        try {
            jTextAreaEditable.getStyledDocument().insertString(0, texto, attrs);
        } catch (Exception e) {
        }
        int posicion=-1;
        for (int i = 0; i < tokens.size(); i++) { 
           posicion =jTextAreaEditable.getText().indexOf(tokens.get(i).getLexema(), posicion+1);
           tokens.get(i).setPosicionReal(posicion); 
        }
        
        //jTextAreaEditable.setText(textoAremplazar);
        for (int i = 0; i < tokens.size(); i++) {
            try {
                if (tokens.get(i).getGrupo().equals("Palabras clave")) {
                    Color colM = new Color(153, 82, 182);
                    StyleConstants.setForeground(attrs, colM);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);
                } else if (tokens.get(i).getGrupo().equals("Constante")) {
                    StyleConstants.setForeground(attrs, Color.RED);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);
                } else if (tokens.get(i).getGrupo().equals("Comentario")) {
                    Color colG = new Color(66, 73, 73);
                    StyleConstants.setForeground(attrs, colG);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);
                } else if (tokens.get(i).getGrupo().equals("Aritmetico") || tokens.get(i).getGrupo().equals("Comparacion")
                        ||(tokens.get(i).getGrupo().equals("Logicos")) || tokens.get(i).getGrupo().equals("Asignacion")) {
                    Color colC = new Color(52, 152, 219);
                    StyleConstants.setForeground(attrs, colC);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);

                }  else if(tokens.get(i).getGrupo().equals("Otros")){
                    StyleConstants.setForeground(attrs, Color.GREEN);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);
                }else {
                    StyleConstants.setForeground(attrs, Color.WHITE);
                    jTextAreaEditable.getStyledDocument().setCharacterAttributes(tokens.get(i).getPosicionReal(), tokens.get(i).getPosicionReal() + tokens.get(i).getLexema().length(), attrs, true);
                }
            } catch (Exception e) {
                System.out.println("error " + e.toString());
            }

        }
        
        StyleConstants.setForeground(attrs, Color.white);
        try {
            jTextAreaEditable.getStyledDocument().insertString(0, "", attrs);
        } catch (Exception e) {
        }
        

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaErrores = new javax.swing.JTextArea();
        labelConteo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEditable = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(204, 204, 204));

        textAreaErrores.setEditable(false);
        textAreaErrores.setColumns(20);
        textAreaErrores.setRows(5);
        jScrollPane2.setViewportView(textAreaErrores);

        labelConteo.setText("Linea 0 Columna 0");

        jTextAreaEditable.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(jTextAreaEditable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(18, 18, 18)
                        .addComponent(labelConteo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelConteo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextAreaEditable;
    private javax.swing.JLabel labelConteo;
    private javax.swing.JTextArea textAreaErrores;
    // End of variables declaration//GEN-END:variables
}
