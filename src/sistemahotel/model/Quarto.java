/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.model;

/**
 *
 * @author klayt
 */
public class Quarto {
    private int id;
    private String numero;
    private String tipo;
    private String status;

    public Quarto() {}

    public Quarto(int id, String numero, String tipo, String status) {
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
    
    @Override
    public String toString() {
        // O JComboBox vai mostrar o número, tipo e status
        return "Quarto " + this.getNumero() + " (" + this.getTipo() + ")";
    }

    
}
