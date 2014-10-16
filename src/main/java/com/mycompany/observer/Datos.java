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

    public Datos(int n){
    x=new int[n];
        Random r = new Random((System.currentTimeMillis()));
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(200);            
        }      
    }
    
     public int[] getX() {
        return x;
    }  
   
}
