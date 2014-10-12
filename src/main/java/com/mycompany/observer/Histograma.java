/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author f212
 */

public class Histograma extends JPanel implements Observer{

    Datos d;
    public Histograma(Datos d) {
       //super.setSize(400, 400); //To change body of generated methods, choose Tools | Templates.
       this.d=d;
       this.setSize(new Dimension(400, 400));
       this.setMinimumSize(new Dimension(400, 400));      
       this.setPreferredSize(new Dimension(400, 400));
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
       boolean control=false;
        int xi=10;
        int i=0;
        for(int y:d.getX()){
            if(i%2==0)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawLine(xi,10, xi,10+y);
            xi+=5;
            i++;
            g.setColor(Color.blue);            
            g.drawString(Arrays.toString(d.getX()), 0, 350);
        }
    }
    
    public static void main(String[] args) {
        final JFrame f= new JFrame("Histograma");        
        final Datos d= new Datos();
        Histograma h = new Histograma(d);
        d.addObserver(h);
        f.getContentPane().add(h);
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                   f.setVisible(true);
            }
        });
        t.start();
        
        Thread orden = new Thread(new Runnable(){
        @Override
        public void run(){d.ordenarDatos();}
        });
        orden.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Histograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
     public void update(Observable o, Object arg) {
        this.updateUI();
    }
}
