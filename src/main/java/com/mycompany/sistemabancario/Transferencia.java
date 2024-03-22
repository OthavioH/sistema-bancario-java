package com.mycompany.sistemabancario;

public class Transferencia {
    private Conta transferindo, recebendo;
    private double valor;

    public Transferencia() {
    }

    public Transferencia(Conta transferindo, Conta recebendo, double valor) {
        this.transferindo = transferindo;
        this.recebendo = recebendo;
        this.valor = valor;
    }

    public Conta getTransferindo() {
        return transferindo;
    }

    public void setTransferindo(Conta transferindo) {
        this.transferindo = transferindo;
    }

    public Conta getRecebendo() {
        return recebendo;
    }

    public void setRecebendo(Conta recebendo) {
        this.recebendo = recebendo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
