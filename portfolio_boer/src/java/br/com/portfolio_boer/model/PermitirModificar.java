/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.portfolio_boer.model;

import java.util.Date;

/**
 *
 * @author João Lucas Farias
 */
public class PermitirModificar {

    private int idPermitirModificar;
    private Date data;
    private Boolean modificacao;
    private Boolean mudouVisualizacao;
    private String obs;
    private Adm adm;
    private Portfolio portfolio;

    public PermitirModificar() {
    }

    public PermitirModificar(int idPermitirModificar) {
        this.idPermitirModificar = idPermitirModificar;
    }

    public PermitirModificar(Adm adm, Portfolio portfolio) {
        this.adm = adm;
        this.portfolio = portfolio;
    }

    public PermitirModificar(int idPermitirModificar, Date data, Boolean modificacao, Boolean mudouVisualizacao, String obs, Adm adm, Portfolio portfolio) {
        this.idPermitirModificar = idPermitirModificar;
        this.data = data;
        this.modificacao = modificacao;
        this.mudouVisualizacao = mudouVisualizacao;
        this.obs = obs;
        this.adm = adm;
        this.portfolio = portfolio;
    }

    /**
     * @return the idPermitirModificar
     */
    public int getIdPermitirModificar() {
        return idPermitirModificar;
    }

    /**
     * @param idPermitirModificar the idPermitirModificar to set
     */
    public void setIdPermitirModificar(int idPermitirModificar) {
        this.idPermitirModificar = idPermitirModificar;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the modificacao
     */
    public Boolean getModificacao() {
        return modificacao;
    }

    /**
     * @param modificacao the modificacao to set
     */
    public void setModificacao(Boolean modificacao) {
        this.modificacao = modificacao;
    }

    /**
     * @return the mudouVisualizacao
     */
    public Boolean getMudouVisualizacao() {
        return mudouVisualizacao;
    }

    /**
     * @param mudouVisualizacao the mudouVisualizacao to set
     */
    public void setMudouVisualizacao(Boolean mudouVisualizacao) {
        this.mudouVisualizacao = mudouVisualizacao;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the adm
     */
    public Adm getAdm() {
        return adm;
    }

    /**
     * @param adm the adm to set
     */
    public void setAdm(Adm adm) {
        this.adm = adm;
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
