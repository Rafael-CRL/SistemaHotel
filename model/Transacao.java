/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.model;

import java.util.Date;

/**
 *
 * @author Ray Carvalho
 */

public class Transacao {
   private int id;
   private int id_reservas;
   private double valor;
   private String tipo;
   private  Date dataTransacao;

    public Transacao(int id, int id_reservas, double valor, String tipo, Date dataTransacao) {
        this.id = id;
        this.id_reservas = id_reservas;
        this.valor = valor;
        this.tipo = tipo;
        this.dataTransacao = dataTransacao;
    }
   
   public Transacao(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reservas() {
        return id_reservas;
    }

    public void setId_reservas(int id_reservas) {
        this.id_reservas = id_reservas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
   
   
   
}
