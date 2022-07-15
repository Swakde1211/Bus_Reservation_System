package user;
import java.text.SimpleDateFormat;  	//Update
import java.util.Date;  			//update
import java.util.InputMismatchException;

import static Reservation.GlobalVariables.*;


import java.sql.Connection;

import java.util.Scanner;

import Reservation.Login;
import admin.admin;
import exceptions.*;


public class User_Input {
	
	public static void printMenu(String[] options) {

		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}

		// TODO Auto-generated method stub
		public static  void userinputt() throws Exception
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
				String[] choose = { "1 - Book a Ticket", "2 - Cancel My Ticket", "3 - Check Bus Status", "4 - View History", "5 - Exit" }; 
				printMenu(choose);
				
				choose_option = scanner.nextInt();
				if(choose_option!=1 && choose_option!=2 && choose_option!=3 && choose_option!=4 && choose_option!=5 && choose_option!=6)
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
				
//				else if (choose_option == 4) {
//				Report r=new Report();
//				r.reportt();
//				} 
				
				else if(choose_option == 4) {	
					
					View_History bh=new View_History();
					bh.view_history();
					
			} 
//					else if(choose_option == 5) {
//					
//					Cancel_Ticket_History cth=new Cancel_Ticket_History();
//					cth.cancel_ticket_historyy();
//					
//				} 
				else if (choose_option == 5) {
					System.out.println("Exit");
					Login l=new Login();
					l.loginn();
					
					if(is_admin) {
						admin ad=new admin();
						ad.adminn();
					}
					else {
						User_Input u=new User_Input();
						u.userinputt();			
					}
					
					System.exit(0);
				} 
				else {
					System.out.println("Please Enter Valid Choice !\n");
					continue;
				}

				 }while(choose_option!=5);
			}
				catch( InputMismatchException e){
				//System.out.println(exp) ;
				System.out.println("Please try again!!") ;
				System.exit(0);
			}
			
			
			
					

		}	
}
