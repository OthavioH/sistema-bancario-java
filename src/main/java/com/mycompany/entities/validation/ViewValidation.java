/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities.validation;

/**
 *
 * @author ots
 * 
 * @param <T> determina o tipo do sucesso.
 */
public class ViewValidation<T>{
    public boolean hasErro;
    public String erroMensagem;
    public T successValue;
    
    public ViewValidation(boolean hasErro, String erroMensagem) {
        this.hasErro = hasErro;
        this.erroMensagem = erroMensagem;
    }
    
    public ViewValidation(T success) {
        this.successValue = success;
        this.hasErro = false;
    }
    
}
