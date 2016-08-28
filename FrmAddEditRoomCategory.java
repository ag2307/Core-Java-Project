package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditRoomCategory extends JInternalFrame
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblId,lblCategory,lblCount,lblCharge;
	JTextField txtId,txtCategory,txtCount,txtCharge;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int roomcat_id=0;
	FrmAddEditRoomCategory(boolean flag,String query,FrmRoomCategory frc)
	{
		super("Add/Edit RoomCategory",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(5,2));
		
		lblId = new JLabel("Room Category Id");
		lblId.setForeground(new Color(128,0,64));
     	lblId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblCategory = new JLabel("Room Category Name");
		lblCategory.setForeground(new Color(128,0,64));
     	lblCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblCount = new JLabel("Number of Rooms");
		lblCount.setForeground(new Color(128,0,64));
     	lblCount.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblCharge = new JLabel("Room Charge");
		lblCharge.setForeground(new Color(128,0,64));
     	lblCharge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtId=new JTextField();
		txtId.setEditable(false);
		txtId.setBackground(new Color(192,192,192));
		txtId.setForeground(new Color(106,106,106));
     	txtId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtCategory=new JTextField();
		txtCategory.setForeground(new Color(106,106,106));
     	txtCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtCount=new JTextField();
		txtCount.setForeground(new Color(106,106,106));
     	txtCount.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtCharge=new JTextField();
		txtCharge.setForeground(new Color(106,106,106));
     	txtCharge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(roomcat_id) from roomcat");
				rst.next();
				roomcat_id=rst.getInt(1)+1;
				txtId.setText(roomcat_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtId.setText(rst.getString(1));
				txtCategory.setText(rst.getString(2));
				txtCount.setText(rst.getString(3));
				txtCharge.setText(rst.getString(4));
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
		 		String s2=txtCategory.getText();
		 		String s3=txtCount.getText();
		 		String s4=txtCharge.getText();
		 		
		   		 
		        if(flag==true)
		 			dc.executeOther("Insert into roomcat values("+s1+",'"+s2+"',"+s3+","+s4+")");
		 		else
		 			dc.executeOther("update roomcat set roomcat_name='"+s2+"',room_count="+s3+",room_charge="+s4+" where roomcat_id="+s1);
		 		dispose();
		 		frc.reload();
		 		//fd.jsp.repaint();
		 		//fd.repaint();
		 	}
		 });
		 add(lblId);add(txtId);
		 add(lblCategory);add(txtCategory);
		 add(lblCount);add(txtCount);
		 add(lblCharge);add(txtCharge);
		 
		 add(btnSave);add(btnDiscard);
		 setSize(400,300);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}
	
	
		
	
	
	
}
