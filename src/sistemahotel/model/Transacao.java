/*
 * Classe de modelo que representa uma transação financeira no sistema.
 */
package sistemahotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {

    private int id;
    private int idReserva; // Corrigido para o padrão camelCase
    private BigDecimal valor; // Corrigido para BigDecimal, ideal para valores monetários
    private String tipo;
    private LocalDateTime dataTransacao; // Corrigido para a API moderna de Data/Hora do Java
    private String status;

    /**
     * Construtor padrão.
     */
    public Transacao() {
    }

    // Getters e Setters para todos os atributos

    public int getId() {
        return id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}