/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.model;

/**
 *
 * @author João Lucas Farias
 */
public class Pessoa {

    private int idPessoa;
    private String nomeCompleto;
    private String nomeTratamento;
    private String email;
    private String login;
    private String senha;
    private Boolean ativo;

    public Pessoa() {
    }

    public Pessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa(int idPessoa, String nomeCompleto, String nomeTratamento, String email, String login, String senha, Boolean ativo) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.nomeTratamento = nomeTratamento;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    /**
     * @return the idPessoa
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * @param nomeCompleto the nomeCompleto to set
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    /**
     * @return the nomeTratamento
     */
    public String getNomeTratamento() {
        return nomeTratamento;
    }

    /**
     * @param nomeTratamento the nomeTratamento to set
     */
    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
