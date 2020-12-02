/*
 * CB
 */
package Controller;

import dao.Conexao;
import dao.NotasDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.userInfos;
import view.Avaliacaoview;
import view.Menuview;


public class AvaliacaoController {

    private final Avaliacaoview view;

    public AvaliacaoController(Avaliacaoview view) {
        this.view = view;
    }

    public void ReturnMenu() throws SQLException, IOException {
        Menuview menuReturn = new Menuview();
        view.setVisible(false);
        menuReturn.setVisible(true);
    }

    public void listarAvaliacao() throws SQLException, IOException {

        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        NotasDAO notasDAO = new NotasDAO(conexao);

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();

        //Retorna o resultado da querry1
        ResultSet resultSet = notasDAO.selectAvaliacao(id);

        while (resultSet.next()) {
            Object[] data = {resultSet.getString("nome"), resultSet.getString("nota")};
            view.setAvaliacaoTable(data);
        }

        //Retorna o resultado da querry2
        ResultSet resultSet1 = notasDAO.selectNotAvaliado(id);

        while (resultSet1.next()) {
            String nota;
            if (resultSet1.getString("nota") == null) {
                nota = "0";
                Object[] data = {resultSet1.getString("nome"), nota};
                view.setAvaliacaoTable(data);
            }

        }

    }

    public void salvarAvaliacao(String nome, String nota) throws IOException, SQLException {
        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        NotasDAO notasDAO = new NotasDAO(conexao);

        userInfos usrInfo = new userInfos();
        String id = usrInfo.getID();

        boolean existe = notasDAO.existeAvaliacao(id, nome);

        if (existe) {
            notasDAO.updateNota(nome, nota, id);
        } else {
            notasDAO.addNota(nome, nota, id);
        }
    }

    public void mediaAvalaicao(String nome) throws SQLException {
        int count = 0;
        String aux, aux2;

        //Conexao com o banco
        Connection conexao = new Conexao().getConnection();
        NotasDAO notasDAO = new NotasDAO(conexao);

        ResultSet resultSet = notasDAO.selectAllNota(nome);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("musicasid") + resultSet.getString("nota"));
        }
    }

}
