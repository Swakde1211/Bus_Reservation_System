package admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class admin {

	public void adminn() throws IOException, SQLException, ClassNotFoundException {
		int admin_choice;
		Scanner sc=new Scanner(System.in);
		do {
		System.out.println("Please Select Option\n1 - Add Bus\n2 - Add Bus through CSV file\n3 - Delete Bus\n4 - Edit Bus Info\n5 - View Booking Summary\n6 - Report\n7 - Exit");
			admin_choice= sc.nextInt();
			if(admin_choice==1) {
			Add_bus add_bus=new Add_bus();
			add_bus.add_buss();
				
			}else if(admin_choice==2) {
				Csv_Import cv=new Csv_Import();
				cv.editOrderCsv();
			}
			else if(admin_choice==3) {
				delete_bus dlt_bus=new delete_bus();
				dlt_bus.delete_buss();
			}else if(admin_choice==4) {
				Edit_bus_info ebf=new Edit_bus_info();
				ebf.edit_bus_info();
			}else if(admin_choice==5) {
				View_booking_summary vbss=new View_booking_summary();
				vbss.vbs();
			}else if(admin_choice==6) {
				Report r=new Report();
				r.reportt();

				
			}else if(admin_choice==7) {
				System.out.println("You Have Successfully Exited!");
				System.exit(0);
				
			}else {
				System.out.println("Please Enter Correct Option !");
			}
		}while(admin_choice!=7);
	}
}