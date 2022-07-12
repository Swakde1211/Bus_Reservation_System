package Reservation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;  	//Update
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//update
import java.util.*;

public class Report {
	public static void max_booking(String date) {
		Statement stmt =null;
	
		   //GENERATING MAX BOOKING TABLE
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
				stmt = c.createStatement();
		}catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		   //SHOWS COUNT OF BOOKING TABLE & GENERATE CSV FILE
		
		try {
			
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql ="select bus_source, bus_destination,date,count(bus_source) as count from public.\"booking\" group by bus_source,bus_destination,date having date='"+date+"';";
	        PreparedStatement s = c.prepareStatement(sql);
	        ResultSet rs=s.executeQuery();
	        
			
			
			ArrayList <String> sreport=new ArrayList<String>();
	       
			while(rs.next())
	        {
	        	sreport.add(rs.getString("bus_source"));
	        	sreport.add(rs.getString("bus_destination"));
	        	
	        	sreport.add(rs.getString("date"));
	        	sreport.add(rs.getString("count"));
	        	
	        	
	        	
	        }
	        File file = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\booking_summary_report.csv");
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Booking Summary:");
	        bw.newLine();
	        bw.newLine();
		      
	        bw.write("Bus Sources"+ "," +"Bus Destination"+","+"Date"+ "," +"Count");
	        bw.newLine();
	        for(int i=0;i<sreport.size();i++)
	        {
	            bw.write(sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i));
	            bw.newLine();
	            
	        }
	        bw.close();
			}

	       

			
	    catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println();
		

		   //SHOWS COUNT OF CANCELLATION TABLE & GENERATE CSV FILE
		
		try {
			Connection c1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql1 ="select bus_source, bus_destionation,cancel_date,count(bus_source) as count from public.\"cancellation_ticket\" group by bus_source,bus_destionation,cancel_date having cancel_date='"+date+"';";
	        PreparedStatement s1 = c1.prepareStatement(sql1);
	        ResultSet rs1=s1.executeQuery();
	        
	        ArrayList <String> sreport1=new ArrayList<String>();
		       
			while(rs1.next())
	        {
	        	sreport1.add(rs1.getString("bus_source"));
	        	sreport1.add(rs1.getString("bus_destionation"));
	        	
	        	sreport1.add(rs1.getString("cancel_date"));
	        	sreport1.add(rs1.getString("count"));
	        	
	        	
	        	
	        }
	        File file1 = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\cancellation_ticket_file.csv");
	        FileWriter fw1 = new FileWriter(file1);
	        BufferedWriter bw1 = new BufferedWriter(fw1);
	        bw1.write("Cancellation ticket file Summary:");
	        bw1.newLine();
	        bw1.newLine();
		      
	        bw1.write("Bus Sources"+ "," +"Bus Destination"+","+"Cancel Date"+ "," +"Count");
	        bw1.newLine();
	        for(int i=0;i<sreport1.size();i++)
	        {
	            bw1.write(sreport1.get(i++)+","+sreport1.get(i++)+","+sreport1.get(i++)+sreport1.get(i));
	            bw1.newLine();
	            
	        }
	        bw1.close();

		    
		}
		catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
}

	public static void show_report(String table, String attribute, String sort , String date) {		//Push from here
		Statement stmt =null;
		

		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			
		stmt = c.createStatement();
		
		}catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		

		   //SORT OF BOOKING TABLE & GENERATE CSV FILE
		try {
			int i=1;	
			
			
			Connection c3 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql3 ="select * from public.\"booking\" where date='"+ date +"' ORDER BY "+attribute+" "+sort+";";
	        PreparedStatement s3 = c3.prepareStatement(sql3);
	        ResultSet rs3=s3.executeQuery();
	        
	        ArrayList <String> sreport3=new ArrayList<String>();
	        if(!rs3.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
	        
			while(rs3.next())
	        {
	        	sreport3.add(rs3.getString("user_id"));
	        	sreport3.add(rs3.getString("trans_id"));
	        	
	        	sreport3.add(rs3.getString("bus_source"));
	        	sreport3.add(rs3.getString("bus_destination"));
	        	sreport3.add(rs3.getString("bus_time"));
	        	sreport3.add(rs3.getString("bus_fare"));
	        	sreport3.add(rs3.getString("bus_id"));
	        	sreport3.add(rs3.getString("bus_number"));
	        	sreport3.add(rs3.getString("date"));
	        	
	        	
	        	
	        }
			File file3 = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\booking_report_file.csv");
	        FileWriter fw3 = new FileWriter(file3);
	        BufferedWriter bw3 = new BufferedWriter(fw3);
	        bw3.write("\n*********Booking Report*********");
	        bw3.newLine();
	        bw3.newLine();
		      
	        bw3.write("User id"+","+"Trans_id"+","+"Bus Sources"+ "," +"Bus Destination"+","+"Bus time"+ "," +" Bus fare"+","+"Bus ID"+","+"Bus Number"+","+"Date");
	        bw3.newLine();
	        for(int i1=0;i1<sreport3.size();i1++)
	        {
	            bw3.write(sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1));
	            bw3.newLine();
	            
	        }
	        bw3.close();

			 
	 	   //SORT OF CANCELLATION TABLE & GENERATE CSV FILE
	    	
			i=1;
			Connection c4 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql4 ="select * from public.\"cancellation_ticket\" where cancel_date='"+ date +"' ORDER BY "+attribute+" "+sort+";";
	        PreparedStatement s4 = c4.prepareStatement(sql4);
	        ResultSet rs4=s4.executeQuery();
	        
	        ArrayList <String> sreport4=new ArrayList<String>();
	        if(!rs4.isBeforeFirst()) {
				System.out.println("\tWe don't have any Cancellation Today!");
			}
			
			while(rs4.next())
	        {
	        	sreport4.add(rs4.getString("user_id"));
	        	sreport4.add(rs4.getString("trans_id"));
	        	
	        	sreport4.add(rs4.getString("bus_source"));
	        	sreport4.add(rs4.getString("bus_destionation"));
	        	sreport4.add(rs4.getString("bus_time"));
	        	sreport4.add(rs4.getString("bus_fare"));
	        	sreport4.add(rs4.getString("bus_id"));
	        	sreport4.add(rs4.getString("bus_number"));
	        	sreport4.add(rs4.getString("ticket_date"));
	        	sreport4.add(rs4.getString("cancel_date"));
	        	
	        	
	        	
	        	
	        }
			File file4 = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\cancellation_report_file.csv");
	        FileWriter fw4 = new FileWriter(file4);
	        BufferedWriter bw4 = new BufferedWriter(fw4);
	        bw4.write("\n*********Cancellation Report*********");
	        bw4.newLine();
	        bw4.newLine();
		      
	        bw4.write("User id"+","+"Trans_id"+","+"Bus Sources"+ "," +"Bus Destination"+","+"Bus time"+ "," +" Bus fare"+","+"Bus ID"+","+"Bus Number"+","+"Ticket Date"+","+"Cancel Date");
	           bw4.newLine();
	        for(int i2=0;i2<sreport4.size();i2++)
	        {
	            bw4.write(sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2++)+","+sreport4.get(i2));
	            bw4.newLine();
	            
	        }
	        bw4.close();

			
	    }
		
	    catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}

		System.out.println();
	} //push till here


public static void reportt()
{
	String date1;
	Scanner sc=new Scanner(System.in);
	System.out.println("1 - Booking History of Today.\n2 - Booking History of specific date");
	int date_choice=sc.nextInt();
	if(date_choice==1) {
		//public static void show_report(String table, String attribute, String sort , String date) {
	 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    date1=formatter.format(date);
	    System.out.println("Today's Date : " +formatter.format(date)); 
	    System.out.println("Please Select Option :");
	    System.out.println("1 - Ascending sort by Bus fare");
	    System.out.println("2 - Descending sort by Bus fare");
	    System.out.println("3 - Ascending sort by Transaction Id");
	    System.out.println("4 - Descending sort by Transaction Id");
	    int sort_option=sc.nextInt();
	    if(sort_option==1){
	    	
	    	show_report("booking","bus_fare","asc",date1);    	
	    }else if(sort_option==2){			    	
	    	show_report("booking","bus_fare","desc",date1);    	
	    }else if(sort_option==3){			    	
	    	show_report("booking","trans_id","asc",date1);    	
	    }else if(sort_option==4){			    	
	    	show_report("booking","trans_id","desc",date1);    	
	    }else {
	    	System.out.println("Invalid Input!\nSorting by Bus Fare");
	    	show_report("booking","bus_fare","asc",date1);    	
	    }
	   max_booking(date1);
	}														
	else if(date_choice==2) {
		String date;
		System.out.println("Enter the date in yyyy-MM-dd format only : ");
		date=sc.next();			//checking for invalid input is pending
        String regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";		
        String regex2 = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$";		
		if(!date.matches(regex)) {							
			System.out.println("Invalid Format!");			
			System.exit(0);									 
		}													
		if(!date.matches(regex2)) {						
			System.out.println("Invalid Date!");			
			System.exit(0);
		}
		System.out.println("Date You Have Entered - "+date);
		System.out.println("Please Select Option :");
	    System.out.println("1 - Ascending sort by Bus fare");
	    System.out.println("2 - Descending sort by Bus fare");
	    System.out.println("3 - Ascending sort by Transaction Id");
	    System.out.println("4 - Descending sort by Transaction Id");
	    int sort_option=sc.nextInt();
	    if(sort_option==1){
	    	show_report("cancellation_ticket","bus_fare","asc",date);    	
	    }else if(sort_option==2){			    	
	    	show_report("cancellation_ticket","bus_fare","desc",date);    	
	    }else if(sort_option==3){			    	
	    	show_report("cancellation_ticket","trans_id","asc",date);    	
	    }else if(sort_option==4){			    	
	    	show_report("cancellation_ticket","trans_id","desc",date);    	
	    }else {
	    	System.out.println("Invalid Input!\nSorting by Bus Fare");
	    	show_report("cancellation_ticket","bus_fare","asc",date);    	
	    }
	   //GENERATING MAX BOOKING TABLE
		   max_booking(date);

	}
}
}
