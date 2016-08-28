package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class FrmAddEditPCategory extends JInternalFrame 
	
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
	int pcat_id=0;
	FrmAddEditPCategory(boolean flag,String query,FrmPCategories fpc)
	{
		super("Add/Edit Procedure Category",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(3,2));
		
		lblPCategoryId = new JLabel("Category Id");
		lblPCategoryId.setForeground(new Color(64,0,0));
     	lblPCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblPCategoryName = new JLabel("Category Name");
		lblPCategoryId.setForeground(new Color(64,0,0));
     	lblPCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPCategoryId=new JTextField();
		txtPCategoryId.setForeground(new Color(49,49,49));
		txtPCategoryId.setBackground(new Color(192,192,192));
		txtPCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		txtPCategoryId.setEditable(false);
		
		txtPCategoryName=new JTextField();
		txtPCategoryName.setForeground(new Color(106,106,106));
		txtPCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(pcat_id) from pcat");
				rst.next();
				pcat_id=rst.getInt(1)+1;
				txtPCategoryId.setText(pcat_id+"");
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
		 			dc.executeOther("Insert into pcat values("+s1+",'"+s2+"')");
		 		else
		 			dc.executeOther("update pcat set pcat_name='"+s2+"' where pcat_id="+s1);
		 		dispose();
		 		fpc.reload();
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
