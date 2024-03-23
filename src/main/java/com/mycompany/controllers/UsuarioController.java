/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Usuario;
import com.mycompany.entities.validation.ViewError;
import com.mycompany.repositories.UsuarioRepository;

/**
 *
 * @author othavio
 */
public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    
    public UsuarioController(){
        this.usuarioRepository = new UsuarioRepository();
    }
    
    public ViewError logarUsuario(Long cpf, String senha) {
        Usuario usuario = this.usuarioRepository.getUsuarioLogin(cpf, senha);
        
        if (usuario == null) return new ViewError(true, "Usuário ou senha incorretos");
        
        return new ViewError(false, "");
    }
    
    public ViewError validarCamposConfirmarSenha(char[] senha, char[] confirmarSenha) {
        
        if(senha.length < 1 || confirmarSenha.length < 1) return new ViewError(true, "As senhas não podem ser vazias");
        
        if (!senha.equals(confirmarSenha)) return new ViewError(true, "Ase senhas não são compatíveis");
        
        return new ViewError(false, "");
    }
    
    public ViewError validarCampoNome(String nome) {
        
        if(nome.length() < 1) return new ViewError(true, "Você deve inserir um nome");
        
        return new ViewError(false, "");
    }
    
    public ViewError validarCampoCpf(String campoCpf) {
        String cpfInvalidoMensagem = "CPF Inválido";
        
        if (campoCpf.isEmpty() | campoCpf.length() != 11) return new ViewError(true, cpfInvalidoMensagem);
        
        return new ViewError(false, "");
    }

    public ViewError validarCampoSenha(char[] senha) {
        
        if(senha.length < 1) return new ViewError(true, "A senha deve ter pelo menos 1 caracter");
        
        return new ViewError(false, "");
    }
}
