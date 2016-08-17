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
public class Adm extends Pessoa {

    private int idAdm;
    private int qtdModificacoes;

    public Adm() {
    }

    public Adm(int idPessoa) {
        super(idPessoa);
    }

    public Adm(int idAdm, int qtdModificacoes) {
        this.idAdm = idAdm;
        this.qtdModificacoes = qtdModificacoes;
    }

    public Adm(int idAdm, int qtdModificacoes, int idPessoa, String nomeCompleto, String nomeTratamento, String email, String login, String senha, Boolean ativo) {
        super(idPessoa, nomeCompleto, nomeTratamento, email, login, senha, ativo);
        this.idAdm = idAdm;
        this.qtdModificacoes = qtdModificacoes;
    }

    /**
     * @return the idAdm
     */
    public int getIdAdm() {
        return idAdm;
    }

    /**
     * @param idAdm the idAdm to set
     */
    public void setIdAdm(int idAdm) {
        this.idAdm = idAdm;
    }

    /**
     * @return the qtdModificacoes
     */
    public int getQtdModificacoes() {
        return qtdModificacoes;
    }

    /**
     * @param qtdModificacoes the qtdModificacoes to set
     */
    public void setQtdModificacoes(int qtdModificacoes) {
        this.qtdModificacoes = qtdModificacoes;
    }

}
