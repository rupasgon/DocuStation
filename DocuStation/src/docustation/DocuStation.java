/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docustation;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.FuncionesUsers;

/**
 *
 * @author RPascual
 */
public class DocuStation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Connection conn = null;
        FuncionesUsers f1 = new FuncionesUsers();
        
        try{
            
            conn = f1.getConnection(conn);
            
            if(conn.getAutoCommit()){
                
                conn.setAutoCommit(false);
                
            }
            
            FuncionesUsers fuWithOutAutocommit = new FuncionesUsers(conn);
            
            fuWithOutAutocommit.insert("Miguel", "Tablero", "miguel.tablero@gmail.com", "Prueba2018");
            
            
        }catch(SQLException e){
            
            //e.printStackTrace();
            //e.getErrorCode();
            e.getCause();
            
        }
        
        
        
        
        
        
        
        
        

        
    }
    
}
