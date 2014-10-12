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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Histograma extends JPanel implements Observer{

    Datos d;
    boolean control=false;
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
        
        int cantN=0;
        Object objCant = JOptionPane.showInputDialog("Ingrese cantidad de números a ordenar");
        cantN=Integer.parseInt(objCant.toString());
        
        JLabel jlbtitulo = new JLabel("MÉTODOS DE ORDENAMIENTO");
        coord.gridx=0;coord.gridy=0;coord.gridwidth=60;coord.gridheight=20;
        f.add(jlbtitulo,coord);
        
        final Datos d= new Datos(cantN);
        Histograma h = new Histograma(d);
        d.addObserver(h);                          
        coord.gridx=0;coord.gridy=20;coord.gridwidth=60;coord.gridheight=50;
        f.add(h,coord); 
        
        JLabel jlbmensaje = new JLabel("Seleccione el método deseado: ");
        coord.gridx=0;coord.gridy=70;coord.gridwidth=20;coord.gridheight=10;
        f.add(jlbmensaje,coord);
        JComboBox<String> cmbMetodos = new JComboBox<>(new String[]{"...","BubbleSort","QuickSort","ShellSort"});
        coord.gridx=20;coord.gridy=70;coord.gridwidth=20;coord.gridheight=10;
        f.add(cmbMetodos,coord);
        JButton btnOrdenar = new JButton("ORDENAR");
        coord.gridx=40;coord.gridy=70;coord.gridwidth=20;coord.gridheight=10;
        f.add(btnOrdenar,coord);
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {f.setVisible(true);}
        });
        t.start();
        
        final Thread orden = new Thread(new Runnable(){
        @Override
        public void run(){d.ordenarDatosBubble()
            ;}
        });             
        
        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orden.start();
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
