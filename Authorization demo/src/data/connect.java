package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class connect {
	 static Connection con;
	 static PreparedStatement ps;
	 static Statement st;
	 public static Connection getConnection()
	 {
		 if(con==null)
		 {
		 try {
			 
			 System.out.println("entered");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 System.out.println("verified");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","demodbjava","parteek@1234");
			} 
			catch (Exception e) {
				System.out.println("1");
				System.out.println(e);
			} 
		 }
		 System.out.println("done");
			return con;
	 }
}
