package sistemahotel.model;

import java.math.BigDecimal;

/**
 * Representa um quarto no hotel, encapsulando seus dados e comportamentos.
 */
public class Quarto {
    private int id;
    private String numero;
    private String tipo;
    private String status;
    private int capacidade;
    private BigDecimal precoDiaria;

    public Quarto() {}

    public Quarto(int id, String numero, String tipo, String status, int capacidade, BigDecimal precoDiaria) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
    }


    public void ocupar() {
        if (!"Disponível".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Não é possível ocupar um quarto que não está disponível. Status atual: " + this.status);
        }
        this.status = "Ocupado";
    }

    /**
     * Altera o status do quarto para "Disponível" após uma reserva ser finalizada.
     */
    public void liberar() {
        this.status = "Disponível";
    }
    
    /**
     * Altera o status do quarto para "Manutenção", impedindo novas reservas.
     */
    public void bloquearParaManutencao() {
        this.status = "Manutenção";
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }
    
    // O status é controlado principalmente pelos métodos de comportamento.
    // Este setter pode ser mantido para uso interno do DAO, se necessário.
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getCapacidade(){
        return capacidade;
    }
    
    public void setCapacidade(int capacidade){
        if (capacidade < 1 || capacidade > 4) {
            throw new IllegalArgumentException("Capacidade deve ser entre 1 e 4");
        }
        this.capacidade = capacidade;
    }
    
    public BigDecimal getPrecoDiaria(){
        return precoDiaria;
    }
    
    public void setPrecoDiaria(BigDecimal precoDiaria){
        if (precoDiaria == null || precoDiaria.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço da diária deve ser um valor positivo.");
        }
        this.precoDiaria = precoDiaria;
    }
    
    @Override
    public String toString() {
        return "Quarto " + numero + " (" + tipo + ")";
    }
}