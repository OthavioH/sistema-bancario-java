
package com.mycompany.telas;

import com.mycompany.controllers.UsuarioController;
import com.mycompany.entities.validation.ViewError;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaLogin extends javax.swing.JPanel {
    private final UsuarioController usuarioController;
    public TelaLogin() {
        initComponents();
        usuarioController = new UsuarioController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlCpf = new javax.swing.JLabel();
        jlSenha = new javax.swing.JLabel();
        jlMensagemErro = new javax.swing.JLabel();
        jbLogin = new javax.swing.JButton();
        jlRegistrarConta = new javax.swing.JLabel();
        jtfCpf = new javax.swing.JFormattedTextField();
        jpSenha = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(330, 360));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Bem-Vindo");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("SWItalt", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Banco IFSC");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlCpf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlCpf.setText("CPF");

        jlSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlSenha.setText("Senha");

        jlMensagemErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jbLogin.setBackground(new java.awt.Color(0, 204, 204));
        jbLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbLogin.setText("Login");
        jbLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 3, true));
        jbLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoginActionPerformed(evt);
            }
        });

        jlRegistrarConta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlRegistrarConta.setText("Não tenho uma conta");
        jlRegistrarConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlRegistrarContaMouseClicked(evt);
            }
        });

        try {
            jtfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtfCpf.setText("");
        jtfCpf.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jlSenha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jlCpf)
                                        .addGap(26, 26, 26)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfCpf)
                                    .addComponent(jpSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addComponent(jlRegistrarConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jlMensagemErro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCpf)
                    .addComponent(jtfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSenha)
                    .addComponent(jpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMensagemErro)
                .addGap(18, 36, Short.MAX_VALUE)
                .addComponent(jbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlRegistrarConta)
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoginActionPerformed
        boolean camposValidados = this.validarCampos();
        if (!camposValidados) return;
        
        Long cpf = Long.valueOf(jtfCpf.getText());
        String senha = new String(jpSenha.getPassword());
        try {
            ViewError loginError = usuarioController.logarUsuario(cpf, senha);
        
            if(loginError.hasErro){
                this.jlMensagemErro.setText(loginError.erroMensagem);
                return;
            }
            
            Janela.telaInicial = new TelaInicial();                                          
            JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
            janela.getContentPane().remove(Janela.telaLogin);                          
            janela.add(Janela.telaInicial, BorderLayout.CENTER);                         
            janela.pack();
        } catch (Exception e) {
            this.jlMensagemErro.setText("Erro ao realizar login.");
        }
    }//GEN-LAST:event_jbLoginActionPerformed

    private boolean validarCampos(){
        ViewError cpfValidado = usuarioController.validarCampoCpf(jtfCpf.getText().trim());
        ViewError senhaValidada = usuarioController.validarCampoSenha(jpSenha.getPassword());
        
        if (cpfValidado.hasErro){
            jtfCpf.requestFocus();
            jlMensagemErro.setText(cpfValidado.erroMensagem);
            return false;
        }
        if (senhaValidada.hasErro){
            jpSenha.requestFocus();
            jlMensagemErro.setText(senhaValidada.erroMensagem);
            return false;
        }
        
        jlMensagemErro.setText("");
        return true;
    }
    
    private void jlRegistrarContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlRegistrarContaMouseClicked
        Janela.telaCriarUsuario = new TelaCriarUsuario();                                          
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);    
        janela.getContentPane().remove(Janela.telaLogin);                          
        janela.add(Janela.telaCriarUsuario, BorderLayout.CENTER);                         
        janela.pack(); 
    }//GEN-LAST:event_jlRegistrarContaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbLogin;
    private javax.swing.JLabel jlCpf;
    private javax.swing.JLabel jlMensagemErro;
    private javax.swing.JLabel jlRegistrarConta;
    private javax.swing.JLabel jlSenha;
    private javax.swing.JPasswordField jpSenha;
    private javax.swing.JFormattedTextField jtfCpf;
    // End of variables declaration//GEN-END:variables
}
