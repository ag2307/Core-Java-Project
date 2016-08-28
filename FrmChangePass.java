package hms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class FrmChangePass extends JInternalFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblType,lblName,lblOld,lblNew,lblRe;
	JTextField txtName;
	JComboBox<String> jcbType;
	JPasswordField txtOld,txtNew,txtRe;
	String arr[]={"admin","operator","doctor"};
	JButton btnChange,btnExit;
	JPanel p1,p2;
	DConnection dc;
	String user;
	FrmChangePass(String user,FrmMainFrame mainframe)
	{
		super("Change Password",true,true,true,true);
		this.user=user;
		lblType=new JLabel("User Type");
		lblName=new JLabel("User Name");
		lblOld=new JLabel("Old Password");
		lblNew=new JLabel("New Password");
		lblRe=new JLabel("Retype");
		
		txtName=new JTextField();
		txtName.setText(user);
		txtName.setEditable(false);
		txtOld=new JPasswordField();
		txtNew=new JPasswordField();
		txtRe=new JPasswordField();
		
		dc=new DConnection();
		jcbType=new JComboBox<>(arr);
		try
		{
			ResultSet rst=dc.executeQuery("select usertype from users where user_id='"+user+"'");
			rst.next();
			String s=rst.getString(1);
			jcbType.setSelectedItem(s);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		jcbType.setEnabled(false);
		
		btnChange=new JButton("Change Password");
		btnChange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String type=(String)jcbType.getSelectedItem();
				String name=txtName.getText();
				String old=new String(txtOld.getPassword());
				
				String newPass=new String(txtNew.getPassword());
				String re=new String(txtRe.getPassword());
				String oldx="";
				
				if(old.isEmpty() || newPass.isEmpty() || re.isEmpty())
					JOptionPane.showMessageDialog(null,"All Fields Are Mandatry To Fill","Error",JOptionPane.ERROR_MESSAGE);
				else if(!(newPass.length()>=8 && newPass.length()<=15))
					JOptionPane.showMessageDialog(null,"Password Should be 8 to 15 Characters Long","Error",JOptionPane.ERROR_MESSAGE);
				else if(!newPass.equals(re))
					JOptionPane.showMessageDialog(null,"Password and Re Type cannot match","Error",JOptionPane.ERROR_MESSAGE);
				else
				{
					try
					{
						ResultSet rst=dc.executeQuery("select password from users where usertype='"+type+"' and user_id='"+name+"'");
						rst.next();
						oldx=rst.getString(1);
						dc.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					if(!(oldx.equals(old)))
						JOptionPane.showMessageDialog(null,"Invalid Old Password! Retry!","Error",JOptionPane.ERROR_MESSAGE);
					else
					{
						dc.executeOther("update users set password='"+newPass+"' where user_id='"+name+"' and usertype='"+type+"'");
						dispose();
						mainframe.dispose();
						String s="Welcome ";
						s+=type;
						s+="! Your Id Is ";
						s+=name;
						s+=" and new Password is ";
						s+=newPass;
						JOptionPane.showMessageDialog(null,s,"Changed Sucessfully",JOptionPane.INFORMATION_MESSAGE);
				   		new FrmLogin();
				   	}
				}
			}			
		});
		btnChange.setBackground(Color.blue);
		btnChange.setForeground(Color.white);
		
		btnExit=new JButton("Cancel");
		btnExit.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		dispose();
		 	}	
		});
		btnExit.setBackground(Color.red);
		btnExit.setForeground(Color.white);
	
		p1=new JPanel();
		p1.setLayout(new GridLayout(5,2));
		p1.add(lblType);p1.add(jcbType);
		p1.add(lblName);p1.add(txtName);
		p1.add(lblOld);p1.add(txtOld);
		p1.add(lblNew);p1.add(txtNew);
		p1.add(lblRe);p1.add(txtRe);
		p1.setVisible(true);
	
		p2=new JPanel();
		p2.setLayout(new GridLayout(1,2));
		p2.add(btnExit);p2.add(btnChange);
	
		add(p1,"Center");
		add(p2,"South");
		setTitle("Change Password");
		setVisible(true);
		setSize(300,300);
		setResizable(false);
		setLocation(CommonMethods.getCenterPoint(getSize()));
	}
}