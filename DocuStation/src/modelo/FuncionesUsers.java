/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author RPascual
 */
public class FuncionesUsers {
    
      
    private String host;
    private String port;
    private String dbname;
    private String user;
    private String passwd;
    private String url;
    
    private final String SQL_INSERT = "INSERT INTO t_users(name, surname, mail, pass) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE t_users SET pass=? WHERE id_persona=?";
    private final String SQL_DELETE = "DELETE FROM t_users WHERE id_user=?";
    private final String SQL_SELECT = "SELECT id_user, name, surname, mail FROM t_users ORDER BY id_user";
    
    public FuncionesUsers(){
        init();
    }
    
    public int insert(String name, String surname, String mail, String pass){
        init();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        int rows = 0;
        
        try {
            
            conn = ConexionMySQL.getConnection(this.url,this.user,this.passwd);
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            
            stmt.setString(index++, name);
            stmt.setString(index++, surname);
            stmt.setString(index++, mail);
            stmt.setString(index++, pass);
            System.out.println("Ejecutando la query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        }finally{
            
           ConexionMySQL.close(stmt);
           ConexionMySQL.close(conn);
            
        }
        
    return rows;
        
    }
    
    public int update(int id_user){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            
            conn = ConexionMySQL.getConnection(this.url,this.user,this.passwd);
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index, id_user);
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        }finally{
            
            ConexionMySQL.close(stmt);
            ConexionMySQL.close(conn);
            
        }
        
        return rows;
        
    }
    
    public int delete(int id_user){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            
            conn = ConexionMySQL.getConnection(this.url,this.user,this.passwd);
            System.out.println("Ejecutando la query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id_user);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }finally{
            
            ConexionMySQL.close(stmt);
            ConexionMySQL.close(conn);
            
        }
        
        return rows;
    }
    
    public List<User> select(){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        
        List<User> users = null;
        users = new ArrayList<User>();
        
        try {
            
            conn = ConexionMySQL.getConnection(this.url,this.user,this.passwd);
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                int id_user = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String mail = rs.getString(4);
                String pass = rs.getString(5);
                
                user = new User();
                user.setId_user(id_user);
                user.setName(name);
                user.setSurname(surname);
                user.setMail(mail);
                user.setPass(pass);
                
                users.add(user);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }finally{
            
            ConexionMySQL.close(stmt);
            ConexionMySQL.close(conn);
            
        }
        
        return users;
        
    }
    
        public void init(){
        
        Properties prop = new Properties();
        InputStream propStream = this.getClass().getClassLoader().getResourceAsStream("configuracion/db.properties");
        
        try{
            
            prop.load(propStream);
            this.host = prop.getProperty("host");
            this.port = prop.getProperty("port");
            this.dbname = prop.getProperty("dbname");
	    this.user = prop.getProperty("user");
	    this.passwd = prop.getProperty("passwd");
            
            this.url = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.dbname+"?use=false";
            
            
        }catch (IOException e){
            
            String message = "Error: db.properties file could not be found";
            System.err.println(message);
            throw new RuntimeException(message, e);
            
        }
        
    }
    
    
}
