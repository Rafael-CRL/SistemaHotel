/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.model;

import java.math.BigDecimal;

/**
 *
 * @author klayt
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
    
    public Quarto(int id, String numero, String tipo, String status){
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
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
        if (precoDiaria.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo");
        }
        this.precoDiaria = precoDiaria;
    }
    
    @Override
    public String toString() {
        // O JComboBox vai mostrar o número, tipo e status
        return "Quarto " + numero + " (" + tipo + ") - Capacidade: " + capacidade + " - R$" + precoDiaria + "/noite";
    }
}
