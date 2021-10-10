/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Java And Web Design CS 3913
 * @author Jeffrey Garip
 * NetID: JWG327
 * N#: 18881932
 * Note: to run program please run server first then Client.
 * 
 * 
 * 
 * 
 */


public class Client {
   //member variables long list... 
    static String addr = ""; 
    static String name = null;
    static String token = "";
    static JFrame frame;
    static JPanel jpanel1;
    static JPanel jpanel2;
    static JPanel jpanel3;
    static JButton jbutton;
    static JTextField jtext1;
    static JLabel jlabel1;
    static JTextField jtext2;
    static JLabel jlabel2;
    static JTextField jtext3;
    static JTextArea jtext4;
    static JLabel jlabel3;
    static JLabel jlabel4;
    static JScrollPane scroller ;
    
        
static class IPaddress implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent ae){
        JTextField javatext = (JTextField) ae.getSource();
        addr = javatext.getText();
       javatext.setEditable(false);
        
    }
}

static class Name implements ActionListener{
    PrintWriter out;
    Name(PrintWriter newOut){out = newOut;}
    @Override
    public void actionPerformed(ActionEvent ae){
        JTextField jtext = (JTextField) ae.getSource();
        name = jtext.getText();
        out.println(name);
        jtext3.setEditable(true);
        jtext.setEditable(false);
        
    }
}

static class Message implements ActionListener{
  
    PrintWriter out;
   
    Message(PrintWriter newOut){out = newOut;}
    @Override
    public void actionPerformed(ActionEvent ae){
        String line = jtext3.getText();
        out.println(line);
        jtext3.setText("");
    
    }   
}

static class ReadInput extends Thread{
Socket s;
ReadInput(Socket newS){s = newS;}
synchronized public void run(){
    Scanner sin;
    token = jtext4.getText();
    try {
        sin = new Scanner(s.getInputStream());
        while(!s.isClosed()){
            
            while(sin.hasNext()){
                token =token+"\n"+sin.nextLine();
                jtext4.setText(token);
            }
        }
        
    } catch (IOException ex) {
      //System.out.println("EXIT");
    }
    
 }
}
public  static void UIbuilder(){

frame = new JFrame("ChatApp");
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel3 = new JPanel();
        jpanel1.setBackground(Color.BLUE);
        jpanel2.setBackground(Color.red);
        jpanel3.setBackground(Color.white);
        jpanel3.setBorder(BorderFactory.createLineBorder (Color.BLUE));
        jlabel4 = new JLabel("");
        jlabel4.setSize(500, 500);
        jlabel1 = new JLabel("IP Address");
        jlabel2 = new JLabel("User Name");
        jlabel3 = new JLabel("Enter Message:");
        jbutton = new JButton("SEND");
        jbutton.setBackground(Color.white);
        jtext1 = new JTextField("");
        jbutton.setBackground(Color.white);
        jtext4 = new JTextArea("Welcome to the Chat  "+"type credentials and press enter\n");
        jtext4.setBackground(Color.white);
        jtext4.setColumns(30);
        jtext4.setAlignmentY(400);
        jtext4.setEditable(false);
        jtext1.addActionListener(new IPaddress());
        jtext1.setColumns(10);
        jtext1.setToolTipText("Enter IP address: ");
        jtext2 = new JTextField("");
        jtext2.setToolTipText("Enter your username");
        jtext2.setColumns(10);
        jtext3 = new JTextField("");
        jtext3.setToolTipText("Enter message");
        jtext3.setColumns(20);
        jtext3.setEditable(false);
        jpanel3.add(jtext4, BorderLayout.CENTER);
        jpanel2.add(jlabel3, BorderLayout.LINE_START);
        jpanel2.add(jtext3, BorderLayout.PAGE_START);
        jpanel2.add(jbutton, BorderLayout.EAST);
        jpanel1.add(jlabel1, BorderLayout.PAGE_START);
        jpanel1.add(jtext1, BorderLayout.PAGE_START);
        jpanel1.add(jlabel2, BorderLayout.PAGE_START);
        jpanel1.add(jtext2, BorderLayout.PAGE_END);
        jpanel3.add(jlabel4, BorderLayout.PAGE_START);
       
        scroller = new JScrollPane (jtext4);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jpanel2, BorderLayout.SOUTH);
        frame.add(jpanel3, BorderLayout.CENTER);
        frame.add(jpanel1, BorderLayout.NORTH);
        frame.add(scroller);
        frame.setVisible(true);
        
  
        
       // System.out.println("Operational");
        try{
            Socket s = new Socket(addr,5190);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            jtext2.addActionListener(new Name(out));
            jbutton.addActionListener(new Message(out));
            jtext3.addActionListener(new Message(out));
            new ReadInput(s).start();
          
        }
        catch(IOException e){
            //System.out.println("Connection has Failed");
        }
        
    }




    
    public static void main(String[] args) 
    {
      UIbuilder();     
    
    }
}
    
    
    
    
    
    
    































