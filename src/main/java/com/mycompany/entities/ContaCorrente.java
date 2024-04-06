package com.mycompany.entities;

/**
 * Classe que contém implementações de uma ContaCorrente
 * 
 * @author ots
 */
public class ContaCorrente extends Conta{
    private double limite;

    /**
     * Construtor da classe [ContaCorrente]
     */
    public ContaCorrente() {
    }

    /**
     * Construtor da classe [ContaCorrente]
     * 
     * @param limite 
     */
    public ContaCorrente(double limite) {
        this.limite = limite;
    }

    /**
     * Construtor da classe [ContaCorrente]
     * 
     * @param limite
     * @param saldo
     * @param id 
     */
    public ContaCorrente(double limite, double saldo, int id) {
        super(id, saldo, 0);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
}
