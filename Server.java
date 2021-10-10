/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4;
import java.io.*;
import java.net.*;
import java.util.*;

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

public class Server {
    
    static ArrayList<Socket> cbox =new ArrayList<>();
    public static void main(String[] args) {
        Clients cl = new Clients(cbox);
         try{
            ServerSocket ss = new ServerSocket(5190);
            //System.out.println(ss.getLocalSocketAddress());
            while(true){
                Socket sock = ss.accept();
                //System.out.println(sock.getInetAddress().getHostAddress()+"online");
                cbox.add(sock);
                new ClientHandle(sock,cl).start();
            }
        }
        catch(IOException e){
            //System.out.println("IOException!");
        }
    }
}
    
class Clients{
        static ArrayList<Socket> cbox;
        Clients(ArrayList<Socket> curr){
            cbox = curr;
        }
       
        public void write(String name,String message) throws IOException{
            PrintStream ps;
            for(Socket x:cbox){
                ps = new PrintStream(x.getOutputStream());
                ps.print(name+": "+message+"\n");
            }
    }
        
}

class ClientHandle extends Thread{
    Clients clientlist;
    Socket sock;
    String name;
    ClientHandle(Socket aSock, Clients cbox){
        sock=aSock;
        name = "";
        clientlist = cbox;
    }
    
    public void run(){
        try{
            Scanner sin = new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
                
            while (!sock.isClosed()){
                String line = sin.nextLine();
                if(name.equals("")){
                    name = line;
                }
                else{
                    clientlist.write(name,line);
                }
                
                if (line.equalsIgnoreCase("EXIT"))
                    sock.close();
            }
        }
        catch(IOException e){}
        //System.out.println(sock.getInetAddress().getHostAddress()+"not_online");
        
    }
}




