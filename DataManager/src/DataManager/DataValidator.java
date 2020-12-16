package DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataValidator {
	static String UserName,Password,DOB;
public static  boolean Validate(String UName,String Pword,String Dob)
{
	UserName=UName;
	Password=Pword;
	DOB=Dob;
	char s[]=UserName.toCharArray();
	if(s.length==0)
		return false;
	if(!UserDOBValidator(DOB))
		return false;
	
	try {
		Connection con=Connector.getConnection();
		String res=UserIdGenerator(UserName,DOB);
		PreparedStatement ps=con.prepareStatement("SELECT * FROM USER_STORE WHERE USERNAME=\'"+UserName+"\' AND PASSWORD=\'"+Password+"\' AND USERID=\'"+res+"\'");
		int res1=ps.executeUpdate();
		if(res1==0)
			return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
}
public static String UserIdGenerator(String UserName,String DOB)
{
	String res=new String();
	System.out.println("username "+UserName+" DOB "+DOB);
res+=UserName+"_"+DOB.substring(0,2)+DOB.substring(3,5)+DOB.substring(6,10);
	return res;
}
public static  boolean UserDOBValidator(String DOB)
{
	char s[]=DOB.toCharArray();
	if(s[2]!='-' || s[5]!='-' || s.length!=10)
		return false;
//	System.out.println("reached step 1");
	for(int i=0;i<s.length;i++)
	{
		
		if(s[i]=='-' && (i==2 || i==5))
				continue;
		if(s[i]<48 || s[i]>57)
			return false;
	}
//	System.out.println("reached step 2");
	return true;
}

}
