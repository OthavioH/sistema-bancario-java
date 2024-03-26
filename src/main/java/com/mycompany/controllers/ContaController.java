/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.repositories.ContaRepository;
import java.sql.SQLException;

/**
 *
 * @author othavio
 */
public class ContaController {
    private final ContaRepository contaRepository;
    
    public ContaController () {
        this.contaRepository = new ContaRepository();
    }
    
    public Conta getContaById(int contaId){
        Conta conta = null;
        try {
            conta = this.contaRepository.getUsuarioContaById(contaId);
        } catch (SQLException e) {
            System.out.println("Erro ao tentar buscar a conta do usuário");
        }
        
        return conta;
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
}
