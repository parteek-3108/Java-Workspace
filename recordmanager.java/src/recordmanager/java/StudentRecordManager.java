package recordmanager.java;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;
class record
{
	long rollno;
	String FirstName,LastName,section;
public record(long rollno,String firstname,String lastname,String section)
{
	this.rollno=rollno;
	this.FirstName=firstname;
	this.LastName=lastname;
	this.section=section;
}
}
public class StudentRecordManager {
List <record> list;
JFrame frame;
JComboBox dropdown;
  Connection con;
  Statement st;
  PreparedStatement ps;
  

JPanel INSERT,UPDATE,DELETE,DISPLAY;
			
private static boolean  digitchecker(String s)
{
	String regex="[0-9]+";
	Pattern p=Pattern.compile(regex);
	if(s==null)
		return false;
	Matcher m=p.matcher(s);
	
	return m.matches();
}

	public StudentRecordManager() {
		list=new ArrayList<record>();
//		String Languages[]=new String[] {"C","C#","C++","JAVA","PYTHON","HTML","CSS","JAVASCRIPT","OS","CSA"};
		String options[]=new String[] {"INSERT","DELETE","UPDATE","DISPLAY"};
		con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DEMODBJAVA","parteek@1234");
			st=con.createStatement();
		}catch(Exception e)
		{
			
		}
		dropdown=new JComboBox(options);
		frame=new JFrame();
		dropdown.setBounds(500,100,100,30);
		dropdown.setEditable(false);
		dropdown.setForeground(Color.WHITE);
		dropdown.setBackground(Color.BLACK);
		frame.add(dropdown);
	
		//UPDATE
		UPDATE=new JPanel();
		String opt[]=new String[] {"Firstname","Lastname","Section"};
		JTextField rollnoPK=new JTextField();
		rollnoPK.setBounds(380,140,200,33);
		JLabel PK=new JLabel("Enter Your RollNo here");
		PK.setBounds(180,100,300,100);
		PK.setFont(new Font("Verdana", Font.PLAIN, 15));
		JTextField nv1=new JTextField();
		nv1.setBounds(380,280,200,33);
		JLabel nv2=new JLabel("Enter The Value here");
		nv2.setBounds(180,240,300,100);
		nv2.setFont(new Font("Verdana", Font.PLAIN, 15));
		UPDATE.add(PK);
		UPDATE.add(rollnoPK);
		UPDATE.add(nv2);
		UPDATE.add(nv1);
		JButton updatebtn=new JButton("UPDATE");
		updatebtn.setBounds(350,340,100,30);
		UPDATE.add(updatebtn);
		JComboBox updatelist=new JComboBox(opt);
		updatelist.setBounds(350,200,200,30);
		updatelist.setFont(new Font("Verdana", Font.PLAIN, 13));		
		UPDATE.add(updatelist);
		
		UPDATE.setBounds(200,300,800,800);	
		UPDATE.setForeground(Color.WHITE);
		UPDATE.setBackground(Color.BLACK);
		UPDATE.setVisible(true);
		UPDATE.setLayout(null);
		frame.add(UPDATE);
		updatebtn.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				String t=(String) updatelist.getItemAt(updatelist.getSelectedIndex());
				
				if(rollnoPK==null || !digitchecker(rollnoPK.getText()) || nv1==null)
				{
					
				}
				else
				{
					try {
						String temp="UPDATE JDBCPRACTICE SET "+t.toUpperCase()+" = \'"+nv1.getText()+"\' 	WHERE ROLLNO = "+rollnoPK.getText();
						System.out.println(temp);
						ps=con.prepareStatement(temp);
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
				});
		//UPDATE ENDS	
		//DELETE
		UPDATE.setVisible(false);
		DELETE=new JPanel();
		JTextField rollfield=new JTextField();
		JLabel rolllabel=new JLabel("Enter the Roll No here");
		JButton deletebtn=new JButton("DELETE");
		rolllabel.setBounds(200,100,250,100);
		rolllabel.setFont(new Font("Verdana", Font.PLAIN, 13));	
		rollfield.setBounds(400,140,100,30);
		deletebtn.setBounds(300,200,100,30);
		DELETE.add(rolllabel);
		DELETE.add(rollfield);
		DELETE.add(deletebtn);
		DELETE.setBounds(200,300,800,800);	
		DELETE.setForeground(Color.WHITE);
		DELETE.setBackground(Color.BLACK);
		DELETE.setVisible(true);
		DELETE.setLayout(null);
		frame.add(DELETE);
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(rollfield==null || !digitchecker(rollfield.getText()))
				{
					
				}
				else
				{
					try {
						ps=con.prepareStatement("DELETE FROM JDBCPRACTICE WHERE ROLLNO="+rollfield.getText());
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		//DELETE ENDS
		
		//DISPLAY
		DELETE.setVisible(false);
		DISPLAY=new JPanel();
		try {
			ResultSet rs=st.executeQuery("SELECT * FROM JDBCPRACTICE");
			Vector res=new Vector();
			while(rs.next())
			{
			Vector temp=new Vector();
			temp.add(rs.getInt(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			res.add(temp);
			}
			Vector coldata=new Vector();
			coldata.add("ROLLNO");
			coldata.add("FIRST NAME");
			coldata.add("LAST NAME");
			coldata.add("SECTION");
			JTable table=new JTable(res,coldata);
			table.setBounds(100,0,700,700);
			table.setFont(new Font("Verdana", Font.PLAIN, 18));
			table.setEnabled(false);
			DISPLAY.add(table);
			DISPLAY.setBounds(200,300,800,800);	
			DISPLAY.setForeground(Color.WHITE);
			DISPLAY.setBackground(Color.BLACK);
			DISPLAY.setVisible(true);
			DISPLAY.setLayout(null);
			frame.add(DISPLAY);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//DISPLAY ENDS
		//INSERT 
		DISPLAY.setVisible(false);
				INSERT=new JPanel();
				INSERT.setBounds(200,300,800,800);
				JTextField  rollno=new JTextField();
				JTextField  firstname=new JTextField();
				JTextField  secondname=new JTextField();
				JTextField  section=new JTextField();
				JLabel rollnol=new JLabel("ROLLNO");
				JLabel  firstnamel=new JLabel("FIRSTNAME");
				JLabel secondnamel=new JLabel("LASTNAME");
				JLabel  sectionl=new JLabel("SECTION");
				JButton insertbtn=new JButton("INSERT");
				insertbtn.setBounds(380,460,100,30);
				rollnol.setBounds(300,-20,100,100);
				firstnamel.setBounds(300,90,100,100);
				secondnamel.setBounds(300,190,100,100);
				sectionl.setBounds(300,290,100,100);
				rollno.setBounds(450,20,150,30);
				firstname.setBounds(450,110,150,30);
				secondname.setBounds(450,220,150,30);
				section.setBounds(450,330,150,30);
				INSERT.add(firstname);
				INSERT.add(secondname);
				INSERT.add(rollno);
				INSERT.add(section);
				INSERT.add(firstnamel);
				INSERT.add(secondnamel);
				INSERT.add(sectionl);
				INSERT.add(rollnol);
				INSERT.add(insertbtn);
				INSERT.setForeground(Color.WHITE);
				INSERT.setBackground(Color.BLACK);
				INSERT.setVisible(true);
				INSERT.setLayout(null);
				frame.add(INSERT);
				insertbtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(rollno==null || !digitchecker(rollno.getText()))
						{
							
						}
						else if(firstname==null || digitchecker(firstname.getText()))
						{
							
						}
						else if(secondname==null || digitchecker(secondname.getText()))
						{
							
						}
						else if(section==null || digitchecker(section.getText())) {
							
						}
						else
						{
							SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
							Date date = new Date(System.currentTimeMillis());
							String datestring=formatter.format(date);
							
							try {
								ps=con.prepareStatement("INSERT INTO JDBCPRACTICE VALUES(recseq.nextval,?,?,?,?,?)");
								System.out.println("1");
								ps.setInt(1, Integer.valueOf(rollno.getText()));
								System.out.println("2");
								
								ps.setString(2,firstname.getText());
								System.out.println("3");
								ps.setString(3, secondname.getText());
								System.out.println("4");
								ps.setString(4, section.getText());
								ps.setString(5, datestring);
								ps.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						}
					}
				});
				//INSERT ENDS
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setSize(1200,1200);
		frame.setLayout(null);
		frame.setVisible(true);
		dropdown.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
//				System.out.println(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("UPDATE"));
				if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("UPDATE"))
				{
				UPDATE.setVisible(true);
				DELETE.setVisible(false);
				INSERT.setVisible(false);
				DISPLAY.setVisible(false);
				}
				else if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("INSERT"))
				{
					UPDATE.setVisible(false);
					DELETE.setVisible(false);
					INSERT.setVisible(true);
					DISPLAY.setVisible(false);
				}
				else if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("DELETE"))
				{
					UPDATE.setVisible(false);
					DELETE.setVisible(true);
					INSERT.setVisible(false);
					DISPLAY.setVisible(false);
				}
				else
				{
					UPDATE.setVisible(false);
					DELETE.setVisible(false);
					INSERT.setVisible(false);
					DISPLAY.setVisible(true); 
				}		
			}

				});
	}
	public static void main(String[] args)
	{
		StudentRecordManager x=new   StudentRecordManager();
	}
}
