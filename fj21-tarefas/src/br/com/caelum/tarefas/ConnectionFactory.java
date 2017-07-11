package br.com.caelum.tarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection(){
		try{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21?useTimezone=true&serverTimezone=UTC","root","root");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
