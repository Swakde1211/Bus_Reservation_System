package Reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Home {
	/*public static void generate_report()
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
		
	}*/
	
	public static void main(String args[]) {
		//generate_report();
		
		Login l=new Login();
		l.loginn();
		
		
		User_Input u=new User_Input();
		u.userinputt();
	
		
	}
}

	