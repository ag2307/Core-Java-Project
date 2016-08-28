package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class FrmAddEditDoctor extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblId,lblName,lblContact,lblDegree,lblSp,lblFees,lblMailId,lblTimings;
	JTextField txtId,txtName,txtContact,txtDegree,txtFees,txtMailId,txtTimings;
	JComboBox<String> jcbSp;
	JButton btnSave,btnDiscard;
	DConnection dc;
	ResultSet rst;
	int dr_id=0;
	FrmAddEditDoctor(boolean flag,String query,FrmDoctor fd)
	{
		super("Add/Edit Category",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(9,2));
		
		lblId = new JLabel("Doctor Id");
		lblId.setForeground(new Color(128,0,255));
     	lblId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblName = new JLabel("Doctor Name");
		lblName.setForeground(new Color(128,0,255));
     	lblName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblContact = new JLabel("Contact");
		lblContact.setForeground(new Color(128,0,255));
     	lblContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblDegree = new JLabel("Degree");
		lblDegree.setForeground(new Color(128,0,255));
     	lblDegree.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblSp = new JLabel("Specialization");
		lblSp.setForeground(new Color(128,0,255));
     	lblSp.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblFees = new JLabel("Fees");
		lblFees.setForeground(new Color(128,0,255));
     	lblFees.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblMailId = new JLabel("Mail Id");
		lblMailId.setForeground(new Color(128,0,255));
     	lblMailId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblTimings = new JLabel("Timings");
		lblTimings.setForeground(new Color(128,0,255));
     	lblTimings.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtId=new JTextField();
		txtId.setForeground(new Color(106,106,106));
		txtId.setBackground(new Color(192,192,192));
     	txtId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	txtId.setEditable(false);
     	
		txtName=new JTextField();
		txtName.setForeground(new Color(106,106,106));
     	txtName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtContact=new JTextField();
		txtContact.setForeground(new Color(106,106,106));
     	txtContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		txtDegree=new JTextField();
		txtDegree.setForeground(new Color(106,106,106));
     	txtDegree.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		jcbSp=new JComboBox<String>();
		jcbSp.setForeground(new Color(106,106,106));
     	jcbSp.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		jcbSp.addItem("Physician");
		jcbSp.addItem("Orthopaedic");
		jcbSp.addItem("Paediatrician");
		
		txtFees=new JTextField();
		txtFees.setForeground(new Color(106,106,106));
     	txtFees.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtMailId=new JTextField();
		txtMailId.setForeground(new Color(106,106,106));
     	txtMailId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtTimings=new JTextField();
		txtTimings.setForeground(new Color(106,106,106));
     	txtTimings.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(dr_id) from doctor");
				rst.next();
				dr_id=rst.getInt(1)+1;
				txtId.setText(dr_id+"");
				dc.close();
				btnSave=new JButton("Save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtId.setText(rst.getString(1));
				txtName.setText(rst.getString(2));
				txtContact.setText(rst.getString(3));
				txtDegree.setText(rst.getString(4));
				jcbSp.setSelectedItem(rst.getString(5));
				txtFees.setText(rst.getString(6));
				txtMailId.setText(rst.getString(7));
				txtTimings.setText(rst.getString(8));
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
		 		String s3=txtContact.getText();
		 		String s4=txtDegree.getText();
		 		String s5=(String)jcbSp.getSelectedItem();
		 		String s6=txtFees.getText();
		 		String s7=txtMailId.getText();
		 		String s8=txtTimings.getText();
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
					JOptionPane.showMessageDialog(fd,"Contact can't be blank","Error",JOptionPane.ERROR_MESSAGE);
					txtContact.requestFocus();
			     	return;
		        }
		        else if(s4.isEmpty())
		        {
					JOptionPane.showMessageDialog(fd,"Degree can't be blank","Error",JOptionPane.ERROR_MESSAGE);
					txtDegree.requestFocus();
					return;
		        }
		        else if(s5.isEmpty())
		        {
					JOptionPane.showMessageDialog(fd,"Specialization can't be blank","Error",JOptionPane.ERROR_MESSAGE);
					jcbSp.requestFocus();
					return;
		        }
		        else if(s6.isEmpty())
		        {
					JOptionPane.showMessageDialog(fd,"Fees can't be blank","Error",JOptionPane.ERROR_MESSAGE);
					txtFees.requestFocus();
					return;
		        }
		        else if(s8.isEmpty())
		        {
					JOptionPane.showMessageDialog(fd,"Timings can't be blank","Error",JOptionPane.ERROR_MESSAGE);
					txtTimings.requestFocus();
					return;
		        }
		        if(flag==true)
					dc.executeOther("Insert into doctor values("+s1+",'"+s2+"','"+s3+"','"+s4+"','"+s5+"',"+s6+",'"+s7+"','"+s8+"')");
		 		else
					dc.executeOther("update doctor set dr_name='"+s2+"',contact='"+s3+"',degree='"+s4+"',sp='"+s5+"',fees="+s6+",mail_id='"+s7+"',timings='"+s8+"' where dr_id="+s1);
		 		dispose();
		 		fd.reload();
		 	}	
		 });
		 add(lblId);add(txtId);
		 add(lblName);add(txtName);
		 add(lblContact);add(txtContact);
		 add(lblDegree);add(txtDegree);
		 add(lblSp);add(jcbSp);
		 add(lblFees);add(txtFees);
		 add(lblMailId);add(txtMailId);
		 add(lblTimings);add(txtTimings);
		 add(btnSave);add(btnDiscard);
		 setSize(280,300);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
	}	
}
