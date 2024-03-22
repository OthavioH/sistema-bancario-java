/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author alunolages
 */
@Embeddable
public class ContapoupancaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Conta_id")
    private int contaid;
    @Basic(optional = false)
    @Column(name = "Conta_Usuario_cpf")
    private int contaUsuariocpf;

    public ContapoupancaPK() {
    }

    public ContapoupancaPK(int contaid, int contaUsuariocpf) {
        this.contaid = contaid;
        this.contaUsuariocpf = contaUsuariocpf;
    }

    public int getContaid() {
        return contaid;
    }

    public void setContaid(int contaid) {
        this.contaid = contaid;
    }

    public int getContaUsuariocpf() {
        return contaUsuariocpf;
    }

    public void setContaUsuariocpf(int contaUsuariocpf) {
        this.contaUsuariocpf = contaUsuariocpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) contaid;
        hash += (int) contaUsuariocpf;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContapoupancaPK)) {
            return false;
        }
        ContapoupancaPK other = (ContapoupancaPK) object;
        if (this.contaid != other.contaid) {
            return false;
        }
        if (this.contaUsuariocpf != other.contaUsuariocpf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.ContapoupancaPK[ contaid=" + contaid + ", contaUsuariocpf=" + contaUsuariocpf + " ]";
    }
    
}
