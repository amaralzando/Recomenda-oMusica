/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FormCadastroview;


public class FormCadastroController {
    private final FormCadastroview view;

    public FormCadastroController(FormCadastroview view) {
        this.view = view;
    }
    
    public void salvaUsuario(){
        
        view.getjTextFieldUsuario().getText();
        
        
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        Usuario usuarioEscrito = new Usuario(usuario, senha);
        
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuarioEscrito);
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!!!");
            view.setVisible(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
