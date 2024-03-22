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
@Table(name = "contapoupanca")
@NamedQueries({
    @NamedQuery(name = "Contapoupanca.findAll", query = "SELECT c FROM Contapoupanca c"),
    @NamedQuery(name = "Contapoupanca.findByContaid", query = "SELECT c FROM Contapoupanca c WHERE c.contapoupancaPK.contaid = :contaid"),
    @NamedQuery(name = "Contapoupanca.findByContaUsuariocpf", query = "SELECT c FROM Contapoupanca c WHERE c.contapoupancaPK.contaUsuariocpf = :contaUsuariocpf"),
    @NamedQuery(name = "Contapoupanca.findByRendimento", query = "SELECT c FROM Contapoupanca c WHERE c.rendimento = :rendimento")})
public class Contapoupanca implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContapoupancaPK contapoupancaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "rendimento")
    private BigDecimal rendimento;
    @JoinColumns({
        @JoinColumn(name = "Conta_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Conta_Usuario_cpf", referencedColumnName = "Usuario_cpf", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Conta conta;

    public Contapoupanca() {
    }

    public Contapoupanca(ContapoupancaPK contapoupancaPK) {
        this.contapoupancaPK = contapoupancaPK;
    }

    public Contapoupanca(ContapoupancaPK contapoupancaPK, BigDecimal rendimento) {
        this.contapoupancaPK = contapoupancaPK;
        this.rendimento = rendimento;
    }

    public Contapoupanca(int contaid, int contaUsuariocpf) {
        this.contapoupancaPK = new ContapoupancaPK(contaid, contaUsuariocpf);
    }

    public ContapoupancaPK getContapoupancaPK() {
        return contapoupancaPK;
    }

    public void setContapoupancaPK(ContapoupancaPK contapoupancaPK) {
        this.contapoupancaPK = contapoupancaPK;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public void setRendimento(BigDecimal rendimento) {
        this.rendimento = rendimento;
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
        hash += (contapoupancaPK != null ? contapoupancaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contapoupanca)) {
            return false;
        }
        Contapoupanca other = (Contapoupanca) object;
        if ((this.contapoupancaPK == null && other.contapoupancaPK != null) || (this.contapoupancaPK != null && !this.contapoupancaPK.equals(other.contapoupancaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Contapoupanca[ contapoupancaPK=" + contapoupancaPK + " ]";
    }
    
}
