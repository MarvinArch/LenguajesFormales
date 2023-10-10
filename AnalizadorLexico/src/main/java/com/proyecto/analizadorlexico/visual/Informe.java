/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.proyecto.analizadorlexico.visual;

import com.proyecto.analizadorlexico.model.Token;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class Informe extends javax.swing.JPanel {

    /**
     * Creates new form Informe
     */
    private InformeTokens tabla;
    public Informe(String ruta) {
        initComponents();
        tabla = new InformeTokens(ruta);
        this.add(tabla);
        tabla.setBounds(0, 50, 1050, 640);
        tabla.setVisible(true);
        
    }
    
    public void llenartabla(List<Token> lista){
        tabla.crearTabla(lista);
        tabla.setListaTokensinicial(lista);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonClasificar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();

        buttonClasificar.setText("Clasificar");
        buttonClasificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonClasificarMouseClicked(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Identificador", "Aritmetico", "Comparacion", "Logicos", "Asignacion", "Palabras Clave", "Constante", "Comentario", "Otros", " " }));

        jTextFieldBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldBuscar.setText("Buscar Lexema");
        jTextFieldBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMouseClicked(evt);
            }
        });
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jButtonBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(buttonClasificar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscar)
                .addGap(164, 164, 164))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonClasificar)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addContainerGap(588, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonClasificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonClasificarMouseClicked
        String opcion= jComboBox1.getSelectedItem().toString();
        if (opcion.equals("Todos")) {
            tabla.crearTabla(tabla.getListaTokensinicial());
        }else{
            tabla.crearTabla(tabla.clasificado(opcion));
        }
    }//GEN-LAST:event_buttonClasificarMouseClicked

    private void jButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMouseClicked
        if (!jTextFieldBuscar.equals("Buscar Lexema") && !jTextFieldBuscar.equals(" ")) {
            List<Token> TokenLista = tabla.getListaTokensinicial().stream().filter(tok -> tok.getLexema().contains(jTextFieldBuscar.getText())).toList();
            if (TokenLista.size()!=0) {
                tabla.crearTabla(TokenLista);
            }else{
                JOptionPane.showMessageDialog(this, "No existen Lexemas que coincidan con la Busqueda");
            }
        }
    }//GEN-LAST:event_jButtonBuscarMouseClicked

    private void jTextFieldBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMouseClicked
        if(jTextFieldBuscar.getText().equals("Buscar Lexema")){
            jTextFieldBuscar.setText("");
        }
    }//GEN-LAST:event_jTextFieldBuscarMouseClicked

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClasificar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
