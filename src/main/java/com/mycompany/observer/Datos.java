/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author f212
 */
public class Datos extends Observable {
    int x[];

    public int[] getX() {
        return x;
    }
    public Datos(){
    x=new int[50];
        Random r = new Random();
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(100);            
        }
       this.ordenarDatos();
    }
    public void ordenarDatos(){
        for (int i = 0; i < x.length; i++) {
            for (int j=0;j<x.length-1;j++){
                if(x[j]<x[j+1]){
                int temp=x[j];
                x[j]=x[j+1];
                x[j+1]=temp;
                }
            }
            
        }
    }
}
