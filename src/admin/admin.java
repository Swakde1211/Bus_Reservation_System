package admin;
import java.util.Scanner;
public class admin {

	public void adminn() {
		int admin_choice;
		Scanner sc=new Scanner(System.in);
		do {
		System.out.println("Please Select Option\n1 - Add Bus\n2 - Delete Bus\n3 - Edit Bus Info\n4 - View Booking Summary\n5 - Report\n6 - Exit");
			admin_choice= sc.nextInt();
			if(admin_choice==1) {
				Add_bus add_bus=new Add_bus();
				add_bus.add_buss();
			}else if(admin_choice==2) {
				delete_bus dlt_bus=new delete_bus();
				dlt_bus.delete_buss();
			}else if(admin_choice==3) {
				Edit_bus_info ebf=new Edit_bus_info();
				ebf.edit_bus_info();
			}else if(admin_choice==4) {
				View_booking_summary vbss=new View_booking_summary();
				vbss.vbs();
			}else if(admin_choice==5) {
				Report r=new Report();
				r.reportt();

				
			}else if(admin_choice==6) {
				System.out.println("You Have Successfully Exited!");
				System.exit(0);
				
			}else {
				System.out.println("Please Enter Correct Option !");
			}
		}while(admin_choice!=6);
	}
}