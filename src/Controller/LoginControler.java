/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.Loginview;
import view.Menuview;
import utils.userInfos;
import view.FormCadastroview;

public class LoginControler {

    private final Loginview view;

    public LoginControler(Loginview view) {
        this.view = view;
    }

    public void autenticar() throws SQLException, IOException {
        //Buscar um usuario da view
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();

        Usuario usuarioAutenticar = new Usuario(usuario, senha);

        //Verificar se existe no banco de dados.
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        boolean existe = usuarioDao.ExisteEsseUsuario(usuarioAutenticar);
        
        //Se existir redireciona para o menu
        if (existe) {

            //Salva o id do usuario para uso futuros
            saveUserID(usuarioAutenticar, usuarioDao);

            //Logado vai para o menu
            Menuview telaDeMenu = new Menuview();
            telaDeMenu.setVisible(true);
            view.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(view, "Faça seu cadastro!!!");
        }

    }

    public void saveUserID(Usuario usuario, UsuarioDAO usuarioDao) throws SQLException {
        //Retorna o resultado da querry
        ResultSet resultSet = usuarioDao.returnUserID(usuario);

        //Salva Staticamente
        while (resultSet.next()) {
            userInfos usrInfo = new userInfos();
            usrInfo.setUserID(resultSet.getString("id"));
            usrInfo.salvar();
        }
    }
    
    public void FormCadastroview(){
        FormCadastroview telaDeCadastrar = new FormCadastroview();
        view.setVisible(false);
        telaDeCadastrar.setVisible(true);
    }

}
