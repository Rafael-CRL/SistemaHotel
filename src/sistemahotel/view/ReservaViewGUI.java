/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.view;

/**
 *
 * @author LabLa
 */

import sistemahotel.controller.ReservaController;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class ReservaViewGUI extends JFrame {
    private final ReservaController controller;
    private final JComboBox<Hospede> cbHospedes;
    private final JComboBox<Quarto> cbQuartos;
    private final JTextField txtDataEntrada;
    private final JTextField txtDataSaida;
    private final JButton btnCriarReserva;
    private final MenuViewGUI menuViewGUI;

    public ReservaViewGUI(ReservaController controller, List<Hospede> hospedes, List<Quarto> quartos, MenuViewGUI menuViewGUI) {
        this.controller = controller;
        this.menuViewGUI = menuViewGUI;
        setTitle("Criar Reserva");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Hóspede:"));
        cbHospedes = new JComboBox<>(hospedes.toArray(new Hospede[0]));
        panel.add(cbHospedes);

        panel.add(new JLabel("Quarto:"));
        cbQuartos = new JComboBox<>(quartos.toArray(new Quarto[0]));
        panel.add(cbQuartos);

        panel.add(new JLabel("Data de Entrada (dd/MM/yyyy):"));
        txtDataEntrada = new JTextField();
        panel.add(txtDataEntrada);

        panel.add(new JLabel("Data de Saída (dd/MM/yyyy):"));
        txtDataSaida = new JTextField();
        panel.add(txtDataSaida);

        btnCriarReserva = new JButton("Criar Reserva");
        btnCriarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarReserva();
            }
        });
        panel.add(btnCriarReserva);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            if (menuViewGUI != null) {
                menuViewGUI.setVisible(true);
            }
        });
        panel.add(btnVoltar);

        add(panel);
    }

    private void criarReserva() {
        try {
            Hospede hospede = (Hospede) cbHospedes.getSelectedItem();
            Quarto quarto = (Quarto) cbQuartos.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataEntrada = sdf.parse(txtDataEntrada.getText());
            Date dataSaida = sdf.parse(txtDataSaida.getText());

            controller.criarReserva(quarto.getId(), hospede.getId(), dataEntrada, dataSaida, 0.0, "Pendente");
            JOptionPane.showMessageDialog(this, "Reserva criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            if (menuViewGUI != null) {
                menuViewGUI.setVisible(true);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro no formato da data. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
