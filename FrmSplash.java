package hms;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
public class FrmSplash extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int duration=3000;
	private JLabel label1,label2,label3;
	private JPanel panel;
	ImageIcon pic1,pic2;
	Font font;
	public FrmSplash()
	{
		super("SPLASH SCREEN");
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		panel=new JPanel(new GridLayout(2,1));
		panel.setBackground(Color.white);
		
		pic1=new ImageIcon("images/splashscreen_pic2.jpg");
		label1=new JLabel(pic1);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label1);
		
		pic2 =new ImageIcon("images/hourglass.gif");
		label2=new JLabel(pic2,JLabel.CENTER);
		font=new Font("Serif",Font.BOLD,80);
		label2.setText("Hospital Management System");
		label2.setFont(font);
		label2.setForeground(Color.red);
		label2.setHorizontalTextPosition(SwingConstants.CENTER);
		label2.setVerticalTextPosition(SwingConstants.TOP);
		panel.add(label2);
		add(panel,BorderLayout.CENTER);
		
		label3=new JLabel();
		font=new Font("Sans-Serif",Font.BOLD,20);
		label3.setText("   COPYRIGHT "+(char)169+" 2016, Abhinav Gupta");
		label3.setFont(font);
		label3.setHorizontalTextPosition(SwingConstants.CENTER);
		add(label3,BorderLayout.SOUTH);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		try
		{
			Thread.sleep(duration);
		} 
		catch(InterruptedException e)
		{
				e.printStackTrace();
		}
		CommonMethods.createAllTables();
		dispose();
		try
		{
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeQuery("select count(*) from users");
			rst.next();
			int cnt=rst.getInt(1);
			dc.close();
			if(cnt==0)
				new FrmCreateUser();
			else
				new FrmLogin();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	public static void main (String[] args)
	{
    	new FrmSplash();
    }
}