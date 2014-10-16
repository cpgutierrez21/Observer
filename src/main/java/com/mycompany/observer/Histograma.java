/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.observer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Histograma extends JPanel implements Observer{

    Datos d;
    //boolean control=false;
    static BCommand burbuja;
    static SCommand shell;
    static QCommand quick;
    public Histograma(Datos d) {
       this.d=d;
       this.setSize(new Dimension(400, 400));
       this.setMinimumSize(new Dimension(400, 400));      
       this.setPreferredSize(new Dimension(400, 400));
    }
    
    @Override
    protected void paintComponent(Graphics g){
       super.paintComponent(g);              
        String texto;
        int xi=10;
        int i=0;
        for(int y:d.getX()){
            if(i%2==0)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawLine(xi,10, xi,10+y);
            xi+=5;i++;
            g.setColor(Color.blue);
            texto=Arrays.toString(d.getX());
            int j=250;int aux=texto.length()/5;int t=0; int k=1;
            while (k<=5){
             g.drawString(texto.substring(t,aux), 0, j); 
             t=aux; k++; j+=15; aux+=(texto.length()/5);
            }
        }
    }
    
       
    public static void main(String[] args) {
        final JFrame f= new JFrame("Histograma");       
        f.setLayout(new GridBagLayout());
        GridBagConstraints coord = new GridBagConstraints();        
        
        int cantN;
        Object objCant = JOptionPane.showInputDialog("Ingrese cantidad de números a ordenar");
        cantN=Integer.parseInt(objCant.toString());
        
        JLabel jlbtitulo = new JLabel("MÉTODOS DE ORDENAMIENTO");
        coord.gridx=0;coord.gridy=0;coord.gridwidth=60;coord.gridheight=20;
        f.add(jlbtitulo,coord);
        
        final Datos d= new Datos(cantN);
        final Histograma h = new Histograma(d);
        final Invoker in = new Invoker();
                                        
        coord.gridx=0;coord.gridy=20;coord.gridwidth=60;coord.gridheight=50;
        f.add(h,coord); 
        
        JLabel jlbmensaje = new JLabel("Seleccione el método deseado: ");
        coord.gridx=0;coord.gridy=70;coord.gridwidth=60;coord.gridheight=10;
        f.add(jlbmensaje,coord);        
        JButton btnOrdenarB = new JButton("BubbleSort");
        coord.gridx=0;coord.gridy=80;coord.gridwidth=30;coord.gridheight=10;        
        f.add(btnOrdenarB,coord);
        JButton btnOrdenarQ = new JButton("QuickSort");
        coord.gridx=30;coord.gridy=80;coord.gridwidth=30;coord.gridheight=10;        
        f.add(btnOrdenarQ,coord);
        JButton btnOrdenarS = new JButton("ShellSort");
        coord.gridx=60;coord.gridy=80;coord.gridwidth=30;coord.gridheight=10;        
        f.add(btnOrdenarS,coord);
        
        burbuja = new BCommand(d.getX());
        shell = new SCommand(d.getX());
        quick = new QCommand(d.getX());
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
               
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {f.setVisible(true);}
        });
        t.start();
        
        final Thread ordenB = new Thread(new Runnable(){
        @Override
        public void run(){       
           in.setCommand(burbuja);
           burbuja.addObserver(h);
           in.Ordenar();
        }}); 
        
        final Thread ordenQ = new Thread(new Runnable(){
        @Override
        public void run(){           
            in.setCommand(quick);
            quick.addObserver(h);
            in.Ordenar();
                    }
        });
        
        final Thread ordenS = new Thread(new Runnable(){
        @Override
        public void run(){         
            in.setCommand(shell);
            shell.addObserver(h);
            in.Ordenar();
                    }
        });
        
        btnOrdenarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenB.start();
            }
        });
        btnOrdenarQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenQ.start();
            }
        });        
        btnOrdenarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenS.start();
            }
        });
        
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
