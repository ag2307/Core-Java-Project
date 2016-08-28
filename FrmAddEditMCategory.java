package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class FrmAddEditMCategory extends JInternalFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblPCategoryId,lblPCategoryName;
	JTextField txtPCategoryId,txtPCategoryName;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int ct_id=0;
	
	FrmAddEditMCategory(boolean flag,String query,FrmMCategory fmc)
	{
		super("Add/Edit Procedure Category",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(3,2));
		
		lblPCategoryId = new JLabel("Category Id");
		lblPCategoryId.setForeground(new Color(0,128,64));
     	lblPCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblPCategoryName = new JLabel("Category Name");
		lblPCategoryName.setForeground(new Color(0,128,64));
     	lblPCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtPCategoryId=new JTextField();
		txtPCategoryId.setEditable(false);
		txtPCategoryId.setBackground(new Color(192,192,192));
		txtPCategoryId.setForeground(new Color(106,106,106));
     	txtPCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPCategoryName=new JTextField();
		txtPCategoryName.setForeground(new Color(106,106,106));
     	txtPCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(ct_id) from mcategory");
				rst.next();
				ct_id=rst.getInt(1)+1;
				txtPCategoryId.setText(ct_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtPCategoryId.setText(rst.getString(1));
				txtPCategoryName.setText(rst.getString(2));
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
		 		String s1=txtPCategoryId.getText();
		 		String s2=txtPCategoryName.getText();
		 		
		 		if(flag==true)
		 			dc.executeOther("Insert into mcategory values("+s1+",'"+s2+"')");
		 		else
		 			dc.executeOther("update mcategory set ct_name='"+s2+"' where ct_id="+s1);
		 		dispose();
		 		fmc.reload();
		 		//fpc.jsp.repaint();
		 		//fpc.repaint();
		 	}	
		 });
		 add(lblPCategoryId);add(txtPCategoryId);
		 add(lblPCategoryName);add(txtPCategoryName);
		 add(btnSave);add(btnDiscard);
		 setSize(300,200);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}
}

		