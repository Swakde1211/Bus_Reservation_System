package Reservation;
import java.text.SimpleDateFormat;  	//Update
import java.util.Date;  			//update

import static Reservation.GlobalVariables.*;


import java.sql.Connection;

import java.util.Scanner;


class AgeException extends Exception {
	 
	 public AgeException(String msg) {
	  super(msg);
	 }
	}


public class User_Input {
	
	public static void printMenu(String[] options) {

		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}

		// TODO Auto-generated method stub
		public static  void userinputt()
		{
			Connection c = null;
			
			Scanner scanner = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			
			boolean flag = true;
		

			int choose_option;
			System.out.println("you have choosen " + bus_type1);
			do {						
			System.out.println("\nChoose the option");
			String[] choose = { "1 - Book a Ticket", "2 - Cancel My Ticket", "3 - Check Bus Status", "4 - Report", "5 - Booking History", "6 - Cancellation History" , "7 - Exit" }; 
			printMenu(choose);
			choose_option = scanner.nextInt();


			if (choose_option == 1) {
				
			Booking_Process bp=new Booking_Process();
			bp.booking_processs();
			}

			
			else if (choose_option == 2) {
				Refund r=new Refund();
				r.refundd();
				//System.out.println("Refund");
			} 
			
			else if (choose_option == 3) {	
				
			Bus_Status b=new Bus_Status();
			b.bus_statuss();
			
			} 
			
			else if (choose_option == 4) {
		//		System.out.println("Your Report is downloaded at your location:");
			Report r=new Report();
			r.reportt();
			} 
			
			else if(choose_option == 5) {	
				
				System.out.println("****************GETTING BOOKING HISTORY****************");
				Booking_History bh=new Booking_History();
				bh.booking_historyy();
				
			} else if(choose_option == 6) {
				
				Cancel_Ticket_History cth=new Cancel_Ticket_History();
				cth.cancel_ticket_historyy();
				
			} else if (choose_option == 7) {
				System.out.println("Exit");
				System.exit(0);
			} else {
				System.out.println("Please Enter Valid Choice !\n");
			}
			}while(choose_option!=7);

		}	
}
