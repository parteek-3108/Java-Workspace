package recordmanager.java;
import javax.swing.*;   
import java.awt.Font;
public class recordmanager {
JFrame frame;
JLabel label1,label2,label3;
	public recordmanager() {
		frame=new JFrame("new frame");
		label1=new JLabel("Name");
		label2=new JLabel("RollNo");
		label3=new JLabel("Subject");
		label1.setBounds(100,100,100,200);
		label2.setBounds(250,100,100,200);
		label3.setBounds(400,100,100,200);
		 label1.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label2.setFont(new Font("Verdana", Font.PLAIN, 18));
		 label3.setFont(new Font("Verdana", Font.PLAIN, 18));
		 
		//frame.add(button);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
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
