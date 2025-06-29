package sistemahotel.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import sistemahotel.controller.ReservaController;
import sistemahotel.dao.HospedeDAO;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.model.Funcionario;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;
import sistemahotel.view.MenuViewGUI;

/**
 *
 * @author Ray Carvalho
 */
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
    
    public ReservaViewGUI(){
        initComponents();
    }
    
    private void carregarDadosIniciais() {
        try {
            System.out.println("--- DEBUG: Iniciando carregamento de dados para a tela de Reserva ---");

            // Limpa os comboboxes
            cmbHospedes.removeAllItems();
            cmbQuartos.removeAllItems();

            // Carrega os hóspedes
            HospedeDAO hospedeDAO = new HospedeDAO();
            List<Hospede> hospedes = hospedeDAO.listarHospedes();
            // Teste 1: Quantos hóspedes o DAO realmente encontrou?
            System.out.println("DEBUG: Hóspedes encontrados no banco: " + hospedes.size());

            for (Hospede h : hospedes) {
                cmbHospedes.addItem(h);
            }

            // Carrega os quartos disponíveis
            QuartoDAO quartoDAO = new QuartoDAO();
            List<Quarto> quartos = quartoDAO.listarQuartosDisponiveis();
            // Teste 2: Quantos quartos disponíveis o DAO encontrou?
            System.out.println("DEBUG: Quartos disponíveis encontrados: " + quartos.size());

            for (Quarto q : quartos) {
                cmbQuartos.addItem(q);
            }

            System.out.println("DEBUG: Dados adicionados aos ComboBoxes com sucesso.");
            System.out.println("--------------------------------------------------------------------");

        } catch (Exception e) {
            // Se cair aqui, um erro grave aconteceu durante a busca no banco.
            System.err.println("--- ERRO CRÍTICO em carregarDadosIniciais ---");
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados iniciais: " + e.getMessage());
            e.printStackTrace(); // Imprime o erro completo no console de saída
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            Hospede hospedeSelecionado = (Hospede) cmbHospedes.getSelectedItem();
            Quarto quartoSelecionado = (Quarto) cmbQuartos.getSelectedItem();

            if (hospedeSelecionado == null || quartoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede e um quarto válidos.");
                return;
            }
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataEntrada = LocalDate.parse(txtDataEntrada.getText(), formatter);
            LocalDate dataSaida = LocalDate.parse(txtDataSaida.getText(), formatter);

            boolean sucesso = reservaController.criarReserva(quartoSelecionado.getId(), hospedeSelecionado.getId(), dataEntrada, dataSaida);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Reserva criada com sucesso!");
                carregarDadosIniciais(); // Atualiza a lista de quartos
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao criar reserva. Verifique a disponibilidade ou os logs.");
            }
            
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
        String precoDiariaStr = JOptionPane.showInputDialog(this, "Digite o valor da diária (ex: 150.50):");

        try {
            int idReserva = Integer.parseInt(idReservaStr);
            BigDecimal precoDiaria = new BigDecimal(precoDiariaStr);

            BigDecimal valorTotal = reservaController.realizarCheckOut(idReserva, precoDiaria);

            if (valorTotal != null) {
                JOptionPane.showMessageDialog(this, "Check-out realizado!\nValor Total a Pagar: R$ " + valorTotal);
                carregarDadosIniciais(); // Atualiza a lista de quartos
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ReservaViewGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCheckIN;
    private javax.swing.JButton BtnCheckOut;
    private javax.swing.JButton BtnCriarReserva;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JComboBox<Hospede> cmbHospedes;
    private javax.swing.JComboBox<Quarto> cmbQuartos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables

}
