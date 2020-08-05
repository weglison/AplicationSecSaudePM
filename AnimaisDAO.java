/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Animais;
import model.bean.Pessoa;

/**
 *
 * @author aluno
 */
public class AnimaisDAO {

    private Connection con = null;

    public AnimaisDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void insertAnimais(Pessoa pessoa) {
        String sql = "INSERT INTO tbl_animais (idanimais,especie,quantidade,dataanimais) VALUES (?,?,?,?)";

        PreparedStatement stmt = null;
        for (int i = 0; i < pessoa.getListaAnimais().size(); i++) {
            try {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, pessoa.getId());
                stmt.setString(2, pessoa.getListaAnimais().get(i).getEspecie());
                stmt.setInt(3, pessoa.getListaAnimais().get(i).getQuantidade());
                java.sql.Date data = new java.sql.Date(pessoa.getListaAnimais().get(i).getData().getTime());
                stmt.setDate(4, data);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.err.println("Erro:" + ex);
            }
        }
    }

    public void updateAnimais(Pessoa pessoa) {
        String sql = "DELETE FROM bancosecsaude.tbl_animais WHERE idanimais = ? AND dataanimais = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pessoa.getId());
            java.sql.Date data = new java.sql.Date(pessoa.getListaAnimais().get(0).getData().getTime());
            stmt.setDate(2, data);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        String sql1 = "INSERT INTO bancosecsaude.tbl_animais SET idanimais = ? , especie = ? , quantidade = ? , dataanimais = ? ";
        PreparedStatement stmt1 = null;
        for (int i = 0; i < pessoa.getListaAnimais().size(); i++) {
            try {
                stmt1 = con.prepareStatement(sql1);
                stmt1.setInt(1, pessoa.getId());
                stmt1.setString(2, pessoa.getListaAnimais().get(i).getEspecie());
                stmt1.setInt(3, pessoa.getListaAnimais().get(i).getQuantidade());
                java.sql.Date data = new java.sql.Date(pessoa.getListaAnimais().get(i).getData().getTime());
                stmt1.setDate(4, data);
                stmt1.executeUpdate();
            } catch (SQLException ex) {
                System.err.println("Erro:" + ex);
            }
        }
    }

    

    public boolean updateAnimais(Animais animal, Pessoa pessoa) {
        String sql = "UPDATE tbl_animais SET quantidade = ? WHERE idanimais = ? and especie = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, animal.getQuantidade());
            stmt.setInt(2, pessoa.getId());
            stmt.setString(3, animal.getEspecie());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }

    }

    public boolean deleteAnimal(Animais animal, Pessoa pessoa) {
        String sql = "DELETE FROM tbl_animais WHERE idanimais = ? and especie =?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pessoa.getId());
            stmt.setString(2, animal.getEspecie());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }

    }

    public int somaEspecieBairro(String bairro, Date dataIncial, Date dataFinal, String especie) {

        String sql = "SELECT sum(quantidade)  FROM tbl_pessoas INNER JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais WHERE LOWER(especie) = LOWER(?) AND bairro = ? AND dataanimais BETWEEN ? AND ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        int soma = 0;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, especie);
            stmt.setString(2, bairro);
            stmt.setDate(3, dataIncial);
            stmt.setDate(4, dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                soma = rs.getInt("sum(quantidade)");
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return soma;
    }

    public int somaEspecieRegiao(String regiao, Date dataIncial, Date dataFinal, String especie) {

        String sql = "SELECT sum(quantidade)  FROM tbl_pessoas INNER JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais WHERE LOWER(especie) = LOWER(?) AND regiao = ? AND dataanimais BETWEEN ? AND ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        int soma = 0;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, especie);
            stmt.setString(2, regiao);
            stmt.setDate(3, dataIncial);
            stmt.setDate(4, dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                soma = rs.getInt("sum(quantidade)");
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return soma;
    }

}
