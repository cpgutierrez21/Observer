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
public class QCommand extends Observable implements ICommand {
int[] x;
    public QCommand (int[]x){
        this.x=x;
    }
    @Override
    public void Ejecutar() {
        Boolean b=true;
       int lowerIndex = 0;
       int higherIndex = x.length-1;
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
            higherIndex=j;
            Ejecutar();
        if (i < higherIndex)
            lowerIndex=i;
            Ejecutar();
    }
    private void exchangeNumbers(int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
}
