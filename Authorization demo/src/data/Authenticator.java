package data;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Authenticator
 */
@WebServlet("/Authenticator")
public class Authenticator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=connect.getConnection();
		
		if(request.getParameter("mentorname")!=null)
		{
			try {
				PreparedStatement ps=con.prepareStatement("SELECT * FROM MENTORDATA WHERE NAME=? AND PASSWORD=? AND ID=?");
				ps.setString(1, request.getParameter("mentorname"));
				ps.setString(2, request.getParameter("password"));
				ps.setInt(3, Integer.valueOf(request.getParameter("id")));
				ResultSet rs=ps.executeQuery();
				//System.out.println("i "+i);
				//PrintWriter out=response.getWriter();
				if(rs!=null)
				{
					
					RequestDispatcher rd=request.getRequestDispatcher("/StudentData.jsp");
					rd.forward(request, response);
					//out.print("login successful");
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("/Mentor.html");
					rd.forward(request, response);
					//out.println("login failed");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("mentor");
		}
		else if(request.getParameter("adminname")!=null)
		{
			try {
				PreparedStatement ps=con.prepareStatement("SELECT * FROM ADMIN WHERE ADMINNAME=? AND PASSWORD=?");
				ps.setString(1, request.getParameter("adminname"));
				ps.setString(2, request.getParameter("password"));
				int i=ps.executeUpdate();
				//PrintWriter out=response.getWriter();
				if(i>=1)
				{
					RequestDispatcher rd=request.getRequestDispatcher("/MentorData.jsp");
					rd.forward(request, response);
					//out.print("login successful");
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("/Admin.html");
					rd.forward(request, response);
					//out.println("login failed");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("admin");
		}
		else if(request.getParameter("studentname")!=null)
		{
			
			try {
				PreparedStatement ps=con.prepareStatement("SELECT * FROM STUDENTDATA WHERE NAME=? AND PASSWORD=?");
			ps.setString(1,request.getParameter("studentname"));
			ps.setString(2, request.getParameter("password"));
			int res=ps.executeUpdate();
			System.out.println("studentname "+request.getParameter("studentname")+" password "+request.getParameter("password"));
			if(res==0)
			{
				System.out.println("Student Login Failed");
			}
			else
			{
				System.out.println("Student Login Successful");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("student");
		}
		else if(request.getParameter("studentform")!=null)
		{
			if(request.getParameter("studentform").equals("Add"))
			{
				try {
					PreparedStatement ps=con.prepareStatement("INSERT INTO STUDENTDATA VALUES(?,?,?,?,?,?,?,?)");
				    ps.setString(1, request.getParameter("name"));
				    ps.setInt(2, Integer.valueOf(request.getParameter("rollno")));
				    ps.setString(3,request.getParameter("section"));
				    ps.setString(4,request.getParameter("phoneno"));
				    ps.setString(5,request.getParameter("branch"));
				    ps.setString(6,request.getParameter("gmail"));
				    ps.setString(7,request.getParameter("rollno")+"_"+request.getParameter("mentorid"));
				   ps.setString(8, passwordGenerator(request.getParameter("rollno"),request.getParameter("mentorid")));
				   int res=ps.executeUpdate();
					if(res==0)
					{
						System.out.println("Failed");
					}
					else
					{
						System.out.println("Addition Successful");
					}
					System.out.println(request.getParameter("mentorid"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				
			}
			System.out.println("student form"+request.getParameter("studentform"));
		}
		else if(request.getParameter("MentorForm")!=null)
		{
			if(request.getParameter("MentorForm").equals("Add"))
			{
				try {
					PreparedStatement ps=con.prepareStatement("INSERT INTO MENTORDATA VALUES(?,?,?,?,?,?,?,?)");
					ps.setString(1,request.getParameter("name"));
					ps.setInt(2, Integer.valueOf(request.getParameter("ID")));
					ps.setString(3, request.getParameter("address"));
					ps.setString(4, request.getParameter("phoneno"));
					ps.setString(5,request.getParameter("branch"));
					ps.setString(6, request.getParameter("gmail"));
					ps.setString(7, request.getParameter("exp"));
					ps.setString(8,request.getParameter("name")+"_"+request.getParameter("ID"));
		int res=ps.executeUpdate();
		if(res==0)
		{
			System.out.println("Failed");
		}
		else
		{
			System.out.println("Addition Successful");
		}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				
			}
			System.out.println("mentor form"+request.getParameter("mentorform"));
		}
		else
		{
			
		}
	}
	public String passwordGenerator(String t,String s)
	{
		char S[]=s.toCharArray();
		char T[]=t.toCharArray();
		StringBuilder sb=new StringBuilder();
		int i=0,j=0;
		while(i<S.length && j<T.length)
		{
			sb.append(S[i]);
			sb.append(T[j]);
			i++;
			j++;
		}
		while(i<S.length)
		{
			sb.append(S[i]);
			i++;
		}
		while(j<T.length)
		{
			sb.append(T[j]);
			j++;
		}
		return sb.toString();
		
	}

}
