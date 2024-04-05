/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.telas;

import com.mycompany.controllers.UsuarioController;
import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.entities.Usuario;
import com.mycompany.entities.validation.ViewValidation;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author alunolages
 */
public class TelaCriarConta extends javax.swing.JPanel {
    private boolean hasContaCorrente, hasContaPoupanca;
    private final UsuarioController usuarioController;
    private final Conta contaAtivaNaTela;
    private final Usuario usuario;
    /**
     * Creates new form TelaCriarConta
     * @param usuario
     */
    public TelaCriarConta(Conta conta,Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.contaAtivaNaTela = conta;
        this.usuarioController = new UsuarioController();
        initContasExistentes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbCancelar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlCorrente = new javax.swing.JLabel();
        jlPoupanca = new javax.swing.JLabel();
        jlSaldoTextCorrente = new javax.swing.JLabel();
        jlSaldoTextPoupanca = new javax.swing.JLabel();
        jlValorSaldo = new javax.swing.JLabel();
        jlValorPoupanca = new javax.swing.JLabel();
        jbConcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jlTipoContaDisponivel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jltContasDisponiveis = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jbSair = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        jbCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), " Conta(as) Existentes: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jlCorrente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlCorrente.setText("Corrente:");

        jlPoupanca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlPoupanca.setText("Poupança:");

        jlSaldoTextCorrente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlSaldoTextCorrente.setText("Saldo: R$");

        jlSaldoTextPoupanca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlSaldoTextPoupanca.setText("Saldo: R$");

        jlValorSaldo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlValorSaldo.setText("0");

        jlValorPoupanca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlValorPoupanca.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jlPoupanca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlSaldoTextPoupanca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlValorPoupanca))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jlCorrente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlSaldoTextCorrente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlValorSaldo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSaldoTextCorrente)
                    .addComponent(jlValorSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPoupanca, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSaldoTextPoupanca)
                    .addComponent(jlValorPoupanca)))
        );

        jbConcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbConcluir.setText("Concluir");
        jbConcluir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConcluirActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), " Criar Conta: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jlTipoContaDisponivel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTipoContaDisponivel.setText("Tipo de Conta Disponível:");

        jltContasDisponiveis.setBackground(new java.awt.Color(204, 204, 255));
        jltContasDisponiveis.setBorder(null);
        jScrollPane1.setViewportView(jltContasDisponiveis);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jlTipoContaDisponivel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jlTipoContaDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        jLabel6.setFont(new java.awt.Font("SWItalt", 1, 18)); // NOI18N
        jLabel6.setText("Criar Conta");

        jbSair.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbSair.setText("Sair");
        jbSair.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 3, true));
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jbConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initContasExistentes() {
        DefaultListModel demoList = new DefaultListModel();
        
        ContaCorrente contaCorrente = usuarioController.getUsuarioContaCorrente(this.usuario.getCpf());
        if (contaCorrente == null){
            hasContaCorrente = false;
            jlCorrente.setVisible(false);
            jlSaldoTextCorrente.setVisible(false);
            jlValorSaldo.setVisible(false);
            demoList.addElement("Conta Corrente");
        }
        else {
            hasContaCorrente = true;
            jlValorSaldo.setText(String.valueOf(contaCorrente.getSaldo()));
        }
        
        ContaPoupanca contaPoupanca = usuarioController.getUsuarioContaPoupanca(this.usuario.getCpf());
        if (contaPoupanca == null) {
            hasContaPoupanca = false;
            jlPoupanca.setVisible(false);
            jlSaldoTextPoupanca.setVisible(false);
            jlValorPoupanca.setVisible(false);
            demoList.addElement("Poupança");
        }
        else {
            hasContaPoupanca = true;
            jlValorPoupanca.setText(String.valueOf(contaPoupanca.getSaldo()));
        }
        
        if (hasContaCorrente && hasContaPoupanca){
            this.jltContasDisponiveis.removeAll();
            this.jltContasDisponiveis.setVisible(false);
            this.jlTipoContaDisponivel.setText("Você não tem nenhum tipo de conta disponível.");
        }
        else {
            this.jltContasDisponiveis.setModel(demoList);
        }
    }
    
    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        this.irParaTelaInicial();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConcluirActionPerformed
        if (hasContaCorrente && hasContaPoupanca){
            this.irParaTelaInicial();
            return;
        }
        
        String contaSelectionada = this.jltContasDisponiveis.getSelectedValue();
        int tipoConta = 0;
        if (contaSelectionada.contains("Poupança")){
            tipoConta = 1;
        }

        boolean isContaCriada = this.usuarioController.criarNovaConta(usuario, tipoConta);
        if (!isContaCriada){
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar uma nova conta.\nTente novamente mais tarde.", "Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Nova conta criada!");
        this.irParaTelaInicial();
    }//GEN-LAST:event_jbConcluirActionPerformed

    private void irParaTelaInicial() {
        Janela.telaInicial = new TelaInicial(this.contaAtivaNaTela,this.usuario);
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
        janela.getContentPane().remove(Janela.telaCriarConta);
        janela.add(Janela.telaInicial, BorderLayout.CENTER);
        janela.pack();
    }
    
    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que quer sair?", "Aviso", JOptionPane.YES_NO_OPTION);
        System.exit(0);
    }//GEN-LAST:event_jbSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbConcluir;
    private javax.swing.JButton jbSair;
    private javax.swing.JLabel jlCorrente;
    private javax.swing.JLabel jlPoupanca;
    private javax.swing.JLabel jlSaldoTextCorrente;
    private javax.swing.JLabel jlSaldoTextPoupanca;
    private javax.swing.JLabel jlTipoContaDisponivel;
    private javax.swing.JLabel jlValorPoupanca;
    private javax.swing.JLabel jlValorSaldo;
    private javax.swing.JList<String> jltContasDisponiveis;
    // End of variables declaration//GEN-END:variables
}
