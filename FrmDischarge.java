package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
class FrmDischarge extends JInternalFrame 
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblIPDno,lblPatient,lblDate,lblRoomCharges,lblProCharges,lblDrCharges,lblMedCharges,lblOtherCharges,lblTotal,lblAdvance,lblNet;
	TextField txtIPDno,txtPatient,txtDate,txtRoomCharges,txtProCharges,txtDrCharges,txtMedCharges,txtOtherCharges,txtTotal,txtAdvance,txtNet;
	static FrmDischarge p1; 
	JButton btnDischarge,btnDiscard;
	DConnection dc;
	ResultSet rst,rst2;
	int ipd_no=0;
	FrmDischarge(boolean flag,String query,FrmIPDAdmission fpp)
	{
		super("Discharge",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(12,2));
		lblIPDno = new JLabel("IPD No. ");
		lblPatient = new JLabel("Patient ");
		lblDate = new JLabel("Date ");
		lblRoomCharges = new JLabel("RoomCharges ");
		lblProCharges= new JLabel("ProCharges ");
		lblDrCharges= new JLabel("DrCharges ");
		lblMedCharges= new JLabel("MedCharges ");
		lblOtherCharges = new JLabel("OtherCharges ");
		lblTotal = new JLabel("Total ");
		lblAdvance = new JLabel("Advance. ");
		lblNet = new JLabel("Net ");
		txtIPDno=new TextField();
		txtIPDno.setEditable(false);
		txtPatient = new TextField();
		txtDate = new TextField();
		txtDate.setEditable(false);
		txtRoomCharges = new TextField();
		txtRoomCharges.setEditable(false);
		txtProCharges= new TextField();
		txtProCharges.setEditable(false);
		txtDrCharges= new TextField();
		txtMedCharges= new TextField();
		txtMedCharges.setEditable(false);
		txtOtherCharges = new TextField();
		txtTotal = new TextField();
		txtTotal.setEditable(false);
		txtAdvance = new TextField();
		txtNet = new TextField();
		txtNet.setEditable(false);
		Date dNow = new Date();
      	SimpleDateFormat ft =new SimpleDateFormat ("dd.MM.yyyy");

      	txtDate.setText((ft.format(dNow)));
		try
		{
				
				rst=dc.executeQuery(query);
				rst.next();
				String dt=rst.getString("doa");
				String s11[]=dt.split("\\.");
				Calendar cd=Calendar.getInstance();
				Calendar doa=Calendar.getInstance();
				doa.set(Integer.parseInt(s11[2]),
		    	Integer.parseInt(s11[1])-1,Integer.parseInt(s11[0]));
		    	long l1=cd.getTime().getTime();
		    	long l2=doa.getTime().getTime();
		    	long diff=l1-l2;
		    	long days=diff/1000/60/60/24;

				txtIPDno.setText(rst.getString(1));	
				ResultSet rst3;
				rst3=dc.executeQuery("select * from patient where pat_id='"+rst.getString("pat_id")+"'");
				rst3.next();
				ResultSet rst9,rst7;
			    rst9=dc.executeQuery("select sum(amount) from ipdmedicine where ipd_no="+rst.getString("ipd_no"));
				
				rst7=dc.executeQuery("select sum(amount) from ipdprocedure where ipd_no="+rst.getString("ipd_no"));
				
				txtPatient.setText(rst3.getString("pat_name"));
				ResultSet rst4;
				rst4=dc.executeQuery("select * from room where room_number='"+rst.getString("room_number")+"'");
				rst4.next();
				lblRoomCharges.setText("Room Charges ("+days+"x"+rst4.getInt("room_charges")+")");
				txtRoomCharges.setText(days*Integer.parseInt(rst4.getString("room_charges"))+"");
				if(rst7.next())
					txtProCharges.setText(rst7.getString(1));
				else
					txtProCharges.setText("0");
				if(rst9.next())	
					txtMedCharges.setText(rst9.getString(1));
				else
					txtMedCharges.setText("0");
				txtDrCharges.setText(rst.getString("drcharges"));
				txtOtherCharges.setText(rst.getString("othercharges"));
				txtAdvance.setText(rst.getString("adpay"));
			//	txtTotal.setText(rst4.getString("room_charges"));
			//	txtNet.setText(rst.getString((rst.getInt("total")-rst.getInt("adpay"))+""));
				
				btnDischarge=new JButton("Discharge");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		btnDiscard=new JButton("Discard");
		
		txtDrCharges.addTextListener(new TextListener()
		{	
			public void textValueChanged(TextEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		txtOtherCharges.addTextListener(new TextListener()
		{	
			public void textValueChanged(TextEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		
		
		
		txtProCharges.addFocusListener(new FocusListener()
		{	
			public void focusGained(FocusEvent fe){
				
				}
			public void focusLost(FocusEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		txtMedCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
				
				}
			public void focusLost(FocusEvent fe)
			{
			
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			}
			
		});
		txtOtherCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
				}
			public void focusLost(FocusEvent fe)
			{
			
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			}
			
		});
		txtDrCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
			
				}
			public void focusLost(FocusEvent fe)
			{
				
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		btnDiscard.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		dispose();
		 	}
		});
		 
		btnDischarge.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
		 		String s1=txtIPDno.getText();
		 		dc.executeOther("update ipdpatient set dod='"+txtDate.getText()+"',total="+txtTotal.getText()+",net="+txtNet.getText()+",procharges="+txtProCharges.getText()+",medcharges="+txtMedCharges.getText()+",othercharges="+txtOtherCharges.getText()+",drcharges="+txtDrCharges.getText()+" where ipd_no="+s1);
		 		dispose();
		 		fpp.reload();
		 		//fpp.jsp.repaint();
		 		//fpp.repaint();
		 	}	
		 });
		 add(lblIPDno);add(txtIPDno);
		 add(lblPatient);add(txtPatient);
		 add(lblDate);add(txtDate);
		 add(lblRoomCharges);add(txtRoomCharges);
		 add(lblProCharges);add(txtProCharges);
		 add(lblDrCharges);add(txtDrCharges);
		 add(lblMedCharges);add(txtMedCharges);
		 add(lblOtherCharges);add(txtOtherCharges);
		 add(lblTotal);add(txtTotal);
		 add(lblAdvance);add(txtAdvance);
		 add(lblNet);add(txtNet);
		 add(btnDischarge);add(btnDiscard);
		 
		 
		 setSize(500,500);
		 setVisible(true);
		 Point p=CommonMethods.getCenterPoint(getSize());
		 setLocation(p.x,p.y-30);
	}
}
