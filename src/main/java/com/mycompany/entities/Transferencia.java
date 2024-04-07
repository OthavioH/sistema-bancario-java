package com.mycompany.entities;

import java.util.Date;

public class Transferencia {
    public int id;
    private Conta remetente, destinatario;
    private double valor;
    private Date data;

    public Transferencia() {
    }

    public Transferencia(Conta remetente, Conta destinatario, double valor, Date data) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conta getRemetente() {
        return remetente;
    }

    public void setRemetente(Conta remetente) {
        this.remetente = remetente;
    }

    public Conta getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Conta destinatario) {
        this.destinatario = destinatario;
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
