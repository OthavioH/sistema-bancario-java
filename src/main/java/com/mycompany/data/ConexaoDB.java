/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alunolages
 */
public class ConexaoDB {
    private EntityManagerFactory emf;
    
    ConexaoDB(){
        this.emf = Persistence.createEntityManagerFactory("SistemaBancarioPU");
    }
    
    public EntityManagerFactory getConexao() {
        return this.emf;
    }
}
