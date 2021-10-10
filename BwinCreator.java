/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahw2;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import static javahw2.BwinCreator.Jbarr;
import javax.swing.*;
/**
 *
 * @author jeff garip
 */


public class BwinCreator 
{
    static JButton[] Jbarr;
    int size;
    
 BwinCreator(int asize)
 {
  size=asize;
  Jbarr=new JButton[size];
  JFrame jf = new JFrame("Java Hw 2 Jeffrey Garip");
  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  jf.setSize(1800,1600);
  
  JPanel jp = new JPanel(new BorderLayout());
  jp.setLayout(new GridLayout(4,2,4,2));
  for(int i=0; i<size;i++)
  {
 // String val=String.valueOf(i);
  Random rnd = new Random();
  JButton ba=new JButton();
  Color newcolor= new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));

  ba.setBackground(newcolor);
  ba.addActionListener(new ButtonListener());
  Jbarr[i]=ba;
  jp.add(ba);
  }
  jf.add(jp);
  jf.setVisible(true);
 }
  
 }


class ButtonListener implements ActionListener{
  @Override
    public void actionPerformed(ActionEvent e) 
    {
       JButton[] j=Jbarr;
       JButton javab = (JButton) e.getSource();
       for(int i=0; i<j.length;i++){
           if(javab.equals(j[i])){}
           else{
             Random rnd = new Random();
            Color newcolor= new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
            j[i].setBackground(newcolor);
           
           }
  
           
       }
        
    }
    
 }
 




class Javahw2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
       BwinCreator B= new BwinCreator(8);
        
        
    }
    
}
