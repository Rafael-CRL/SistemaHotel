package sistemahotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Reserva {

    private int id;
    private int idQuarto;
    private int idHospede;
    private LocalDate dataEntrada; 
    private LocalDate dataSaida;   
    private BigDecimal valorTotal; 
    private String status;


    public Reserva() {
    }

    public Reserva(int idQuarto, int idHospede, LocalDate dataEntrada, LocalDate dataSaida, String status) {
        this.idQuarto = idQuarto;
        this.idHospede = idHospede;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = BigDecimal.ZERO; // Valor total começa como zero
        this.status = status;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdQuarto() { return idQuarto; }
    public void setIdQuarto(int idQuarto) { this.idQuarto = idQuarto; }
    public int getIdHospede() { return idHospede; }
    public void setIdHospede(int idHospede) { this.idHospede = idHospede; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}