/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.view;

/**
 *
 * @author LabLa
 */
import javax.swing.*;

public class NumeroPessoasDialog extends JDialog {
    private JComboBox<Integer> comboPessoas;
    private JButton btnConfirmar;
    private int numeroSelecionado;
    
    public NumeroPessoasDialog(javax.swing.JFrame parent){
        super(parent, "Selecionar Número de Pessoas", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        
        comboPessoas = new JComboBox<>(new Integer[]{1, 2, 3, 4});
        btnConfirmar = new JButton("Confirmar");
        
        btnConfirmar.addActionListener(e -> {numeroSelecionado = (int) comboPessoas.getSelectedItem();dispose();});
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Número de pessoas:"));
        panel.add(comboPessoas);
        panel.add(btnConfirmar);
        
        add(panel);
    }
    
    public int getNumeroSelecionado(){
        return numeroSelecionado;
    }
}
