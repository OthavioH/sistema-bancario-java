package com.mycompany.entities;

/**
 * Classe que contém implementações de uma Conta
 * 
 * @author ots
 */
public class Conta {
    private int id;
    private double saldo;
    private int tipoConta;

    /**
     * Construtor da classe [Conta]
     */
    public Conta(){}
    
    /**
     * Construtor da classe [Conta]
     * 
     * @param id
     * @param saldo
     * @param tipoConta 
     */
    public Conta(int id, double saldo, int tipoConta) {
        this.id = id;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    /**
     * Construtor da classe [Conta]
     * 
     * @param saldo
     * @param id 
     */
    public Conta(double saldo, int id) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }
    
}
