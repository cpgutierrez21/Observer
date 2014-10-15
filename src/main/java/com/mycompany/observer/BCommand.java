/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author c1026268
 */
public class BCommand extends Observable implements ICommand{
    int[] x;
    public BCommand (int[] x){
       this.x=x;
       
    }
    @Override
    public void Ejecutar() {
        Boolean b=true;
        for (int i = 0; i < x.length; i++) {
            for (int j=0;j<x.length-1;j++){
                if(x[j]<x[j+1]){
                exchangeNumbers(j, j+1);
                this.setChanged();
                this.notifyObservers(j);
                }
                synchronized (b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }}} }
    }
     private void exchangeNumbers(int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
    
}
