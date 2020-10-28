package recordmanager.java;
import javax.swing.*;
import javax.swing.table.*;   
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
//class record
//{
//	String name,marks,subject;
//	public record(String marks,String name,String subject)
//	{
//		this.marks=marks;
//		this.name=name;
//		this.subject=subject;
//	}
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
JLabel label1,label2,label3;
JButton add,sort;
JTextField name,marks,subject;
String col[]=new String[] {"Marks","Name","Subject"};
int size=0;
//DefaultTableModel model=new DefaultTableModel();
JTable jt=new JTable();
	public recordmanager() {
		rec=new String[1000][3];        
 jt=new JTable(); 

		frame=new JFrame("new frame");
		add=new JButton("Add");
		sort=new JButton("Sort");
		name=new JTextField(" ");
		marks=new JTextField(" ");
		subject=new JTextField(" ");
		name.setBounds(600,100,150,50);
		marks.setBounds(400,100,150,50);
		subject.setBounds(800,100,150,50);
		add.setBounds(1050,100,100,40);
		sort.setBounds(600,250,150,40);
		JSeparator sep=new JSeparator();
		label1=new JLabel("Name");
		label2=new JLabel("Marks");
		label3=new JLabel("Subject");
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
		 
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(name);
		frame.add(marks);
		frame.add(subject);
		frame.add(add);
		frame.add(sort);
		frame.add(sep);
		frame.add(jt);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(marks.getText().equals(" ") || !digitchecker(marks.getText()))
				{
					System.out.println("Input Invalid");
				}
				else if(name.getText().equals(" "))
				{
					System.out.println("Please enter the subject");
				}
				else if(subject.getText().equals(" "))
				{
					System.out.println("Please enter the marks");
				}
				else					
				{rec[size][0]=marks.getText();
				rec[size][1]=name.getText();
				rec[size][2]=subject.getText();
				jt.setModel(new DefaultTableModel(rec,col));
				size++;
				System.out.println(rec.length);}
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
