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
public class Portfolio {

    private int idPortfolio;
    private String nome;
    private String titulo;
    private String subtitulo;
    private String descricao;
    private Boolean aprovado;
    private Boolean ativo;
    private Usuario usuario;

    public Portfolio() {
    }

    public Portfolio(int idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public Portfolio(int idPortfolio, String nome, String titulo, String subtitulo, String descricao, Boolean aprovado, Boolean ativo, Usuario usuario) {
        this.idPortfolio = idPortfolio;
        this.nome = nome;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descricao = descricao;
        this.aprovado = aprovado;
        this.ativo = ativo;
        this.usuario = usuario;
    }

    /**
     * @return the idPortfolio
     */
    public int getIdPortfolio() {
        return idPortfolio;
    }

    /**
     * @param idPortfolio the idPortfolio to set
     */
    public void setIdPortfolio(int idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the subtitulo
     */
    public String getSubtitulo() {
        return subtitulo;
    }

    /**
     * @param subtitulo the subtitulo to set
     */
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the aprovado
     */
    public Boolean getAprovado() {
        return aprovado;
    }

    /**
     * @param aprovado the aprovado to set
     */
    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
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

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
