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
    	
		while(true){
	    	Scanner inputer = new Scanner(System.in);
	    	System.out.println("\nEnter the complete path of the CVS File:");
	    	String csvPath = inputer.nextLine();
	   	
	          String line = "";
	          File f = new File(csvPath);
	          if(f.exists() && !f.isDirectory()) {
	        	
	    	  BufferedReader br = new BufferedReader(new FileReader(csvPath));
	          br.readLine(); // skip header line
	          while ((line = br.readLine()) != null)
	          {
	        	 // System.out.print("kahipan");
	        	 // System.out.print((br.readLine()) != null);
	        	 if(line.equals("")) {
	        		 break;
	        	 }
	        	  
	        	  
	            String[] orderDetails = line.split(",");
	    		int bus_id = Integer.parseInt(orderDetails[0]);
	    		String bus_number = orderDetails[1];
				String bus_fare = orderDetails[2];
				String source = orderDetails[3];
				String destination = orderDetails[4];
				String bus_time = orderDetails[5];
				String bus_type = orderDetails[6];
				int seat_available = Integer.parseInt(orderDetails[7]);
				
	        	
	        	
				if (doesProductExist(bus_id)) {		
					
					Class.forName("org.postgresql.Driver");

					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

				//	String query = "UPDATE public.\booking\" SET bus_id=?, bus_number=?, bus_fare=? WHERE bus_id = ?;";
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

					  
					//String query = "INSERT INTO public.csv_file (bus_id, bus_number, bus_fare) VALUES (?, ?, ?);";
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
	          System.out.println("\nProduct Details successfully updated!!");
	          break;
	          }
	          else {
	        	  System.out.println("\nNo such file exists, Please enter the correct File Path!!");
	        	
	          }
	 		}
	      }
	
//	public static void editOrderCsv() throws IOException, SQLException {
//		File f = null;
//		String csvPath = "";
//		String line = "";
//        int nline = 1;
//        Path path = null;
//       
//		while (true) {
//			Scanner inputer = new Scanner(System.in);
//			while(true) {
//				try {
//					System.out.println("\nEnter the complete path of the CVS File:");
//					csvPath = inputer.nextLine();
//				
//					
//					f = new File(csvPath);
//					path = Paths.get(csvPath);
//	                break;
//				}catch(Exception e) {
//					System.out.println("please enter valid path!!");
//					break;
//				}
//			}
//			if (f.exists() && !f.isDirectory()) {
//				
//				BufferedReader br = new BufferedReader(new FileReader(csvPath));
//				br.readLine(); // skip header line
//				while ((line = br.readLine()) != null)
//				{
//					try {
//						if(line.equals(""))
//							break;
//						String[] orderDetails = line.split(",");
//						int Bus_Id = Integer.parseInt(orderDetails[0]);
//						String Bus_number = orderDetails[1];
//						int Bus_fare = Integer.parseInt(orderDetails[2]);
//						
//						
//						if (orderDetails[2].matches("^[0-9]+"))
//						{
//							if (doesProductExist(Bus_Id)) {		
//								
//								Class.forName("org.postgresql.Driver");
//
//								
//								Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
//
//								String query = "UPDATE public.\"csv_file\" SET Bus_Id=?, Bus_number=?, Bus_fare=? WHERE Bus_Id = ?;";
//								
//								PreparedStatement preparedStatement = c.prepareStatement(query);
//								preparedStatement.setInt(1, Bus_Id);
//								preparedStatement.setString(2, Bus_number);
//								preparedStatement.setInt(3, Bus_fare);
//								preparedStatement.execute();
//							} else {					
//								Class.forName("org.postgresql.Driver");
//
//								
//								Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
//
//								String query = "INSERT INTO  public.\"csv_file\"  VALUES Bus_Id=?, Bus_number=?, Bus_fare=? ";
//								
//								PreparedStatement preparedStatement = c.prepareStatement(query);
//								preparedStatement.setInt(1, Bus_Id);
//								preparedStatement.setString(2, Bus_number);
//								preparedStatement.setInt(3, Bus_fare);
//								preparedStatement.execute();
//
//							}
//						}
////						else {
////							int lineError = nline+1;
////							System.out.printf("\nIncorrect Data on line %d ", lineError);
////						}
//					}catch(NumberFormatException e) {
//						System.out.println("Error on line no "+ (nline + 1));
//					}catch(Exception e) {
//						System.out.println("please enter valid path");
//					}
//					//nline++;
//				}
//		
//				System.out.println("\nProduct Details successfully updated!!");
//				break;
//			} else {
//				System.out.println("\nNo such file exists, Please enter the correct File Path!!");
//			}
//		}
//	}

}
