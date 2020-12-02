/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.NotasMusicas;


public class NotasDAO {

    private final Connection connection;

    public NotasDAO(Connection connection) {
        this.connection = connection;
    }

    public ResultSet returnUserID(NotasMusicas notasMusicas) throws SQLException {
        String sql = "";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }

    public ResultSet selectAvaliacao(String id) throws SQLException {

        String sql = "SELECT ms.nome, nm.nota FROM musicas ms \n"
                + "		JOIN notasmusicas nm ON ms.id = nm.musicasid\n"
                + "			WHERE nm.userid = '" + id + "' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }

    public ResultSet selectNotAvaliado(String id) throws SQLException {

        String sql = "SELECT ms.nome, nm.nota FROM musicas ms \n"
                + "	left JOIN generos ge ON POSITION(ge.genero IN ms.genero)<>0\n"
                + "		left JOIN notasmusicas nm ON ms.id = nm.musicasid and ge.userid = nm.userid\n"
                + "			WHERE ge.userid = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }

    public boolean existeAvaliacao(String id, String nome) throws SQLException {
        String sql = "SELECT * FROM musicas ms \n"
                + "		JOIN notasmusicas nm ON ms.id = nm.musicasid\n"
                + "			WHERE nm.userid = '" + id + "' AND ms.nome = '" + nome + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }

    public void addNota(String nome, String nota, String userid) throws SQLException {

        String sql = "INSERT INTO notasmusicas (musicasid, nota, userid) values ((SELECT id FROM musicas WHERE nome = '" + nome + "'),'" + nota + "','" + userid + "')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();

    }

    public void updateNota(String nome, String nota, String userid) throws SQLException {

        String sql = "UPDATE notasmusicas SET nota='" + nota + "' WHERE userid = '" + userid + "' and musicasid = (SELECT id FROM musicas WHERE nome = '" + nome + "')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public ResultSet selectAllNota(String nome) throws SQLException {

        String sql = "SELECT musicasid, nota FROM notasmusicas WHERE musicasid = (SELECT id FROM musicas WHERE nome= '" + nome + "')";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }

}
