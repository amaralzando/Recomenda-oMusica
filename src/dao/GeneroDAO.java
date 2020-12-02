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
import model.Genero;


public class GeneroDAO {

    private final Connection connection;

    public GeneroDAO(Connection connection) {
        this.connection = connection;
    }

    public void addGenero(Genero genero, String id) throws SQLException {

        String sql = "insert into generos(genero,datatime,userId) values('" + genero.getGenero() + "', '" + genero.getData() + "', '" + id + "'); ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();

    }

    public void removeGenero(String genero, String id) throws SQLException {
        String sql = "DELETE FROM generos WHERE genero = '" + genero + "' and userid = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public boolean isGeneroOndb(Genero genero, String id) throws SQLException {

        String sql = "select * from generos where genero = '" + genero.getGenero() + "' and userId = '" + id + "'; ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }

    public ResultSet selectGenero(String id) throws SQLException {

        String sql = "select * from generos where userId = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

}
