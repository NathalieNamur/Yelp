package it.polito.tdp.Yelp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
	
	//CONNECTION POOLING: 
	
	//I.
	static private HikariDataSource ds = null; 
	static private String url = "jdbc:mysql://localhost:3306/yelp?user=root&password=moustache";
	
	//II.
	public static Connection getConnection() {
		
		//La datasource è già stata aperta (esiste)?
		
		//Se la ds non esiste ancora, bisogna crearla:
		
		if(ds==null) {
			ds = new HikariDataSource();
			ds.setJdbcUrl(url);
			ds.setUsername("root");
			ds.setPassword("moustache");
		}
		
		//Per poi restituire una nuova connessione 
		//dalla ds appena creata:
		
		try {
			return ds.getConnection();
			
		} catch (SQLException e) {
			System.out.println("ERRORE di connessione al database.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}
