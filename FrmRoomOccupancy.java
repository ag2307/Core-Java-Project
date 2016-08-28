package hms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
public class FrmRoomOccupancy extends JInternalFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton jbt;
	JTable jtb;
	ResultSet rst;
	JScrollPane jsp;
	String[] heads={"Room Id","Room Number","Room Category","Description","Room Charges","Bed Count","Bed Occupied","Bed Free"};
	Object data[][];
    DConnection dc;
   	FrmRoomOccupancy()
	{
		super("Room Occupancy Chart",true,true,true,true);

		jbt=new JButton("Print");
		jbt.addActionListener(this);
		add(jbt,"South");

		try
		{
	    	dc=new DConnection();
	    	rst=dc.executeQuery("select count(*) from room");//tabale name??
	    	rst.next();
			int n=rst.getInt(1);
	    	data=new Object[n][8];
	    	rst=dc.executeQuery("select * from room");
	     	for(int i=0;rst.next();i++)
	     	{
	     		data[i][0] = rst.getString(1);
	     		data[i][1] = rst.getString(2);
	     		data[i][2] = rst.getString(3);
	     		data[i][3] = rst.getString(5);
	     		data[i][4] = rst.getString(6);
	     		data[i][5] = rst.getString(4);
	     		data[i][6] = "";
	     		data[i][7] = "";
	
	     	}
	     	dc.close();
	     	for(int i=0;i<n;i++)
	     	{
	     		String rn=(String)data[i][1];
	     		rst=dc.executeQuery("select count(*) from ipdpatient where room_number="+rn +" and dod='Not Discharged Yet'");
	     		rst.next();
	     		int cnt=rst.getInt(1);
	     		dc.close();
	     		data[i][6]=cnt+"";
	     		data[i][7]=Integer.parseInt((String)data[i][5])-cnt+"";
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
		jtb.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        String status = (String)table.getModel().getValueAt(row, 7);
        if ("0".equals(status)) {
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }       
        return this;
    }   
});
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