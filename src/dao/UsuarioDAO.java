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
import model.Usuario;


public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Usuario usuario) throws SQLException {

        String sql = "insert into usuarios(usuario,senha) values('" + usuario.getUsuario() + "', '" + usuario.getSenha() + "'); ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public boolean ExisteEsseUsuario(Usuario usuario) throws SQLException {
        String sql = "select * from usuarios where usuario = '" + usuario.getUsuario() + "' and senha = '" + usuario.getSenha() + "' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }

    public ResultSet returnUserID(Usuario usuario) throws SQLException {

        String sql = "select id from usuarios where usuario = '" + usuario.getUsuario() + "' and senha = '" + usuario.getSenha() + "' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;

    }
}
