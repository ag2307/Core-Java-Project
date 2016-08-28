package hms;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class FrmSearchOPDAdmission extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblSearch,lblSelect;
	JTextField jtfSearch;
	JComboBox<String> jcbSelect;
	JButton btnSearch,btnCancel;
	DConnection dc;
	ResultSet rst;
	FrmOPDAdmission fpp;
	FrmSearchOPDAdmission(FrmOPDAdmission fpp)
	{
		super("Search",false,true,false,false);
		this.fpp=fpp;
		setLayout(new GridLayout(3,2));
		
	    lblSearch=new JLabel("Search");
	    add(lblSearch);
	    
	    jtfSearch=new JTextField();
	    add(jtfSearch);
	    
	    lblSelect=new JLabel("Select");
	    add(lblSelect);
	    
	    jcbSelect=new JComboBox<String>();
	    jcbSelect.addItem("reg_no");
	    jcbSelect.addItem("pat_id");
	    jcbSelect.addItem("dr_id");
	    add(jcbSelect);
	    
	    btnSearch=new JButton("Search");
	    btnSearch.addActionListener(new ActionListener()
	    {
	       public void actionPerformed(ActionEvent ae)
	       {	
	    	dc=new DConnection();
	    	String select=(String)jcbSelect.getSelectedItem();
	    	String value=jtfSearch.getText();
	    	FrmOPDAdmission.query="from opd where "+select+" = "+value+"";
     		dispose();
     		fpp.reload();
	      }
	    });
	    add(btnSearch);
	    
	    btnCancel=new JButton("Cancel");
	    btnCancel.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent ae)
	    	{
	    		dispose();
	    	}
	    });
	    add(btnCancel);
	    
	    setSize(300,200);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}