package hms;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.print.*;
public class FrmDocList extends JInternalFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton jbt;
	JTable jtb;
	ResultSet rst;
	JScrollPane jsp;
	String[] heads={"Doctor Id","Name","Contact","Degree","Specialization","Fees","Mail id","Timings"};
	Object data[][];
    DConnection dc;
   	FrmDocList()
	{
		super("Doctor List",true,true,true,true);

		jbt=new JButton("Print");
		jbt.addActionListener(this);
		add(jbt,"South");

		try
		{
	    	dc=new DConnection();
	    	rst=dc.executeQuery("select count(*) from doctor");//tabale name??
	    	rst.next();
			int n=rst.getInt(1);
	    	data=new Object[n][8];
	    	rst=dc.executeQuery("select * from doctor");
	     	for(int i=0;rst.next();i++)
	     	{
	     		for(int j=0;j<8;j++)
	     			data[i][j] = rst.getString(j+1);
	     	}
	     	dc.close();
	    }
	    catch(Exception e)
		{		
			e.printStackTrace();
		}

		jtb=new JTable(data,heads);	
		jtb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		jtb.setFillsViewportHeight(true);
		jsp = new JScrollPane(jtb);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		
		setVisible(true);
		setSize(900,500);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			jtb.print();
		}
		catch(PrinterException e)
		{
			e.printStackTrace();
		}
	}
}