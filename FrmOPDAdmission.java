package hms;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class FrmOPDAdmission extends JInternalFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static FrmOPDAdmission fpp;
	JTable jtb;
	Object [][]data;
	JScrollPane jsp;
	String heads[]={"OPD No.","Date","Patient","Amount","Doctor"};
	JPanel p1;
	JButton btnAdd,btnUpdate,btnSearch,btnShowAll,btnDelete;
	JTableHeader header;
	DConnection dc;
	ResultSet rst;
	static String query="";
	FrmOPDAdmission(JDesktopPane jdp)
	{
		super("OPD Registration",true,true,true,true);
		dc=new DConnection();
		btnAdd=new JButton("Add");
		btnUpdate=new JButton("Update");
		btnSearch=new JButton("Search");
		btnShowAll=new JButton("Show All");
		btnDelete=new JButton("Delete");
		p1=new JPanel();
		p1.setLayout(new GridLayout(1,5));
		fpp=this;
		btnAdd.setForeground(Color.white);
		btnAdd.setBackground(Color.blue);
		btnAdd.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				FrmAddEditOPDAdmission pp =new FrmAddEditOPDAdmission(true,"",fpp);
				jdp.add(pp);
				jdp.setComponentZOrder(pp,0);
				jdp.setComponentZOrder(fpp,1);				
			}
		});
		btnUpdate.setForeground(Color.white);
		btnUpdate.setBackground(Color.blue);
		btnUpdate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int r=jtb.getSelectedRow();
				if(r==-1)
				{
					JOptionPane.showMessageDialog(null,"No row selected");
				}
				else
				{
					String s1=(String)jtb.getValueAt(r,0);
					FrmAddEditOPDAdmission pp=new FrmAddEditOPDAdmission(false,"select * from opd where reg_no="+s1,fpp);
					jdp.add(pp);
					jdp.setComponentZOrder(pp,0);
					jdp.setComponentZOrder(fpp,1);
					
					
				}
			}
		});
		btnSearch.setForeground(Color.white);
		btnSearch.setBackground(new Color(128,0,255));
		btnSearch.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
	   			   query="";
	               FrmSearchOPDAdmission pp=new FrmSearchOPDAdmission(fpp);
	               jdp.add(pp);
	               jdp.setComponentZOrder(pp,0);
	   		       jdp.setComponentZOrder(fpp,1);
			}
		});
		btnShowAll.setForeground(Color.white);
		btnShowAll.setBackground(new Color(128,0,255));	
		btnShowAll.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnShowAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
	   			   query="";
	   			   reload();
			}
		});
		btnDelete.setForeground(Color.white);
		btnDelete.setBackground(Color.red);
		btnDelete.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int r=jtb.getSelectedRow();
				if(r==-1)
				{
					JOptionPane.showMessageDialog(null,"No row selected");
				}
				else
				{
					String s1=(String)jtb.getValueAt(r,0);
					dc.executeOther("delete from opd where reg_no="+s1);
					reload();
				}
				
			}
		});
		p1.add(btnAdd);	p1.add(btnUpdate);p1.add(btnSearch);
		p1.add(btnShowAll);p1.add(btnDelete);
		add(p1,"South");
		jtb=createJTable();
		jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(128,0,255));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,0,128));
		jsp=new JScrollPane(jtb);
		add(jsp);
		Dimension desktop=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(desktop.width,desktop.height-200);
		Point p=CommonMethods.getCenterPoint(getSize());
		setLocation(p.x,p.y-100);
		setVisible(true);
	}
	void reload()
	{
		remove(jsp);
		jtb=createJTable();
		jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(128,0,255));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,0,128));
		jsp=new JScrollPane(jtb);
		add(jsp);
		revalidate();
	}
	JTable createJTable()
	{
	    try
	    {
	    	if(query==null || "".equals(query))
	    		rst=dc.executeQuery("Select count(*) from opd");	
	    	else
	    		rst=dc.executeQuery("Select count(*)" + query);	
	    	rst.next();
	    	int n=rst.getInt(1);
	    	if(n!=0)
	    	{
	    		if(query==null || "".equals(query))
	    	   		rst=dc.executeQuery("Select * from opd");
	    	   	else
	    	   		rst=dc.executeQuery("Select * "+query);
	    		data=new Object[n][5];
	    		int i=0;
	    		while(rst.next())
	    		{
	    			data[i][0]=rst.getString(1);
	    			data[i][1]=rst.getString(2);
	    			data[i][2]=CommonMethods.getPatientName(rst.getString(3));
	    			data[i][3]=rst.getString(4);
	    			data[i][4]=rst.getString(5);
	    			i++;
	    		}
	   			dc.close();
	   	   	}
	    	else
	    	{
	    		data=new Object[0][5];		
	    	}
		}
	   	catch(SQLException e)
	   	{
	   		e.printStackTrace();
	   	}
	   	jtb=new JTable(data,heads);
	   	return jtb;
	}
}