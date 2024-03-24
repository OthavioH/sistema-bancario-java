package com.mycompany.entities;

public class Conta {
    private int id;
    private double saldo;
    private int tipoConta;

    public Conta(){}
    
    public Conta(int id, double saldo, int tipoConta) {
        this.id = id;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

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
