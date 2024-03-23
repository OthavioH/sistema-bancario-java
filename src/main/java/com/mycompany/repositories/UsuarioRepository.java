/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repositories;

import com.mycompany.data.ConexaoDB;
import com.mycompany.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author othavioh
 */
public class UsuarioRepository {
    
    ConexaoDB db;
    
    public UsuarioRepository () {
        db = new ConexaoDB();
    }
    
    /**
     * Realiza uma busca por um usuário utilizando seu CPF.
     * Caso não exista um usuário, o valor retornado será null.
     * 
     * @param cpf
     * @return O Usuário (caso exista) ou null
     */
    public Usuario getUsuarioPorCpf(Long cpf){
        Usuario usuario = null;
        
        try {
            PreparedStatement consulta = this.db.prepararQuery("SELECT * FROM usuario WHERE cpf = "+cpf);
            consulta.execute();
            ResultSet result = consulta.getResultSet();
            
            while (result.next()){
                String nome = result.getString("nome");
                String senha = result.getString("senha");
                usuario = new Usuario(nome,cpf,senha);
            }
            
            result.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados do Usuario: " +e.getMessage());
        }
        
        return usuario;
    }
    
    public boolean cadastrarNovoUsuario(Usuario novoUsuario) {
        try {
            PreparedStatement query = this.db.prepararQuery("INSERT INTO Usuario(cpf,nome,senha) values (?,?,?)");
            
            query.setLong(1, novoUsuario.getCpf());
            query.setString(2, novoUsuario.getNome());
            query.setString(3, novoUsuario.getSenha());
            query.execute();
            
            query.close();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados de Usuario: " +e.getMessage());
        }
        return false;
    }
}
