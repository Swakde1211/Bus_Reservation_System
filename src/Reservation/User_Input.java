package Reservation;
import java.text.SimpleDateFormat;  	//Update
import java.util.Date;  			//update

import static Reservation.GlobalVariables.*;


import java.sql.Connection;

import java.util.Scanner;

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
		
			
			System.out.println("Add source");
			String Source = sc.next();
			user_source = Source;
			System.out.println("Add Destination");
			String Destination = sc.next();
			user_dest = Destination;

			
			System.out.println("Add Age");
			int Age = sc.nextInt();
			user_age = Age;
			if (Age < 5) {
				System.out.println("You dont need any ticket!!");
			} else if (Age > 60) {
				System.out.println("You will get concession ticket!!");

			}
			System.out.println("Your Gender");
			String Gender = sc.next();
			user_gender = Gender;

			if (Gender.equals("F") || Gender.equals("f")) {
				System.out.println("You will get concession ticket!!");
			}

			System.out.println("Bus Type");

			
			
			
			String[] options = { "1- AC", "2- NON AC" , "3- ALL"}; 	//Today 
			printMenu(options);
			int option;
			int attempt=0;		//Today
			do {				//Today
			option = scanner.nextInt();
			if (option == 1) {
				bus_type1 = "AC";
				break;
			}else if(option==2) {
				bus_type1 = "Non AC";
				break;
			}else if(option==3) {		//Today
				bus_type1= "true";		//Today
				break;					//Today
			}							//Today
			else {						//Today
				System.out.println("Enter Valid Option!");	//Today
				attempt++;				//Today
			}							//Today
			}while(attempt!=3);			//Today
			if(attempt==3) {			//Today
				System.out.println("You have entered wrong information");		//Today
				System.exit(0);	//Today
			}	//Today

			int choose_option;
			System.out.println("you have choosen " + bus_type1);
			do {						//Today [ display menu]
			System.out.println("\nChoose the option");
			String[] choose = { "1 - Book a Ticket", "2 - Cancel My Ticket", "3 - Check Bus Status", "4 - Report", "5 - Booking History", "6 - Cancellation History" , "7 - Exit" }; //Today
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
				System.out.println("Your Report is downloaded at your location:");
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
