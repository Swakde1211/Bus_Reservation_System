package admin;

import java.beans.Statement;
import java.util.Scanner;

import database.Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Add_bus {

	public static void add_buss() {
		Connection c=null;
		java.sql.Statement stmt=null;
		boolean flag=false;
		String bus_id_temp;
		int bus_id=0;
		
		try {
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			}catch (Exception e) {
				System.out.println("Oops! Database Connection Failed");
			}
			
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Enter Following Details");
		
		//CHECKING FOR INTEGER BUS ID.
		do {
			System.out.println("Enter Bus ID :");
			bus_id_temp=sc.next();
		try {
			Integer.parseInt(bus_id_temp);
			flag=true;
		}catch(NumberFormatException ex) {
			System.out.println("Please Enter Integer Value!!!");
		}
		}while(!flag);
		bus_id=Integer.parseInt(bus_id_temp);
		flag=false;
		
		//CHECKING FOR UNIQUE BUS ID
		do {
		try {
			stmt = c.createStatement();
			ResultSet rs1 = ((java.sql.Statement) stmt).executeQuery("SELECT bus_id FROM public.\"bus_info\"");
			while(rs1.next()) {
				 int get_id=rs1.getInt("bus_id");
				 if(get_id==bus_id) {
					 System.out.println("Bus ID you have entered is already Exist.");
					 flag=true;
					 System.out.println("Please Enter Bus ID Again :");
					 bus_id=sc.nextInt();
				 }
				 else {
					 flag=false;
				 }
			}
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
		}while(flag);
		System.out.println("Enter Bus Number :");
		String bus_number=sc.next();
		System.out.println("Enter Bus Fare :");
		String bus_fare=sc.next();
		System.out.println("Enter Source :");
		String source=sc.next();
		System.out.println("Enter Destination :");
		String destination=sc.next();
		System.out.println("Enter Bus Time :");
		String bus_time=sc.next();
		System.out.println("Enter Bus Type :");
		String bus_type=sc.next();
		System.out.println("Enter Seats Capacity :");
		int seats_available=sc.nextInt();
		//Handle exception for character
		
		try {
			String sql2 ="INSERT INTO public.\"bus_info\"(bus_id, bus_number, bus_fare, source, destination, bus_time, bus_type, seats_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

			PreparedStatement s = c.prepareStatement(sql2);	
            s.setInt(1, bus_id);
			s.setString(2, bus_number);
			s.setString(3, bus_fare);
			s.setString(4, source);
			s.setString(5, destination);
			s.setString(6, bus_time);
			s.setString(7, bus_type);
			s.setInt(8, seats_available);
            
            s.executeUpdate();

       
            System.out.println("\nBus Data Updated Succcessfully! ");
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}
