package DataManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validator
 */
@WebServlet("/validate")
public class Validator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			Connection con=Connector.getConnection();
System.out.println("step 1");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM USER_DATA WHERE USERID=\'"+DataValidator.UserIdGenerator(DataValidator.UserName,DataValidator.DOB)+"\'");
			ResultSet rs=ps.executeQuery();
			System.out.println("step 2");
			System.out.println(rs);
			String t=new String("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Insert title here</title>\r\n"
					+ "</head>\r\n"
					+ "<body>");
			t+="<table>";
			t+="<tr><th>FIRSTNAME</th><th>LASTNAME</th><th>SEX</th><th>AGE</th><th>ADDRESS</th><th>PINCODE</th><th>DATEOFBIRTH</th></tr>";
			while(rs.next())
			{
				t+="<tr>";
			t+="<td>"+rs.getString(1)+"</td>";
			t+="<td>"+rs.getString(2)+"</td>";
			t+="<td>"+rs.getString(3)+"</td>";
			t+="<td>"+rs.getInt(4)+"</td>";
			t+="<td>"+rs.getString(5)+"</td>";
			t+="<td>"+rs.getInt(6)+"</td>";
			t+="<td>"+rs.getString(8)+"</td>";
			t+="</tr>";
			}
			t+="</table><br><br><br>";
			t+="<a href=\"http://localhost:8088/DataManager/data.html\"><button>ADD MORE DATA</button></a></body></html>";
			System.out.println(t);
			System.out.println("step 3");
			out.print(t);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((new ArrayList(request.getParameterMap().keySet())).size()==3)
		{
			System.out.println("login");
			RequestDispatcher rd;
			PrintWriter out = response.getWriter();
			DataValidator datav=new DataValidator();
			
			if(datav.Validate(request.getParameter("name"), request.getParameter("password"), request.getParameter("dob")))
			{
				//out.print("log in successful");
				System.out.println("passed");
				 rd=request.getRequestDispatcher("/data.html");
				 rd.include(request, response);
			}
			else
			{
				
				//out.print("Invalid Details");
				System.out.println("not passed");
				rd=request.getRequestDispatcher("/InputValidator.html");
				rd.include(request, response);
			}
		}
		else if((new ArrayList(request.getParameterMap().keySet())).size()==4)
		{
			System.out.println("new account");
			DataValidator datav=new DataValidator();
			RequestDispatcher rd;
			PrintWriter out = response.getWriter();
			System.out.println("check1 "+request.getParameter("name")+" "+request.getParameter("newpassword")+" "+request.getParameter("dob"));
			datav.Validate(request.getParameter("name"), request.getParameter("newpassword"), request.getParameter("dob"));
			if(!datav.UserDOBValidator(request.getParameter("dob")) || !request.getParameter("newpassword").equals(request.getParameter("confirmPassword")))
			{
				
				
				out.print("Invalid Details");
				System.out.println(request.getParameter("newpassword")+" "+request.getParameter("confirmPassword"));
				rd=request.getRequestDispatcher("/NewAccount.html");
				 rd.include(request, response);
				 
			}
			else
			{
			try {
				Connection con=Connector.getConnection();
				
				String query="INSERT INTO USER_STORE VALUES(\'"+request.getParameter("name")+"\',\'"+datav.UserIdGenerator(request.getParameter("name"), request.getParameter("dob"))+"\',\'"+ request.getParameter("newpassword")+"\')";
				System.out.println(query+"  query");
				PreparedStatement ps=con.prepareStatement(query);
				int rs=ps.executeUpdate();
				if(rs==1)
				{
					//out.print("ADDED SUCCESSFULLY");
					rd=request.getRequestDispatcher("/data.html");
					 rd.include(request, response);
				}
				else
				{
					//out.print("Invalid Details");
					rd=request.getRequestDispatcher("/NewAccount.html");
					 rd.include(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		else
		{
			
			try {
				Connection con=Connector.getConnection();
				String res="INSERT INTO USER_DATA VALUES(\'"+request.getParameter("FirstName")+"\',\'"+request.getParameter("LastName")+"\',\'"+request.getParameter("sex")+"\',\'"+request.getParameter("age")+"\',\'"+request.getParameter("address")+"\',\'"+request.getParameter("pincode")+"\',\'"+DataValidator.UserIdGenerator(DataValidator.UserName, DataValidator.DOB)+"\',\'"+request.getParameter("dob")+"\')";
				PreparedStatement ps=con.prepareStatement(res);
				System.out.println(res);
				ps.executeUpdate();
			
				RequestDispatcher rd;
				rd=request.getRequestDispatcher("/data.html");
				 rd.include(request, response);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Display data");
		}
	}

}
