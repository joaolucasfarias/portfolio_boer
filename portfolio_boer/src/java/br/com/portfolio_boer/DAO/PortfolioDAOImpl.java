/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

import br.com.portfolio_boer.model.Pessoa;
import br.com.portfolio_boer.model.Portfolio;
import br.com.portfolio_boer.model.Usuario;
import br.com.portfolio_boer.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Lucas Farias
 */
public class PortfolioDAOImpl implements GenericDAO {

    private Connection conn;

    public PortfolioDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Portfolio port = (Portfolio) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Portfolio (idPessoa, nome, titulo, subtitulo, descricao, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, port.getUsuario().getIdPessoa());
            stmt.setString(2, port.getNome());
            stmt.setString(3, port.getTitulo());
            stmt.setString(4, port.getSubtitulo());
            stmt.setString(5, port.getDescricao());
            stmt.setBoolean(6, port.getAtivo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Portfolio!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Portfolio pr "
                + "INNER JOIN Pessoa p ON pr.idPessoa = p.idPessoa "
                + "INNER JOIN Usuario u ON p.idPessoa = u.idPessoa";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Portfolio port = null;
            Usuario user = null;
            while (rs.next()) {
                port = new Portfolio();
                user = new Usuario();

                user.setIdPessoa(rs.getInt("idPessoa"));
                user.setNomeCompleto(rs.getString("nomeCompleto"));
                user.setNomeTratamento(rs.getString("nomeTratamento"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setAtivo(rs.getBoolean("ativo"));
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));

                port.setIdPortfolio(rs.getInt("idPortfolio"));
                port.setUsuario(user);
                port.setNome(rs.getString("nome"));
                port.setTitulo(rs.getString("titulo"));
                port.setSubtitulo(rs.getString("subtitulo"));
                port.setDescricao(rs.getString("descricao"));
                port.setAprovado(rs.getBoolean("aprovado"));
                port.setAtivo(rs.getBoolean("ativo"));

                resultado.add(port);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Portfolio!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Boolean excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Portfolio WHERE idPortfolio = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Portfolio!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());;
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Portfolio port = null;
        Usuario user = null;

        String sql = "SELECT * FROM Portfolio pr "
                + "INNER JOIN Pessoa p ON pr.idPessoa = p.idPessoa "
                + "INNER JOIN Usuario u ON p.idPessoa = u.idPessoa "
                + "WHERE pr.idPortfolio = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                port = new Portfolio();
                user = new Usuario();

                user.setIdPessoa(rs.getInt("idPessoa"));
                user.setNomeCompleto(rs.getString("nomeCompleto"));
                user.setNomeTratamento(rs.getString("nomeTratamento"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setAtivo(rs.getBoolean("ativo"));
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));

                port.setIdPortfolio(rs.getInt("idPortfolio"));
                port.setUsuario(user);
                port.setNome(rs.getString("nome"));
                port.setTitulo(rs.getString("titulo"));
                port.setSubtitulo(rs.getString("subtitulo"));
                port.setDescricao(rs.getString("descricao"));
                port.setAprovado(rs.getBoolean("aprovado"));
                port.setAtivo(rs.getBoolean("ativo"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Portfolio!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return port;
    }

    @Override
    public Boolean alterar(Object object) {
        Portfolio port = (Portfolio) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE Portfolio SET nome = ?, titulo = ?, subtitulo = ?, descricao = ?, aprovado = ?, ativo = ? WHERE idPortfolio = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, port.getNome());
            stmt.setString(2, port.getTitulo());
            stmt.setString(3, port.getSubtitulo());
            stmt.setString(4, port.getDescricao());
            stmt.setBoolean(5, port.getAprovado());
            stmt.setBoolean(6, port.getAtivo());
            stmt.setInt(7, port.getUsuario().getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar Portfolio!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PortfolioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
