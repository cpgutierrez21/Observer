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
   /** public void ordenarDatosBubble(){
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
    
    public void ordenarDatosQuick(int lowerIndex, int higherIndex){
      /* Boolean b=true;
       int i = lowerIndex;
       int j = higherIndex;
       int pivot = x[lowerIndex+(higherIndex-lowerIndex)/2];
       while (i <= j) {
       while (x[i] > pivot) {
                i++;
        }
       while (x[j] < pivot) {j--;}
       if (i <= j) {
                exchangeNumbers(i, j);
                this.setChanged();
                this.notifyObservers(j);
                i++; j--;
            }
       synchronized(b){
          try {
                    b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    } }
       }
        if (lowerIndex < j)
            ordenarDatosQuick(lowerIndex, j);
        if (i < higherIndex)
            ordenarDatosQuick(i, higherIndex);
    }
    
    private void exchangeNumbers(int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
    
    public void ordenarDatosShell(){
      /* Boolean b=true;
       int increment = x.length / 2;
	while (increment > 0) {
		for (int i = increment; i < x.length; i++) {
			int j = i;
			int temp = x[i];
			while (j >= increment && x[j - increment] < temp) {
				x[j] = x[j - increment];
				j = j - increment;
			}
			x[j] = temp;
                       this.setChanged();
                       this.notifyObservers(temp); 
		}                
		if (increment == 2) {
			increment = 1;
		} else {
			increment *= (5.0 / 11);
		}
                synchronized (b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }}
	}
    }**/
   
}
