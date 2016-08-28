package hms;
import javax.swing.*;
class FrmViewHelp extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FrmViewHelp()
	{
		super("Help",true,true,true,true);
		setSize(500,500);
		JEditorPane editorPane = new JEditorPane();  
		editorPane.setEditable(false);			
		editorPane.setContentType("text/html");		
		String s1="<html><head><title></title></head><body><ol><li><h3 style='color:blue;'>File</h3></li><ul><li><h4><u>Change"+ "Password:</u></h4> You can change your login password using this option.</li><li><h4><u> Add User:</h4></u> You can add a new user using this"+ "option.</li><li><h4><u> Log Off:-</h4></u>The current user can log off using this option. </li></ul></li><h3 style='color:blue'><li> Masters</h3>"+
"<ul><li><h4><u> Doctors:-</h4></u> You can add, update, search, delete or view the complete details of the doctors in the"+ "hospital.</li><li><h4><u> Patients:-</h4></u> You can add, update, search, delete or view the complete details of the patients in the hospital"+ ".</li><li><h4><u> Procedure and Procedure Categories:-</h4></u> You can add, update, search, delete or view the procedures and its different"+ "categories in the hospital . </li><li><h4><u>  Medicine and Medicine Categories:-</h4></u>You can add, update, search, delete or view the"+ "medicines and its categories in the hospital . </li><li><h4><u>  Room and Room Categories:- </h4></u>You can add, update, search, delete or view"+ "the room and its categories available in the hospital . </li></ul></li><h3 style='color:blue'><li> Reports</h3><ul>"+
"<li><h4><u>  List of Doctors:-</h4></u> You can view the complete details of the doctors in the hospital .</li>"+
"<li><h4><u>  Room Occupancy:-</h4></u> You can see the current status of the room (occupied or vacant) in the hospital.</li>"+
"<li><h4><u>  Room Charges:- </h4></u>You can see the charges of rooms in the hospital . </li></ul></li><h3 style='color:blue'><li>OPD </h3><ul>"+
"<li><h4><u>  Prescription Slip:-</h4></u> You can receive your prescription slip .</li></ul></li><h3 style='color:blue'><li> IPD </h3><ul>"+
"<li><h4><u>  Patient Admission:-</h4></u>Enter the details of the list of patients admitted .</li>"+
"<li> <h4><u> Add Procedure and Add Medicine:-</h4></u> You can add procedures and medicines. .</li>"+
"<li> <h4><u> Final Bill:-</h4></u> You can view the total amount of money to be paid for the treatment . </li></ul></li></ol></body></html>";
		editorPane.setText(s1);
		add(new JScrollPane(editorPane));				
		setVisible(true);		
	}
}
