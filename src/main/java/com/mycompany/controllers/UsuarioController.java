/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.entities.Emprestimo;
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
 * Controla, valida e realiza ações no banco de dados.
 *
 * @author othavio
 */
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;
    
    /**
     * Construtor da classe UsuarioController.
     */
    public UsuarioController(){
        this.usuarioRepository = new UsuarioRepository();
        this.contaRepository = new ContaRepository();
    }
 
    /**
     * Cadastra um novo usuário no banco.
     * @param jtNome
     * @param jtfCpf
     * @param jpSenha
     * @param tipoConta
     * @return [ViewValidation] que determina se o cadastro foi realizado
     * com sucesso ou não.
     */
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
    
    /**
     * Realiza o campos de Cadastro do usuário.
     * @param jtNome
     * @param jtfCpf
     * @param jpSenha
     * @return [ViewValidation] que determina se os campos estão corretos ou não.
     */
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
    
    /**
     * Realiza o Login do Usuário, verificando campos e se há uma conta criada para este CPF.
     * @param jtfCpf
     * @param jpSenha
     * @return [ViewValidation] que determina se o login foi realizado
     * com sucesso ou não.
     */
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
    
    /**
     * Verifica os campos de login como CPF e Senha.
     * @param jtfCpf
     * @param jpSenha
     * @return [ViewValidation] que determina se os campos estão corretos ou não.
     */
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
    
    /**
     * Valida os campos Senha e Confirmar Senha.
     * @param senha
     * @param confirmarSenha
     * @return [ViewValidation] que determina se os campos estão corretos ou não.
     */
    public ViewValidation validarCamposConfirmarSenha(char[] senha, char[] confirmarSenha) {
        ViewValidation campoSenhaValidacao = validarCampoSenha(senha);
        if (campoSenhaValidacao.hasErro) return campoSenhaValidacao;
        
        if(confirmarSenha.length < 1) return new ViewValidation(true, "As senhas não podem ser vazias");
        
        if (!Arrays.equals(senha, confirmarSenha)) return new ViewValidation(true, "As senhas não são compatíveis");
        
        return new ViewValidation("Usuario registrado com sucesso");
    }
    
    /**
     * Valida o campo de Nome.
     * @param nome
     * @return [ViewValidation] que determina se o campo está correto ou não.
     */
    public ViewValidation validarCampoNome(String nome) {
        
        if(nome.length() < 1) return new ViewValidation(true, "Você deve inserir um nome");
        
        return new ViewValidation(false, "");
    }
    
    /**
     * Valida o campo de CPF
     * @param campoCpf
     * @return [ViewValidation] que determina se o campo está correto ou não.
     */
    public ViewValidation validarCampoCpf(String campoCpf) {
        String cpfInvalidoMensagem = "CPF Inválido";
        
        if (campoCpf.isEmpty() | campoCpf.length() != 11) return new ViewValidation(true, cpfInvalidoMensagem);
        
        return new ViewValidation("");
    }

    /**
     * Valida o campo de senha.
     * @param senha
     * @return [ViewValidation] que determina se o campo está correto ou não.
     */
    public ViewValidation validarCampoSenha(char[] senha) {
        
        if(senha.length < 1) return new ViewValidation(true, "A senha deve ter pelo menos 1 caracter");
        
        return new ViewValidation("");
    }

    /**
     * Procura por uma Conta Corrente com base no CPF do Usuário
     * @param cpf
     * @return [ContaCorrente] ou [null] caso não haja nenhuma conta corrente
     * para este CPF.
     */
    public ContaCorrente getUsuarioContaCorrente(Long cpf) {
        ContaCorrente contaCorrente = null;
        try {
            contaCorrente = this.contaRepository.getUsuarioContaCorrente(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta corrente do usuário");
        }
        
        return contaCorrente;
    }
    
    /**
     * Procura por uma Conta Poupança com base no CPF do Usuário
     * @param cpf
     * @return [ContaPoupanca] ou [null] caso não haja nenhuma conta poupança
     * para este CPF.
     */
    public ContaPoupanca getUsuarioContaPoupanca(Long cpf) {
        ContaPoupanca contaPoupanca = null;
        try {
            contaPoupanca = this.contaRepository.getUsuarioContaPoupanca(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta corrente do usuário");
        }
        
        return contaPoupanca;
    }
    
    /**
     * Cria uma nova conta para o usuário baseado no tipo de conta.
     * @param usuario
     * @param tipoConta
     * @return [true] caso a conta tenha sido criada com sucesso e
     * [false] caso tenha havido alguma falha na criação de conta.
     */
    public boolean criarNovaConta(Usuario usuario, int tipoConta) {
        return this.contaRepository.criarNovaConta(usuario, tipoConta);
    }
    
    /**
     * Remove uma conta do usuário baseado no tipo de conta.
     * @param cpf
     * @param tipoConta
     * @return [ViewValidation] que determina se a ação teve sucesso ou falha.
     */
    public ViewValidation<String> removerConta(Long cpf, int tipoConta){
        Conta conta = null;
        if (tipoConta == 1) conta = this.getUsuarioContaPoupanca(cpf);
        else conta = this.getUsuarioContaCorrente(cpf);
        
        if (conta.getSaldo() > 0) return new ViewValidation(false,"Há saldo nesta conta. Realize uma transferência ou saque antes de removê-la.");
        
        try {
            Emprestimo emprestimo = this.contaRepository.getContaEmprestimo(conta.getId());
            if(emprestimo != null) return new ViewValidation(false,"Há pendências de empréstimos. Realize o pagamento de seus empréstimos antes de remover a conta.");
            
            boolean isContaDeletada = this.contaRepository.deletarConta(conta.getId());
            
            if (!isContaDeletada) return new ViewValidation(false, "Erro ao tentar remover a conta.");
            
        } catch (SQLException e) {
            return new ViewValidation(false, "Erro ao tentar validar pendências da conta.");
        }
        
        return new ViewValidation("Conta removida com sucesso.");
    }
}
