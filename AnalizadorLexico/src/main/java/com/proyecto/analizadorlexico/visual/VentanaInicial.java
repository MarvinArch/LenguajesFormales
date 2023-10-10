/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.proyecto.analizadorlexico.visual;


import com.proyecto.analizadorlexico.AnalizadorLexico;
import com.proyecto.analizadorlexico.others.AnalizadorLetras;
import java.awt.Image;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.proyecto.analizadorlexico.others.CargarArchivo;
import com.proyecto.analizadorlexico.others.GuardaArchivo;
import java.awt.Color;

/**
 *
 * @author david
 */
public class VentanaInicial extends javax.swing.JFrame {

    /**
     * Creates new form VentanaInicial
     */
    
    private int x ;
    private int y ;
    private String ubicacion;
    private List<String> ubicacionesAbiertos;
    private Informe info;
    private ArrayList<PanelPrincipal> panel;
    public VentanaInicial() {
        panel= new ArrayList<>();
        ubicacionesAbiertos= new ArrayList<>();
        this.setUndecorated(true);
        initComponents();
        Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 30, 30);
        this.setShape(forma);
        URL rutaca = AnalizadorLexico.class.getProtectionDomain().getCodeSource().getLocation(); 
        String ruta = rutaca.getPath();
        info = new Informe(ruta);
        this.ubicacion = ruta.replace("target/classes/", "");
         info = new Informe(ubicacion);
        imagenCierre(ubicacion);
        imagenMini(ubicacion);
        tab.setBounds(3, 100, 1080,700);
        this.add(tab);
        this.panel.add(new PanelPrincipal());
        tab.add(panel.get(0));
        tab.setTitleAt(0, "Sin Titulo");
        labelTitulo.setText("Sin Titulo");
        
    }
    
    public void imagenCierre(String direccion){
        ImageIcon image = new ImageIcon(direccion+"src/main/java/com/proyecto/analizadorlexico/Resource/cerrar1.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelCierre.setIcon(icon);
        labelCierre.setText("");
        
    }
    public void imagenMini(String direccion){
        ImageIcon image = new ImageIcon(direccion+"src/main/java/com/proyecto/analizadorlexico/Resource/minimizarNegro.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelMini.setIcon(icon);
        labelMini.setText("");
    }
    
    public void crearPanel(){
        this.panel.add(new PanelPrincipal());
        tab.add(panel.get(panel.size()-1));
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelSuperior = new javax.swing.JPanel();
        labelCierre = new javax.swing.JLabel();
        labelMini = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        buttonCargar = new javax.swing.JButton();
        buttonPestaña = new javax.swing.JButton();
        ButtonCerrar = new javax.swing.JButton();
        tab = new javax.swing.JTabbedPane();
        buttonAnalizar = new javax.swing.JButton();
        buttonInfo = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        PanelSuperior.setBackground(new java.awt.Color(204, 204, 204));
        PanelSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelSuperiorMouseDragged(evt);
            }
        });
        PanelSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelSuperiorMousePressed(evt);
            }
        });

        labelCierre.setText("label");
        labelCierre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCierreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCierreMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelCierreMousePressed(evt);
            }
        });

        labelMini.setText("jLabel1");
        labelMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMiniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelMiniMousePressed(evt);
            }
        });

        labelTitulo.setText("jLabel1");

        javax.swing.GroupLayout PanelSuperiorLayout = new javax.swing.GroupLayout(PanelSuperior);
        PanelSuperior.setLayout(PanelSuperiorLayout);
        PanelSuperiorLayout.setHorizontalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(481, Short.MAX_VALUE)
                .addComponent(labelTitulo)
                .addGap(464, 464, 464)
                .addComponent(labelMini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelSuperiorLayout.setVerticalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCierre)
                    .addComponent(labelMini)
                    .addComponent(labelTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonCargar.setText("Abrir");
        buttonCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCargarMouseClicked(evt);
            }
        });

        buttonPestaña.setText("Nueva Pestaña");
        buttonPestaña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPestañaMouseClicked(evt);
            }
        });

        ButtonCerrar.setText("Cerra Pestaña Actual");
        ButtonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonCerrarMouseClicked(evt);
            }
        });

        tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabStateChanged(evt);
            }
        });

        buttonAnalizar.setText("Analizar");
        buttonAnalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAnalizarMouseClicked(evt);
            }
        });

        buttonInfo.setText("Ver Informe");
        buttonInfo.setEnabled(false);
        buttonInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonInfoMouseClicked(evt);
            }
        });
        buttonInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInfoActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonGuardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPestaña, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonCerrar)
                .addGap(18, 18, 18)
                .addComponent(buttonAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCargar)
                    .addComponent(buttonPestaña)
                    .addComponent(ButtonCerrar)
                    .addComponent(buttonAnalizar)
                    .addComponent(buttonInfo)
                    .addComponent(buttonGuardar))
                .addGap(18, 18, 18)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 608, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PanelSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSuperiorMousePressed
        // TODO add your handling code here:
        int x =evt.getX();
        int y =evt.getY();
        //layeredPaneMenu.setVisible(false);

    }//GEN-LAST:event_PanelSuperiorMousePressed

    private void PanelSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSuperiorMouseDragged
        // TODO add your handling code here:
        Point point = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(point.x-x, point.y-y);
    }//GEN-LAST:event_PanelSuperiorMouseDragged

    private void labelCierreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCierreMousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_labelCierreMousePressed

    private void labelCierreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCierreMouseEntered
        // TODO add your handling code here:
        ImageIcon image = new ImageIcon(ubicacion+"src/main/java/com/proyecto/analizadorlexico/Resource/cerrar-sesion.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelCierre.setIcon(icon);
    }//GEN-LAST:event_labelCierreMouseEntered

    private void labelCierreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCierreMouseExited
        
        ImageIcon image = new ImageIcon(ubicacion+"src/main/java/com/proyecto/analizadorlexico/Resource/cerrar1.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelCierre.setIcon(icon);
    }//GEN-LAST:event_labelCierreMouseExited

    private void labelMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMiniMouseEntered
        ImageIcon image = new ImageIcon(ubicacion+"src/main/java/com/proyecto/analizadorlexico/Resource/minimizarGris.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelMini.setIcon(icon);
    }//GEN-LAST:event_labelMiniMouseEntered

    private void labelMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMiniMouseExited
        ImageIcon image = new ImageIcon(ubicacion+"src/main/java/com/proyecto/analizadorlexico/Resource/minimizarNegro.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        labelMini.setIcon(icon);
    }//GEN-LAST:event_labelMiniMouseExited

    private void labelMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMiniMousePressed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_labelMiniMousePressed

    private void buttonCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCargarMouseClicked
        // TODO add your handling code here:
        int apuntador = 0;
        CargarArchivo arch = new CargarArchivo();
        List<String> texto = arch.cargarMiArchivo(this);
        if (texto.get(0).equalsIgnoreCase("aceptado")) {
            texto.remove(0);
            int restul = JOptionPane.showConfirmDialog(this, "Desea Abrir en una nueva Ventana");
            ubicacionesAbiertos.add(arch.getDireccion());
            if (restul == 0) {
                crearPanel();
                apuntador = panel.size() - 1;
            } else {
                apuntador = tab.getSelectedIndex();
            }
            tab.setTitleAt(apuntador, arch.devolverTitulo());
            this.panel.get(apuntador).cambiarTextoArea(texto);
            tab.setSelectedIndex(apuntador);
            labelTitulo.setText(arch.devolverTitulo());
        }else{
            texto.remove(0);
        }
        
        
    }//GEN-LAST:event_buttonCargarMouseClicked

    private void buttonPestañaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPestañaMouseClicked
        crearPanel();
        tab.setTitleAt(panel.size()-1, "Sin Titulo "+ panel.size());
        tab.setSelectedIndex(panel.size()-1);
        labelTitulo.setText("Sin Titulo "+ panel.size());
    }//GEN-LAST:event_buttonPestañaMouseClicked

    private void ButtonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCerrarMouseClicked
        int pestaña = tab.getSelectedIndex()==0 ? 0:tab.getSelectedIndex() ;
        tab.remove(pestaña);
        panel.remove(pestaña);
        
    }//GEN-LAST:event_ButtonCerrarMouseClicked

    private void tabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabStateChanged
        if (tab.getSelectedIndex()!=-1) {
            labelTitulo.setText(tab.getTitleAt(tab.getSelectedIndex()));
        }else{
            labelTitulo.setText("");
        }
        
    }//GEN-LAST:event_tabStateChanged

    private void buttonAnalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAnalizarMouseClicked
        AnalizadorLetras ana = new AnalizadorLetras(panel.get(tab.getSelectedIndex()).devolverTexto());
        boolean errores=ana.analizar() ;
        if (errores==false) {
            buttonInfo.setEnabled(true);
            info.llenartabla(ana.getToken());
            panel.get(tab.getSelectedIndex()).remplaceText2(ana.getToken());//cambia de color el texto
            this.panel.get(tab.getSelectedIndex()).limpiarErrores();
        }else{
            buttonInfo.setEnabled(false);
            List<String> lista = new ArrayList<>();
            int err= ana.getErrores().size();
            for (int i = 0; i < err; i++) {
                lista.add(ana.getErrores().get(i).toString());
            }
            this.panel.get(tab.getSelectedIndex()).cambiarTextoReporte(lista );
        }
    }//GEN-LAST:event_buttonAnalizarMouseClicked

    private void buttonInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonInfoMouseClicked
        if(buttonInfo.getText().equals("Ver Informe")){
            tab.setVisible(false);
            
            this.add(info);
            info.setVisible(true);
            info.setBounds(2, 100, 1050, 660);
            buttonInfo.setText("Volver Archivo");
            buttonInfo.setBackground(Color.red);
        }else{
            buttonInfo.setText("Ver Informe");
            info.setVisible(false);
            tab.setVisible(true);
            buttonInfo.setBackground(buttonAnalizar.getBackground());
        }
    }//GEN-LAST:event_buttonInfoMouseClicked

    private void buttonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonGuardarMouseClicked
        GuardaArchivo arch = new GuardaArchivo();
        if (labelTitulo.getText().contains("Sin Titulo")) {
            boolean grardar = arch.guardarNuevoArchivo(this, this.panel.get(tab.getSelectedIndex()).devolverTexto());
            if (grardar) {
                ubicacionesAbiertos.add(arch.getUbicacion());
                int apuntador = tab.getSelectedIndex();
                tab.setTitleAt(apuntador, arch.devolverTitulo());
                tab.setSelectedIndex(apuntador);
                labelTitulo.setText(arch.devolverTitulo());
            }

        } else {
            String ubica=ubicacionesAbiertos.stream()
                    .filter(ubi -> ubi.contains(labelTitulo.getText()))
                    .findFirst()
                    .orElseThrow(()->new IllegalArgumentException());
            
            arch.guardarArchivoExistente(this, this.panel.get(tab.getSelectedIndex()).devolverTexto(), ubica);
        }
    }//GEN-LAST:event_buttonGuardarMouseClicked

    private void buttonInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonInfoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCerrar;
    private javax.swing.JPanel PanelSuperior;
    private javax.swing.JButton buttonAnalizar;
    private javax.swing.JButton buttonCargar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonInfo;
    private javax.swing.JButton buttonPestaña;
    private javax.swing.JLabel labelCierre;
    private javax.swing.JLabel labelMini;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTabbedPane tab;
    // End of variables declaration//GEN-END:variables
}
