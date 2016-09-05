/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

import br.com.portfolio_boer.model.PermitirModificar;
import br.com.portfolio_boer.model.Adm;
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
public class PermitirModificarDAOImpl implements GenericDAO {

    private Connection conn;

    public PermitirModificarDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        PermitirModificar perMod = (PermitirModificar) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO PermitirModificar (idPessoa, idPortfolio, modificacao, mudouVisualizacao, obs) "
                + "VALUES (?, ?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, perMod.getAdm().getIdPessoa());
            stmt.setInt(2, perMod.getPortfolio().getIdPortfolio());
            stmt.setBoolean(3, perMod.getModificacao());
            stmt.setBoolean(4, perMod.getMudouVisualizacao());
            stmt.setString(5, perMod.getObs());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar PermitirModificar!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT pm.*, p.*, p.ativo AS ativoPortfolio, a.*, pr.*, p2.idPessoa AS idPessoaUser, "
                + "p2.nomeCompleto AS nomeCompletoUser, p2.nomeTratamento AS nomeTratamentoUser, "
                + "p2.email AS emailUser, p2.login AS loginUser, p2.ativo AS ativoUser, u.idUsuario, "
                + "u.nomeVisivel "
                + "FROM PermitirModificar pm "
                + "INNER JOIN Pessoa p ON pm.idPessoa = p.idPessoa "
                + "INNER JOIN Adm a ON p.idPessoa = a.idPessoa "
                + "INNER JOIN Portfolio pr ON pm.idPortfolio = pr.idPortfolio "
                + "INNER JOIN Pessoa p2 ON pr.idPessoa = p2.idPessoa "
                + "INNER JOIN Usuario u ON p2.idPessoa = u.idPessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            PermitirModificar pm = null;
            Adm adm = null;
            Portfolio port = null;
            Usuario user = null;
            while (rs.next()) {
                user = new Usuario();
                port = new Portfolio();
                adm = new Adm();
                pm = new PermitirModificar();

                user.setIdPessoa(rs.getInt("idPessoaUser"));
                user.setNomeCompleto(rs.getString("nomeCompletoUser"));
                user.setNomeTratamento(rs.getString("nomeTratamentoUser"));
                user.setEmail(rs.getString("emailUser"));
                user.setLogin(rs.getString("loginUser"));
                user.setAtivo(rs.getBoolean("ativoUser"));
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));

                port.setIdPortfolio(rs.getInt("idPortfolio"));
                port.setUsuario(user);
                port.setNome(rs.getString("nome"));
                port.setTitulo(rs.getString("titulo"));
                port.setSubtitulo(rs.getString("subtitulo"));
                port.setDescricao(rs.getString("descricao"));
                port.setAprovado(rs.getBoolean("aprovado"));
                port.setAtivo(rs.getBoolean("ativoPortfolio"));

                adm.setIdPessoa(rs.getInt("idPessoa"));
                adm.setNomeCompleto(rs.getString("nomeCompleto"));
                adm.setNomeTratamento(rs.getString("nomeTratamento"));
                adm.setEmail(rs.getString("email"));
                adm.setLogin(rs.getString("login"));
                adm.setAtivo(rs.getBoolean("ativo"));
                adm.setIdAdm(rs.getInt("idAdm"));
                adm.setQtdModificacoes(rs.getInt("qtdModificacoes"));

                pm.setIdPermitirModificar(rs.getInt("idPermitidoModificar"));
                pm.setAdm(adm);
                pm.setPortfolio(port);
                pm.setData(rs.getDate("data"));
                pm.setModificacao(rs.getBoolean("modificacao"));
                pm.setMudouVisualizacao(rs.getBoolean("mudouVisualizacao"));
                pm.setObs(rs.getString("obs"));

                resultado.add(pm);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar PermitirModificar!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Boolean excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM PermitirModificar WHERE idPermitirModificar = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir PermitirModificar!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());;
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PermitirModificar pm = null;

        String sql = "SELECT pm.*, p.*, p.ativo AS ativoPortfolio, a.*, pr.*, p2.idPessoa AS idPessoaUser, "
                + "p2.nomeCompleto AS nomeCompletoUser, p2.nomeTratamento AS nomeTratamentoUser, "
                + "p2.email AS emailUser, p2.login AS loginUser, p2.ativo AS ativoUser, u.idUsuario, "
                + "u.nomeVisivel "
                + "FROM PermitirModificar pm "
                + "INNER JOIN Pessoa p ON pm.idPessoa = p.idPessoa "
                + "INNER JOIN Adm a ON p.idPessoa = a.idPessoa "
                + "INNER JOIN Portfolio pr ON pm.idPortfolio = pr.idPortfolio "
                + "INNER JOIN Pessoa p2 ON pr.idPessoa = p2.idPessoa "
                + "INNER JOIN Usuario u ON p2.idPessoa = u.idPessoa"
                + "WHERE idPermitirModificar = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            Adm adm = null;
            Portfolio port = null;
            Usuario user = null;
            if (rs.next()) {
                user = new Usuario();
                port = new Portfolio();
                adm = new Adm();
                pm = new PermitirModificar();

                user.setIdPessoa(rs.getInt("idPessoaUser"));
                user.setNomeCompleto(rs.getString("nomeCompletoUser"));
                user.setNomeTratamento(rs.getString("nomeTratamentoUser"));
                user.setEmail(rs.getString("emailUser"));
                user.setLogin(rs.getString("loginUser"));
                user.setAtivo(rs.getBoolean("ativoUser"));
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));

                port.setIdPortfolio(rs.getInt("idPortfolio"));
                port.setUsuario(user);
                port.setNome(rs.getString("nome"));
                port.setTitulo(rs.getString("titulo"));
                port.setSubtitulo(rs.getString("subtitulo"));
                port.setDescricao(rs.getString("descricao"));
                port.setAprovado(rs.getBoolean("aprovado"));
                port.setAtivo(rs.getBoolean("ativoPortfolio"));

                adm.setIdPessoa(rs.getInt("idPessoa"));
                adm.setNomeCompleto(rs.getString("nomeCompleto"));
                adm.setNomeTratamento(rs.getString("nomeTratamento"));
                adm.setEmail(rs.getString("email"));
                adm.setLogin(rs.getString("login"));
                adm.setAtivo(rs.getBoolean("ativo"));
                adm.setIdAdm(rs.getInt("idAdm"));
                adm.setQtdModificacoes(rs.getInt("qtdModificacoes"));

                pm.setIdPermitirModificar(rs.getInt("idPermitidoModificar"));
                pm.setAdm(adm);
                pm.setPortfolio(port);
                pm.setData(rs.getDate("data"));
                pm.setModificacao(rs.getBoolean("modificacao"));
                pm.setMudouVisualizacao(rs.getBoolean("mudouVisualizacao"));
                pm.setObs(rs.getString("obs"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar PermitirModificar!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pm;
    }

    @Override
    public Boolean alterar(Object object) {
        PermitirModificar pm=(PermitirModificar)object;
        PreparedStatement stmt = null;

        String sql = "UPDATE PermitirModificar SET idPessoa = ?, idPortfolio = ?, data = CURRENT_DATE, modificacao = ?, mudouVisualizacao = ?, obs = ? "
                + "WHERE idPermitirModificar = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pm.getAdm().getIdPessoa());
            stmt.setInt(2, pm.getPortfolio().getIdPortfolio());
            stmt.setBoolean(3, pm.getModificacao());
            stmt.setBoolean(4, pm.getMudouVisualizacao());
            stmt.setString(5, pm.getObs());
            stmt.setInt(6, pm.getIdPermitirModificar());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar PermitirModificar!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe PermitirModificarDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
