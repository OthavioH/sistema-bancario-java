/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alunolages
 */
@Entity
@Table(name = "conta")
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findById", query = "SELECT c FROM Conta c WHERE c.contaPK.id = :id"),
    @NamedQuery(name = "Conta.findBySaldo", query = "SELECT c FROM Conta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Conta.findByUsuariocpf", query = "SELECT c FROM Conta c WHERE c.contaPK.usuariocpf = :usuariocpf"),
    @NamedQuery(name = "Conta.findByTipoConta", query = "SELECT c FROM Conta c WHERE c.tipoConta = :tipoConta")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContaPK contaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Basic(optional = false)
    @Column(name = "tipo_conta")
    private int tipoConta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaDestinatario")
    private List<Transferencia> transferenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaRemetente")
    private List<Transferencia> transferenciaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaid")
    private List<Emprestimo> emprestimoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "conta")
    private Contapoupanca contapoupanca;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "conta")
    private Contacorrente contacorrente;
    @JoinColumn(name = "Usuario_cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Conta() {
    }

    public Conta(ContaPK contaPK) {
        this.contaPK = contaPK;
    }

    public Conta(ContaPK contaPK, BigDecimal saldo, int tipoConta) {
        this.contaPK = contaPK;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    public Conta(int id, int usuariocpf) {
        this.contaPK = new ContaPK(id, usuariocpf);
    }

    public ContaPK getContaPK() {
        return contaPK;
    }

    public void setContaPK(ContaPK contaPK) {
        this.contaPK = contaPK;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public List<Transferencia> getTransferenciaList() {
        return transferenciaList;
    }

    public void setTransferenciaList(List<Transferencia> transferenciaList) {
        this.transferenciaList = transferenciaList;
    }

    public List<Transferencia> getTransferenciaList1() {
        return transferenciaList1;
    }

    public void setTransferenciaList1(List<Transferencia> transferenciaList1) {
        this.transferenciaList1 = transferenciaList1;
    }

    public List<Emprestimo> getEmprestimoList() {
        return emprestimoList;
    }

    public void setEmprestimoList(List<Emprestimo> emprestimoList) {
        this.emprestimoList = emprestimoList;
    }

    public Contapoupanca getContapoupanca() {
        return contapoupanca;
    }

    public void setContapoupanca(Contapoupanca contapoupanca) {
        this.contapoupanca = contapoupanca;
    }

    public Contacorrente getContacorrente() {
        return contacorrente;
    }

    public void setContacorrente(Contacorrente contacorrente) {
        this.contacorrente = contacorrente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contaPK != null ? contaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.contaPK == null && other.contaPK != null) || (this.contaPK != null && !this.contaPK.equals(other.contaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Conta[ contaPK=" + contaPK + " ]";
    }
    
}
