/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.Conexao;
import dao.RecomendaDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.userInfos;
import view.Menuview;
import view.Recomendaview;


public class RecomendaController {
    
    private final Recomendaview view;

    public RecomendaController(Recomendaview view) {
        this.view = view;
    }
    
    public void ReturnMenu() throws SQLException, IOException {
        Menuview menuReturn = new Menuview();
        view.setVisible(false);
        menuReturn.setVisible(true);
    }
    
    public void jTableGetRecomenda() throws SQLException, IOException {
        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        RecomendaDAO recomendaDAO = new RecomendaDAO(conexao);

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();

        //Retorna o resultado da querry
        ResultSet resultSet = recomendaDAO.getRecomendedMusics(id);

        //Adiciona os valores na tabela
        while (resultSet.next()) {
            Object[] data = {resultSet.getString("nome"), resultSet.getString("nota")};
            view.setMusicsTable(data);
        }
    }
    
}
