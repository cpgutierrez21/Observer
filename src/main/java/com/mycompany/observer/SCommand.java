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
public class SCommand extends Observable implements ICommand{
int[] x;
    public SCommand (int[]x){
        this.x=x;
    }
    @Override
    public void Ejecutar() {
      Boolean b=true;
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
    }
    
}
