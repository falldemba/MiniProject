/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fall
 */
public class server {
     Connection con;
   public Connection connec() {
	  try 
	  { 
	     
		  Class.forName("com.mysql.jdbc.Driver").newInstance ( );
	            
	  }
	  catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
	  {
		 System.out .println("Erreur driver:  "+e.getMessage ( )) ;
	  }
	  try {
		 con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/Mini_projet","root","") ;
	} catch (SQLException e) {
		System.out.println("Erreur de connexion: "+ e.getMessage ( ));
	}
	return con;
}
}
