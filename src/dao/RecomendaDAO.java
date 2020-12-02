/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecomendaDAO {

    private final Connection connection;

    public RecomendaDAO(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getRecomendedMusics(String id) throws SQLException, IOException {
        String sql = "SELECT ms.id, ms.nome, ms.nota FROM musicas ms \n"
                + "	left JOIN generos ge ON POSITION(ge.genero IN ms.genero)<>0\n"
                + "		left JOIN notasmusicas nm ON ms.id = nm.musicasid and ge.userid = nm.userid\n"
                + "			WHERE ge.userid = '" + id + "' and nm.nota is null OR nm.nota = '0' ORDER BY ms.nota desc";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }

}
