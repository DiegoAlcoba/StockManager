/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.*;

import modelo.db.OperacionesBD_usuario;
import modelo.entidad.Usuario;

/**
 *
 * @author diego
 */
public class ControladorLoggin extends JFrame {

    public Usuario acceso(JTextField user, JPasswordField pass, JPanel log, JPanel menu){
        if(user.getText().isEmpty() || pass.getPassword().length == 0){
            JOptionPane.showMessageDialog(null, "Rellena los campos para continuar", "Error de acceso", JOptionPane.ERROR_MESSAGE);
        }else{
            String usuario = user.getText();
            char[] contra = pass.getPassword();
            if(OperacionesBD_usuario.getUsuario_BD(usuario) != null){
                Usuario usuarioBD = OperacionesBD_usuario.getUsuario_BD(usuario);
                if(usuarioBD.getPass().equals(String.valueOf(contra))){
                    getRootPane().setDefaultButton(null);
                    Usuario logger = usuarioBD;
                    menu.setVisible(true);
                    log.setVisible(false);
                    revalidate();
                    repaint();
                    pack();
                    setSize(400, 600);
                    return logger;
                }else{
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error de acceso", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "El usuario no es correcto", "Error de acceso", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
