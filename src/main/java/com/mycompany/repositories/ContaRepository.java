/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repositories;

import com.mycompany.data.ConexaoDB;
import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaCorrente;
import com.mycompany.entities.ContaPoupanca;
import com.mycompany.entities.Emprestimo;
import com.mycompany.entities.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ots
 */
public class ContaRepository {
    ConexaoDB db;
    
    public ContaRepository () {
        db = new ConexaoDB();
    }
    
    public boolean criarNovaConta(Usuario usuario, int tipoConta){
        try (PreparedStatement query = db.prepararQuery("INSERT INTO conta (Usuario_cpf, tipo_conta) values (?,?)", Statement.RETURN_GENERATED_KEYS)){
            
            query.setLong(1, usuario.getCpf());
            query.setInt(2, tipoConta);
            query.executeUpdate();
            
            
            ResultSet chavesGeradas = query.getGeneratedKeys();
            while(chavesGeradas.next()){
                int contaId = chavesGeradas.getInt(1);
                if (tipoConta == 0) criarNovaContaCorrente(contaId, usuario.getCpf());
                else criarNovaContaPoupanca(contaId, usuario.getCpf());
            }
            
            query.close();
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ContaCorrente getUsuarioContaCorrente(Long cpf) throws SQLException{
        ContaCorrente contaCorrente = null;
        String sqlQuery = "SELECT c.id, ccr.conta_usuario_cpf, ccr.limite, c.saldo from contacorrente ccr join conta c on ccr.conta_id = c.id where ccr.conta_usuario_cpf = ?";
        PreparedStatement query = db.prepararQuery(sqlQuery);
        
        query.setLong(1,cpf);
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            contaCorrente = new ContaCorrente();
            contaCorrente.setId(results.getInt("id"));
            contaCorrente.setSaldo(results.getDouble("saldo"));
            contaCorrente.setLimite(results.getInt("limite"));
        }
        
        return contaCorrente;
    }
    
    public ContaPoupanca getUsuarioContaPoupanca(Long cpf) throws SQLException{
        ContaPoupanca contaCorrente = null;
        String sqlQuery = "SELECT c.id, cp.conta_usuario_cpf, cp.rendimento, c.saldo from contapoupanca cp join conta c on cp.conta_id = c.id where cp.conta_usuario_cpf = ?";
        PreparedStatement query = db.prepararQuery(sqlQuery);
        
        query.setLong(1,cpf);
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            contaCorrente = new ContaPoupanca();
            contaCorrente.setId(results.getInt("id"));
            contaCorrente.setSaldo(results.getDouble("saldo"));
            contaCorrente.setRendimento(results.getInt("rendimento"));
        }
        
        return contaCorrente;
    }
    
    public Emprestimo getContaEmprestimo(int contaId) throws SQLException {
        Emprestimo contaCorrente = null;
        String sqlQuery = "SELECT emp.id, emp.saldo_disponivel from emprestimo emp join conta c on emp.conta_id = c.id where c.id = ?";
        PreparedStatement query = db.prepararQuery(sqlQuery);
        
        query.setInt(1,contaId);
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            contaCorrente = new Emprestimo();
            contaCorrente.setId(results.getInt("id"));
            contaCorrente.setSaldoDisponivel(results.getDouble("saldo_disponivel"));
        }
        
        return contaCorrente;
    }
    
    public boolean deletarConta(int contaId) throws SQLException{
        String sqlQuery = "DELETE FROM conta WHERE id = ?";
        PreparedStatement query = db.prepararQuery(sqlQuery);
        
        query.setInt(1,contaId);
        int rowsAffected = query.executeUpdate();
        if (rowsAffected <= 0) return false;
        return true;
    }

    private void criarNovaContaCorrente(int contaId, Long userCpf) throws SQLException{
        PreparedStatement query = db.prepararQuery("INSERT INTO ContaCorrente (conta_id,conta_usuario_cpf) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        
        query.setInt(1,contaId);
        query.setLong(2, userCpf);
        
        query.executeUpdate();
        
    }
    
    private void criarNovaContaPoupanca(int contaId, Long userCpf) throws SQLException{
        PreparedStatement query = db.prepararQuery("INSERT INTO ContaPoupanca (conta_id,conta_usuario_cpf) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        
        query.setInt(1,contaId);
        query.setLong(2, userCpf);
        
        query.executeUpdate();
    }

    
}
