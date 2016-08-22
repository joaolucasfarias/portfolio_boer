/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.DAO;

import br.com.portfolio_boer.model.Adm;
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
public class AdmDAOImpl implements GenericDAO {

    private Connection conn;

    public AdmDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Connectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar ao Banco de Dados na classe AdmDAOImpl!\nErro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Adm adm = (Adm) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Adm (idPessoa) VALUES (?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAOImpl().cadastrar(adm));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Administrador!\nErro: " + ex.getMessage());
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
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe AdmDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Adm a "
                + "INNER JOIN Pessoa p ON p.idPessoa = a.idPessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Adm adm = null;
            while (rs.next()) {
                adm = new Adm();

                adm.setIdPessoa(rs.getInt("idPessoa"));
                adm.setNomeCompleto(rs.getString("nomeCompleto"));
                adm.setNomeTratamento(rs.getString("nomeTratamento"));
                adm.setEmail(rs.getString("email"));
                adm.setLogin(rs.getString("login"));
                adm.setAtivo(rs.getBoolean("ativo"));

                adm.setIdAdm(rs.getInt("idAdm"));
                adm.setQtdModificacoes(rs.getInt("qtdModificacoes"));

                resultado.add(adm);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Adm!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe AdmDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Boolean excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Adm WHERE idPessoa = ?;";
        sql += "COMMIT;";
        sql += "DELETE FROM Pessoa WHERE idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Adm!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe AdmDAOImpl!\nErro: " + ex.getMessage());;
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Adm adm = null;

        String sql = "SELECT * FROM Adm a "
                + "INNER JOIN Pessoa p ON p.idPessoa = a.idPessoa "
                + "WHERE p.idPessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                adm = new Adm();

                adm.setIdPessoa(rs.getInt("idPessoa"));
                adm.setNomeCompleto(rs.getString("nomeCompleto"));
                adm.setNomeTratamento(rs.getString("nomeTratamento"));
                adm.setEmail(rs.getString("email"));
                adm.setLogin(rs.getString("login"));
                adm.setAtivo(rs.getBoolean("ativo"));

                adm.setIdAdm(rs.getInt("idAdm"));
                adm.setQtdModificacoes(rs.getInt("qtdModificacoes"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Adm!\nErro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar os parâmetros de conexão na classe AdmDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return adm;
    }

    @Override
    public Boolean alterar(Object object) {
        Adm adm = (Adm) object;
        PreparedStatement stmt = null;

        try {
            if (!new PessoaDAOImpl().alterar(adm)) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Pessoa!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        String sql = "UPDATE Adm SET qtdModificacoes = ? WHERE idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, adm.getQtdModificacoes());
            stmt.setInt(2, adm.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao alterar Adm!\nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão na classe AdmDAOImpl!\nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
