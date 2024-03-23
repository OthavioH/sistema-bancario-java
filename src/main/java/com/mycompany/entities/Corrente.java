package com.mycompany.entities;

public class Corrente extends Conta{
    private double limite;

    public Corrente() {
    }

    public Corrente(double limite) {
        this.limite = limite;
    }

    public Corrente(double limite, double saldo, int id) {
        super(saldo, id);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
}
