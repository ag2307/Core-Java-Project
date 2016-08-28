package hms;
import javax.swing.*;
import java.sql.*;
import javax.swing.tree.*;
class FrmRoomCharges extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DConnection dc;
	JTree tree;
	public FrmRoomCharges() 
	{
		super("Room Charges",true,true,true,true);
    	dc=new DConnection();
    	DefaultMutableTreeNode rooms = new DefaultMutableTreeNode("Rooms");
    	tree=createNodes(rooms);
        add(tree);
        
        setSize(400,400);
        setVisible(true); 
    }
    private JTree createNodes(DefaultMutableTreeNode rooms)
    {
		ResultSet rst1,rst2;
		DefaultMutableTreeNode roomcat,room;
    	try
		{
			rst1=dc.executeQuery("select count(*) from roomcat");
			rst1.next();
		    int c=rst1.getInt(1);
		    dc.close();
		    if(c!=0)
		    {
		    	rst1=dc.executeQuery("select * from roomcat");
		    	while(rst1.next())
		    	{
		    		String roomcatname=rst1.getString(2);
    				roomcat = new DefaultMutableTreeNode(roomcatname);
    				rooms.add(roomcat);
    				   				
    				//add room
   					rst2=dc.executeQuery("select count(*) from room where room_category='"+roomcatname+"'");
					rst2.next();
	    			int s=rst2.getInt(1);
	    			if(s!=0)
	    			{
	    				rst2=dc.executeQuery("select * from room where room_category='"+roomcatname+"'");
	    				while(rst2.next())
	    				{
   							room = new DefaultMutableTreeNode(rst2.getString(2)+" "+rst2.getString(5)+" " + rst2.getString(6));
   							roomcat.add(room);
		    			}	
					}
			    }	
			}
		}	
	   	catch(SQLException e)
	    {
	    	e.printStackTrace();
	    	System.out.println("Category Exception !");
	    }
	    dc.close();
    	tree = new JTree(rooms);
    	return tree;
    }
}

    
