package com.mycompany.entities;

import java.util.Date;

public class Transferencia {
    private Conta transferindo, recebendo;
    private double valor;
    private Date data;

    public Transferencia() {
    }

    public Transferencia(Conta transferindo, Conta recebendo, double valor, Date data) {
        this.transferindo = transferindo;
        this.recebendo = recebendo;
        this.valor = valor;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
