package it.polito.tdp.Yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Yelp.model.Business;

public class YelpDAO {
	
	//METODO PER ACQUISIRE TUTTI I BUSINESSES DAL DATABASE:
	public List<Business> readAllBusinesses() {
		
		//Stringa contenente la query:
		String sql = "SELECT * FROM Business" ;
	
		//Struttura dati dei valori di ritorno:
		List<Business> businesses = new ArrayList<>() ;
		
		
		//Codice di accesso effettivo al database (try-catch):
		
		//Connessione:
		Connection conn = DBConnect.getConnection() ;
		
		try {
			
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			//Inserimento paramentri nella query (metodo set corretto):
			//-
			
			//Esecuzione della query e salvarne il risultato:
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				//Creazione di un business temporaneo in cui salvare
				//le informazioni della riga letta dal database.
				
				//Aggiunta di tale business alla struttura dati di ritorno.
				
				businesses.add(new Business(
											res.getString("business_id"),
											res.getString("full_address"),
											res.getString("active"),
											res.getString("categories"),
											res.getString("city"),
											res.getInt("review_count"),
											res.getString("business_name"),
											res.getString("neighborhoods"),
											res.getDouble("latitude"),
											res.getDouble("longitude"),
											res.getString("state"),
											res.getDouble("stars") ));
			}
			
			//Chiudere tutti gli elementi:
			conn.close();
			
			//Ritornare la struttura dati creata:
			return businesses ;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//METODO PER CALCOLARE LA MEDIA DELLE STELLE DI UN DATO BUSINESS:
	public double averageStars(Business business) {
		
		//Stringa contenente la query:
		String sql = "SELECT AVG(stars) AS avg_stars, COUNT(*) AS number "
					 + "FROM reviews "
					 + "WHERE business_id = ?";
		
		//Struttura dati dei valori di ritorno:
		//-
		
		//Codice di accesso effettivo al database (try-catch):
		
		//Connessione:
		Connection conn = DBConnect.getConnection();
		
		try {
			
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql);
			
			//Inserimento paramentri nella query (metodo set corretto):
			st.setString(1, business.getBusinessId());
			
			//Esecuzione della query e salvarne il risultato:
			
			ResultSet res = st.executeQuery();
			
			//NB:
			//In questo caso non è necessario il while, 
			//perchè c'è una sola riga:
			
			res.first();
			double stars = res.getDouble("avg_stars") ;
			
			
			//Chiudere tutti gli elementi:
			conn.close();
			
			//Ritornare valore di ritorno:
			return stars ;
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return 0 ;
		}
		
	}

	
	
}
