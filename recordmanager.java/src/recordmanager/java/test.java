package recordmanager.java;

import java.sql.*;

public class test {
	Connection con;
	Statement st;
	PreparedStatement ps;

	test() {
		con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DEMODBJAVA","parteek@1234");
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test db = new test();
		System.out.println("success");
	}

}