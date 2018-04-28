/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class Filme {
    
    private int idFilme = 1;
    private String titulo;
    private Date dataLancamento;
    private int nota;
    private String descricao;
    private int quantidade;
    private Double preco;
    private static int cont = 1;

    public Filme() {
        this.idFilme = cont++;
    }

    public Filme(String titulo, Date dataLancamento, int nota, String descricao, int quantidade) {
        this.idFilme = cont++;
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.nota = nota;
        this.descricao = descricao;
        this.quantidade = quantidade;   
        this.preco = 2.0;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    
    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void decrementaQuantidade(){
        this.quantidade = this.quantidade - 1;
    }
    
}
