package sistemahotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma reserva com seus dados e regras de negócio.
 */
public class Reserva {

    private int id;
    private Quarto quarto;
    private List<Hospede> hospedes;
    private Funcionario funcionarioResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valorTotal;
    private String status;

    public Reserva() {
        this.hospedes = new ArrayList<>();
    }

    /**
     * Calcula o valor total da estadia com base no número de diárias.
     */
    public BigDecimal calcularValorTotal() {
        if (this.quarto == null || this.quarto.getPrecoDiaria() == null || this.dataEntrada == null || this.dataSaida == null) {
            throw new IllegalStateException("Dados insuficientes para calcular o valor total da reserva.");
        }

        long diarias = ChronoUnit.DAYS.between(this.dataEntrada, this.dataSaida);
        if (diarias == 0) diarias = 1;

        return this.quarto.getPrecoDiaria().multiply(new BigDecimal(diarias));
    }

    /**
     * Adiciona um hóspede à reserva, respeitando a capacidade do quarto.
     */
    public void adicionarHospede(Hospede hospede) {
        if (this.hospedes.size() >= this.quarto.getCapacidade()) {
            throw new IllegalStateException("Capacidade máxima do quarto atingida.");
        }
        this.hospedes.add(hospede);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Hospede> hospedes) {
        this.hospedes = hospedes;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
