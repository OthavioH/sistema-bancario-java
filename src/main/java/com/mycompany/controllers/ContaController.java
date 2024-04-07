/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.entities.Transferencia;
import com.mycompany.repositories.ContaRepository;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Controla, valida e realiza ações no banco de dados envolvendo Contas.
 * 
 * @author othavio
 */
public class ContaController {
    private final ContaRepository contaRepository;
    
    /**
     * Construtor do [ContaController]
     */
    public ContaController () {
        this.contaRepository = new ContaRepository();
    }
    
    /**
     * Busca por um ID de uma conta e retorna a mesma.
     * @param contaId
     * @return [Conta] ou [null] caso não haja conta naquele ID.
     */
    public Conta getContaById(int contaId){
        Conta conta = null;
        try {
            conta = this.contaRepository.getUsuarioContaById(contaId);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta do usuário");
        }
        
        return conta;
    }
    
    /**
     * Returna a lista de contas de um usuário.
     * 
     * @param cpf
     * @return Lista de contas do usuário.
     */
    public List<Conta> getUsuarioContas(Long cpf) {
        List<Conta> listaContas = new ArrayList<>();
        try {
            listaContas = this.contaRepository.getUsuarioContas(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta corrente do usuário");
        }
        
        return listaContas;
    }
    
    /**
     * Retorna a conta de um usuário baseado no tipo de conta.
     * 
     * @param cpf
     * @param tipoConta
     * @return 
     */
    public Conta getUsuarioContaByTipo(Long cpf, int tipoConta){
        Conta conta = null;
        try {
            if(tipoConta == 0) {
                conta = this.contaRepository.getUsuarioContaCorrente(cpf);
                return conta;
            }
            
            conta = this.contaRepository.getUsuarioContaPoupanca(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta do usuário");
        }
        return conta;
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
     * Retorna o extrato de transferências de uma conta.
     * 
     * @param contaId
     * @return Extrato de transferencias de uma conta.
     */
    public List<String> getExtratoConta(int contaId) {
        List<String> extrato = new ArrayList<>();
        
        try {
            var listaTransferencias = this.contaRepository.getContaTransferencias(contaId);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Transferencia transferencia : listaTransferencias) {
                String dataTransferencia = dateFormat.format(transferencia.getData());
                
                String mensagemExtrato = dataTransferencia + " ";
                
                if (transferencia.getRemetente().getId() == contaId){
                    mensagemExtrato += "R$ -"+transferencia.getValor();
                }
                else {
                    mensagemExtrato += "R$ +"+transferencia.getValor();
                }
                
                extrato.add(mensagemExtrato);
            }
            
            return extrato;
        } catch (SQLException e) {
            return extrato;
        }
    }
    
    /**
     * Cadastra uma transferência no banco e muda o saldo do Remetente e do
     * Destinatário.
     * 
     * @param transferencia
     * @return [true] ou [false] caso haja um erro.
     */
    public boolean realizarTransferencia(Transferencia transferencia) {
        try {
            boolean isTransferenciaCadastrada = contaRepository.cadastrarTransferencia(transferencia);
            if(!isTransferenciaCadastrada){
                return false;
            }
            
            var contaRemetente = transferencia.getRemetente();
            boolean mudouSaldoRemetente = subtrairTransferenciaDoSaldo(contaRemetente, transferencia);
            if(!mudouSaldoRemetente){
                return false;
            }
            
            var contaDestinatario = transferencia.getRemetente();
            boolean mudouSaldoDestinatario = subtrairTransferenciaDoSaldo(contaDestinatario, transferencia);
            
            return mudouSaldoDestinatario;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Muda o saldo de uma conta.
     * @param conta
     * @param transferencia
     * @return [true] ou [false] caso haja um erro. 
     */
    public boolean subtrairTransferenciaDoSaldo(Conta conta, Transferencia transferencia) {
        try {
            double novoSaldoRemetente = conta.getSaldo() - transferencia.getValor();
            boolean mudouSaldoRemetente = contaRepository.mudarContaSaldo(conta.getId(), novoSaldoRemetente);
            return mudouSaldoRemetente;
        } catch (SQLException e) {
            return false;
        }
    } 
    
    /**
     * Realiza o depósito em uma conta.
     * 
     * @param conta
     * @param valor
     * @return [true] ou [false] caso haja um erro.
     */
    public boolean depositarNaConta(Conta conta, double valor){
        try {
            double novoSaldoConta = conta.getSaldo() + valor;
            boolean mudouSaldo = contaRepository.mudarContaSaldo(conta.getId(), novoSaldoConta);
            return mudouSaldo;
        } catch (SQLException e) {
            return false;
        }
    }
    
}
