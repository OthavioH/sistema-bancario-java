/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alunolages
 */
@Entity
@Table(name = "contacorrente")
@NamedQueries({
    @NamedQuery(name = "Contacorrente.findAll", query = "SELECT c FROM Contacorrente c"),
    @NamedQuery(name = "Contacorrente.findByContaid", query = "SELECT c FROM Contacorrente c WHERE c.contacorrentePK.contaid = :contaid"),
    @NamedQuery(name = "Contacorrente.findByContaUsuariocpf", query = "SELECT c FROM Contacorrente c WHERE c.contacorrentePK.contaUsuariocpf = :contaUsuariocpf"),
    @NamedQuery(name = "Contacorrente.findByLimite", query = "SELECT c FROM Contacorrente c WHERE c.limite = :limite")})
public class Contacorrente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContacorrentePK contacorrentePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "limite")
    private BigDecimal limite;
    @JoinColumns({
        @JoinColumn(name = "Conta_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Conta_Usuario_cpf", referencedColumnName = "Usuario_cpf", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Conta conta;

    public Contacorrente() {
    }

    public Contacorrente(ContacorrentePK contacorrentePK) {
        this.contacorrentePK = contacorrentePK;
    }

    public Contacorrente(ContacorrentePK contacorrentePK, BigDecimal limite) {
        this.contacorrentePK = contacorrentePK;
        this.limite = limite;
    }

    public Contacorrente(int contaid, int contaUsuariocpf) {
        this.contacorrentePK = new ContacorrentePK(contaid, contaUsuariocpf);
    }

    public ContacorrentePK getContacorrentePK() {
        return contacorrentePK;
    }

    public void setContacorrentePK(ContacorrentePK contacorrentePK) {
        this.contacorrentePK = contacorrentePK;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contacorrentePK != null ? contacorrentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacorrente)) {
            return false;
        }
        Contacorrente other = (Contacorrente) object;
        if ((this.contacorrentePK == null && other.contacorrentePK != null) || (this.contacorrentePK != null && !this.contacorrentePK.equals(other.contacorrentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Contacorrente[ contacorrentePK=" + contacorrentePK + " ]";
    }
    
}
