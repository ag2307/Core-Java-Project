package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditPatient extends JInternalFrame 
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblId,lblName,lblContact,lblAddress,lblMailId,lblAge,lblGender,lblF_Name;
	JTextField txtId,txtName,txtContact,txtMailId,txtAge,txtF_Name;
	JTextArea txtarAddress;
	JRadioButton rbGen_Male,rbGen_Female;
	ButtonGroup bg;
	JPanel p1;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int pat_id=0;
	FrmAddEditPatient(boolean flag,String query,FrmPatient fp)
	{
		super("Add/Edit Patient",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		p1=new JPanel();
		setLayout(new GridLayout(9,2));
		
		lblId = new JLabel("Patient Id");
		lblId.setForeground(new Color(64,0,0));
     	lblId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblName = new JLabel("Patient Name");
		lblName.setForeground(new Color(64,0,0));
     	lblName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblContact = new JLabel("Contact");
		lblContact.setForeground(new Color(64,0,0));
     	lblContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(64,0,0));
     	lblAddress.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblMailId = new JLabel("Mail Id");
		lblMailId.setForeground(new Color(64,0,0));
     	lblMailId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblAge=new JLabel("Age");
		lblAge.setForeground(new Color(64,0,0));
     	lblAge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblGender=new JLabel("Gender");
		lblGender.setForeground(new Color(64,0,0));
     	lblGender.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblF_Name=new JLabel("Father's Name");
		lblF_Name.setForeground(new Color(64,0,0));
     	lblF_Name.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtId=new JTextField();
		txtId.setEditable(false);
		txtId.setForeground(new Color(49,49,49));
		txtId.setBackground(new Color(192,192,192));
		txtId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtName=new JTextField();
		txtName.setForeground(new Color(106,106,106));
		txtName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtContact=new JTextField();
		txtContact.setForeground(new Color(106,106,106));
		txtContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtarAddress=new JTextArea();
		txtarAddress.setForeground(new Color(106,106,106));
		txtarAddress.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtMailId=new JTextField();
		txtMailId.setForeground(new Color(106,106,106));
		txtMailId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtAge=new JTextField();
		txtAge.setForeground(new Color(106,106,106));
		txtAge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtF_Name=new JTextField();
		txtF_Name.setForeground(new Color(106,106,106));
		txtF_Name.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		rbGen_Male=new JRadioButton("Male");
		rbGen_Male.setForeground(new Color(106,106,106));
		rbGen_Male.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		rbGen_Female=new JRadioButton("Female");
		rbGen_Female.setForeground(new Color(106,106,106));
		rbGen_Female.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		bg=new ButtonGroup();
		bg.add(rbGen_Male);bg.add(rbGen_Female);
		
		p1.add(rbGen_Male);p1.add(rbGen_Female);
		try
		{
		
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(pat_id) from patient");
				rst.next();
				pat_id=rst.getInt(1)+1;
				txtId.setText(pat_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtId.setText(rst.getString(1));
				txtName.setText(rst.getString(2));
				txtAge.setText(rst.getString(3));
				txtF_Name.setText(rst.getString(4));
				if(rst.getString(5).equals("Male"))
				rbGen_Male.setSelected(true);
				else
				rbGen_Female.setSelected(true);
				txtarAddress.setText(rst.getString(6));
				txtContact.setText(rst.getString("contact"));
				txtMailId.setText(rst.getString("mail_id"));
				btnSave=new JButton("Update");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		btnDiscard=new JButton("Discard");
		btnDiscard.setForeground(Color.white);
		btnDiscard.setBackground(Color.red);
		btnDiscard.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		btnDiscard.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		dispose();
		 	}
		});
		 
		btnSave.setForeground(Color.white);
		btnSave.setBackground(Color.blue);
		btnSave.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		btnSave.addActionListener(new ActionListener()
	
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		String s1=txtId.getText();
		 		String s2=txtName.getText();
		 		String s3=txtAge.getText();
		 		String s4=txtF_Name.getText();
		 		String s5="";
		 		if(rbGen_Male.isSelected())
		 		s5="Male";
		 		else if(rbGen_Female.isSelected())
		 		s5="Female";
		 		String s6=txtarAddress.getText();
		 		String s7=txtContact.getText();
		 		String s8=txtMailId.getText();
		 		
		 		if(flag==true)
		 			dc.executeOther("Insert into patient values("+s1+",'"+s2+"',"+s3+",'"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')");
		 		else
		 			dc.executeOther("update patient set pat_name='"+s2+"',age="+s3+",father_name='"+s4+"',gender='"+s5+"',address='"+s6+"',contact='"+s7+"',mail_id='"+s8+"' where pat_id="+s1);
		 		dispose();
		 		fp.reload();
		 		//fp.jsp.repaint();
		 		//fp.repaint();
		 	}	
		 });
		 add(lblId);add(txtId);
		 add(lblName);add(txtName);
		 add(lblAge);add(txtAge);
		 add(lblF_Name);add(txtF_Name);
		 add(lblGender);add(p1);
		 add(lblAddress);add(txtarAddress);
		 add(lblContact);add(txtContact);
		 add(lblMailId);add(txtMailId);
		 add(btnSave);add(btnDiscard);
		 setSize(350,350);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}
}
