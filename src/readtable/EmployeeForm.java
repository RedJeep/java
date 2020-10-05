package readtable;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.Statement;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmployeeForm
{
    static JLabel label= new JLabel("Enter Path with FileName");
    static JTable table;
    static JTable table1;
    static JTable table2;
    static JTable table3;
    static JTable table4;
    static String filepath;
    static String number="";
    static JTabbedPane tabbedPane = new JTabbedPane();
   static JFrame frame = new JFrame("Employee Data");
   static JLabel inputlabel= new JLabel("Enter Salary value:");
   static JPanel panelOne= new JPanel(new BorderLayout());
   static JPanel panelTwo= new JPanel(new BorderLayout());
   static JPanel panelThird=new JPanel(new BorderLayout());
   static JPanel Panelfour=new JPanel(new BorderLayout());
   static JPanel mainPanel1 = new JPanel(new BorderLayout());
   static JPanel mainPanel2 = new JPanel(new BorderLayout());
   static JPanel mainPanel3 = new JPanel(new BorderLayout());
   static JPanel mainPanel4 = new JPanel();
  static  JTextField field1 =new JTextField(20);
static JTextField field2 =new JTextField(20);
static JButton Submit=new JButton("Submit");
   static  JButton Sumbit2= new JButton("Submit");
   static  DefaultTableModel dtm = new DefaultTableModel();
   static  DefaultTableModel dtm1 = new DefaultTableModel();
   static DefaultTableModel dtm2 = new DefaultTableModel();
    static DefaultTableModel dtm3 = new DefaultTableModel();
     static String hostname="root";
     static String password="654321";
      static String dbURL = "jdbc:mysql://localhost:3306/employees?autoReconnect=true&useSSL=false";
      static Connection conn = null;
      static Connection conn1 = null;
      static Connection conn2 = null;
      static Statement stmt = null; 
      static Statement stmt1=null;
      static Statement stmt3=null;
      static JButton TwoB=new JButton("B2");
      static JButton ThirdB=new JButton("B3");
   
   
   
   
   
   public static void saveTable()throws Exception
{
   System.out.println("I'm in tablesave");
   BufferedWriter bfw = new BufferedWriter(new FileWriter(filepath));
  for(int i = 0 ; i < table.getColumnCount() ; i++)
  {
    bfw.write(table.getColumnName(i));
    bfw.write("\t");
  }

  for (int i = 0 ; i < table.getRowCount(); i++)
  {
    bfw.newLine();
    for(int j = 0 ; j < table.getColumnCount();j++)
    {
      bfw.write((String)(table.getValueAt(i,j)));
      bfw.write("\t");
    }
  }
  bfw.close();
}
    
    
public static void createPage1()
{
   panelOne.add(field1,BorderLayout.WEST);
   panelOne.add(Submit,BorderLayout.AFTER_LINE_ENDS);
   mainPanel1.add(panelOne,BorderLayout.LINE_END);
   mainPanel1.add(label,BorderLayout.WEST);
Submit.addActionListener(new ActionListener()
   {
   @Override 
   public void actionPerformed(ActionEvent e){
   String val=field1.getText().toString();
   filepath=val;
   System.out.println(filepath);
     try {
         //System.out.println("I HIt sumbit"); 
         saveTable();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   }
   });
      try{
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {System.out.println("THis is works");}
         ResultSet rs;
          stmt = conn.createStatement();
        rs = stmt.executeQuery("select * from Roles ORDER BY ROLE;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
       Vector<String> columnNames = new Vector<String>();
    
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(rsmd.getColumnName(column));
    }
    
     Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
   
    }
     dtm=new DefaultTableModel(data,columnNames){
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

     };
     table= new JTable(dtm);
        JScrollPane tableSP = new JScrollPane(table);
        tableSP.setPreferredSize(new Dimension(400,400));
        panelOne.add(tableSP);
        }   
        catch (ClassNotFoundException ex) {} 
                catch (SQLException ex) {}





   tabbedPane.add("Roles",mainPanel1);
      
  

   
   
   
   
  
}    
    
public static void createPage2()
{

    
   try{
        
       Class.forName("com.mysql.jdbc.Driver");
       conn1 = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {System.out.println();}
         ResultSet rs1;
          stmt1 = conn.createStatement();
        rs1 = stmt.executeQuery("select * from workerdata ORDER BY Name;");
        ResultSetMetaData rsmd1 = rs1.getMetaData();
        int columnCount1 = rsmd1.getColumnCount();
       Vector<String> columnNames1 = new Vector<String>();
    
    for (int column = 1; column <= columnCount1; column++) {
        columnNames1.add(rsmd1.getColumnName(column));
    }
    
     Vector<Vector<Object>> data1 = new Vector<Vector<Object>>();
    while (rs1.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount1; columnIndex++) {
            vector.add(rs1.getObject(columnIndex));
        }
        data1.add(vector);
   
    }
     dtm1=new DefaultTableModel(data1,columnNames1){
     
     

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

     
     
     };
     table1= new JTable(dtm1);
     JScrollPane tableSP = new JScrollPane(table1);
        tableSP.setPreferredSize(new Dimension(900, 900));
       
        table1.setEnabled(false);
        panelTwo.add(tableSP);
        
  }   
        catch (ClassNotFoundException ex) {} 
                catch (SQLException ex) {}
   panelTwo.add(TwoB,BorderLayout.PAGE_END);
   mainPanel2.add(panelTwo);
   tabbedPane.add("WorkerData",mainPanel2);
   
   
   
   
   
   
   
   
   
}        
    
   public static void createPage3()
   {
        try{
        
       Class.forName("com.mysql.jdbc.Driver");
       conn1 = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {System.out.println();}
         ResultSet rs1;
          stmt1 = conn.createStatement();
        rs1 = stmt.executeQuery("select * from SalNfo ORDER BY Name;");
        ResultSetMetaData rsmd1 = rs1.getMetaData();
        int columnCount1 = rsmd1.getColumnCount();
       Vector<String> columnNames1 = new Vector<String>();
    
    for (int column = 1; column <= columnCount1; column++) {
        columnNames1.add(rsmd1.getColumnName(column));
    }
    
     Vector<Vector<Object>> data1 = new Vector<Vector<Object>>();
    while (rs1.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount1; columnIndex++) {
            vector.add(rs1.getObject(columnIndex));
        }
        data1.add(vector);
   
    }
 
     dtm1=new DefaultTableModel(data1,columnNames1){
     
     

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

     
     
     };
     table2= new JTable(dtm1);
     
     JScrollPane tableSP = new JScrollPane(table2);
        tableSP.setPreferredSize(new Dimension(900, 900));
       
        table2.setEnabled(false);
        panelThird.add(tableSP);
        
        
        
        
        }   
        catch (ClassNotFoundException ex) {} 
                catch (SQLException ex) {}
    
    
   
       
       
       
       
       
       
       
       
       
       
       mainPanel3.add(panelThird,BorderLayout.LINE_END);
       tabbedPane.add("SalaryINFO",mainPanel3);

   
   
   }     
    
   
     

    
    
    
    
    
   
    
    
    
    
    
  
      
      
      
     
   
public static void createPage4()
{
Sumbit2.addActionListener(new ActionListener(){
    @Override 
     public void actionPerformed(ActionEvent e){
       if(e.getSource()==Sumbit2)
       {
           number=field2.getText().toString();
            System.out.println(number);
             try{
   
       Class.forName("com.mysql.jdbc.Driver");
       conn1 = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {System.out.println();}
         ResultSet rs1;
         String sql= "select s.id,s.salary,w.name, w.etype,date_format(w.birthday,'%Y-%m-%d') as birthday\n" +
"from workerdata w ,Salnfo s\n" +
"where s.id= w.id and w.etype='full time' and w.birthday between '1955-03-01'and'1969-02-27'and s.salary>"+number+"\n" +
"ORDER by s.id,w.birthday;";
   // String mysql= String.format(sql,number.toString());
    System.out.println(sql);
          stmt1 = conn.createStatement();
        rs1 = stmt.executeQuery(sql);
        ResultSetMetaData rsmd1 = rs1.getMetaData();
        int columnCount1 = rsmd1.getColumnCount();
       Vector<String> columnNames1 = new Vector<String>();
    
    for (int column = 1; column <= columnCount1; column++) {
        System.out.println(rsmd1.getColumnName(column));
        columnNames1.add(rsmd1.getColumnName(column));
    }
    
     Vector<Vector<Object>> data1 = new Vector<Vector<Object>>();
    while (rs1.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount1; columnIndex++) {
            vector.add(rs1.getObject(columnIndex));
        }
        data1.add(vector);
   
    }
 
     dtm3=new DefaultTableModel(data1,columnNames1){
     
     

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

     
     
     };
     table4= new JTable(dtm3);
     
     JScrollPane tableSP = new JScrollPane(table4);
        tableSP.setPreferredSize(new Dimension(900, 900));
       
        table4.setEnabled(false);
        Panelfour.add(tableSP);
        
        
        
        
        }   
        catch (ClassNotFoundException ex) {} 
                catch (SQLException ex) {}
    
       
       mainPanel4.add(Panelfour,BorderLayout.LINE_END);

            
            
            
            
        
           
        } 
       
          
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


     }
 
 
});
//Panelfour.add(inputlabel,BorderLayout.WEST);
mainPanel4.add(inputlabel,BorderLayout.WEST);  
Panelfour.add(field2,BorderLayout.WEST);
   Panelfour.add(Sumbit2,BorderLayout.AFTER_LINE_ENDS);
   mainPanel4.add(Panelfour,BorderLayout.LINE_END);
  //mainPanel4.add(inputlabel,BorderLayout.WEST);
 
    
       
       tabbedPane.add("TOPSAL",mainPanel4);
     

}      
   
    
 public static void createFrame(){
  
    frame.setSize(1800,1800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(tabbedPane);
    frame.setVisible(true);
}
    
    public static void main(String[] args){
   
   
   createFrame();
   createPage1();
   createPage2();
   createPage3();
   createPage4();
   
   
  
    }
    
}
    


  
  
  

