/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alunolages
 */
public class ConexaoDB {
    private String url, porta, banco, usuario, senha, nomeBanco;
    
    public ConexaoDB(){
        this.usuario = "root";
        this.senha = "";
        this.nomeBanco = "sistemabanco";
        this.porta = "3306";
        this.banco = "mysql";
    }
    
    public Connection getConexao() {
        Connection conexao = null;
        
        try {
            this.url = "jdbc:"+this.banco+"://localhost:"+this.porta+"/"+this.nomeBanco;
            conexao  = DriverManager.getConnection(url, this.usuario, this.senha);
        }
        catch (SQLException e) {
            System.out.println("Problemas na conexão com o banco: " + e.getMessage());
        }
        
        
        return conexao;
    }
}
