package com.mycompany.entities;

/**
 * Classe que contém implementações de uma ContaPoupanca
 * 
 * @author ots
 */
public class ContaPoupanca extends Conta{
    private double rendimento;

    /**
     * Construtor da classe [ContaPoupanca]
     */
    public ContaPoupanca() {
    }

    /**
     * Construtor da classe [ContaPoupanca]
     * @param rendimento 
     */
    public ContaPoupanca(double rendimento) {
        this.rendimento = rendimento;
    }

    /**
     * Construtor da classe [ContaPoupanca]
     * @param rendimento
     * @param saldo
     * @param id 
     */
    public ContaPoupanca(double rendimento, double saldo, int id) {
        super(id, saldo, 1);
        this.rendimento = rendimento;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    
}
