/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.sql.SQLException;
import view.Avaliacaoview;
import view.Generosview;
import view.Loginview;
import view.Menuview;
import view.Recomendaview;


public class MenuController {

    private final Menuview view;

    public MenuController(Menuview view) {
        this.view = view;
    }

    public void TelaGeneros() throws SQLException, IOException {
        Generosview telaGeneros = new Generosview();
        view.setVisible(false);
        telaGeneros.setVisible(true);
    }
    
    public void ReturnLogin() throws SQLException, IOException {
        Loginview telaLogin = new Loginview();
        view.setVisible(false);
        telaLogin.setVisible(true);
    }
    
    public void TelaAvaliacao() throws SQLException, IOException {
        Avaliacaoview avaliacaoview = new Avaliacaoview();
        view.setVisible(false);
        avaliacaoview.setVisible(true);
    }
    
    public void TelaRecomenda() throws SQLException, IOException {
        Recomendaview recomendaview = new Recomendaview();
        view.setVisible(false);
        recomendaview.setVisible(true);
    }
    
}
