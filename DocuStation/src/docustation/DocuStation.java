/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docustation;

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
        
        FuncionesUsers f1 = new FuncionesUsers();
        
        for(int i=1;i<=3;i++){
            
        //System.out.println(i);
            
       f1.insert("Usuario"+i, "Usuario"+i, "usuario"+i+"@prueba.com", "Prueba2018");
       
       
        }
        
    }
    
}
