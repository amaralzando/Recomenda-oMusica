/*
 * CB
 * Armazena em txt 
 * CB
 */
package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class userInfos {

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String salvar() {

        try {

            FileWriter fw = new FileWriter("dados.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.userID);
            pw.flush();
            pw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(userInfos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String getID() throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("dados.txt"));
        String linha = "";
        String userId = "";
        while (true) {
            if (linha != null) {
                userId = linha;
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
        buffRead.close();
        return userId;
    }

}
