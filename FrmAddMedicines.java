package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.awt.event.FocusListener;

class FrmAddMedicines extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblDate,lblPatient_id,lblIPD_no,lblRoom_no,lblMCategory,lblMedicine,lblPrice,lblQuantity,lblAmount;
	JTextField txtDate,txtPatient_id,txtRoom_no,txtPrice,txtAmount;
	
	JComboBox<String> jcbmc;
	JComboBox<String> jcbmd;
	TextField txtID,txtQuantity;
	final DefaultListModel<String> model = new DefaultListModel<String>();
	
	JScrollPane jsp1;
	DConnection dc;
	JButton btnSave,btnDiscard;
	ResultSet rst;
	Date date;
	FocusListener myFocusListener;
	FrmAddMedicines()
	{
		super("Add/Edit Category",true,true,true,true);
		FrmAddMedicines p11=this;
	 	Date date = new Date();
	 	JList<String> list1=new JList<String>(model);
		list1.setVisibleRowCount(5);
		list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setResizable(false);
		dc=new DConnection();
		JPanel p1=new JPanel(new GridLayout(10,2));
		lblDate = new JLabel("Date");
		lblIPD_no=new JLabel("IPD");
		lblPatient_id=new JLabel("Patient name");
		lblRoom_no=new JLabel("room number");
		lblMCategory=new JLabel("Medicine category");
		lblMedicine=new JLabel("medicine");
		lblPrice=new JLabel("Price");
		lblQuantity=new JLabel("quantity");
		lblAmount=new JLabel("Amount");
			
		txtDate = new JTextField(date.toString());
		txtID=new TextField();
		txtDate.setEditable(false);
		txtPatient_id=new JTextField(""); txtPatient_id.setEditable(false);
		txtRoom_no=new JTextField("");		txtRoom_no.setEditable(false);
		txtPrice=new JTextField("0");  txtPrice.setEditable(false);
		txtQuantity=new TextField("0");
		txtAmount=new JTextField("0"); txtAmount.setEditable(false);
		jcbmc=new JComboBox<String>(); jcbmc.setEditable(false);
		jcbmd=new JComboBox<String>();
		txtAmount.setText("0");
		try{
      		int i=0;
      		ResultSet rst2=dc.executeQuery("select * from ipdpatient where dod = 'Not Discharged Yet' order by ipd_no");
      		while(rst2.next())
			{
			//	model.add(i,rst.getString(2));
			//	i++;
				ResultSet rst3=dc.executeQuery("select * from patient where pat_id="+rst2.getString("pat_id"));
				rst3.next();
				String s1=rst2.getString("ipd_no")+"-"+rst3.getString("pat_name");
				model.add(i,s1);
				i++;
			}
      	}
      	catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		btnSave=new JButton("save");
		btnDiscard=new JButton("discard");
		btnDiscard.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		dispose();
		 	}
		});
		int cat=0;
		try
		{
			rst=dc.executeQuery("select * from mcategory");
			while(rst.next())
			{
				if(cat==0)
					cat=rst.getInt(1);
				jcbmc.addItem(rst.getString(2));
			}
			rst=dc.executeQuery("select * from medicine where ct_id="+cat);
			while(rst.next())
			{
				jcbmd.addItem(rst.getString(2));
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		jcbmc.addItemListener(new ItemListener()
		{
		 	public void itemStateChanged(ItemEvent fe)
		 	{
		 		jcbmd.removeAllItems();
		 		try
				{
					System.out.println((String)jcbmc.getSelectedItem());
					rst=dc.executeQuery("select * from medicine where md_cat='"+(String)jcbmc.getSelectedItem()+"'");
					while(rst.next())
					{
						jcbmd.addItem(rst.getString(2));
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
		 	}
		});
		
		jcbmd.addFocusListener (new FocusListener () 
		{
   			 public void focusLost(FocusEvent e) 
   			 {
   			 	try
				{
					rst=dc.executeQuery("select price from medicine where md_cat='"+(String)jcbmc.getSelectedItem()+"'and md_name='"+(String)jcbmd.getSelectedItem()+"'");
					if(rst.next()==true)
					{
						txtPrice.setText(rst.getString(1));
					}
				}
				catch(SQLException le)
				{
					le.printStackTrace();
				}
		  	}
		  	 public void focusGained(FocusEvent e){}
		});
		
	
		txtQuantity.addTextListener(new TextListener()
		{
		 	public void textValueChanged(TextEvent te)
		 	{
		 		txtAmount.setText((Integer.parseInt(txtQuantity.getText())*Integer.parseInt(txtPrice.getText()))+"");
		 	}
		});
		

		btnSave.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		String s1=(String)date.toString();
		 		String s3="",s4="";
		 		String s[]=((String)list1.getSelectedValue()).split("-");
		 		ResultSet rst2=dc.executeQuery("select * from medicine where md_name='"+(String)jcbmd.getSelectedItem()+"'");
		 		try{
		 		rst2.next();
		 		s3=rst2.getString("ct_id");
		 		s4=rst2.getString("md_id");
		 		}
		 		catch(SQLException e){
		 		}
		 		int s5=Integer.parseInt(txtQuantity.getText());
		 		int s6=Integer.parseInt(txtAmount.getText());
		 	   
		       if(s4.isEmpty())
		        {
                 JOptionPane.showMessageDialog(null,"please enter medicine name","Error",JOptionPane.ERROR_MESSAGE);

			     jcbmc.requestFocus();
			     return;
		        }
		       
		       
		       
		       
		       
		 			dc.executeOther("Insert into ipdmedicine values('"+s1+"',"+s[0]+",'"+s3+"','"+s4+"',"+s5+","+s6+")");
		 	dispose();
		 	//	null.reload();
		 		//null.jsp.repaint();
		 		//null.repaint();*/
		 	}	
		 });
		 
		 txtID.addTextListener(new TextListener()
			{  
		    public void textValueChanged(TextEvent te)
		    {
		    			    	
				int len=model.getSize();
				
				int count=txtID.getText().length(),k=0;
				if(count!=0)
				{
					list1.clearSelection();
					int value[]={-1,-1,-1,-1};
					for(int j=0;j<len;j++)
					{
						String s1=(String)model.getElementAt(j);
						if(s1.length()>=count)
						{
					
							if(s1.substring(0,count).equalsIgnoreCase(txtID.getText()))
							{
								
								value[k]=j; 
								k++;
							}
						}
					}
					//	list1.ensureIndexIsVisible(value[0]);
						list1.setSelectedIndices(value);
						
				}
				else	list1.clearSelection();	
		    }
		});
		
		
		
		txtID.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
			//	txtPatient.setBackground(Color.yellow);
				jsp1.setVisible(true);
				p11.revalidate();
				p11.setSize(p11.getSize().width+1,p11.getSize().height+1);
			}
			
			public void focusLost(FocusEvent fe)
			{
				jsp1.setVisible(false);
				ResultSet rst;
				if(list1.isSelectionEmpty()==false){
					}		
				else{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					String s2=list_value[2];
					ResultSet rst2=dc.executeQuery("select * from ipdpatient where ipd_no="+list_value[0]);
				
					try{
					rst2.next();//done to avoid duplicate name errors
					rst=dc.executeQuery("select * from patient where pat_name='"+s2+"' and pat_id="+rst2.getString("pat_id"));
			 		while(rst.next()){
		 			txtPatient_id.setText(rst.getString("pat_name"));
		 			txtRoom_no.setText(rst2.getString("room_number"));
					}	
					}
					catch(SQLException e){
				
							}
			}}
			
		});
		
		list1.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent me)
			{
				txtID.requestFocus();
				String list_value[]=((String)list1.getSelectedValue()).split("-");
				txtID.setText(list_value[0]);
				txtPatient_id.setText(list_value[1]);
				try{
					ResultSet rst3=dc.executeQuery("select * from ipdpatient where ipd_no="+list_value[0]);
					rst3.next();
					txtRoom_no.setText(rst3.getString("room_number"));}
					catch(SQLException e){
					}
				jsp1.setVisible(false);
			}
			public void mousePressed(MouseEvent me){}
			public void mouseReleased(MouseEvent me){}
			public void mouseEntered(MouseEvent me){}
			public void mouseExited(MouseEvent me){}
			
		});
		list1.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent ke)
			{
				
			}
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtID.setText(list_value[0]);
					txtPatient_id.setText(list_value[1]);
				try{
					ResultSet rst3=dc.executeQuery("select * from ipdpatient where ipd_no="+list_value[0]);
					rst3.next();
					txtRoom_no.setText(rst3.getString("room_number"));}
					catch(SQLException e){
					}
					
					jsp1.setVisible(false);
				}
			}
			public void keyReleased(KeyEvent ke){}
		});		
		
		txtID.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent ke)
			{
				
			}
			public void keyPressed(KeyEvent ke)
			{//	if(list1.)
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtID.setText(list_value[0]);
					txtPatient_id.setText(list_value[1]);
				try{
					ResultSet rst3=dc.executeQuery("select * from ipdpatient where ipd_no="+list_value[0]);
					rst3.next();
					txtRoom_no.setText(rst3.getString("room_number"));}
					catch(SQLException e){
					}
					jsp1.setVisible(false);
				}
				else if(ke.getKeyCode()==KeyEvent.VK_UP)
				{
					int index=list1.getSelectedIndex();
					if(index!=0)
						index-=1;
					list1.setSelectedIndex(index);
					
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtID.setText(list_value[0]);	
				}
				else if(ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					int index=list1.getSelectedIndex();
					if(index!=model.getSize())
						index+=1;
					list1.setSelectedIndex(index);
					
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtID.setText(list_value[0]);
				}
				else if(ke.getKeyCode()==KeyEvent.VK_TAB)
				{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtID.setText(list_value[0]);
					
					jsp1.setVisible(false);
				}
			}
			public void keyReleased(KeyEvent ke){}
		}); 
		
		
		jsp1=new JScrollPane(list1);
		 jsp1.setVisible(false);
		
		
		p1.add(lblDate);p1.add(txtDate);
		p1.add(lblIPD_no);p1.add(txtID);
		p1.add(lblPatient_id);p1.add(txtPatient_id);
		p1.add(lblRoom_no);p1.add(txtRoom_no);
		p1.add(lblMCategory);p1.add(jcbmc);
		p1.add(lblMedicine);p1.add(jcbmd);
		p1.add(lblPrice);p1.add(txtPrice);
		p1.add(lblQuantity);p1.add(txtQuantity);
		p1.add(lblAmount);p1.add(txtAmount);
		p1.add(btnSave);p1.add(btnDiscard);
		add(p1,"Center");
		add(jsp1,"East");
		 setSize(500,500);
		 setVisible(true);
		 setLocation(CommonMethods.getCenterPoint(getSize()));
		jsp1.setVisible(false);
	}
}