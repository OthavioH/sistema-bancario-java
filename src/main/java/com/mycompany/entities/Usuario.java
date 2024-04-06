package com.mycompany.entities;

/**
 * Classe que contém implementações de um Usuario.
 * 
 * @author ots
 */
public class Usuario {
    private String nome, senha;
    private Long cpf;

    /**
     * Construtor da classe [Usuario]
     */
    public Usuario() {
    }

    /**
     * Construtor da classe [Usuario]
     * 
     * @param nome
     * @param cpf
     * @param senha 
     */
    public Usuario(String nome, Long cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
