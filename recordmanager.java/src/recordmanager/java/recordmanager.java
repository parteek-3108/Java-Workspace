package recordmanager.java;
import javax.swing.*;   
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
class record
{
	String name,marks,subject;
	public record(String marks,String name,String subject)
	{
		this.marks=marks;
		this.name=name;
		this.subject=subject;
	}
}
public class recordmanager {
JFrame frame;
record rec[];
JLabel label1,label2,label3;
JButton add,sort;
JTextField name,marks,subject;
int size=0;
JTable jt;
	public recordmanager() {
		rec=new record[1000];
		//jt=new JTable({"Marks","Name","Subject"},rec);
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
		label1=new JLabel("Name");
		label2=new JLabel("RollNo");
		label3=new JLabel("Subject");
		label1.setBounds(600,300,100,200);
		label2.setBounds(850,300,100,200);
		label3.setBounds(1100,300,100,200);
		add.setFont(new Font("Verdana", Font.PLAIN, 18));
		sort.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label1.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label2.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label3.setFont(new Font("Verdana", Font.PLAIN, 18));
		 
		//frame.add(button);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(name);
		frame.add(marks);
		frame.add(subject);
		frame.add(add);
		frame.add(sort);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(marks.getText().equals(" "))
				{
					System.out.println("Please enter the name");
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
				{rec[size++]=new record(marks.getText(),name.getText(),subject.getText());
				System.out.println(rec[size-1]);}
				}
		});
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setVisible(true);
	}
public static void main(String args[])
{
	recordmanager start=new recordmanager();
}
}
