/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class ConnectionFactory {
    public static Connection getConnection() {

        java.sql.Connection conexao;
        //classe do driver
        String driver = "com.mysql.jdbc.Driver";
        // informações sobre o banco
        String url = "jdbc:mysql://localhost:3306/bancosecsaude?useSSL=false";
        String user = "root";
        String password = "12345";
        // estabelecendo a conexão com o banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);

            System.out.println("Conectou-se ao banco !");
            return conexao;
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("Driver não existe !");
            return null;
        } catch (java.sql.SQLException e) {
            System.err.println("Não conectou !");
            return null;
        }
    }

    public static void closeConnetion(Connection con) {

        if (con != null) {

            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão !" + ex);
            }

        }
    }

    public static void closeConnetion(Connection con, PreparedStatement stmt) {

        if (stmt != null) {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão !" + ex);
            }
            closeConnetion(con);
        }
    }

    public static void closeConnetion(Connection con, PreparedStatement stmt, ResultSet rs) {

        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão !" + ex);
            }
            closeConnetion(con, stmt);
        }
    }

}
