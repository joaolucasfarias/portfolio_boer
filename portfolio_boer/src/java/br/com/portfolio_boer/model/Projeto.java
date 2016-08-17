/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.model;

import java.io.InputStream;

/**
 *
 * @author João Lucas Farias
 */
public class Projeto {

    private int idProjeto;
    private String nome;
    private InputStream imagem;
    private int fileItem;
    private String descricao;
    private Boolean relevante;
    private Portfolio portfolio;

    public Projeto() {
    }

    public Projeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Projeto(int idProjeto, String nome, InputStream imagem, int fileItem, String descricao, Boolean relevante, Portfolio portfolio) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.imagem = imagem;
        this.fileItem = fileItem;
        this.descricao = descricao;
        this.relevante = relevante;
        this.portfolio = portfolio;
    }

    /**
     * @return the idProjeto
     */
    public int getIdProjeto() {
        return idProjeto;
    }

    /**
     * @param idProjeto the idProjeto to set
     */
    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
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
     * @return the imagem
     */
    public InputStream getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(InputStream imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the fileItem
     */
    public int getFileItem() {
        return fileItem;
    }

    /**
     * @param fileItem the fileItem to set
     */
    public void setFileItem(int fileItem) {
        this.fileItem = fileItem;
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
     * @return the relevante
     */
    public Boolean getRelevante() {
        return relevante;
    }

    /**
     * @param relevante the relevante to set
     */
    public void setRelevante(Boolean relevante) {
        this.relevante = relevante;
    }

    /**
     * @return the portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * @param portfolio the portfolio to set
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

}
