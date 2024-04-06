/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repositories;

import com.mycompany.data.ConexaoDB;
import com.mycompany.entities.Conta;
import com.mycompany.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realiza operações no Banco de Dados da aplicação
 * 
 * @author othavioh
 */
public class UsuarioRepository {
    
    ConexaoDB db;
    
    /**
     * Construtor da classe UsuarioRepository.
     */
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
            PreparedStatement consulta = this.db.prepararQuery("SELECT * FROM usuario WHERE cpf = ?");
            consulta.setLong(1, cpf);
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
    
    /**
     * Verifica se o login para o usuário está válido
     * @param cpf
     * @param senha
     * @return [Usuario] logado ou null caso as informações estejam incorretas ou
     * o CPF não esteja cadastrado no banco.
     */
    public Usuario getUsuarioLogin(Long cpf, String senha) {
        Usuario usuario = null;
        
        try {
            PreparedStatement consulta = this.db.prepararQuery("SELECT * FROM usuario WHERE cpf = ? and senha = ? LIMIT 1");
            
            consulta.setLong(1, cpf);
            consulta.setString(2, senha);
            consulta.execute();
            ResultSet result = consulta.getResultSet();
            
            while (result.next()){
                String nome = result.getString("nome");
                usuario = new Usuario(nome,cpf,senha);
            }
            
            result.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados do Usuario: " +e.getMessage());
        }
        
        return usuario;
    }
    
    /**
     * Cadastra um novo usuário.
     * 
     * @param novoUsuario
     * @return [true] caso a operação tenha sido realizada com sucesso ou
     * [false] em caso de falha.
     */
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
