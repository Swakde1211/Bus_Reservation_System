 
 
 package admin;
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Csv_Import {
	
	
	public static boolean doesProductExist(int bus_id) throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

		String query = "SELECT * FROM public.bus_info WHERE bus_id = ?;";

		PreparedStatement preparedStatement = c.prepareStatement(query);
		preparedStatement.setInt(1, bus_id);
		
		ResultSet rs = preparedStatement.executeQuery();
		return rs.next();
	}
	
		
			
		
		
	public static void editOrderCsv() throws IOException, SQLException, ClassNotFoundException,NumberFormatException {
	  int nline = 1;
	  String csvPath = "";
	  String line = "";
	  File f=null;
	  String flag = "1";
	  
		while(true){
	    	Scanner inputer = new Scanner(System.in);
	    	
	        try
	        {
	        	
	        
	    	System.out.println("\nEnter the complete path of the CVS File:");
	    	 csvPath = inputer.nextLine();
	   	
	           f = new File(csvPath);
	           
	        }
	        catch(Exception e) {
	            System.out.println("please enter valid path");
	            }
	        
		
	          if(f.exists() && !f.isDirectory()) {
	        	
	    	  BufferedReader br = new BufferedReader(new FileReader(csvPath));
	          br.readLine(); 
	          while ((line = br.readLine()) != null)
	          {
	        	 if(line.equals("") || line.equals(" ")) {
	        		 break;
	        	 }
	        try {	  
	        	  
	            String[] orderDetails = line.split(",");
	    		int bus_id = Integer.parseInt(orderDetails[0]);
	    		String bus_number = orderDetails[1];
				String bus_fare = orderDetails[2];
				String source = orderDetails[3];
				String destination = orderDetails[4];
				String bus_time = orderDetails[5];
				String bus_type = orderDetails[6];
				int seat_available = Integer.parseInt(orderDetails[7]);
				
				if (orderDetails[7].equals("")) {
					System.out.printf("\nIncorrect Data on line %d ", (nline+1));
				}
				
				if (orderDetails[0].matches("^[0-9]+") && orderDetails[1].matches("^[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}[0-9]{0,4}") && orderDetails[2].matches("^[0-9]+") &&  orderDetails[3].matches("^[a-zA-Z]+") && orderDetails[4].matches("^[a-zA-Z]+") && orderDetails[5].matches("^[0-9]{0,2}:[0-9]{0,2}:[0-9]{0,2}") && orderDetails[6].matches("^[A-Za-z]+") && orderDetails[7].matches("^[0-9]+"))
			    {    	
	        	
				if (doesProductExist(bus_id)) {		
					
					Class.forName("org.postgresql.Driver");

					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

					String query = "UPDATE public.bus_info SET bus_id=?, bus_number=?, bus_fare=?, source=?, destination=?, bus_time=?, bus_type=?, seats_available=?  WHERE bus_id = ?;";
					    
					PreparedStatement preparedStatement = c.prepareStatement(query);
					preparedStatement.setInt(1, bus_id);
					preparedStatement.setString(2, bus_number);
					preparedStatement.setString(3, bus_fare);
					preparedStatement.setString(4, source);
					preparedStatement.setString(5, destination);
					preparedStatement.setString(6, bus_time);
					preparedStatement.setString(7, bus_type);
					preparedStatement.setInt(8, seat_available);
					preparedStatement.setInt(9, bus_id);
					preparedStatement.execute();
				} else {					
					Class.forName("org.postgresql.Driver");

					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

					  
					String query="INSERT INTO public.bus_info (bus_id, bus_number, bus_fare, source, destination, bus_time, bus_type, seats_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
					 
					PreparedStatement preparedStatement = c.prepareStatement(query);
					preparedStatement.setInt(1, bus_id);
					preparedStatement.setString(2, bus_number);
					preparedStatement.setString(3, bus_fare);
					preparedStatement.setString(4, source);
					preparedStatement.setString(5, destination);
					preparedStatement.setString(6, bus_time);
					preparedStatement.setString(7, bus_type);
					preparedStatement.setInt(8, seat_available);
					preparedStatement.execute();

				}
			    }
				else {
					flag="0";
				    int lineError = nline+1;
				    System.out.printf("\nIncorrect Data on line %d ", lineError);
				    break;
				    }
	        }
	        
	        catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
	        	flag="0";
	            System.out.println("Error on line no "+ (nline + 1));
	            break;
	            }
	        nline++;
				
	          }
	          
	         if(flag.equals("1")) { 
	          System.out.println("\nProduct Details successfully updated!!");
	         }
	          
	          
	          }
	          else {
	        	  System.out.println("\nNo such file exists, Please enter the correct File Path!!");
	        	
	          }
	 		}

	}
}

	