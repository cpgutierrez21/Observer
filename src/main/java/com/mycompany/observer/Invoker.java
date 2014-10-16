/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

/**
 *
 * @author c1026268
 */
public class Invoker {
    private ICommand Command;
    
    public void setCommand(ICommand c){
    this.Command=c;
    }
    public void Ordenar(){
    Command.Ejecutar();
    }
}
