/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f212
 */
public class Datos extends Observable {
    int x[];

    public Datos(int n){
    x=new int[n];
        Random r = new Random((System.currentTimeMillis()));
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(200);            
        }      
    }
    public void ordenarDatosBubble(){
        Boolean b=true;
        for (int i = 0; i < x.length; i++) {
            for (int j=0;j<x.length-1;j++){
                if(x[j]<x[j+1]){
                int temp=x[j];
                x[j]=x[j+1];
                x[j+1]=temp;
                this.setChanged();
                this.notifyObservers(temp);
                }
                synchronized (b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }
    }
    public int[] getX() {
        return x;
    }
}
