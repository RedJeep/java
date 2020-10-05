package employee;


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
    static JTable table;
    static JTable table1;
    static JTable table2;
    static JTable table3;
    static String filepath;
    static String number;
  
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
    
 
    
    public static void main(String[] args){
          JLabel inputlabel= new JLabel("Enter Salary value");
    JFrame frame = new JFrame("Employee Data");
    frame.setSize(1800,1800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   JLabel label= new JLabel("Enter Path with FileName");
   JTextField field1 =new JTextField(20);
   JTextField field2 =new JTextField(20);
   field1.setPreferredSize(new Dimension());
   JTabbedPane tabbedPane = new JTabbedPane();
   JPanel panelOne= new JPanel(new BorderLayout());
   JPanel panelTwo= new JPanel(new BorderLayout());
   JPanel panelThird=new JPanel(new BorderLayout());
   JPanel Panelfour=new JPanel();
   JPanel mainPanel1 = new JPanel(new BorderLayout());
   JPanel mainPanel2 = new JPanel(new BorderLayout());
   JPanel mainPanel3 = new JPanel(new BorderLayout());
   JPanel mainPanel4 = new JPanel();
    JButton Submit=new JButton("Submit");
    JButton OneB=new JButton("B1");
    JButton TwoB=new JButton("B2");
    JButton ThirdB=new JButton("B3");
    JButton Sumbit2= new JButton("Submit");
   panelOne.add(OneB,BorderLayout.PAGE_END);
   panelOne.add(field1,BorderLayout.WEST);
   panelOne.add(Submit,BorderLayout.AFTER_LINE_ENDS);
   mainPanel1.add(panelOne,BorderLayout.LINE_END);
   mainPanel1.add(label,BorderLayout.WEST);
   mainPanel2.add(panelTwo,BorderLayout.LINE_END);
   panelTwo.add(TwoB,BorderLayout.PAGE_END);
   panelThird.add(ThirdB,BorderLayout.PAGE_END);
   mainPanel3.add(panelThird,BorderLayout.LINE_END);
   
   
   
   
   
   
   
   
   
   
   
   
  
   Sumbit2.addActionListener(new ActionListener(){
    @Override 
     public void actionPerformed(ActionEvent e){
        
         number=field2.getText().toString();
        
         System.out.println(number);
        
        
        
         
     
     
     
     
     
     
     
     
     
     
     
     
     
     }
   });
   
   
   
   
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
  OneB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            System.out.println("button1 ");
                try {
                    EmployeeForm.saveTable();
                } catch (Exception ex) {
                    Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }           
   });
   
      
  
   // tab1 setup 
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();
     DefaultTableModel dtm3 = new DefaultTableModel();
    //dtm.
      
                String hostname="root";
                String password="654321";
                String dbURL = "jdbc:mysql://localhost:3306/employees?autoReconnect=true&useSSL=false";
      Connection conn = null;
      Connection conn1 = null;
      Connection conn2 = null;
      Statement stmt = null; 
      Statement stmt1=null;
      Statement stmt3=null;
    /// code for Table1 
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
  
    // code for Table#2 
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
    
       
    // table3 
    
    
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
    
    
   
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      
    tabbedPane.add("Roles",mainPanel1);
    tabbedPane.add("WorkerData",mainPanel2);
    tabbedPane.add("SalaryINFO",mainPanel3);
    tabbedPane.add("TopSal",mainPanel4);
    frame.add(tabbedPane);
    frame.setVisible(true);
  
        
    
    
    }
    
}
    


  
  
  

