package FInalProjectJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sql implements DbUrl{
	
	
	
	public List<Plate> getMenu() {
		
		 try(Connection conn = DriverManager.getConnection(DB_URL))
	        {
			 Statement statemnt1 = conn.createStatement();
             //Created statement and execute it 
             ResultSet rs = statemnt1.executeQuery("SELECT * FROM menu"); 
			ArrayList<Plate> plates = new ArrayList<Plate>();
			
			while(rs.next()) {
				plates.add(new Plate(rs.getInt("id"), rs.getString("description"), rs.getInt("price")));
				
			}
			
			
			return plates;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
	public void saveOrder(List<Plate> order) {
		
		 try(Connection conn = DriverManager.getConnection(DB_URL))
	        {
			 Statement statemnt1 = conn.createStatement();
            //Created statement and execute it 
		       List<String> values = new ArrayList<>();
		       
		       for (Plate plate : order) {
				values.add( "("+plate.getId()+",'"+LocalDateTime.now()+"')");
			}
		       
             statemnt1.execute("INSERT INTO board(menu_id,time) VALUES"+values.stream().collect(Collectors.joining(","))+ ";"); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
		
	

}
