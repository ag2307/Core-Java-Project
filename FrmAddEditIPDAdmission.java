package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class FrmAddEditIPDAdmission extends JInternalFrame 
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblIPDno,lblPatient,lblDate,lblAddress,lblContact,lblAge,lblGender,lblAdvance,lblRoom,lblRoomCategory;
	JTextField txtIPDno,txtDate,txtAddress,txtContact,txtAge,txtAdvance,txtRoomCategory;											 
	TextField txtPatient;
	JComboBox<String> jcbR;
	static FrmAddEditIPDAdmission p1; 
	JPanel pnl1;
	JRadioButton rbGen_Male,rbGen_Female;
	JButton btnSave,btnDiscard;
	ButtonGroup bg;
	DConnection dc;
	ResultSet rst,rst1,rst2;
	final DefaultListModel<String> model = new DefaultListModel<String>();

	JScrollPane jsp1;
	int ipd_no=0;
	FrmAddEditIPDAdmission(boolean flag,String query,FrmIPDAdmission fpp)
	{
		super("Add/Edit IPD Patient",true,true,true,true);
		pnl1=new JPanel(new GridLayout(11,2));
		
		JList<String> list1=new JList<String>(model);
		list1.setVisibleRowCount(5);
		list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setResizable(false);
		dc=new DConnection();
		setLayout(new BorderLayout());
		lblIPDno = new JLabel("IPD No. ");
		lblIPDno.setForeground(new Color(128,0,255));
     	lblIPDno.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblPatient = new JLabel("Patient ");
		lblPatient.setForeground(new Color(128,0,255));
     	lblPatient.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblDate= new JLabel("Date ");
		lblDate.setForeground(new Color(128,0,255));
     	lblDate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblAddress= new JLabel("Address ");
		lblAddress.setForeground(new Color(128,0,255));
     	lblAddress.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblAge = new JLabel("Age ");
		lblAge.setForeground(new Color(128,0,255));
     	lblAge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblContact = new JLabel("Contact ");
		lblContact.setForeground(new Color(128,0,255));
     	lblContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblGender= new JLabel("Gender ");
		lblGender.setForeground(new Color(128,0,255));
     	lblGender.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblAdvance= new JLabel("Advance ");
		lblAdvance.setForeground(new Color(128,0,255));
     	lblAdvance.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblRoom= new JLabel("Room ");
		lblRoom.setForeground(new Color(128,0,255));
     	lblRoom.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		lblRoomCategory = new JLabel("Room Category ");
		lblRoomCategory.setForeground(new Color(128,0,255));
     	lblRoomCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtIPDno= new JTextField(20);
		txtIPDno.setForeground(new Color(106,106,106));
		txtIPDno.setBackground(new Color(192,192,192));
     	txtIPDno.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		txtIPDno.setEditable(false);

		txtPatient= new TextField(20);
		txtPatient.setForeground(new Color(106,106,106));
     	txtPatient.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtDate= new JTextField(20);
		txtDate.setForeground(new Color(106,106,106));
     	txtDate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtDate.setEditable(false);
		txtDate.setForeground(new Color(106,106,106));
     	txtDate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtAddress= new JTextField(20);
		txtAddress.setForeground(new Color(106,106,106));
     	txtAddress.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtContact= new JTextField(20);
		txtContact.setForeground(new Color(106,106,106));
     	txtContact.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtAge= new JTextField(20);
		txtAge.setForeground(new Color(106,106,106));
     	txtAge.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtAdvance= new JTextField(20);
		txtAdvance.setForeground(new Color(106,106,106));
     	txtAdvance.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));

		txtRoomCategory= new JTextField();
		txtRoomCategory.setForeground(new Color(106,106,106));
     	txtRoomCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		txtRoomCategory.setEditable(false);

		rbGen_Male=new JRadioButton("Male",true);
		rbGen_Female=new JRadioButton("Female");
		Date dNow = new Date();
      	SimpleDateFormat ft =new SimpleDateFormat ("dd.MM.yyyy");
      	
      	try{
      		int i=0;
      		rst2=dc.executeQuery("select * from patient order by pat_name");
      		while(rst2.next())
			{
			//	model.add(i,rst.getString(2));
			//	i++;
				String s1=rst2.getString("pat_name")+"-"+rst2.getString("contact");
				model.add(i,s1);
				i++;
			}
      	}
      	catch(SQLException e)
		{
			e.printStackTrace();
		}

      	txtDate.setText(ft.format(dNow));
		bg=new ButtonGroup();
		bg.add(rbGen_Male);bg.add(rbGen_Female);
		JPanel p1= new JPanel();
		p1.add(rbGen_Male);p1.add(rbGen_Female);
		//rst1=dc.executeQuery("select * from room");
		jcbR=new JComboBox<String>();
		try
		{
	    	rst=dc.executeQuery("select count(*) from room");//tabale name??
	    	rst.next();
			int n=rst.getInt(1);
	    	String data[][]=new String[n][4];
	    	rst=dc.executeQuery("select * from room");
	     	for(int i=0;rst.next();i++)
	     	{
	     		data[i][0] = rst.getString(2);
	     		data[i][1] = rst.getString(4);
	     		data[i][2] = rst.getString(3);
	     	}
	     	dc.close();
	     	for(int i=0;i<n;i++)
	     	{
	     		String rn=data[i][0];
	     		rst=dc.executeQuery("select count(*) from ipdpatient where room_number="+rn +" and dod='Not Discharged Yet'");
	     		rst.next();
	     		int cnt=rst.getInt(1);
	     		dc.close();
	     		data[i][3]=cnt+"";	     	
	     		if(Integer.parseInt(data[i][1])-Integer.parseInt(data[i][3]) != 0)	
	     		{
	     			jcbR.addItem(data[i][0]);
	     			if(txtRoomCategory.getText().isEmpty())
	     				txtRoomCategory.setText(data[i][2]);
	     		}
	     	}
	     	dc.close();
			jcbR.setMaximumRowCount(4);	
	    }
	    catch(Exception e)
		{		
			e.printStackTrace();
		}

		try
		{
			if(flag==true)//add new
			{
				rst=dc.executeQuery("select max(ipd_no) from ipdpatient");
				rst.next();
				ipd_no=rst.getInt(1)+1;
				txtIPDno.setText(ipd_no+"");
				dc.close();
				btnSave=new JButton("save");
			}
			else//edit
			{
				rst=dc.executeQuery(query);
				rst.next();
				txtIPDno.setText(rst.getString(1));
				ResultSet rst3;
				rst3=dc.executeQuery("select * from patient where pat_id="+rst.getString(2));
				rst3.next();
				txtPatient.setText(rst3.getString("pat_name"));
				
				txtDate.setText(rst.getString("doa"));
				txtAddress.setText(rst3.getString("address"));
				txtContact.setText(rst3.getString("contact"));
				txtAge.setText(rst3.getString("age"));
				txtAdvance.setText(rst.getString("adpay"));
				ResultSet rst4;
				rst4=dc.executeQuery("select * from room where room_number="+rst.getString("room_number"));
				rst4.next();
				txtRoomCategory.setText(rst4.getString("room_category"));
				if((rst3.getString("gender")).equals("Male"))
				rbGen_Male.setSelected(true);
				else
				rbGen_Female.setSelected(true);
				jcbR.setSelectedItem(rst.getString("room_number"));
				
				btnSave=new JButton("update");
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
		 		String s1=txtIPDno.getText();
		 		String s2="";
		 		ResultSet rst3;
		 		try{
		 		rst3=dc.executeQuery("select * from patient where pat_name='"+txtPatient.getText()+"'");
		 		rst3.next();
		 		s2=rst3.getString("pat_id");
		 		}
		 		catch(SQLException e){
		 			e.printStackTrace();
		 		}
		 		String s3=txtDate.getText();
		 		String s4=(String)jcbR.getSelectedItem();
		 		//String s4 =txtRoom.getText();
		 		String sadv=txtAdvance.getText();
		 		String s6="Not Discharged Yet"; 
		 		
		 	/*	rst=dc.executeQuery("select pcat_id from pcat where pcat_name='"+(String)jcbR.getSelectedItem()+"'");
		 		int s5=0;
		 		try
		 		{
					while(rst.next())
					{
						s5=rst.getInt("pcat_id");
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
		 	*/	if(flag==true)
		 			dc.executeOther("Insert into ipdpatient values("+s1+","+s2+",'"+s3+"','"+s6+"',"+s4+","+0+","+0+","+0+","+0+","+0+","+sadv+","+0+")");
		 		else
		 			dc.executeOther("update ipdpatient set pat_id="+s2+",doa='"+s3+"',dod='"+s6+"',room_number="+s4+",procharges="+0+",drcharges="+0+",medcharges="+0+",othercharges="+0+",total="+0+",adpay="+sadv+",net="+0+" where ipd_no="+s1);
		 		dispose();
		 		fpp.reload();
		 		//fpp.jsp.repaint();
		 		//fpp.repaint();
		 	}	
		 });
		 jcbR.addItemListener (new ItemListener () {
   			 public void itemStateChanged(ItemEvent e) {
   			 	try{
   			 		
   			 	  ResultSet rst=dc.executeQuery("select * from room where room_number="+(String)jcbR.getSelectedItem());
   			 	  rst.next();
   			 	  
      			  txtRoomCategory.setText(rst.getString("room_category"));
  				  }
  				  catch(SQLException la){
  				  	la.printStackTrace();
  				  }
  				  //p1.revalidate();
  				  }
				});
		 txtPatient.addTextListener(new TextListener()
			{  
		    public void textValueChanged(TextEvent te)
		    {
		    	int len=model.getSize();
				
				int count=txtPatient.getText().length(),k=0;
				if(count!=0)
				{
					list1.clearSelection();
					int value[]={-1,-1,-1,-1};
					for(int j=0;j<len;j++)
					{
						String s1=(String)model.getElementAt(j);
						if(s1.length()>=count)
						{
					
							if(s1.substring(0,count).equalsIgnoreCase(txtPatient.getText()))
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
		
		
		
		txtPatient.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
			//	txtPatient.setBackground(Color.yellow);
				jsp1.setVisible(true);
				p1.revalidate();
				
				p1.setSize(p1.getSize().width+1,p1.getSize().height+1);
			}
			
			public void focusLost(FocusEvent fe)
			{
				jsp1.setVisible(false);
				String s1=txtPatient.getText();
				if(s1.isEmpty())
					return;
				if(list1.isSelectionEmpty()==false){
					}		
				else{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					String s2=list_value[1];
					rst=dc.executeQuery("select * from patient where pat_name='"+s1+"' and contact='"+s2+"'");
				
					try{
		
			 		while(rst.next()){
		 			txtAddress.setText(rst.getString("address"));
		 			txtContact.setText(rst.getString("contact"));
		 			if((rst.getString("gender")).equals("Male"))
					rbGen_Male.setSelected(true);
					else
					rbGen_Female.setSelected(true);
					txtAge.setText(rst.getString("age"));
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
				txtPatient.requestFocus();
				String list_value[]=((String)list1.getSelectedValue()).split("-");
				String s2=list_value[1];
				rst=dc.executeQuery("select * from patient where pat_name='"+list_value[0]+"' and contact='"+s2+"'");
				try{
		
			 		while(rst.next()){
		 			txtAddress.setText(rst.getString("address"));
		 			txtContact.setText(rst.getString("contact"));
		 			if((rst.getString("gender")).equals("Male"))
					rbGen_Male.setSelected(true);
					else
					rbGen_Female.setSelected(true);
					txtAge.setText(rst.getString("age"));
					}	
					}
					catch(SQLException e){
				
							}
				txtPatient.setText(list_value[0]);
				jsp1.setVisible(false);
				txtAddress.requestFocus();
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
					txtPatient.setText(list_value[0]);
						String s2=list_value[1];
				rst=dc.executeQuery("select * from patient where pat_name='"+list_value[0]+"' and contact='"+s2+"'");
				try{
		
			 		while(rst.next()){
		 			txtAddress.setText(rst.getString("address"));
		 			txtContact.setText(rst.getString("contact"));
		 			if((rst.getString("gender")).equals("Male"))
					rbGen_Male.setSelected(true);
					else
					rbGen_Female.setSelected(true);
					txtAge.setText(rst.getString("age"));
					}	
					}
					catch(SQLException e){
				
							}
					jsp1.setVisible(false);
					txtAddress.requestFocus();
				}
			}
			public void keyReleased(KeyEvent ke){}
		});		
		
		txtPatient.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent ke)
			{
				
			}
			public void keyPressed(KeyEvent ke)
			{//	if(list1.)
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtPatient.setText(list_value[0]);
						String s2=list_value[1];
				rst=dc.executeQuery("select * from patient where pat_name='"+list_value[0]+"' and contact='"+s2+"'");
				try{
		
			 		while(rst.next()){
		 			txtAddress.setText(rst.getString("address"));
		 			txtContact.setText(rst.getString("contact"));
		 			if((rst.getString("gender")).equals("Male"))
					rbGen_Male.setSelected(true);
					else
					rbGen_Female.setSelected(true);
					txtAge.setText(rst.getString("age"));
					}	
					}
					catch(SQLException e){
				
							}
					jsp1.setVisible(false);
					txtAddress.requestFocus();
				}
				else if(ke.getKeyCode()==KeyEvent.VK_UP)
				{
					int index=list1.getSelectedIndex();
					if(index!=0)
						index-=1;
					list1.setSelectedIndex(index);
					
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtPatient.setText(list_value[0]);	
				}
				else if(ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					int index=list1.getSelectedIndex();
					if(index!=model.getSize())
						index+=1;
					list1.setSelectedIndex(index);
					
					String list_value[]=((String)list1.getSelectedValue()).split("-");
					txtPatient.setText(list_value[0]);
				}
			}
			public void keyReleased(KeyEvent ke){}
		}); 
		 
		 jsp1=new JScrollPane(list1);
		 jsp1.setVisible(false);
		 pnl1.add(lblPatient);pnl1.add(txtPatient);
		 pnl1.add(lblIPDno);pnl1.add(txtIPDno);
		 
		 pnl1.add(lblDate);pnl1.add(txtDate);
		 pnl1.add(lblAddress);pnl1.add(txtAddress);
		 pnl1.add(lblContact);pnl1.add(txtContact);
		 pnl1.add(lblAge);pnl1.add(txtAge);
	   	 pnl1.add(lblGender);pnl1.add(p1);
		 pnl1.add(lblAdvance);pnl1.add(txtAdvance);
		 pnl1.add(lblRoom);pnl1.add(jcbR);
		 pnl1.add(lblRoomCategory);pnl1.add(txtRoomCategory);
		 pnl1.add(btnSave);pnl1.add(btnDiscard);
		 add(jsp1,"East");
		 add(pnl1,"Center");
		 setSize(600,500);
		 setVisible(true);
		 Point po=CommonMethods.getCenterPoint(getSize());
		 setLocation(po.x,po.y-75);
		 jsp1.setVisible(false);
	}
}
