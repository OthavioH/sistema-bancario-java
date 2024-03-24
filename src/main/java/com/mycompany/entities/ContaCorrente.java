package com.mycompany.entities;

public class ContaCorrente extends Conta{
    private double limite;

    public ContaCorrente() {
    }

    public ContaCorrente(double limite) {
        this.limite = limite;
    }

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
