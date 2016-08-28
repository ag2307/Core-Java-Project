package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditPProcedure extends JInternalFrame 
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblPProcedureId,lblPProcedureName,lblPCategory,lblPPrice,lblPDescription;
	JTextField txtPProcedureId,txtPProcedureName,txtPPrice,txtPDescription;
	JComboBox<String> jcbCn;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int p_id=0;
	FrmAddEditPProcedure(boolean flag,String query,FrmPProcedure fpp)
	{
		super("Add/Edit Procedure Procedure",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(6,2));
		
		lblPProcedureId = new JLabel("Procedure Id");
		lblPProcedureId.setForeground(new Color(64,0,0));
     	lblPProcedureId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblPProcedureName = new JLabel("Procedure Name");
		lblPProcedureName.setForeground(new Color(64,0,0));
     	lblPProcedureName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblPCategory = new JLabel("Category");
		lblPCategory.setForeground(new Color(64,0,0));
     	lblPCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblPPrice = new JLabel("Price");
		lblPPrice.setForeground(new Color(64,0,0));
     	lblPPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblPDescription = new JLabel("Description");
		lblPDescription.setForeground(new Color(64,0,0));
     	lblPDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPProcedureId = new JTextField();
		txtPProcedureId.setForeground(new Color(49,49,49));
		txtPProcedureId.setBackground(new Color(192,192,192));
		txtPProcedureId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		txtPProcedureId.setEditable(false);
		
		txtPProcedureName = new JTextField();
		txtPProcedureName.setForeground(new Color(106,106,106));
		txtPProcedureName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPPrice = new JTextField();
		txtPPrice.setForeground(new Color(106,106,106));
		txtPPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPDescription = new JTextField();
		txtPDescription.setForeground(new Color(106,106,106));
		txtPDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		rst=dc.executeQuery("select * from pcat");
		jcbCn=new JComboBox<String>();
		try{
		while(rst.next()){
			jcbCn.addItem(rst.getString("pcat_name"));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(p_id) from pprocedure");
				rst.next();
				p_id=rst.getInt(1)+1;
				txtPProcedureId.setText(p_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtPProcedureId.setText(rst.getString(1));
				txtPProcedureName.setText(rst.getString(2));
				txtPPrice.setText(rst.getString(3));
				txtPDescription.setText(rst.getString(4));
				jcbCn.setSelectedItem(rst.getString(5));
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
		 		String s1=txtPProcedureId.getText();
		 		String s2=txtPProcedureName.getText();
		 		String s3=txtPPrice.getText();
		 		String s4=txtPDescription.getText();
		 		rst=dc.executeQuery("select pcat_id from pcat where pcat_name='"+(String)jcbCn.getSelectedItem()+"'");
		 		int s5=0;
		 		try{
					while(rst.next()){
						s5=rst.getInt("pcat_id");
						}
					}
				catch(SQLException e){
					e.printStackTrace();
					}
		 		if(flag==true)
		 			dc.executeOther("Insert into pprocedure values("+s1+",'"+s2+"',"+s3+",'"+s4+"',"+s5+")");
		 		else
		 			dc.executeOther("update pprocedure set p_name='"+s2+"',price="+s3+",description='"+s4+"',pcat_id="+s5+" where p_id="+s1);
		 		dispose();
		 		fpp.reload();
		 		//fpp.jsp.repaint();
		 		//fpp.repaint();
		 	}	
		 });
		 add(lblPProcedureId);add(txtPProcedureId);
		 add(lblPProcedureName);add(txtPProcedureName);
		 add(lblPCategory);add(jcbCn);
		 add(lblPPrice);add(txtPPrice);
		 add(lblPDescription);add(txtPDescription);
		 add(btnSave);add(btnDiscard);
		 setSize(300,300);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}
}
