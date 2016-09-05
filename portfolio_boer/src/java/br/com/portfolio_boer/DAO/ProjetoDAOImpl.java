/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

import br.com.portfolio_boer.model.Portfolio;
import br.com.portfolio_boer.model.Projeto;
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
public class ProjetoDAOImpl implements GenericDAO {

    private Connection conn;

    public ProjetoDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Projeto proj = (Projeto) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Projeto (idPortfolio, nome, imagem, descricao, relevante) VALUES (?, ?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, proj.getPortfolio().getIdPortfolio());
            stmt.setString(2, proj.getNome());
            stmt.setBinaryStream(3, proj.getImagem(), proj.getFileItem());
            stmt.setString(4, proj.getDescricao());
            stmt.setBoolean(5, proj.getRelevante());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Projeto!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT *, proj.nome AS nomeProj FROM Projeto proj "
                + "INNER JOIN Portfolio pr ON proj.idPortfolio = pr.idPortfolio "
                + "INNER JOIN Pessoa p ON pr.idPessoa = p.idPessoa "
                + "INNER JOIN Usuario u ON p.idPessoa = u.idPessoa";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Usuario user = null;
            Portfolio port = null;
            Projeto proj = null;
            while (rs.next()) {
                user = new Usuario();
                port = new Portfolio();
                proj = new Projeto();

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

                proj.setIdProjeto(rs.getInt("idProjeto"));
                proj.setPortfolio(port);
                proj.setNome(rs.getString("nomeProj"));
                proj.setDescricao(rs.getString("descricao"));
                proj.setRelevante(rs.getBoolean("relevante"));

                resultado.add(proj);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Projeto!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Boolean excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Projeto WHERE idProjeto = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Projeto!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());;
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Projeto proj = null;

        String sql = "SELECT *, proj.nome AS nomeProj FROM Projeto proj "
                + "INNER JOIN Portfolio pr ON proj.idPortfolio = pr.idPortfolio "
                + "INNER JOIN Pessoa p ON pr.idPessoa = p.idPessoa "
                + "INNER JOIN Usuario u ON p.idPessoa = u.idPessoa "
                + "WHERE idProjeto = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            Usuario user = null;
            Portfolio port = null;
            if (rs.next()) {
                user = new Usuario();
                port = new Portfolio();
                proj = new Projeto();

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

                proj.setIdProjeto(rs.getInt("idProjeto"));
                proj.setPortfolio(port);
                proj.setNome(rs.getString("nomeProj"));
                proj.setDescricao(rs.getString("descricao"));
                proj.setRelevante(rs.getBoolean("relevante"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Projeto!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return proj;
    }

    @Override
    public Boolean alterar(Object object) {
        Projeto proj = (Projeto) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE Projeto idPortfolio = ?, nome = ?, imagem = ?, descricao = ?, relevante = ? WHERE idProjeto = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, proj.getPortfolio().getIdPortfolio());
            stmt.setString(2, proj.getNome());
            stmt.setBinaryStream(3, proj.getImagem(), proj.getFileItem());
            stmt.setString(4, proj.getDescricao());
            stmt.setBoolean(5, proj.getRelevante());
            stmt.setInt(7, proj.getIdProjeto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar Projeto!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe ProjetoDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
