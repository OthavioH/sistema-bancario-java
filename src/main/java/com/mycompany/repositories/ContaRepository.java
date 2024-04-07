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
import com.mycompany.entities.Transferencia;
import com.mycompany.entities.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Realiza operações no Banco de Dados referente às Contas.
 * 
 * @author ots
 */
public class ContaRepository {
    ConexaoDB db;
    
    /**
     * Construtor da classe ContaRepository.
     */
    public ContaRepository () {
        db = new ConexaoDB();
    }
    
    /**
     * Cria uma nova conta para o usuário dependendo do tipo de conta requisitada.
     * @param usuario
     * @param tipoConta
     * @return [true] caso a operação tenha sido realizada com sucesso ou
     * [false] em caso de falha.
     */
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

    /**
     * Retorna a conta corrente vinculada ao CPF requisitado.
     * 
     * @param cpf
     * @return [ContaCorrente] ou [null] em caso de falha.
     * @throws SQLException 
     */
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
    
    /**
     * Retorna a conta poupança vinculada ao CPF requisitado.
     * 
     * @param cpf
     * @return [ContaPoupanca] ou [null] em caso de falha.
     * @throws SQLException 
     */
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
    
    /**
     * Retorna os detalhes dos empréstimos da conta.
     * 
     * @param contaId
     * @return [Emprestimo] ou [null] em caso de falha.
     * @throws SQLException 
     */    
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

    /**
     * Cria uma nova conta corrente.
     * 
     * @param contaId
     * @param userCpf
     * @throws SQLException 
     */
    private void criarNovaContaCorrente(int contaId, Long userCpf) throws SQLException{
        PreparedStatement query = db.prepararQuery("INSERT INTO ContaCorrente (conta_id,conta_usuario_cpf) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        
        query.setInt(1,contaId);
        query.setLong(2, userCpf);
        
        query.executeUpdate();
        
    }
    
    /**
     * Cria uma nova conta poupança.
     * 
     * @param contaId
     * @param userCpf
     * @throws SQLException 
     */
    private void criarNovaContaPoupanca(int contaId, Long userCpf) throws SQLException{
        PreparedStatement query = db.prepararQuery("INSERT INTO ContaPoupanca (conta_id,conta_usuario_cpf) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        
        query.setInt(1,contaId);
        query.setLong(2, userCpf);
        
        query.executeUpdate();
    }

    /**
     * Busca uma conta no Banco de Dados pelo ID.
     * 
     * @param contaId
     * @return [Conta] ou [null] caso não haja uma conta com esse ID.
     * @throws SQLException 
     */
    public Conta getUsuarioContaById(int contaId) throws SQLException{
        Conta conta = null;
        PreparedStatement query = db.prepararQuery("SELECT * FROM Conta where id = ? LIMIT 1");
        
        query.setInt(1,contaId);
        
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            conta = new Conta();
            conta.setId(contaId);
            conta.setSaldo(results.getDouble("saldo"));
            conta.setTipoConta(results.getInt("tipo_conta"));
        }
        
        return conta;
    }
    
    /**
     * Retorna todas as contas de um usuário
     * 
     * @param cpf
     * @return Lista de contas do usuário.
     * @throws SQLException 
     */
    public List<Conta> getUsuarioContas(Long cpf) throws SQLException{
        List<Conta> listaConta = new ArrayList<>();
        PreparedStatement query = db.prepararQuery("SELECT * FROM Conta where usuario_cpf = ?");
        
        query.setLong(1,cpf);
        
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            Conta conta = new Conta();
            conta.setId(results.getInt("id"));
            conta.setSaldo(results.getDouble("saldo"));
            conta.setTipoConta(results.getInt("tipo_conta"));
            
            listaConta.add(conta);
        }
        
        return listaConta;
    }
    
    /**
     * Cadastrar uma nova transferência no banco
     * 
     * @param transferencia
     * @return [true] ou [false] caso haja um erro.
     * @throws SQLException 
     */
    public boolean cadastrarTransferencia(Transferencia transferencia) throws SQLException {
        PreparedStatement query = db.prepararQuery("INSERT INTO transferencia (data_transferencia, valor, conta_remetente, conta_destinatario) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        var dataTransfrencia = new java.sql.Date(transferencia.getData().getTime());
        System.out.println(dataTransfrencia.toString());
        query.setDate(1, dataTransfrencia);
        query.setDouble(2, transferencia.getValor());
        query.setInt(3, transferencia.getRemetente().getId());
        query.setInt(4, transferencia.getDestinatario().getId());
        
        int affectedRows = query.executeUpdate();
        
        return affectedRows > 0;
    }
    
    /**
     * Muda o saldo de uma conta.
     * 
     * @param contaId
     * @param valor
     * @return [true] ou [false] caso haja um erro.
     * @throws SQLException 
     */
    public boolean mudarContaSaldo(int contaId, double valor) throws SQLException {
        PreparedStatement query = db.prepararQuery("UPDATE conta SET saldo = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        query.setDouble(1, valor);
        query.setInt(2, contaId);
        
        int affectedRows = query.executeUpdate();
        
        return affectedRows > 0;
    }
    
    /**
     * Retorna todas as transferências de uma conta.
     * 
     * @param contaId
     * @return Lista de transferências da conta.
     * @throws SQLException 
     */
    public List<Transferencia> getContaTransferencias(int contaId) throws SQLException {
        List<Transferencia> listaTransferencias = new ArrayList<>();
        PreparedStatement query = db.prepararQuery("SELECT * FROM Transferencia where conta_remetente = ? OR conta_destinatario = ? ORDER BY data_transferencia desc");
        
        query.setInt(1,contaId);
        query.setInt(2,contaId);
        
        query.execute();
        
        ResultSet results = query.getResultSet();
        
        while(results.next()) {
            var transferencia = new Transferencia();
            transferencia.setData(results.getDate("data_transferencia"));
            
            var contaRemetente = new Conta();
            contaRemetente.setId(results.getInt("conta_remetente"));
            transferencia.setRemetente(contaRemetente);
            
            var contaDestinatario = new Conta();
            contaDestinatario.setId(results.getInt("conta_remetente"));
            
            transferencia.setDestinatario(contaDestinatario);
            transferencia.setValor(results.getDouble("valor"));
            
            
            listaTransferencias.add(transferencia);
        }
        
        return listaTransferencias;
    }
    
}
