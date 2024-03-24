package com.mycompany.entities;

public class ContaPoupanca extends Conta{
    private double rendimento;

    public ContaPoupanca() {
    }

    public ContaPoupanca(double rendimento) {
        this.rendimento = rendimento;
    }

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
