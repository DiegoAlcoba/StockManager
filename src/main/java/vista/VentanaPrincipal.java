/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.db.Conexion;
import modelo.db.OperacionesBD_producto;
import modelo.db.OperacionesBD_usuario;
import modelo.entidad.*;

public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        Conexion.getConexion();
        Usuario test = new Usuario("migueli", "limon", true, "miguel", 44, "miguel@gmail.com", 640745903);
        OperacionesBD_usuario.addUser_BD(test);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        userLabel = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        acceder = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StockManager");
        setBackground(new java.awt.Color(3, 132, 186));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        userLabel.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(userLabel, gridBagConstraints);

        user.setPreferredSize(new java.awt.Dimension(100, 22));
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(user, gridBagConstraints);

        passLabel.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(passLabel, gridBagConstraints);

        acceder.setText("Acceder");
        acceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accederActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(acceder, gridBagConstraints);

        pass.setPreferredSize(new java.awt.Dimension(100, 22));
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(pass, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void accederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accederActionPerformed
        
        if(user.getText().isEmpty() || pass.getPassword().length == 0){
            JOptionPane.showMessageDialog(null, "Rellena los campos para continuar", "Error de acceso", JOptionPane.ERROR_MESSAGE);
        }else{
            String usuario = user.getText();
            char[] contra = pass.getPassword();
            if(OperacionesBD_usuario.getUsuario_BD(usuario) != null){
                Usuario usuarioBD = OperacionesBD_usuario.getUsuario_BD(usuario);
                if(usuarioBD.getPass().equals(String.valueOf(contra))){
                    //TODO: abrir ventana de usuario
                }else{
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error de acceso", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "El usuario no es correcto", "Error de acceso", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_accederActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });

        
        //**PRESCINDIBLE** para depuración - app se conecta a la BD correctamente al iniciar
        Connection con = Conexion.getConexion();
        //Desconexión de la BD
        Conexion.closeConexion(con);

       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceder;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passLabel;
    private javax.swing.JTextField user;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
