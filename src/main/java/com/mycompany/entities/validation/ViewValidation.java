/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities.validation;

/**
 * Classe utilizada para determinar se uma ação retorna um Erro ou
 * um sucesso de tipo T.
 * 
 * Qualquer tipo pode ser passado para esta classe, onde T é o tipo do sucesso.
 * 
 * @author ots
 * 
 * @param <T> determina o tipo do sucesso.
 */
public class ViewValidation<T>{
    public boolean hasErro;
    public String erroMensagem;
    public T successValue;
    
    /**
     * Construtor da classe [ViewValidation]
     * 
     * @param hasErro
     * @param erroMensagem 
     */
    public ViewValidation(boolean hasErro, String erroMensagem) {
        this.hasErro = hasErro;
        this.erroMensagem = erroMensagem;
    }
    
    /**
     * Construtor da classe [ViewValidation]
     * 
     * @param success 
     */
    public ViewValidation(T success) {
        this.successValue = success;
        this.hasErro = false;
    }
    
}
