/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.Conexao;
import dao.GeneroDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Genero;
import view.Generosview;

import java.util.Calendar;
import utils.userInfos;
import view.Menuview;


public class GeneroController {

    private Generosview view;

    public GeneroController(Generosview view) {
        this.view = view;
    }

    public void salvaGenero() throws SQLException, IOException {

        String genero = view.getjTextFieldGenero().getText();
        Genero generoIsonDb = new Genero(genero.toLowerCase(), getDate());

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();

        //Verificar se existe no banco de dados.
        Connection conexao = new Conexao().getConnection();
        GeneroDAO generoDAO = new GeneroDAO(conexao);
        boolean existe = generoDAO.isGeneroOndb(generoIsonDb, id);

        if (existe) {
            //Se ja existir retorna msg abaixo
            JOptionPane.showMessageDialog(view, "Este Genero ja esta na sua lista!!!");
        } else {
            //Adiciona genero no banco
            generoDAO.addGenero(generoIsonDb, id);

            //Adiciona Genero no Jpanel
            Object[] data = {genero.toLowerCase(), getDate()};
            view.setGenerosTable(data);

            //Mensagem de adicionado com sucesso
            JOptionPane.showMessageDialog(view, "Genero Adicionado na sua lista!!!");

        }

    }

    public void removerGenero() throws SQLException, IOException {
        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        GeneroDAO generoDAO = new GeneroDAO(conexao);

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();
        
        String genero = view.onClickRemove();
        
        generoDAO.removeGenero(genero, id);
    }

    public void jTableGetGeneros() throws SQLException, IOException {
        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        GeneroDAO generoDAO = new GeneroDAO(conexao);

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();

        //Retorna o resultado da querry
        ResultSet resultSet = generoDAO.selectGenero(id);

        //Adiciona os valores na tabela
        while (resultSet.next()) {
            Object[] data = {resultSet.getString("genero"), resultSet.getString("datatime")};
            view.setGenerosTable(data);
        }
    }
    
    public void ReturnMenu() throws SQLException, IOException{
        Menuview telaMenu = new Menuview();
        view.setVisible(false);
        telaMenu.setVisible(true);
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();
        String s = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        return s;
    }
}
