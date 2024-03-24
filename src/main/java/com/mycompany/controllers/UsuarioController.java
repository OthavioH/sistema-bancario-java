/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.entities.Usuario;
import com.mycompany.entities.validation.ViewValidation;
import com.mycompany.repositories.ContaRepository;
import com.mycompany.repositories.UsuarioRepository;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author othavio
 */
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;
    
    public UsuarioController(){
        this.usuarioRepository = new UsuarioRepository();
        this.contaRepository = new ContaRepository();
    }
    
    public ViewValidation<Usuario> cadastrarUsuario(JTextField jtNome,JFormattedTextField jtfCpf, JPasswordField jpSenha, int tipoConta) {
        ViewValidation camposValidados = this.verificarCamposDeCadastro(jtNome,jtfCpf, jpSenha);
        if (camposValidados.hasErro){
            return camposValidados;
        }
        
        Long cpf = Long.valueOf(jtfCpf.getText());
        Usuario usuario = this.usuarioRepository.getUsuarioPorCpf(cpf);
        
        if (usuario != null){
            return new ViewValidation(true, "Usuário já cadastrado.");
        }
        
        String senha = new String(jpSenha.getPassword());
        usuario = new Usuario(jtNome.getText(),cpf, senha);
        boolean cadastradoComSucesso = this.usuarioRepository.cadastrarNovoUsuario(usuario);
        
        if (!cadastradoComSucesso) {
            return new ViewValidation(true, "Erro ao cadastrar novo usuário.");
        }
        
        boolean isContaCriada = this.contaRepository.criarNovaConta(usuario, tipoConta);
        if (!isContaCriada) {
            return new ViewValidation(true, "Erro ao cadastrar nova conta.");
        }
        
        return new ViewValidation(usuario);
        
    }
       
    public ViewValidation<String> verificarCamposDeCadastro(JTextField jtNome,JFormattedTextField jtfCpf, JPasswordField jpSenha){
        ViewValidation nomeValidado = this.validarCampoNome(jtNome.getText());
        ViewValidation cpfValidado = this.validarCampoCpf(jtfCpf.getText().trim());
        ViewValidation senhaValidada = this.validarCampoSenha(jpSenha.getPassword());
        
        if (cpfValidado.hasErro){
            jtfCpf.requestFocus();
            return cpfValidado;
        }
        if (nomeValidado.hasErro){
            jtNome.requestFocus();
            return nomeValidado;
        }
        if (senhaValidada.hasErro){
            jpSenha.requestFocus();
            return senhaValidada;
        }
        
        return new ViewValidation("Usuário validado com sucesso");
    }
    
    public ViewValidation<Usuario> logarUsuario(JFormattedTextField jtfCpf, JPasswordField jpSenha) {
        ViewValidation camposValidados = this.verificarCamposDeLogin(jtfCpf, jpSenha);
        
        if (camposValidados.hasErro) {
            return camposValidados;
        }
        
        Long cpf = Long.valueOf(jtfCpf.getText());
        String senha = new String(jpSenha.getPassword());
        
        Usuario usuario = this.usuarioRepository.getUsuarioLogin(cpf, senha);
        
        if (usuario == null) return new ViewValidation(true, "Usuário ou senha incorretos");
        
        return new ViewValidation(usuario);
    }
    
    public ViewValidation<String> verificarCamposDeLogin(JFormattedTextField jtfCpf, JPasswordField jpSenha){
        ViewValidation cpfValidado = this.validarCampoCpf(jtfCpf.getText().trim());
        ViewValidation senhaValidada = this.validarCampoSenha(jpSenha.getPassword());
        
        if (cpfValidado.hasErro){
            jtfCpf.requestFocus();
            return cpfValidado;
        }
        if (senhaValidada.hasErro){
            jpSenha.requestFocus();
            return senhaValidada;
        }
        
        return new ViewValidation("Usuário validado com sucesso");
    }
    
    public ViewValidation validarCamposConfirmarSenha(char[] senha, char[] confirmarSenha) {
        ViewValidation campoSenhaValidacao = validarCampoSenha(senha);
        if (campoSenhaValidacao.hasErro) return campoSenhaValidacao;
        
        if(confirmarSenha.length < 1) return new ViewValidation(true, "As senhas não podem ser vazias");
        
        if (!Arrays.equals(senha, confirmarSenha)) return new ViewValidation(true, "As senhas não são compatíveis");
        
        return new ViewValidation("Usuario registrado com sucesso");
    }
    
    public ViewValidation validarCampoNome(String nome) {
        
        if(nome.length() < 1) return new ViewValidation(true, "Você deve inserir um nome");
        
        return new ViewValidation(false, "");
    }
    
    public ViewValidation validarCampoCpf(String campoCpf) {
        String cpfInvalidoMensagem = "CPF Inválido";
        
        if (campoCpf.isEmpty() | campoCpf.length() != 11) return new ViewValidation(true, cpfInvalidoMensagem);
        
        return new ViewValidation(false, "");
    }

    public ViewValidation validarCampoSenha(char[] senha) {
        
        if(senha.length < 1) return new ViewValidation(true, "A senha deve ter pelo menos 1 caracter");
        
        return new ViewValidation(false, "");
    }

    public ContaCorrente getUsuarioContaCorrente(Long cpf) {
        ContaCorrente contaCorrente = null;
        try {
            contaCorrente = this.contaRepository.getUsuarioContaCorrente(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta corrente do usuário");
        }
        
        return contaCorrente;
    }

    public ContaPoupanca getUsuarioContaPoupanca(Long cpf) {
        ContaPoupanca contaPoupanca = null;
        try {
            contaPoupanca = this.contaRepository.getUsuarioContaPoupanca(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta corrente do usuário");
        }
        
        return contaPoupanca;
    }
    
    public boolean criarNovaConta(Usuario usuario, int tipoConta) {
        return this.contaRepository.criarNovaConta(usuario, tipoConta);
    }
}
