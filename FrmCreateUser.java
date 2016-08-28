package hms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmCreateUser extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel title,lblUser,lblPass,lblRetype,welcome,lblType;
	JComboBox<String> jcbType;
	JTextField jtfUser;
	JPasswordField jpfPass,jpfRetype;
	JButton btnCreate;
	String arr[]={"admin","operator","doctor"};
	FrmCreateUser()
	{
		super("User Registration");
		setLayout(null);
		setIconImage(new ImageIcon("images/HMS_logo.jpg").getImage());
		getContentPane().setBackground(Color.white);
		setTitle("Registration");
		
		welcome=new JLabel(new ImageIcon("images/hms_banner.jpg"));
		welcome.setBounds(250,150,800,100);
		add(welcome);
		
		title=new JLabel(new ImageIcon("images/register.jpg"));
		title.setBounds(500,300,400,60);
		add(title);
		
		lblType=new JLabel("User Type");
		lblType.setBounds(500, 380, 140, 25);
		add(lblType);
		
		jcbType=new JComboBox<>(arr);
		jcbType.setBounds(600,380,130,25);
		add(jcbType);
 
 		
 		lblUser=new JLabel("User Name");
		lblUser.setBounds(500,420,140,25);
		add(lblUser);
		
		jtfUser=new JTextField();
		jtfUser.requestFocus();
		jtfUser.setBounds(600,420,130,25);
		add(jtfUser);
		
		lblPass=new JLabel("Password");
		lblPass.setBounds(500,460,140,25);
		add(lblPass);
		
		jpfPass=new JPasswordField();
		jpfPass.setBounds(600,460,130,25);
		add(jpfPass);
		
		lblRetype=new JLabel("ReType");
		lblRetype.setBounds(500,500,140,25);
		add(lblRetype);
		
		jpfRetype=new JPasswordField();
		jpfRetype.setBounds(600,500,140,25);
		add(jpfRetype);
		
		btnCreate=new JButton("Register");
		btnCreate.setBounds(500,540,100,30);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String type=(String)jcbType.getSelectedItem();
				String user=jtfUser.getText();
				String pass=new String(jpfPass.getPassword());
				String retype=new String(jpfRetype.getPassword());
				if(type.isEmpty() || user.isEmpty() || pass.isEmpty() || retype.isEmpty())
					JOptionPane.showMessageDialog(null,"All Fields Are Mandatry To Fill","Error",JOptionPane.ERROR_MESSAGE);
				else if(!(pass.length()>=8 && pass.length()<=15))
					JOptionPane.showMessageDialog(null,"Password Should be 8 to 15 Characters Long","Error",JOptionPane.ERROR_MESSAGE);
				else if(!pass.equals(retype))
					JOptionPane.showMessageDialog(null,"Password and Re Type must match","Error",JOptionPane.ERROR_MESSAGE);
				else
				{
					DConnection dc=new DConnection();
					dc.executeOther("insert into users values('"+user+"','"+pass+"','"+type+"')");
					dispose();
					String s="Welcome "+type+"! Your Id Is "+user+" and Password is "+pass;
					JOptionPane.showMessageDialog(null,s,"Created Sucessfully",JOptionPane.INFORMATION_MESSAGE);
				   	new FrmLogin();
				   	return;
				}
				jtfUser.requestFocus();
			}			
		});
		try
		{
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeQuery("select count(*) from users");
			rst.next();
			int c=rst.getInt(1);
			dc.close();
			if(c==0)
			{
				jcbType.setSelectedItem("admin");
				jcbType.setEnabled(false);
			}	
			else
				jcbType.setEnabled(true);	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}			
		add(btnCreate);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
	}

}