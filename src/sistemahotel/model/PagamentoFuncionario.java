/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * @author Ray Carvalho
 */
public class PagamentoFuncionario extends Funcionario{
    private BigDecimal salario;
    private String cargo;
    private BigDecimal valorPago;
    private java.util.Date dataPagamento;
    private String descricao;
    private String formaDePagamento;

    public PagamentoFuncionario(){}
    
    public PagamentoFuncionario(BigDecimal salario, String cargo,
            BigDecimal valorPago, Date dataPagamento, String descricao, String formaDePagamento) {
        this.salario = salario;
        this.cargo = cargo;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.descricao = descricao;
        this.formaDePagamento = formaDePagamento;
    }
    
    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    @Override
    public String getCargo() {
        return cargo;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public BigDecimal getSalario() {
        return salario;
    }

    @Override
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
}
