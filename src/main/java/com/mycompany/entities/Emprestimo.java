/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author ots
 */
public class Emprestimo {
    private int id;
    private double saldo_disponivel;
    private int conta_id;

    public Emprestimo(){}
    
    public Emprestimo(int id, double saldo_disponivel, int conta_id) {
        this.id = id;
        this.saldo_disponivel = saldo_disponivel;
        this.conta_id = conta_id;
    }

    public Emprestimo(int id, double saldo) {
        this.id = id;
        this.saldo_disponivel = saldo;
    }

    public double getSaldoDisponivel() {
        return saldo_disponivel;
    }

    public void setSaldoDisponivel(double saldo) {
        this.saldo_disponivel = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaId() {
        return conta_id;
    }

    public void setContaId(int tipoConta) {
        this.conta_id = tipoConta;
    }
    
}
