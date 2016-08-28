package hms;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class FrmMedicines extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static FrmMedicines fm;
	JTable jtb;
	Object [][]data;
	JScrollPane jsp;
	String heads[]={"Medicine Id","Medicine Name","Category","Unit","Price"};
	JPanel p1;
	JButton btnAdd,btnUpdate,btnSearch,btnShowAll,btnDelete;
	DConnection dc;
	ResultSet rst;
	static String query="";
	JTableHeader header;
	FrmMedicines(JDesktopPane jdp)
	{
		super("Medicines",true,true,true,true);
		dc=new DConnection();
		btnAdd=new JButton("Add");
		btnUpdate=new JButton("Update");
		btnSearch=new JButton("Search");
		btnShowAll=new JButton("Show All");
		btnDelete=new JButton("Delete");
		p1=new JPanel();
		p1.setLayout(new GridLayout(1,5));
		fm=this;
		
		btnAdd.setForeground(Color.white);
		btnAdd.setBackground(Color.blue);
		btnAdd.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				FrmAddEditMedicine md=new FrmAddEditMedicine(true,"",fm);
				jdp.add(md);
				jdp.setComponentZOrder(md,0);
				jdp.setComponentZOrder(fm,1);
				
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
					FrmAddEditMedicine md=new FrmAddEditMedicine(false,"select * from medicine where md_id="+s1,fm);
					jdp.add(md);
					jdp.setComponentZOrder(md,0);
					jdp.setComponentZOrder(fm,1);
					
					
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
	               FrmSearchMedicine md=new FrmSearchMedicine(fm);
	               jdp.add(md);
	               jdp.setComponentZOrder(md,0);
	   		       jdp.setComponentZOrder(fm,1);
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
					dc.executeOther("delete from medicine where md_id="+s1);
					reload();
				}
				
			}
		});


		p1.add(btnAdd);	p1.add(btnUpdate);
		p1.add(btnSearch);p1.add(btnShowAll);p1.add(btnDelete);
		add(p1,"South");
		jtb=createJTable();
		jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(Color.blue);
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(0,128,64));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(Color.orange);
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
		add(jsp);
		setSize(620,400);
		setLocation(CommonMethods.getCenterPoint(getSize()));
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
	    jtb.setForeground(Color.blue);
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(Color.orange);
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
		add(jsp);
		revalidate();
	}

	JTable createJTable()
	{
	    try
	    {
	    	if(query==null || "".equals(query))
	    		rst=dc.executeQuery("Select count(*) from medicine");	
	    	else
	    		rst=dc.executeQuery("Select count(*)" + query);	
	    	rst.next();
	    	int n=rst.getInt(1);
	    	if(n!=0)
	    	{
	    		if(query==null || "".equals(query))
	    	   		rst=dc.executeQuery("Select * from medicine");
	    	   	else
	    	   		rst=dc.executeQuery("Select * "+query);
	    		data=new Object[n][5];
	    		int i=0;
	    		while(rst.next())
	    		{
	    			data[i][0]=rst.getString(1);
	    			data[i][1]=rst.getString(2);
	    			data[i][2]=rst.getString(3);
	    			data[i][3]=rst.getString(4);
				data[i][4]=rst.getString(5);
	    			i++;
	    		}
	   			dc.close();
	   	   	}
	    	else
	    	{
	    		data=new Object[0][4];	
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
	