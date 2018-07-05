/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RPascual
 */
public class ConexionMySQL {
    
    //private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static Driver driver = null;
    
    /*
    Este constructor ejecuta la funcion init para
    obtener los datos del archivo db.properties
    para la conexión con la base de datos.
    */  
    public ConexionMySQL(){
             
    }
    
    /*
    Recoge la configuración del archivo db.properties
    para establecer la conexión.
    */

    
    public static synchronized Connection getConnection(String url, String user, String passwd)throws SQLException{

        
        if (driver == null){
            
            try {
                
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
                
            } catch (Exception e) {
                
                System.out.println("Fallo en la carga del driver JDBC");
                e.printStackTrace();
                
            }
            
        }
        
        return DriverManager.getConnection(url,user,passwd);
    }
    
    public static void close(ResultSet rs){
        
        try {
             if(rs != null){
            
            rs.close();
     
            }
        } catch (SQLException sqle) {
            
            sqle.printStackTrace();
            
        }
        
    }
    
    public static void close(PreparedStatement stmt){
        
        try {
            
            if (stmt != null){
                
                stmt.close();
                
            }
            
        } catch (SQLException stmte) {
            
            stmte.printStackTrace();
            
        }
        
    }
    
    public static void close(Connection conn){
        
        try {
            
            if (conn != null){
                
                conn.close();
                
            }
            
        } catch (SQLException conne) {
            
            conne.printStackTrace();
            
        }
        
    }
    
}
