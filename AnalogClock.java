/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.pkg5;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author jg
 * 
 * 
 */

class DrawPoint{
    int x;
    int y;
    DrawPoint(int myx, int myy){x = myx; y = myy;}
    DrawPoint(){this(0,0);}
}



public class AnalogClock {
    static int seconds;
    static int minute;
    static int hour;
    public  static String TimeString; 
    public final static String NIST_SERVER="time-b-g.nist.gov";
    public final static int port=13;
    public static JFrame jf;
    public static Calendar clockCal= Calendar.getInstance();
    public static void main(String[] args) {
        JFrame jf = new JFrame("Jeffs Clock");
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TP cp = new TP();
        jf.add(cp);
        GetServerTime();
        new Thread(){
            public void run(){
                while (true){
                    if(seconds%60==0) {
                        GetServerTime();
                    }
                    seconds+=1;
                    cp.repaint();
                    try { sleep(1000); } 
                    catch (InterruptedException ex) { }          
                }
            }
        }.start();
        jf.setVisible(true);
    }
    
    private static void GetServerTime() {
        try {
            Socket dataSocket=new Socket(NIST_SERVER,port);
            //System.out.println(dataSocket.isConnected());
            Scanner sin = new Scanner(dataSocket.getInputStream());
            while (sin.hasNext()){TimeString= sin.nextLine();}
            //System.out.println(TimeString);
            String Timenfo[]= TimeString.split(" ");
            String Datenfo[]= Timenfo[1].split("-");
            String TimeData[]=Timenfo[2].split(":");  
           clockCal.set(Calendar.YEAR, 2000+ Integer.parseInt(Datenfo[0]));
           clockCal.set(Calendar.MONTH, Integer.parseInt(Datenfo[1]));
           clockCal.set(Calendar.DATE, Integer.parseInt(Datenfo[2]));
           clockCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(TimeData[0])-4);
           clockCal.set(Calendar.MINUTE, Integer.parseInt(TimeData[1]));
           clockCal.set(Calendar.SECOND, Integer.parseInt(TimeData[2]));
           //System.out.println("Hour:"+clockCal.get(Calendar.HOUR_OF_DAY));
           //System.out.println("Min:"+clockCal.get(Calendar.MINUTE));
           //System.out.println("secondsond:"+clockCal.get(Calendar.SECOND)); 
           hour = clockCal.get(Calendar.HOUR_OF_DAY);
           minute = clockCal.get(Calendar.MINUTE);
           seconds = clockCal.get(Calendar.SECOND);
           
           
        }
       catch (IOException e) { System.out.println("BIG TIME ERROR:" + e.toString()); }
        
    }
    
}
class TP extends JPanel{
    int rad;
    TP(){
        super();
        AnalogClock.seconds=0;
    }
    DrawPoint calcSecpos(){
        DrawPoint p = new DrawPoint();
        p.x = (int)(Math.sin(Math.toRadians(AnalogClock.seconds)*6)*rad);
        p.y = (int)(Math.cos(Math.toRadians(AnalogClock.seconds)*6)*rad);
        return p;
    }
    DrawPoint calcMinpos(){
        DrawPoint p = new DrawPoint();
        p.x = (int)(Math.sin(Math.toRadians(AnalogClock.minute*6+AnalogClock.seconds/10))*rad);
        p.y = (int)(Math.cos(Math.toRadians(AnalogClock.minute*6+AnalogClock.seconds/10))*rad);
        return p;
    }
    
    DrawPoint calcHrpos(){
        DrawPoint p = new DrawPoint();
        p.x = (int)(Math.sin(Math.toRadians((AnalogClock.hour%12) * 30+AnalogClock.minute/2)) * rad);
        p.y = (int)(Math.cos(Math.toRadians((AnalogClock.hour%12) * 30+AnalogClock.minute/2)) * rad);
        return p;
    }
    
    public void paintComponent(Graphics g){
        int height = this.getSize().height;
        int width = this.getSize().width;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        rad = height/2;
        int xcent = width/2;
        int ycent = height/2;
        int startX = width/2;
        g.setColor(Color.DARK_GRAY);
        g.fillOval(xcent-rad, ycent-rad, rad*2, rad*2);
        g.drawString("9",  xcent- 145, ycent);  
        g.drawString("3", xcent + 135, ycent);  
        g.drawString("12", xcent - 10, ycent - 130);  
        g.drawString("6", xcent - 10, ycent+ 145);
        DrawPoint p = calcSecpos();
        DrawPoint p2=calcMinpos();
        DrawPoint p3=calcHrpos();
        g.setColor(Color.BLUE);
        g.drawLine(xcent, ycent, xcent + p.x, ycent - p.y);
        g.setColor(Color.GREEN);
        g.drawLine(xcent, ycent, xcent + p2.x, ycent - p2.y);
        g.setColor(Color.ORANGE);
        g.drawLine(xcent, ycent, xcent + p3.x, ycent - p3.y);        
    }
}
