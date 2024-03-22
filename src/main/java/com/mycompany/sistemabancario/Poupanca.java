package com.mycompany.sistemabancario;

public class Poupanca extends Conta{
    private double rendimento;

    public Poupanca() {
    }

    public Poupanca(double rendimento) {
        this.rendimento = rendimento;
    }

    public Poupanca(double rendimento, double saldo, int id) {
        super(saldo, id);
        this.rendimento = rendimento;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    
}
