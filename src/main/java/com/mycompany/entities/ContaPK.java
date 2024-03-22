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
public class ContaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Usuario_cpf")
    private int usuariocpf;

    public ContaPK() {
    }

    public ContaPK(int id, int usuariocpf) {
        this.id = id;
        this.usuariocpf = usuariocpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuariocpf() {
        return usuariocpf;
    }

    public void setUsuariocpf(int usuariocpf) {
        this.usuariocpf = usuariocpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) usuariocpf;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaPK)) {
            return false;
        }
        ContaPK other = (ContaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.usuariocpf != other.usuariocpf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.ContaPK[ id=" + id + ", usuariocpf=" + usuariocpf + " ]";
    }
    
}
