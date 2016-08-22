/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

import br.com.portfolio_boer.model.Pessoa;
import br.com.portfolio_boer.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author João Lucas Farias
 */
public class PessoaDAOImpl {

    private Connection conn;

    public PessoaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe PessoaDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    public Integer cadastrar(Pessoa pessoa) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idPessoa = null;

        String sql = "INSERT INTO Pessoa (nomeCompleto, nomeTratamento, email, login, senha) "
                + "VALUES (?, ?, ?, ?, MD5(?)) RETURNING idPessoa;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getNomeTratamento());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            rs = stmt.executeQuery();
            if (rs.next()) {
                idPessoa = rs.getInt("idPessoa");
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Pessoa!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PessoaDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }

    public Boolean alterar(Pessoa pessoa) {
        PreparedStatement stmt = null;

        String sql = "UPDATE Pessoa SET nomeCompleto = ?, nomeTratamento = ?, email = ?, login = ?, senha = MD5(?), ativo = ? WHERE idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getNomeTratamento());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setBoolean(6, pessoa.getAtivo());
            stmt.setInt(7, pessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar Pessoa!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PessoaDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}
