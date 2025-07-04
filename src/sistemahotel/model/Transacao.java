// Em sistemahotel.model.Transacao.java
package sistemahotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private String tipo;
    private String categoria;
    private BigDecimal valor;
    private String descricao;
    private LocalDateTime dataTransacao;
    private String formaPagamento;
    
    // Composição: A transação agora tem os objetos, não os IDs.
    private Reserva reserva;
    private Funcionario funcionario; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
public String getNomeRelacionado() {
    if (funcionario != null && funcionario.getNome() != null) {
        return funcionario.getNome();
    }
    if (reserva != null 
        && reserva.getHospedes() != null 
        && !reserva.getHospedes().isEmpty()
        && reserva.getHospedes().get(0).getNome() != null) {
        return reserva.getHospedes().get(0).getNome();
    }
    return "-";
}
    
    
}