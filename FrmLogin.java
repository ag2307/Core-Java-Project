package hms;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class FrmLogin extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel title,lblUser,lblType,lblPassword,welcome,or;
	JComboBox<String> jcbType;
	JTextField txtUser;
	JPasswordField txtPassword;
	JButton btnLogin,btnSignUp;
	String arr[]={"admin","operator","doctor"};
	int cnt;
	FrmLogin()
	{
		setLayout(null);
		setIconImage(new ImageIcon("images/HMS_logo.jpg").getImage());
		getContentPane().setBackground(Color.white);
		setTitle("Login");
		setResizable(true);
		
		welcome=new JLabel(new ImageIcon("images/hms_banner.jpg"));
		welcome.setBounds(250,150,800,100);
		add(welcome);
		
		title=new JLabel(new ImageIcon("images/login.jpg"));
		title.setBounds(500,300,400,60);
		add(title);
		
		lblType=new JLabel("User Type");
		lblType.setBounds(500, 380, 140, 25);
 
        lblUser=new JLabel("User Name");
		lblUser.setBounds(500,420,140,25);
		
		
		lblPassword=new JLabel("Password");
		lblPassword.setBounds(500,460,140,25);
		
		jcbType=new JComboBox<>(arr);
		jcbType.setBounds(600,380,130,25);
		
		txtUser=new JTextField();
		txtUser.requestFocus();
		txtUser.setBounds(600,420,130,25);
	
		
		txtPassword=new JPasswordField();
		txtPassword.setBounds(600,460,130,25);
		
		
		btnLogin=new JButton("Login");
		btnLogin.setBounds(500,500,100,30);
		
		or=new JLabel("Register here (Only for admin)");
		Font font=new Font("Serif",Font.ITALIC,14);
		or.setFont(font);
		or.setBounds(500,540,200,25);
		
		btnLogin.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent ae)
			{		
				String s1=txtUser.getText();
				String s2=new String(txtPassword.getPassword());
				String s3=(String)jcbType.getSelectedItem();
				DConnection dc=new DConnection();
				String s11="";
				for(int i=0;i<s1.length();i++)
				{
					if(s1.charAt(i)=='\'')
						s11+="\\'";
					else
						s11+=s1.charAt(i);
				}
				s1=s11;
				String s22="";
				for(int i=0;i<s2.length();i++)
				{
					if(s2.charAt(i)=='\'')
						s22+="\\'";
					else
						s22+=s2.charAt(i);
				}
				s2=s22;
				try
				{
					ResultSet rst=dc.executeQuery("select * from users where usertype='"+s3+"' and user_id='"+s1+"' and password='"+s2+"'");
					if(rst.next())
					{
						dc.close();
						dispose();
						new FrmMainFrame(s1);
					}
					else
					{
						dc.close();
						JOptionPane.showMessageDialog(null,"Invalid type,user or password! Retry!","Error",JOptionPane.ERROR_MESSAGE);
						txtUser.requestFocus();
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		btnSignUp=new JButton("SignUp");
		btnSignUp.setBounds(500,565,100,30);

		btnSignUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(cnt==0)
				{
					new FrmCreateUser();
				}
				else
					JOptionPane.showMessageDialog(null,"Sorry! You are not allowed","Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		try
		{
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeQuery("select count(*) from users");
			rst.next();
			cnt=rst.getInt(1);
			dc.close();
			if(cnt==1)
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
		
		add(lblType);add(jcbType);
		add(lblUser);add(txtUser);
		add(lblPassword);add(txtPassword);
		add(btnLogin);
		add(or);
		add(btnSignUp);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);		
	}
}