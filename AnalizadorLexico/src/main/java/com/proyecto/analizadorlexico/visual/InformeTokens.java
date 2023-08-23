/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.proyecto.analizadorlexico.visual;

import com.proyecto.analizadorlexico.model.Token;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 *
 * @author david
 */
public class InformeTokens extends javax.swing.JPanel {

    /**
     * Creates new form InformeTokens
     */
    
    private int posY;
    private List<JButton> botones;
    private List<JLabel> tokens;
    private List<JLabel> patron;
    private List<JLabel> lexema;
    private List<JLabel> linea;
    private List<JLabel> columna;
    private List<JLabel> btn;
    private List<Token> listaTokensinicial;
    public InformeTokens() {
        initComponents();
        posY=0;
        botones = new ArrayList<>();
        tokens = new ArrayList<>();
        patron = new ArrayList<>();
        lexema = new ArrayList<>();
        linea = new ArrayList<>();
        columna = new ArrayList<>();
        btn = new ArrayList<>();
        listaTokensinicial = new ArrayList<>();
    }
    
    public void crearTabla( List<Token> listaTokens){
        posY=0;
        tokens.clear();
        lexema.clear();
        patron.clear();
        linea.clear();
        btn.clear();
        botones.clear();
        columna.clear();
        panel.updateUI();
        panel.setText("");
        crearCabeceras();
        for (int i = 0; i < listaTokens.size(); i++) {
            posY += 50;
            tokens.add(new JLabel(listaTokens.get(i).getGrupo(), SwingConstants.CENTER));
            tokens.get(i+1).setBounds(0, posY, 175, 50);
            tokens.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(tokens.get(i+1));
            patron.add(new JLabel(listaTokens.get(i).getPatron(), SwingConstants.CENTER));
            patron.get(i+1).setBounds(175, posY, 175, 50);
            patron.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(patron.get(i+1));
            lexema.add(new JLabel(listaTokens.get(i).getLexema(), SwingConstants.CENTER));
            lexema.get(i+1).setBounds(350, posY, 175, 50);
            lexema.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(lexema.get(i+1));
            linea.add(new JLabel(listaTokens.get(i).getFila()+"", SwingConstants.CENTER));
            linea.get(i+1).setBounds(525, posY, 175, 50);
            linea.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(linea.get(i+1));
            columna.add(new JLabel(listaTokens.get(i).getColumna()+"", SwingConstants.CENTER));
            columna.get(i+1).setBounds(700, posY, 175, 50);
            columna.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(columna.get(i+1));
            btn.add(new JLabel());
            btn.get(i+1).setBounds(875, posY, 175, 50);
            btn.get(i+1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(btn.get(i+1));
            botones.add(new JButton("grafico" ));
            botones.get(i).setBounds(0, 5, 150, 40);
            btn.get(i+1).add(botones.get(i));
            try {
                panel.getStyledDocument().insertString(panel.getStyledDocument().getLength(), "\n\n\n", null);
            } catch (Exception e) {
            }

        }
    }
    
    private void crearCabeceras() {
        tokens.add(new JLabel("Token", SwingConstants.CENTER));
        tokens.get(0).setBounds(0, posY, 175, 50);
        tokens.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(tokens.get(0));
        patron.add(new JLabel("Patron", SwingConstants.CENTER));
        patron.get(0).setBounds(175, posY, 175, 50);
        patron.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(patron.get(0));
        lexema.add(new JLabel("Lexema", SwingConstants.CENTER));
        lexema.get(0).setBounds(350, posY, 175, 50);
        lexema.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(lexema.get(0));
        linea.add(new JLabel("Linea", SwingConstants.CENTER));
        linea.get(0).setBounds(525, posY, 175, 50);
        linea.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(linea.get(0));
        columna.add(new JLabel("Columna", SwingConstants.CENTER));
        columna.get(0).setBounds(700, posY, 175, 50);
        columna.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(columna.get(0));
        btn.add(new JLabel("Grafico", SwingConstants.CENTER));
        btn.get(0).setBounds(875, posY, 175, 50);
        btn.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(btn.get(0));
        try {
            panel.getStyledDocument().insertString(panel.getStyledDocument().getLength(), "\n\n\n", null);
        } catch (Exception e) {
        }
    }

    public List<Token> getListaTokensinicial() {
        return listaTokensinicial;
    }

    public void setListaTokensinicial(List<Token> listaTokensinicial) {
        this.listaTokensinicial = listaTokensinicial;
    }
    
    public List<Token> clasificado(String grupo){
        List<Token> dev=this.listaTokensinicial.stream()
                .filter(tok -> tok.getGrupo().equalsIgnoreCase(grupo))
                .collect(Collectors.toList());
        return dev;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JTextPane();

        panel.setEditable(false);
        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane panel;
    // End of variables declaration//GEN-END:variables
}
