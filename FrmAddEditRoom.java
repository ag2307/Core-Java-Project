package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditRoom extends JInternalFrame
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblRoomId,lblRoomNumber,lblCategory,lblBedCount,lblCharge,lblDescription;
	JTextField txtRoomId,txtRoomNumber,txtBedCount,txtCharge;
	JTextArea txtarDescription;
	JComboBox<String> jcbCat;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int room_id=0;
	FrmAddEditRoom(boolean flag,String query,FrmRoom fr)
	{
		super("Add/Edit Room",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(7,2));
		
		lblRoomId = new JLabel("Room Id");
		lblRoomId.setForeground(new Color(128,0,64));
     	lblRoomId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblCategory = new JLabel("Category Name");
		lblCategory.setForeground(new Color(128,0,64));
     	lblCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblRoomNumber=new JLabel("Room Number");
		lblRoomNumber.setForeground(new Color(128,0,64));
     	lblRoomNumber.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblBedCount = new JLabel("Number of Beds");
		lblBedCount.setForeground(new Color(128,0,64));
     	lblBedCount.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblCharge = new JLabel("Room Charge");
		lblCharge.setForeground(new Color(128,0,64));
     	lblCharge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblDescription = new JLabel("Description");
		lblDescription.setForeground(new Color(128,0,64));
     	lblDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtRoomId=new JTextField();
		txtRoomId.setBackground(new Color(192,192,192));
		txtRoomId.setForeground(new Color(106,106,106));
     	txtRoomId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	txtRoomId.setEditable(false);
		
		txtRoomNumber=new JTextField();
		txtRoomNumber.setForeground(new Color(106,106,106));
     	txtRoomNumber.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		
		txtBedCount=new JTextField();
		txtBedCount.setForeground(new Color(106,106,106));
     	txtBedCount.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtCharge=new JTextField();
		txtCharge.setForeground(new Color(106,106,106));
     	txtCharge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtarDescription=new JTextArea();
		txtarDescription.setForeground(new Color(106,106,106));
     	txtarDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		jcbCat=new JComboBox<String>();
		jcbCat.setForeground(new Color(106,106,106));
     	jcbCat.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		try
		{
			{
			
			rst=dc.executeQuery("select (roomcat_name) from roomcat");
			while(rst.next())
				{
					jcbCat.addItem(""+rst.getString(1));
				}
			
			}
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(room_id) from room");
				rst.next();
				room_id=rst.getInt(1)+1;
				txtRoomId.setText(room_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtRoomId.setText(rst.getString(1));
				txtRoomNumber.setText(rst.getString(2));
				jcbCat.setSelectedItem(rst.getString(3));
				txtBedCount.setText(rst.getString(4));
				txtarDescription.setText(rst.getString(5));
				txtCharge.setText(rst.getString(6));
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
		 		String s1=txtRoomId.getText();
		 		String s2=txtRoomNumber.getText();
		 		String s3=(String)jcbCat.getSelectedItem();
		 		String s4=txtBedCount.getText();
		 		String s5=txtarDescription.getText();
		 		String s6=txtCharge.getText();
		 		
		   		 
		        if(flag==true)
		 			dc.executeOther("Insert into room values("+s1+","+s2+",'"+s3+"',"+s4+",'"+s5+"',"+s6+")");
		 		else
		 			dc.executeOther("update room set room_number="+s2+",room_category='"+s3+"',room_count="+s4+",room_charges="+s6+",room_description='"+s5+"' where room_id="+s1);
		 		dispose();
		 		fr.reload();
		 		//fd.jsp.repaint();
		 		//fd.repaint();
		 	}
		 });
		 add(lblRoomId);add(txtRoomId);
		 add(lblRoomNumber);add(txtRoomNumber);
		 add(lblCategory);add(jcbCat);
		 add(lblBedCount);add(txtBedCount);
		 add(lblCharge);add(txtCharge);
		 add(lblDescription);add(txtarDescription);
		 
		 add(btnSave);add(btnDiscard);
		 setSize(400,300);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}
	
	
		
	
	
	
}
