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
import model.bean.Perguntas;
import model.bean.Pessoa;

/**
 *
 * @author aluno
 */
public class PerguntasDAO {

    private Connection con = null;

    public PerguntasDAO() {

        con = ConnectionFactory.getConnection();
    }

    public void insertPerguntas(Pessoa pessoa) {
        String sql = "INSERT INTO tbl_perguntas (idperguntas,cisterna,cisternaconsumo,cxdagua,tampada,pcartesiano,pococonsumo,fseptica,animais,capacidade,datapergunta) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            for (int i = 0; i < pessoa.getListaPerguntas().size(); i++) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, pessoa.getId());
                if (pessoa.getListaPerguntas().get(i).isCisterna() == true) {
                    stmt.setInt(2, 1);
                } else {
                    stmt.setInt(2, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isCisternaconsumo() == true) {
                    stmt.setInt(3, 1);
                } else {
                    stmt.setInt(3, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isCxdagua() == true) {
                    stmt.setInt(4, 1);
                } else {
                    stmt.setInt(4, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isTampada() == true) {
                    stmt.setInt(5, 1);
                } else {
                    stmt.setInt(5, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isPcartesiano() == true) {
                    stmt.setInt(6, 1);
                } else {
                    stmt.setInt(6, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isPococonsumo() == true) {
                    stmt.setInt(7, 1);
                } else {
                    stmt.setInt(7, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isFseptica() == true) {
                    stmt.setInt(8, 1);
                } else {
                    stmt.setInt(8, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isAnimais() == true) {
                    stmt.setInt(9, 1);
                } else {
                    stmt.setInt(9, 0);
                }
                stmt.setInt(10, pessoa.getListaPerguntas().get(i).getCapacidade());
                java.sql.Date data = new java.sql.Date(pessoa.getListaPerguntas().get(i).getData().getTime());
                stmt.setDate(11, data);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }

    }
    public void updatePerguntas(Pessoa pessoa) {
        String sql = "UPDATE tbl_perguntas set cisterna = ? , cisternaconsumo = ? , cxdagua = ? , tampada = ? , pcartesiano = ? , pococonsumo = ? , fseptica = ? , animais = ? , capacidade = ?  " +
                   "WHERE idperguntas = ? AND datapergunta = ?";
        PreparedStatement stmt = null;

        try {
            for (int i = 0; i < pessoa.getListaPerguntas().size(); i++) {
                stmt = con.prepareStatement(sql);
                if (pessoa.getListaPerguntas().get(i).isCisterna() == true) {
                    stmt.setInt(1, 1);
                } else {
                    stmt.setInt(1, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isCisternaconsumo() == true) {
                    stmt.setInt(2, 1);
                } else {
                    stmt.setInt(2, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isCxdagua() == true) {
                    stmt.setInt(3, 1);
                } else {
                    stmt.setInt(3, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isTampada() == true) {
                    stmt.setInt(4, 1);
                } else {
                    stmt.setInt(4, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isPcartesiano() == true) {
                    stmt.setInt(5, 1);
                } else {
                    stmt.setInt(5, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isPococonsumo() == true) {
                    stmt.setInt(6, 1);
                } else {
                    stmt.setInt(6, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isFseptica() == true) {
                    stmt.setInt(7, 1);
                } else {
                    stmt.setInt(7, 0);
                }
                if (pessoa.getListaPerguntas().get(i).isAnimais() == true) {
                    stmt.setInt(8, 1);
                } else {
                    stmt.setInt(8, 0);
                }
                stmt.setInt(9, pessoa.getListaPerguntas().get(i).getCapacidade());
                stmt.setInt(10, pessoa.getId());
                java.sql.Date data = new java.sql.Date(pessoa.getListaPerguntas().get(i).getData().getTime());
                stmt.setDate(11, data);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }

    }

    public ArrayList<Perguntas> listaPerguntas(Pessoa pessoa) {

        String sql = "SELECT * FROM tbl_perguntas WHERE idperguntas = ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Perguntas> perguntas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pessoa.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Perguntas perg = new Perguntas();
                perg.setCisterna((rs.getInt("cisterna") == 1));
                perg.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));

                perguntas.add(perg);
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return perguntas;
    }

    public int[] somaPerguntasBairro(String bairro, Date dataIncial, Date dataFinal) {

        String sql = "SELECT sum(cisterna),sum(cisternaconsumo),sum(cxdagua),sum(tampada),sum(pcartesiano),sum(pococonsumo),sum(fseptica),sum(animais) FROM tbl_pessoas "
                + "INNER JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas WHERE bairro = ? AND datapergunta BETWEEN ? AND ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        int [] soma = {0,0,0,0,0,0,0,0};

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, bairro);
            stmt.setDate(2, dataIncial);
            stmt.setDate(3, dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                soma[0]=rs.getInt("sum(cisterna)");
                soma[1]=rs.getInt("sum(cisternaconsumo)");
                soma[2]=rs.getInt("sum(cxdagua)");
                soma[3]=rs.getInt("sum(tampada)");
                soma[4]=rs.getInt("sum(pcartesiano)");
                soma[5]=rs.getInt("sum(pococonsumo)");
                soma[6]=rs.getInt("sum(fseptica)");
                soma[7]=rs.getInt("sum(animais)");
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return soma;
    }
    
    public int[] somaPerguntasRegiao(String regiao, Date dataIncial, Date dataFinal) {

        String sql = "SELECT sum(cisterna),sum(cisternaconsumo),sum(cxdagua),sum(tampada),sum(pcartesiano),sum(pococonsumo),sum(fseptica),sum(animais) FROM tbl_pessoas "
                + "INNER JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas WHERE regiao = ? AND datapergunta BETWEEN ? AND ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        int [] soma = {0,0,0,0,0,0,0,0};

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, regiao);
            stmt.setDate(2, dataIncial);
            stmt.setDate(3, dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                soma[0]=rs.getInt("sum(cisterna)");
                soma[1]=rs.getInt("sum(cisternaconsumo)");
                soma[2]=rs.getInt("sum(cxdagua)");
                soma[3]=rs.getInt("sum(tampada)");
                soma[4]=rs.getInt("sum(pcartesiano)");
                soma[5]=rs.getInt("sum(pococonsumo)");
                soma[6]=rs.getInt("sum(fseptica)");
                soma[7]=rs.getInt("sum(animais)");
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return soma;
    }

}
