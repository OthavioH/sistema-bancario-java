package com.mycompany.telas;

import com.mycompany.controllers.UsuarioController;
import com.mycompany.entities.Conta;
import com.mycompany.entities.Usuario;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaInicial extends javax.swing.JPanel {
    public Usuario usuarioLogado;
    private UsuarioController usuarioController;
    public Conta contaUsuario;
    
    /**
     * Construtor da [TelaInicial] que inicializa os componentes e variáveis da
     * classe.
     * 
     * @param usuario 
     */
    public TelaInicial(Usuario usuario){
        this.usuarioController = new UsuarioController();
        this.usuarioLogado = usuario;
        initComponents();
        this.jlNome.setText(this.usuarioLogado.getNome());
        
        this.changeSaldo();
    }
    
    public TelaInicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jlNome = new javax.swing.JLabel();
        jbSair = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlSaldoAtual = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jbPoupanca = new javax.swing.JButton();
        jbExtrato = new javax.swing.JButton();
        jbTransferencia = new javax.swing.JButton();
        jbEmprestimo = new javax.swing.JButton();
        jbCriarConta = new javax.swing.JButton();
        jbApagarConta = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(330, 360));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel3.setFont(new java.awt.Font("SWItalt", 1, 18)); // NOI18N
        jLabel3.setText("Olá");

        jlNome.setFont(new java.awt.Font("SWRomnt", 1, 18)); // NOI18N
        jlNome.setText("jLabel1");

        jbSair.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbSair.setText("Sair");
        jbSair.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 3, true));
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlNome, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jlNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), " Saldo Atual: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("R$:");

        jlSaldoAtual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlSaldoAtual.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addComponent(jlSaldoAtual))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jbPoupanca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbPoupanca.setText("Poupança");
        jbPoupanca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbPoupanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPoupancaActionPerformed(evt);
            }
        });

        jbExtrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbExtrato.setText("Extrato");
        jbExtrato.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExtratoActionPerformed(evt);
            }
        });

        jbTransferencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbTransferencia.setText("Transferência");
        jbTransferencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTransferenciaActionPerformed(evt);
            }
        });

        jbEmprestimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbEmprestimo.setText("Empréstimo");
        jbEmprestimo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEmprestimoActionPerformed(evt);
            }
        });

        jbCriarConta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbCriarConta.setText("Criar Conta");
        jbCriarConta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbCriarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarContaActionPerformed(evt);
            }
        });

        jbApagarConta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbApagarConta.setText("Apagar Conta");
        jbApagarConta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        jbApagarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbApagarContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbEmprestimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbPoupanca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbExtrato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbCriarConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbApagarConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbTransferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCriarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPoupanca, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbApagarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changeSaldo() {
        contaUsuario = this.usuarioController.getUsuarioContaCorrente(usuarioLogado.getCpf());
        
        if (contaUsuario == null) {
            contaUsuario = this.usuarioController.getUsuarioContaPoupanca(usuarioLogado.getCpf());
        }
        
        this.jlSaldoAtual.setText(String.valueOf(contaUsuario.getSaldo()));
    }
    
    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        Janela.telaLogin = new TelaLogin();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaLogin, BorderLayout.CENTER);                         
        janela.pack();
    }//GEN-LAST:event_jbSairActionPerformed

    private void jbPoupancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPoupancaActionPerformed
        Janela.telaPoupanca = new TelaPoupanca();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaPoupanca, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbPoupancaActionPerformed

    private void jbExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExtratoActionPerformed
        Janela.telaExtrato = new TelaExtrato();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaExtrato, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbExtratoActionPerformed

    private void jbTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTransferenciaActionPerformed
        Janela.telaTransferencia = new TelaTransferencia();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaTransferencia, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbTransferenciaActionPerformed

    private void jbEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEmprestimoActionPerformed
        Janela.telaEmprestimo = new TelaEmprestimo();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaEmprestimo, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbEmprestimoActionPerformed

    private void jbCriarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarContaActionPerformed
        Janela.telaCriarConta = new TelaCriarConta(this.usuarioLogado);                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaCriarConta, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbCriarContaActionPerformed

    private void jbApagarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbApagarContaActionPerformed
        Janela.telaApagarConta = new TelaApagarConta(this.usuarioLogado);                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaInicial);                          
        janela.add(Janela.telaApagarConta, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jbApagarContaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbApagarConta;
    private javax.swing.JButton jbCriarConta;
    private javax.swing.JButton jbEmprestimo;
    private javax.swing.JButton jbExtrato;
    private javax.swing.JButton jbPoupanca;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbTransferencia;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlSaldoAtual;
    // End of variables declaration//GEN-END:variables

    
}
