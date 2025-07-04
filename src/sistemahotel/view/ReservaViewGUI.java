package sistemahotel.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemahotel.controller.CadastroController;
import sistemahotel.controller.FinanceiroController;
import sistemahotel.controller.HospedeController;
import sistemahotel.controller.ReservaController;
import sistemahotel.dao.HospedeDAO;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.model.Funcionario;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;
import sistemahotel.model.Reserva;
import sistemahotel.view.MenuViewGUI;

public class ReservaViewGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReservaViewGUI.class.getName());
    private ReservaController reservaController;
    private Funcionario usuarioLogado;
    private MenuViewGUI menuPai;

    public ReservaViewGUI(MenuViewGUI menuPai, Funcionario usuarioLogado) {
        initComponents();
        this.setLocationRelativeTo(menuPai);

        this.reservaController = new ReservaController();
        this.usuarioLogado = usuarioLogado;
        this.menuPai = menuPai;

        // Chama o método para popular os ComboBoxes assim que a tela abre.
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        try {
            cmbHospedes.removeAllItems();
            cmbQuartos.removeAllItems();

            //responsável por listar entidades.
            CadastroController cadastroController = new CadastroController();

            // Usa o CadastroController para buscar a lista de hóspedes.
            List<Hospede> hospedes = cadastroController.listarHospedes();
            for (Hospede h : hospedes) {
                cmbHospedes.addItem(h);
            }

            // Usa o CadastroController para buscar a lista de quartos disponíveis.
            List<Quarto> quartos = cadastroController.listarQuartosDisponiveis();
            for (Quarto q : quartos) {
                cmbQuartos.addItem(q);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados iniciais: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JTextField();
        txtDataSaida = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        BtnCriarReserva = new javax.swing.JButton();
        BtnCheckIN = new javax.swing.JButton();
        BtnCheckOut = new javax.swing.JButton();
        BtnVoltar = new javax.swing.JButton();
        cmbQuartos = new javax.swing.JComboBox<>();
        cmbHospedes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtCPF = new javax.swing.JTextField();
        BtnBuscarCPF = new javax.swing.JButton();

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Data de Entrada:");

        jLabel4.setText("Data de Saida");

        txtDataEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataEntradaActionPerformed(evt);
            }
        });

        txtDataSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataSaidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtDataEntrada)
                    .addComponent(txtDataSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        BtnCriarReserva.setText("Criar Reserva");
        BtnCriarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarReservaActionPerformed(evt);
            }
        });

        BtnCheckIN.setText("Fazer Check-in");
        BtnCheckIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCheckINActionPerformed(evt);
            }
        });

        BtnCheckOut.setText("Fazer Check-out");
        BtnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCheckOutActionPerformed(evt);
            }
        });

        BtnVoltar.setText("Voltar");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnCriarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(BtnCheckIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriarReserva)
                    .addComponent(BtnCheckIN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnVoltar)
                    .addComponent(BtnCheckOut))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel5.setText("Hospede");

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel6.setText("Quarto");

        jLabel1.setText("    Buscar hóspede por CPF");

        TxtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCPFActionPerformed(evt);
            }
        });

        BtnBuscarCPF.setText("Buscar CPF");
        BtnBuscarCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCPFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cmbHospedes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(BtnBuscarCPF))
                            .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbHospedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBuscarCPF)))
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        this.dispose();
        this.menuPai.setVisible(true);
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnCriarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarReservaActionPerformed
        try {
            // Pega os OBJETOS completos selecionados na tela.
            Quarto quartoSelecionado = (Quarto) cmbQuartos.getSelectedItem();

            // Para o MVP, continuamos selecionando um hóspede principal.
            Hospede hospedePrincipal = (Hospede) cmbHospedes.getSelectedItem();

            if (hospedePrincipal == null || quartoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede e um quarto válidos.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataEntrada = LocalDate.parse(txtDataEntrada.getText(), formatter);
            LocalDate dataSaida = LocalDate.parse(txtDataSaida.getText(), formatter);

            // Monta a lista de hóspedes para a reserva.
            List<Hospede> hospedesDaReserva = new ArrayList<>();
            hospedesDaReserva.add(hospedePrincipal);

            // Chama o controller refatorado, passando os objetos completos.
            boolean sucesso = reservaController.criarReserva(quartoSelecionado, hospedesDaReserva, this.usuarioLogado, dataEntrada, dataSaida);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Reserva criada com sucesso!");
                carregarDadosIniciais(); // Atualiza a lista de quartos disponíveis.
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao criar reserva. Verifique os dados ou a disponibilidade.");
            }

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnCriarReservaActionPerformed

    private void txtDataEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEntradaActionPerformed

    private void txtDataSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataSaidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataSaidaActionPerformed

    private void BtnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCheckOutActionPerformed
        String idReservaStr = JOptionPane.showInputDialog(this, "Digite o ID da Reserva para fazer o Check-out:");

        if (idReservaStr == null || idReservaStr.trim().isEmpty()) {
            return;
        }

        try {
            int idReserva = Integer.parseInt(idReservaStr);
            BigDecimal valorTotal = reservaController.realizarCheckOut(idReserva);

            if (valorTotal != null) {
                // Cria um JComboBox com as opções de pagamento.
                String[] opcoesPagamento = {"Cartão de Crédito", "Cartão de Débito", "PIX", "Dinheiro"};
                javax.swing.JComboBox<String> comboBoxPagamento = new javax.swing.JComboBox<>(opcoesPagamento);

                // Exibe um JOptionPane que contém o JComboBox.
                int result = JOptionPane.showConfirmDialog(this, comboBoxPagamento, "Selecione a Forma de Pagamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String formaPagamento = (String) comboBoxPagamento.getSelectedItem();

                    JOptionPane.showMessageDialog(this, "Check-out realizado!\nValor Total a Pagar: R$ " + valorTotal);

                    // Integração Financeira
                    Reserva reservaFinalizada = reservaController.buscarReservaPorId(idReserva);
                    if (reservaFinalizada != null) {
                        FinanceiroController finController = new FinanceiroController();
                        // Chama o método atualizado, passando a forma de pagamento escolhida.
                        finController.registrarPagamentoReserva(reservaFinalizada, this.usuarioLogado, formaPagamento);
                    }

                    carregarDadosIniciais();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível fazer o check-out. Verifique se a reserva está ativa.", "Falha", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao fazer check-out: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnCheckOutActionPerformed

    private void BtnCheckINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCheckINActionPerformed
        String idReservaStr = JOptionPane.showInputDialog(this, "Digite o ID da Reserva para fazer o Check-in:", "Realizar Check-in", JOptionPane.PLAIN_MESSAGE);

        if (idReservaStr == null || idReservaStr.trim().isEmpty()) {
            return;
        }

        try {
            int idReserva = Integer.parseInt(idReservaStr);
            boolean sucesso = reservaController.realizarCheckIn(idReserva);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Check-in realizado com sucesso para a reserva ID: " + idReserva, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível fazer o check-in.\nVerifique se a reserva existe e está com o status 'Pendente'.", "Falha no Check-in", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao fazer check-in: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnCheckINActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void TxtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCPFActionPerformed

    private void BtnBuscarCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCPFActionPerformed
        // 1. Obter o CPF digitado (removendo formatação se necessário):
        String cpfDigitado = TxtCPF.getText().replaceAll("[^0-9]", "");

        // 2. Validar o CPF:
        if (cpfDigitado.length() != 11) {
            JOptionPane.showMessageDialog(this,
                    "CPF inválido! Deve conter 11 dígitos.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Buscar o hóspede usando o Controller:
        HospedeController controller = new HospedeController();
        Hospede hospedeEncontrado = controller.buscarPorCPF(cpfDigitado);

        // 4. Tratar o resultado da busca:
        if (hospedeEncontrado != null) {
            // Procura o hóspede no JComboBox existente:
            for (int i = 0; i < cmbHospedes.getItemCount(); i++) {
                Hospede h = cmbHospedes.getItemAt(i);
                if (h.getCpf().equals(cpfDigitado)) {
                    cmbHospedes.setSelectedIndex(i);
                    JOptionPane.showMessageDialog(this,
                            "Hóspede encontrado: " + h.getNome(),
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            // Caso o hóspede não esteja no ComboBox (mas foi encontrado no banco):
            JOptionPane.showMessageDialog(this,
                    "Hóspede encontrado, mas não está na lista atual",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Nenhum hóspede encontrado com o CPF: " + cpfDigitado,
                    "Não encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnBuscarCPFActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarCPF;
    private javax.swing.JButton BtnCheckIN;
    private javax.swing.JButton BtnCheckOut;
    private javax.swing.JButton BtnCriarReserva;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JTextField TxtCPF;
    private javax.swing.JComboBox<Hospede> cmbHospedes;
    private javax.swing.JComboBox<Quarto> cmbQuartos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables

}
