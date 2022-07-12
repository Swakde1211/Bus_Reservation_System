package Reservation;
import java.text.SimpleDateFormat;  	//Update
import java.util.Date;  			//update
import java.util.InputMismatchException;

import static Reservation.GlobalVariables.*;


import java.sql.Connection;

import java.util.Scanner;


import exceptions.*;


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
		
			
			
			int choose_option=0;
			
			try {
				System.out.println("you have choosen " + bus_type1);
				
				
				do {						
				System.out.println("\nChoose the option");
				String[] choose = { "1 - Book a Ticket", "2 - Cancel My Ticket", "3 - Check Bus Status", "4 - Report", "5 - Booking History", "6 - Cancellation History" , "7 - Exit" }; 
				printMenu(choose);
				
				choose_option = scanner.nextInt();
				if(choose_option!=1 && choose_option!=2 && choose_option!=3 && choose_option!=4 && choose_option!=5 && choose_option!=6 && choose_option!=7)
				{
					throw new InputMismatchException();
				}
				else if (choose_option == 1) {
					
					Booking_Process bp=new Booking_Process();
					bp.booking_processs();
					}

				else if (choose_option == 2) {
					Refund r=new Refund();
					r.refundd();
				} 
				
				else if (choose_option == 3) {	
					
				Bus_Status b=new Bus_Status();
				b.bus_statuss();
				
				} 
				
				else if (choose_option == 4) {
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
				} 
				else {
					System.out.println("Please Enter Valid Choice !\n");
				}

				 }while(choose_option!=7);
			}
				catch( InputMismatchException e){
				//System.out.println(exp) ;
				System.out.println("Please try again!!") ;
				System.exit(0);
			}
			
			
			
					

		}	
}
