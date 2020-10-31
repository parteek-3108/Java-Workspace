package recordmanager.java;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.*;
public class recordmanager implements MouseListener{
	private static boolean  digitchecker(String s)
	{
		String regex="[0-9]+";
		Pattern p=Pattern.compile(regex);
		if(s==null)
			return false;
		Matcher m=p.matcher(s);
		
		return m.matches();
	}
JFrame frame;
String rec[][];
JLabel label1,label2,label3,label1warning,label2warning,label3warning;
JButton add,sort;
JTextField name,marks,subject;
String col[]=new String[] {"Marks","Name","Subject"};
Connection con;
Statement st;
PreparedStatement ps;
int size=0;

//DefaultTableModel model=new DefaultTableModel();
JTable jt=new JTable();
	public recordmanager() {
		con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DEMODBJAVA","parteek@1234");
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		rec=new String[1000][3];        
		jt=new JTable(); 
		frame=new JFrame("new frame");
		add=new JButton("Add");
		sort=new JButton("Sort");
		name=new JTextField();
		marks=new JTextField();
		subject=new JTextField();
		name.setBounds(600,100,150,50);
		marks.setBounds(400,100,150,50);
		subject.setBounds(800,100,150,50);
		add.setBounds(1050,100,100,40);
		sort.setBounds(600,250,150,40);
		JSeparator sep=new JSeparator();
		label1=new JLabel("Name");
		label2=new JLabel("Marks");
		label3=new JLabel("Subject");
		label1warning=new JLabel("Invalid input");
		label2warning=new JLabel("Invalid input");
		label3warning=new JLabel("Invalid input");
		label1warning.setBounds(400,130,400,100);
		label2warning.setBounds(600,130,400,100);
		label3warning.setBounds(800,130,400,100);
		
		label1.setBounds(600,300,100,200);
		label2.setBounds(850,300,100,200);
		label3.setBounds(1100,300,100,200);	
		jt.setBounds(550,420,700,500);
		jt.setFont(new Font("Verdana", Font.PLAIN, 18));
		add.setFont(new Font("Verdana", Font.PLAIN, 18));
		sort.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label1.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label2.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label3.setFont(new Font("Verdana", Font.PLAIN, 18));
		 jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 label1warning.setForeground(Color.RED);
			label2warning.setForeground(Color.RED);
			label3warning.setForeground(Color.RED);
		 label1warning.setVisible(false);
		 label2warning.setVisible(false);
		 label3warning.setVisible(false);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(label1warning);
		frame.add(label2warning);
		frame.add(label3warning);
		frame.add(name);
		frame.add(marks);
		frame.add(subject);
		frame.add(add);
		frame.add(sort);
		frame.add(sep);
		frame.add(jt);
//		marks.addActionListener(new ActionListener()
//		{
//	public void actionPerformed(ActionEvent e)
//	{
//		System.out.println("entered");
//		label1warning.setForeground(Color.GREEN);
//		label1warning.setText("Checking...");
//	}
//		});
		marks.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	System.out.println("entered");
//				label1warning.setForeground(Color.GREEN);
//				label1warning.setText("Checking...");
		    	label1warning.setVisible(false);
		    }
		    public void focusLost(FocusEvent e)
		    {
		    	
		    }

		});
		name.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	System.out.println("entered");
//				label1warning.setForeground(Color.GREEN);
//				label1warning.setText("Checking...");
		    	label2warning.setVisible(false);
		    }
		    public void focusLost(FocusEvent e)
		    {
		    	
		    }

		});
		subject.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	System.out.println("entered");
//				label1warning.setForeground(Color.GREEN);
//				label1warning.setText("Checking...");
		    	label3warning.setVisible(false);
		    }
		    public void focusLost(FocusEvent e)
		    {
		    	
		    }

		});
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int t=3;
				if(marks.getText()==null || !digitchecker(marks.getText()))
				{
					label1warning.setForeground(Color.RED);
					label1warning.setVisible(true);
//					label1warning.setText("Invalid Input");
					t--;
				}
				 if(name.getText()==null || digitchecker(name.getText()))
				{
					label2warning.setForeground(Color.RED);
					label2warning.setVisible(true);
//					label2warning.setText("Invalid Input");
					t--;
				}
				 if(subject.getText()==null || digitchecker(subject.getText()))
				{
					label3warning.setForeground(Color.RED);
					label3warning.setVisible(true);
//					label3warning.setText("Invalid Input");
					t--;
				}
				if(t==3)					
				{rec[size][0]=marks.getText();
				rec[size][1]=name.getText();
				rec[size][2]=subject.getText();
				
				try {
					ps=con.prepareStatement("INSERT INTO JDBCPRACTICE VALUES(SQ2.nextval,?,?,?)");
					ps.setString(1,marks.getText());
					ps.setString(2,name.getText());
					ps.setString(3,subject.getText());
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jt.setModel(new DefaultTableModel(rec,col));
//				JTextArea textarea1 = (JTextArea)jt.getEditorComponent();
//				   textarea1.setEditable(false);
				jt.setEnabled(false);
				size++;
				System.out.println(rec.length);}
				}
		});
		sort.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				Arrays.sort(rec,(a,b)->(a[0].compareTo(b[0])==0?(a[1].compareTo(b[1])==0?a[2].compareTo(b[2]):a[1].compareTo(b[1])):a[0].compareTo(b[0])));
				jt.setModel(new DefaultTableModel(rec,col));
				jt.setEnabled(false);
//				JTextArea textarea1 = (JTextArea)jt.getEditorComponent();
//				   textarea1.setEditable(false);
			}
				});
		label1.addMouseListener(this);
		label2.addMouseListener(this);
		label3.addMouseListener(this);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()+", "+e.getY() +" , "+e.getSource());
        if(e.getSource()==label1)
        	Arrays.sort(rec,(a,b)->(a[0].compareTo(b[0])));
        else if(e.getSource()==label2)
        	Arrays.sort(rec,(a,b)->(a[1].compareTo(b[1])));     	
        else
        	Arrays.sort(rec,(a,b)->(a[2].compareTo(b[2]))); 
        jt.setModel(new DefaultTableModel(rec,col));
     }
	public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
public static void main(String args[])
{
	recordmanager start=new recordmanager();
}
}

