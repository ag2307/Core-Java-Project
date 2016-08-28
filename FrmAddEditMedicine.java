package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditMedicine extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblId,lblName,lblCategory,lblUnit,lblPrice;
	JTextField txtId,txtName,txtPrice;
	JComboBox<String> jcbct;
	JComboBox<String> jcbun;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int md_id=0;
	
	FrmAddEditMedicine(boolean flag,String query,FrmMedicines fd)
	{	
		super("Add/Edit Medicine Category",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(6,2));
		
		lblId = new JLabel("Medicine Id");
		lblId.setForeground(new Color(0,128,64));
     	lblId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblName = new JLabel("Medicine Name");
		lblName.setForeground(new Color(0,128,64));
     	lblName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(0,128,64));
     	lblCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblUnit = new JLabel("Unit");
		lblUnit.setForeground(new Color(0,128,64));
     	lblUnit.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(0,128,64));
     	lblPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtId=new JTextField();
		txtId.setBackground(new Color(192,192,192));
		txtId.setForeground(new Color(106,106,106));
     	txtId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		txtId.setEditable(false);
		
		txtName=new JTextField();
		txtName.setForeground(new Color(106,106,106));
     	txtName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtPrice=new JTextField();
		txtPrice.setForeground(new Color(106,106,106));
     	txtPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		jcbct=new JComboBox<String>();
		jcbct.setForeground(new Color(106,106,106));
     	jcbct.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		

		try
			{
				rst=dc.executeQuery("select * from mcategory");
				
				while(rst.next())
				{
					jcbct.addItem(rst.getString(2));
				}
				dc.close();
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
			
		jcbun=new JComboBox<String>();
		jcbun.setForeground(new Color(106,106,106));
     	jcbun.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		jcbun.addItem("pcs");
		jcbun.addItem("kg");
		jcbun.addItem("ltr");
		jcbun.addItem("ml");
		
		
	try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(md_id) from medicine");
				rst.next();
				md_id=rst.getInt(1)+1;
				txtId.setText(md_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtId.setText(rst.getString(1));
				txtName.setText(rst.getString(2));
				jcbct.setSelectedItem(rst.getString(3));
				jcbun.setSelectedItem(rst.getString(4));
				txtPrice.setText(rst.getString(5));
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
		 	{		try{
		 	
						 		String s1=txtId.getText();
						 		String s2=txtName.getText();
						 		String s4=(String)jcbun.getSelectedItem();
						 		String s5=txtPrice.getText();
						 		String s3=(String)jcbct.getSelectedItem();
						 		int s6=0;
						 	
								
									rst=dc.executeQuery("select ct_id from mcategory where ct_name='"+(String)jcbct.getSelectedItem()+"'");
								rst.next();
								s6=rst.getInt(1);
							
								dc.close();
							
						
						 	    if(s1.isEmpty())
						        {
				                 JOptionPane.showMessageDialog(fd,"Id can't be blank","Error",JOptionPane.ERROR_MESSAGE);
				
							     txtId.requestFocus();
							     return;
						        }
						        else if(s2.isEmpty())
						        {
				                 JOptionPane.showMessageDialog(fd,"Name can't be blank","Error",JOptionPane.ERROR_MESSAGE);
				
							     txtName.requestFocus();
							     return;
						        }
						       
						        else if(s3.isEmpty())
						        {
				                 JOptionPane.showMessageDialog(fd,"Category can't be blank","Error",JOptionPane.ERROR_MESSAGE);
				
							     jcbct.requestFocus();
							     return;
						        }
						        else if(s4.isEmpty())
						        {
				                 JOptionPane.showMessageDialog(fd,"Unit can't be blank","Error",JOptionPane.ERROR_MESSAGE);
				
							     jcbun.requestFocus();
							     return;
				                
						        }
						        
						        if(flag==true)
						 			dc.executeOther("Insert into medicine values("+s1+",'"+s2+"','"+s3+"','"+s4+"','"+s5+"',"+s6+")");
						 		else
						 			dc.executeOther("update medicine set md_name='"+s2+"',md_cat='"+s3+"',unit='"+s4+"',price='"+s5+"',ct_id="+s6+" where md_id="+s1);
						 		dispose();
						 		fd.reload();
						 		//fd.jsp.repaint();
						 		//fd.repaint();
		 	}
		 	catch(SQLException e)
		{
			e.printStackTrace();
		}
		 	}	
		 });
		
		add(lblId);add(txtId);
		 add(lblName);add(txtName);
		add(lblCategory);add(jcbct);	
		 add(lblUnit);add(jcbun);
		 add(lblPrice);add(txtPrice);
		 add(btnSave);add(btnDiscard);
		 setSize(300,200);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));

		}
}