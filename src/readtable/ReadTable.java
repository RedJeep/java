/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import  org.apache.poi.ss.usermodel.DateUtil;
import java.sql.*;
import java.util.Date;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
/**
 *
 * @author jg
 */
public class ReadTable {
    List myList = new ArrayList();
    private  static final String FILE_PATH="/Users/jg/NetBeansProjects/readTable/EMPLOYEE.xlsx";
    private  static final String FILE_PATH2="/Users/jg/NetBeansProjects/readTable/EMPLOYEE_002.xlsx";
    private static final String FILE_PATH3="/Users/jg/NetBeansProjects/readTable/Sal_Info.xlsx";
    private static final String FILE_PATH4="/Users/jg/NetBeansProjects/readTable/ROLES.xlsx";
    
    
    /*public static void main(String[] args)
    {
       ReadTable table1=new ReadTable();
        try {
            table1.GetSallistFromXLS();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    private void GetEmployeeListfromXlS  ()
    {
   FileInputStream fis = null;
   FileInputStream fistwo = null;
  //DataFormatter dataFormatter = new DataFormatter();
  String hostname="root";
  String password="654321";
  String dbURL = "jdbc:mysql://localhost:3306/employees";
      Connection conn = null; Statement stmt = null; 
       
  
   try{
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
          
             
       fis = new FileInputStream(FILE_PATH);
       Workbook workbook = new XSSFWorkbook(fis);
       Sheet sheet1=workbook.getSheetAt(0);
    Iterator rows = sheet1.rowIterator(); 
    //List data = new ArrayList();
    Row row;
    PreparedStatement statement=null;
    for(int i=1; i<=sheet1.getLastRowNum(); i++){
                row = sheet1.getRow(i);
                String id = (String) row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                Date bday = row.getCell(2).getDateCellValue();
                java.sql.Date Bday= new java.sql.Date(bday.getTime());
                String EmployeeType= row.getCell(3).getStringCellValue();
                String JobStatus=row.getCell(4).getStringCellValue();
                Date hday = row.getCell(5).getDateCellValue();
                java.sql.Date Hday= new java.sql.Date(hday.getTime());
                Date fday = row.getCell(6).getDateCellValue();
                java.sql.Date Fday= new java.sql.Date(fday.getTime());
                 String sql = "INSERT INTO workerdata(ID, Name, Birthday, ESType, EType, HiredDate, FiredDate)VALUES (?,?,?,?,?,?,?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setDate(3,  Bday);
                statement.setString(4, JobStatus);
                statement.setString(5, EmployeeType);
                statement.setDate(6, Hday);
                statement.setDate (7, Fday);
                statement.execute();
             
                
            }
            
            conn.close();
            
    
    
    
    
   }    catch (FileNotFoundException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
private void GetEmployeeListfromXlS2() throws SQLException, FileNotFoundException, IOException{
FileInputStream fis2 = null;
String hostname="root";
  String password="654321";
  String dbURL = "jdbc:mysql://localhost:3306/employees";
  fis2 = new FileInputStream(FILE_PATH2);
       Workbook workbook = new XSSFWorkbook(fis2);
       Sheet sheet1=workbook.getSheetAt(0);
       Iterator rows = sheet1.rowIterator();
       Row row;
       PreparedStatement statement=null;
  
      Connection conn = null; Statement stmt = null; 
 try{
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
 for(int i=1; i<=sheet1.getLastRowNum(); i++)
 {
 row = sheet1.getRow(i);
 String id = (String) row.getCell(0).getStringCellValue();
 String name = row.getCell(1).getStringCellValue();
 String EmployeeType= row.getCell(2).getStringCellValue();
 String sql = "INSERT INTO workers(id, name,role)VALUES (?,?,?)";
 statement = conn.prepareStatement(sql);
 statement.setString(1, id);
 statement.setString(2, name);
 statement.setString(3, EmployeeType);
 statement.execute();
 
 }
  conn.close();
 }       
 
catch(SQLException ex){} catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        }













}

private void GetRolesListFromXlS() throws FileNotFoundException, IOException{
FileInputStream fis4 = null;
String hostname="root";
  String password="654321";
  String dbURL = "jdbc:mysql://localhost:3306/employees";
  fis4 = new FileInputStream(FILE_PATH4);
       Workbook workbook = new XSSFWorkbook(fis4);
       Sheet sheet1=workbook.getSheetAt(0);
       Iterator rows = sheet1.rowIterator();
       Row row;
       PreparedStatement statement=null;  
      Connection conn = null; Statement stmt = null;
try{
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {System.out.println("Connected to the database");}
 for(int i=1; i<=sheet1.getLastRowNum(); i++)
 {
 row = sheet1.getRow(i);
 String sal = (String) row.getCell(0).getStringCellValue();
 String role = row.getCell(1).getStringCellValue();
 String sql = "INSERT INTO Roles(SALID, Role)VALUES (?,?)";
 statement = conn.prepareStatement(sql);
 statement.setString(1, sal);
 statement.setString(2, role);
 statement.execute();
 
 }
  conn.close();
 }       
 
catch(SQLException ex){} catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
        }





}

private void GetSallistFromXLS() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
{
 FileInputStream fis9 = null;
String hostname="root";
  String password="654321";
  String dbURL = "jdbc:mysql://localhost:3306/employees";
  fis9 = new FileInputStream(FILE_PATH3);
       Workbook workbook = new XSSFWorkbook(fis9);
       Sheet sheet1=workbook.getSheetAt(0);
       Iterator rows = sheet1.rowIterator();
       Row row;
       PreparedStatement statement;
  
      Connection conn; 
 
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(dbURL, hostname, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
  
   
 for(int i=1; i<=sheet1.getLastRowNum(); i++)
 {
 row = sheet1.getRow(i);
 String ID = row.getCell(1).getStringCellValue();
 String SALID = row.getCell(2).getStringCellValue();
 String name = row.getCell(3).getStringCellValue();
 var salary= row.getCell(4).getNumericCellValue();
 String sql = "INSERT INTO SalNfo(ID, SALID, Name, Salary)VALUES (?,?,?,?)";
 statement = conn.prepareStatement(sql);
 statement.setString(1, ID);
 statement.setString(2, SALID);
 statement.setString(3, name);
 statement.setDouble(4, salary);
 statement.execute();
 
 }
  conn.close();
 }       
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}       
 












    
    
    
    
    
    
    
    
    
    
    
























