/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities.validation;

/**
 *
 * @author ots
 */
public class ViewError {
    public boolean hasErro;
    public String erroMensagem;
    
    public ViewError(boolean hasErro, String erroMensagem) {
        this.hasErro = hasErro;
        this.erroMensagem = erroMensagem;
    }
    
}
