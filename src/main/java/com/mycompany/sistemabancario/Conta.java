package com.mycompany.sistemabancario;

public class Conta {
    private int id;
    private double saldo;

    public Conta() {
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
    
}
