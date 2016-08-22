/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

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
public class UsuarioDAOImpl implements GenericDAO {

    private Connection conn;

    public UsuarioDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Usuario user = (Usuario) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO user (idPessoa, nomeVisivel) VALUES (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAOImpl().cadastrar(user));
            stmt.setString(2, user.getNomeVisivel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Usuário!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            System.out.println("Problemas ao tentar cadastrar e gerar o id da pessoa\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Usuario u "
                + "INNER JOIN Pessoa p ON p.idPessoa = u.idPessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Usuario user = null;
            while (rs.next()) {
                user = new Usuario();

                user.setIdPessoa(rs.getInt("idPessoa"));
                user.setNomeCompleto(rs.getString("nomeCompleto"));
                user.setNomeTratamento(rs.getString("nomeTratamento"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setAtivo(rs.getBoolean("ativo"));

                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));

                resultado.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Usuário!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Boolean excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Usuario WHERE idPessoa = ?;";
        sql += "COMMIT;";
        sql += "DELETE FROM Pessoa WHERE idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Usuário!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());;
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = null;

        String sql = "SELECT * FROM Adm a "
                + "INNER JOIN Pessoa p ON p.idPessoa = a.idPessoa "
                + "WHERE p.idPessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Usuario();

                user.setIdPessoa(rs.getInt("idPessoa"));
                user.setNomeCompleto(rs.getString("nomeCompleto"));
                user.setNomeTratamento(rs.getString("nomeTratamento"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setAtivo(rs.getBoolean("ativo"));

                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNomeVisivel(rs.getString("nomeVisivel"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Usuário!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Boolean alterar(Object object) {
        Usuario user = (Usuario) object;
        PreparedStatement stmt = null;

        try {
            if (!new PessoaDAOImpl().alterar(user)) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Pessoa!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        String sql = "UPDATE Usuario SET nomeVisivel = ? WHERE idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getNomeVisivel());
            stmt.setInt(2, user.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar Usuario!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe UsuarioDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
