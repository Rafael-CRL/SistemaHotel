/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemahotel.view;

import java.util.List;
import javax.swing.JOptionPane;
import sistemahotel.controller.CadastroController;
import sistemahotel.model.Quarto;

/**
 *
 * @author rafael
 */
public class ListaQuartos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ListaQuartos.class.getName());
    private CadastroController cadastroController ;
    private GestaoView telaAnterior;
    /**
     * Creates new form FuncionarioViewGUI
     */
    public ListaQuartos(){
       this(null);
    }
    
    public ListaQuartos(GestaoView telaAnterior) {
        initComponents();
        this.telaAnterior = telaAnterior;
        this.cadastroController = new CadastroController();
        atualizarTabela();
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnExluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuartos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jbVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Quartos");

        btnExluir.setText("Excluir");
        btnExluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExluirActionPerformed(evt);
            }
        });

        tblQuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Numero", "Tipo", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblQuartos);

        btnAdicionar.setText("Adicionar quarto");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jbVoltar.setText("Voltar");
        jbVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jbVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(653, Short.MAX_VALUE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(537, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExluirActionPerformed
        int selectedRow = tblQuartos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um quarto para excluir.");
            return;
}

        int idQuarto = (int) tblQuartos.getValueAt(selectedRow, 0);

// Verifica se o quarto tem reservas associadas
        if (cadastroController.quartoTemReservas(idQuarto)) {
            JOptionPane.showMessageDialog(this, "Não é possível excluir o quarto. Existem reservas associadas a ele.");
            return;
}

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean sucesso = cadastroController.excluirQuarto(idQuarto);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Quarto excluído com sucesso!");
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao excluir o quarto.");
    }
    }

    }//GEN-LAST:event_btnExluirActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        QuartosFormDialog quar = new QuartosFormDialog(this, true, this.cadastroController, null, this);
        quar.setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int selectedRow = tblQuartos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um quarto na tabela para editar.");
            return;
        }

        // Pega o ID da primeira coluna da linha selecionada
        int idQuarto = (int) tblQuartos.getValueAt(selectedRow, 0);

        // Busca o objeto completo do funcionário no banco
        Quarto QuaParaEditar = cadastroController.buscarQuartoPorId(idQuarto);

        // Abre o formulário, passando o objeto para preencher os campos (MODO EDIÇÃO)
        QuartosFormDialog qua = new QuartosFormDialog(this, true, this.cadastroController, QuaParaEditar, this);
        qua.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jbVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoltarActionPerformed
    this.dispose();        
      if (telaAnterior != null) {
        telaAnterior.setVisible(true); // reabre a anterior
    }
    }//GEN-LAST:event_jbVoltarActionPerformed
    
    public void atualizarTabela() {
    // Pega o "modelo" da sua JTable. É através do modelo que você manipula os dados.
   javax.swing.table.DefaultTableModel tableModel = (javax.swing.table.DefaultTableModel) tblQuartos.getModel();
    
    // Limpa a tabela, removendo todas as linhas existentes para evitar duplicatas.
    tableModel.setRowCount(0);

    // Busca a lista de funcionários do banco de dados através do controller.
   List<Quarto> quartos = cadastroController.listarTodosQuartos();

    // Percorre a lista de funcionários e adiciona cada um como uma nova linha na tabela.
     for (Quarto q : quartos) {
        Object[] rowData = {
            q.getId(),
            q.getNumero(),
            q.getTipo(),
            q.getStatus()
        };
        tableModel.addRow(rowData);
    }
}
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
        java.awt.EventQueue.invokeLater(() -> new ListaQuartos().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbVoltar;
    private javax.swing.JTable tblQuartos;
    // End of variables declaration//GEN-END:variables
}
