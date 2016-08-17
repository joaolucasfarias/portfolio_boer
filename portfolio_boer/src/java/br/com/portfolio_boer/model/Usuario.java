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
public class Usuario extends Pessoa {

    private int idUsuario;
    private String nomeVisivel;

    public Usuario() {
    }

    public Usuario(int idPessoa) {
        super(idPessoa);
    }

    public Usuario(int idUsuario, String nomeVisivel) {
        this.idUsuario = idUsuario;
        this.nomeVisivel = nomeVisivel;
    }

    public Usuario(int idUsuario, String nomeVisivel, int idPessoa, String nomeCompleto, String nomeTratamento, String email, String login, String senha, Boolean ativo) {
        super(idPessoa, nomeCompleto, nomeTratamento, email, login, senha, ativo);
        this.idUsuario = idUsuario;
        this.nomeVisivel = nomeVisivel;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nomeVisivel
     */
    public String getNomeVisivel() {
        return nomeVisivel;
    }

    /**
     * @param nomeVisivel the nomeVisivel to set
     */
    public void setNomeVisivel(String nomeVisivel) {
        this.nomeVisivel = nomeVisivel;
    }

}
