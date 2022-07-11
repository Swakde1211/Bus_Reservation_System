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
/*	public static void generate_report()
	{
		System.out.println("hello");
		try {
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
         String sql ="select * from  public.\"booking\"";
        PreparedStatement s = c.prepareStatement(sql);
        ResultSet res=s.executeQuery();
        ArrayList <String> sreport=new ArrayList<String>();
       
        while(res.next())
        {
        	sreport.add(res.getString("user_id"));

        	sreport.add(res.getString("trans_id"));
        	sreport.add(res.getString("bus_source"));
        	sreport.add(res.getString("bus_destination"));
        	sreport.add(res.getString("bus_time"));
        	
        	sreport.add(res.getString("bus_fare"));
        	sreport.add(res.getString("bus_id"));
        	sreport.add(res.getString("bus_number"));
        	
            	
        }
        File file = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\report.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("User id"+","+"Trans_id"+"Bus Sources" +"Bus Destination");
        bw.newLine();
        for(int i=0;i<sreport.size();i++)
        {
            bw.write(sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i++)+","+sreport.get(i));
            bw.newLine();
            
        }
        bw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
*/
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
		try {
			
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql ="select bus_source, bus_destination,date,count(bus_source) as count from public.\"booking\" group by bus_source,bus_destination,date having date='"+date+"';";
	        PreparedStatement s = c.prepareStatement(sql);
	        ResultSet rs=s.executeQuery();
	        
			
			
		/*	while(rs.next()) {
				System.out.print("Source: "+rs.getString("bus_source"));
				System.out.print("\tDestination: "+rs.getString("bus_destination"));
				System.out.println("\tBooking Count: "+rs.getString("count"));
			}
	    */
			ArrayList <String> sreport=new ArrayList<String>();
	       
			while(rs.next())
	        {
	        	sreport.add(rs.getString("bus_source"));
	        	sreport.add(rs.getString("bus_destination"));
	        	
	        	sreport.add(rs.getString("date"));
	        	sreport.add(rs.getString("count"));
	        	
	        	
	        	
	        }
	        File file = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\report.csv");
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

			/*while(rs.next()) {
				System.out.print("Source: "+rs.getString("bus_source"));
				System.out.print("\tDestination: "+rs.getString("bus_destionation"));
				System.out.println("\tTicket Cancel Count: "+rs.getString("count"));
			}*/
	        
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
			
			
			// c.setAutoCommit(false);
		//System.out.println("Databse Successfully Connected.");
		stmt = c.createStatement();
		
		}catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println(table +" "+attribute+ " "+sort +" "+ date);
		try {
			int i=1;	
			
			
Connection c3 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
	        String sql3 ="select * from public.\"booking\" where date='"+ date +"' ORDER BY "+attribute+" "+sort+";";
	        PreparedStatement s3 = c3.prepareStatement(sql3);
	        ResultSet rs3=s3.executeQuery();
	        
	        ArrayList <String> sreport3=new ArrayList<String>();
		       
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
			if(!rs3.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
	        File file3 = new File("C:\\Users\\swakde\\eclipse-workspace\\Bus_Reservationsystem\\booking_report_file.csv");
	        FileWriter fw3 = new FileWriter(file3);
	        BufferedWriter bw3 = new BufferedWriter(fw3);
	        bw3.write("\n*********Booking Report*********");
	        bw3.newLine();
	        bw3.newLine();
		      
	        bw3.write("Bus Sources"+ "," +"Bus Destination"+","+"Cancel Date"+ "," +"Count");
	        bw3.newLine();
	        for(int i1=0;i1<sreport3.size();i1++)
	        {
	            bw3.write(sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1++)+","+sreport3.get(i1));
	            bw3.newLine();
	            
	        }
	        bw3.close();

/*			 while (rs3.next()) {
				System.out.print("Sr. No. "+ i++);
				//System.out.print("\tDate: "+rs.getString("date"));
				System.out.print("\tBus ID: "+rs3.getInt("bus_id"));
				System.out.print("\tSource: "+rs3.getString("bus_source"));
				System.out.print("\tDestination: "+rs3.getString("bus_destination"));
				System.out.print("\tBus Time: "+rs3.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs3.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs3.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs3.getString("bus_number"));
			}
			 
			
			 */
			 
			 
			 
			System.out.println("\n*********Cancellation Report*********");
			i=1;
			ResultSet rs1 = stmt.executeQuery("select * from public.\"cancellation_ticket\" where cancel_date='"+ date +"' ORDER BY "+attribute+" "+sort+";");
			if(!rs1.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
			while (rs1.next()) {
				System.out.print("Sr. No. "+ i++);
				//System.out.print("\tCancellation Date: "+rs1.getString("cancel_date"));
				System.out.print("\tBus ID: "+rs1.getInt("bus_id"));
				System.out.print("\tSource: "+rs1.getString("bus_source"));
				System.out.print("\tDestination: "+rs1.getString("bus_destionation"));
				System.out.print("\tBus Time: "+rs1.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs1.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs1.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs1.getString("bus_number"));
			}
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
	}														//NEW
	else if(date_choice==2) {
		String date;
		System.out.println("Enter the date in yyyy-MM-dd format only : ");
		date=sc.next();			//checking for invalid input is pending
        //Pattern pattern = Pattern.compile(".*[^0-9].*");
        String regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";		//NEW
        String regex2 = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$";		//NEW
		if(!date.matches(regex)) {							//NEW
			System.out.println("Invalid Format!");			//NEW
			System.exit(0);									//NEW 
		}													//NEW
		if(!date.matches(regex2)) {						
			System.out.println("Invalid Date!");			//NEW
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
	    //show_report("booking","bus_fare","asc",date);
	
	   //GENERATING MAX BOOKING TABLE
		   max_booking(date);

	}
}
}
