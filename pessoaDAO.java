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
import model.bean.Pessoa;
import model.bean.Perguntas;
import model.bean.Animais;

/**
 *
 * @author aluno
 */
public class pessoaDAO {

    private Connection con = null;

    public pessoaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public int insertPessoas(Pessoa pessoa) {
        int id = 0;
        String sql = "INSERT INTO tbl_pessoas (numero,nome,endereco,bairro,cidade,regiao) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getNumero());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getEndereco());
            stmt.setString(4, pessoa.getBairro());
            stmt.setString(5, pessoa.getCidade());
            stmt.setString(6, pessoa.getRegiao());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }
        return id;
    }

    public Pessoa verificador(Pessoa pessoa) {
        String sql = "SELECT * FROM tbl_pessoas WHERE LOWER(numero) = LOWER(?) AND LOWER(endereco) = LOWER(?) AND LOWER(bairro) = LOWER(?) AND LOWER(cidade) = LOWER(?) AND LOWER(regiao) = LOWER(?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pessoa.getNumero());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setString(3, pessoa.getBairro());
            stmt.setString(4, pessoa.getCidade());
            stmt.setString(5, pessoa.getRegiao());
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(Integer.parseInt(rs.getString("idpessoas")));
                p.setNumero((rs.getString("numero")));
                p.setNome(rs.getString("nome"));
                p.setEndereco(rs.getString("endereco"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setData(rs.getString("regiao"));
                return p;
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        return null;
    }

    public ArrayList<Pessoa> listaPessoas(String nome) {

        String sql = "SELECT * FROM tbl_pessoas WHERE nome = ?";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Pessoa> pessoas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                pessoa.setNumero((rs.getString("numero")));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setData(rs.getString("regiao"));
                pessoas.add(pessoa);
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return pessoas;
    }

    public boolean updatePessoa(Pessoa pessoa) {
        String sql = "UPDATE tbl_pessoas SET nome = ? WHERE idpessoas = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }

    }

    public ArrayList<Pessoa> listaEnderecos(String endereco, String bairro, String numero) {

        String sql = "SELECT * FROM tbl_pessoas WHERE endereco = ? AND numero = ? AND bairro = ? ";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Pessoa> Enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, endereco);
            stmt.setString(2, numero);
            stmt.setString(3, bairro);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                pessoa.setNumero((rs.getString("numero")));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setData(rs.getString("regiao"));
                Enderecos.add(pessoa);
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public ArrayList<Pessoa> listaEnderecos2(String endereco, String bairro, String numero) {
        String sql = "SELECT * FROM  (SELECT * FROM (tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas)"
                + "LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais AND tbl_perguntas.dataPergunta = tbl_animais.dataAnimais) AS x "
                + "WHERE LOWER(x.endereco) = LOWER(?) AND x.numero = ? AND LOWER(x.bairro) = LOWER(?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pessoa> Enderecos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, endereco);
            stmt.setString(2, numero);
            stmt.setString(3, bairro);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
                Pessoa pessoa = Enderecos.get(Enderecos.size() - 1);
                if (pessoa.getListaPerguntas().size() == 0) {
                    Perguntas pergunta = new Perguntas();
                    pergunta.setCisterna(rs.getInt("cisterna") == 1);
                    pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                    pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                    pergunta.setTampada(rs.getInt("tampada") == 1);
                    pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                    pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                    pergunta.setFseptica(rs.getInt("fseptica") == 1);
                    pergunta.setAnimais(rs.getInt("animais") == 1);
                    pergunta.setCapacidade(Integer.parseInt(rs.getString("capacidade")));
                    pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                    pessoa.getListaPerguntas().add(pergunta);
                } else {
                    Perguntas ultimaPergunta = pessoa.getListaPerguntas().get(pessoa.getListaPerguntas().size() - 1);
                    if (ultimaPergunta.getData().getTime() != rs.getDate("datapergunta").getTime()) {
                        Perguntas pergunta = new Perguntas();
                        pergunta.setCisterna(rs.getInt("cisterna") == 1);
                        pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                        pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                        pergunta.setTampada(rs.getInt("tampada") == 1);
                        pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                        pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                        pergunta.setFseptica(rs.getInt("fseptica") == 1);
                        pergunta.setAnimais(rs.getInt("animais") == 1);
                        pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                        pessoa.getListaPerguntas().add(pergunta);
                    }
                }
                Animais animais = new Animais();
                animais.setEspecie(rs.getString("especie"));
                animais.setQuantidade(rs.getInt("quantidade"));
                if (rs.getDate("dataanimais") != null) {
                    animais.setData(new java.util.Date(rs.getDate("dataanimais").getTime()));
                    pessoa.getListaAnimais().add(animais);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public ArrayList<Pessoa> listaEnderecos2() {
        String sql = "SELECT * FROM  (SELECT * FROM (tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas)"
                + "LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais AND tbl_perguntas.dataPergunta = tbl_animais.dataAnimais) AS x ";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pessoa> Enderecos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
                Pessoa pessoa = Enderecos.get(Enderecos.size() - 1);
                if (pessoa.getListaPerguntas().size() == 0) {
                    Perguntas pergunta = new Perguntas();
                    pergunta.setCisterna(rs.getInt("cisterna") == 1);
                    pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                    pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                    pergunta.setTampada(rs.getInt("tampada") == 1);
                    pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                    pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                    pergunta.setFseptica(rs.getInt("fseptica") == 1);
                    pergunta.setAnimais(rs.getInt("animais") == 1);
                    pergunta.setCapacidade(Integer.parseInt(rs.getString("capacidade")));
                    pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                    pessoa.getListaPerguntas().add(pergunta);
                } else {
                    Perguntas ultimaPergunta = pessoa.getListaPerguntas().get(pessoa.getListaPerguntas().size() - 1);
                    if (ultimaPergunta.getData().getTime() != rs.getDate("datapergunta").getTime()) {
                        Perguntas pergunta = new Perguntas();
                        pergunta.setCisterna(rs.getInt("cisterna") == 1);
                        pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                        pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                        pergunta.setTampada(rs.getInt("tampada") == 1);
                        pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                        pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                        pergunta.setFseptica(rs.getInt("fseptica") == 1);
                        pergunta.setAnimais(rs.getInt("animais") == 1);
                        pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                        pessoa.getListaPerguntas().add(pergunta);
                    }
                }
                Animais animais = new Animais();
                animais.setEspecie(rs.getString("especie"));
                animais.setQuantidade(rs.getInt("quantidade"));
                animais.setData(new java.util.Date(rs.getDate("dataanimais").getTime()));
                pessoa.getListaAnimais().add(animais);
                System.out.println(animais);

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public ArrayList<Pessoa> listaEnderecos3(String endereco, String bairro, String numero) {
        String sql = "SELECT *, SUM(x.quantidade)  "
                + "FROM  (SELECT * FROM (tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas)"
                + "LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais AND tbl_perguntas.dataPergunta = tbl_animais.dataAnimais) AS x "
                + "WHERE x.endereco = ? AND x.numero = ? AND x.bairro = ? GROUP BY x.dataAnimais";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pessoa> Enderecos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, endereco);
            stmt.setString(2, numero);
            stmt.setString(3, bairro);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
                Pessoa pessoa = Enderecos.get(Enderecos.size() - 1);
                if (pessoa.getListaPerguntas().size() == 0) {
                    Perguntas pergunta = new Perguntas();
                    pergunta.setCisterna(rs.getInt("cisterna") == 1);
                    pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                    pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                    pergunta.setTampada(rs.getInt("tampada") == 1);
                    pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                    pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                    pergunta.setFseptica(rs.getInt("fseptica") == 1);
                    pergunta.setAnimais(rs.getInt("animais") == 1);
                    pergunta.setCapacidade(Integer.parseInt(rs.getString("capacidade")));
                    pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                    pessoa.getListaPerguntas().add(pergunta);
                } else {
                    Perguntas ultimaPergunta = pessoa.getListaPerguntas().get(pessoa.getListaPerguntas().size() - 1);
                    System.out.println("Última pergunta: " + ultimaPergunta);
                    System.out.println(ultimaPergunta.getData().getTime());
                    System.out.println(rs.getDate("datapergunta").getTime());
                    if (ultimaPergunta.getData().getTime() != rs.getDate("datapergunta").getTime()) {
                        Perguntas pergunta = new Perguntas();
                        pergunta.setCisterna(rs.getInt("cisterna") == 1);
                        pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                        pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                        pergunta.setTampada(rs.getInt("tampada") == 1);
                        pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                        pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                        pergunta.setFseptica(rs.getInt("fseptica") == 1);
                        pergunta.setAnimais(rs.getInt("animais") == 1);
                        pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                        pessoa.getListaPerguntas().add(pergunta);
                    }
                }
                Animais animais = new Animais();
                animais.setEspecie(rs.getString("especie"));
                animais.setQuantidade(rs.getInt("quantidade"));
                pessoa.getListaAnimais().add(animais);
                System.out.println(animais);

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public boolean deletePessoa(Pessoa pessoa) {
        String sql = "DELETE FROM tbl_pessoas WHERE idpessoas = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pessoa.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnetion(con, stmt);
        }
    }

    public ArrayList<Pessoa> listaEnderecosData(Date dataIncial, Date dataFinal) {

        String sql = "SELECT * FROM  (SELECT * FROM (tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas)"
                + "LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais AND tbl_perguntas.dataPergunta = tbl_animais.dataAnimais) AS x "
                + "WHERE datapergunta BETWEEN ? AND ? ";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Pessoa> Enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, dataIncial);
            stmt.setDate(2, dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
                Pessoa pessoa = Enderecos.get(Enderecos.size() - 1);
                if (pessoa.getListaPerguntas().size() == 0) {
                    Perguntas pergunta = new Perguntas();
                    pergunta.setCisterna(rs.getInt("cisterna") == 1);
                    pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                    pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                    pergunta.setTampada(rs.getInt("tampada") == 1);
                    pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                    pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                    pergunta.setFseptica(rs.getInt("fseptica") == 1);
                    pergunta.setAnimais(rs.getInt("animais") == 1);
                    pergunta.setCapacidade(Integer.parseInt(rs.getString("capacidade")));
                    pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                    pessoa.getListaPerguntas().add(pergunta);
                } else {
                    Perguntas ultimaPergunta = pessoa.getListaPerguntas().get(pessoa.getListaPerguntas().size() - 1);
                    if (ultimaPergunta.getData().getTime() != rs.getDate("datapergunta").getTime()) {
                        Perguntas pergunta = new Perguntas();
                        pergunta.setCisterna(rs.getInt("cisterna") == 1);
                        pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                        pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                        pergunta.setTampada(rs.getInt("tampada") == 1);
                        pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                        pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                        pergunta.setFseptica(rs.getInt("fseptica") == 1);
                        pergunta.setAnimais(rs.getInt("animais") == 1);
                        pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                        pessoa.getListaPerguntas().add(pergunta);
                    }
                }
                Animais animais = new Animais();
                animais.setEspecie(rs.getString("especie"));
                animais.setQuantidade(rs.getInt("quantidade"));
                if (rs.getDate("dataanimais") != null) {
                    animais.setData(new java.util.Date(rs.getDate("dataanimais").getTime()));
                    pessoa.getListaAnimais().add(animais);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public ArrayList<Pessoa> listaEnderecosQuestionario(String campo) {

        String sql = "SELECT * FROM tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas  WHERE " + campo + " = ? ";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Pessoa> Enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public ArrayList<Pessoa> listaEnderecosQuestionarioEspecie(String especie) {

        String sql = "SELECT * FROM tbl_pessoas LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais WHERE LOWER (especie) = LOWER(?) ";

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ArrayList<Pessoa> Enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, especie);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (Enderecos.size() == 0) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                    pessoa.setNumero((rs.getString("numero")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setRegiao(rs.getString("regiao"));
                    Enderecos.add(pessoa);
                } else {
                    if (Enderecos.get(Enderecos.size() - 1).getId() != Integer.parseInt(rs.getString("idpessoas"))) {
                        Pessoa pessoa = new Pessoa();
                        pessoa.setId(Integer.parseInt(rs.getString("idpessoas")));
                        pessoa.setNumero((rs.getString("numero")));
                        pessoa.setNome(rs.getString("nome"));
                        pessoa.setEndereco(rs.getString("endereco"));
                        pessoa.setBairro(rs.getString("bairro"));
                        pessoa.setCidade(rs.getString("cidade"));
                        pessoa.setRegiao(rs.getString("regiao"));
                        Enderecos.add(pessoa);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {

            ConnectionFactory.closeConnetion(con, stmt, rs);
        }
        return Enderecos;
    }

    public Pessoa buscaEditar(String endereco, String numero, String bairro, Date data) {
        String sql = "SELECT * FROM  (SELECT * FROM (tbl_pessoas LEFT JOIN tbl_perguntas ON tbl_pessoas.idpessoas = tbl_perguntas.idperguntas) "
                + "LEFT JOIN tbl_animais ON tbl_pessoas.idpessoas = tbl_animais.idanimais AND tbl_perguntas.dataPergunta = tbl_animais.dataAnimais) AS x "
                + " WHERE LOWER(numero) = LOWER(?) AND LOWER(endereco) = LOWER(?) AND LOWER(bairro) = LOWER(?) AND datapergunta = ? ";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, numero);
            stmt.setString(2, endereco);
            stmt.setString(3, bairro);
            stmt.setDate(4, data);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(Integer.parseInt(rs.getString("idpessoas")));
                p.setNumero((rs.getString("numero")));
                p.setNome(rs.getString("nome"));
                p.setEndereco(rs.getString("endereco"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setRegiao(rs.getString("regiao"));
                
                Perguntas pergunta = new Perguntas();
                pergunta.setCisterna(rs.getInt("cisterna") == 1);
                pergunta.setCisternaconsumo(rs.getInt("cisternaconsumo") == 1);
                pergunta.setCxdagua(rs.getInt("cxdagua") == 1);
                pergunta.setTampada(rs.getInt("tampada") == 1);
                pergunta.setPcartesiano(rs.getInt("pcartesiano") == 1);
                pergunta.setPococonsumo(rs.getInt("pococonsumo") == 1);
                pergunta.setFseptica(rs.getInt("fseptica") == 1);
                pergunta.setAnimais(rs.getInt("animais") == 1);
                pergunta.setCapacidade(Integer.parseInt(rs.getString("capacidade")));
                pergunta.setData(new java.util.Date(rs.getDate("datapergunta").getTime()));
                p.getListaPerguntas().add(pergunta);
                
                Animais animais = new Animais();
                animais.setEspecie(rs.getString("especie"));
                animais.setQuantidade(rs.getInt("quantidade"));
                if (rs.getDate("dataanimais") != null) {
                    animais.setData(new java.util.Date(rs.getDate("dataanimais").getTime()));
                    p.getListaAnimais().add(animais);
                }
                while (rs.next()) {
                    Animais anim = new Animais();
                    anim.setEspecie(rs.getString("especie"));
                    anim.setQuantidade(rs.getInt("quantidade"));
                    if (rs.getDate("dataanimais") != null) {
                        anim.setData(new java.util.Date(rs.getDate("dataanimais").getTime()));
                        p.getListaAnimais().add(anim);
                    }
                }
                return p;
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        return null;
    }

}
