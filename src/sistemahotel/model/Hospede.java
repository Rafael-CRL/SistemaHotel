/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.model;

/**
 *
 * @author klayt
 */
public class Hospede extends Pessoa {

    public Hospede() {}

    public Hospede(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
   
    @Override
    public String toString() {
        // O JComboBox vai mostrar o nome e o CPF do hóspede
        return this.getNome() + " (" + this.getCpf() + ")";
    }
    
    
}