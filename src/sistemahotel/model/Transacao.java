/*
 * Classe de modelo que representa uma transação financeira no sistema.
 */
package sistemahotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Ray Carvalho
 */

public class Transacao extends PagamentoFuncionario{

    private int id;
    private int idReserva; // Corrigido para o padrão camelCase
    private BigDecimal valor; // Corrigido para BigDecimal, ideal para valores monetários
    private String tipo;
    private LocalDateTime dataTransacao; // Corrigido para a API moderna de Data/Hora do Java
    private String status;
    private String formaPagamento;
    private String descricao;
    private String nomeRelacionado;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeRelacionado() {
        return nomeRelacionado;
    }

    public void setNomeRelacionado(String nomeRelacionado) {
        this.nomeRelacionado = nomeRelacionado;
    }

    public Transacao() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}