package hms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrmMainFrame extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String user;
	JPanel panel_Top,panel_Bottom;
	JToolBar jtb;
	JMenuBar jmb;
	JButton btnLogOff,btnChangePassword,btnExit,btnAddUser,btnSettings,btnDoctors,btnPatients,btnProcedure,btnProcedureCategories,btnMedicines;
	JButton btnMedicineCategories,btnRoom,btnRoomCategories,btnListofDoctors,btnRoomOccupancy,btnAboutUs,btnViewHelp,btnConsultancyCharges,
	btnRoomCharges,btnPrescriptionSlip,btnPatientAdmission,btnAddProcdure,btnFinalBill,btnAddMedicine;
	JMenu mnuFile,mnuMasters,mnuReports,mnuOPD,mnuIPD,mnuHelp;
	JMenuItem jmiLogOff,jmiChangePassword,jmiSettings,jmiExit,jmiAddUser,jmiDoctors,jmiPatients,jmiProcedure,jmiProcedureCategories,jmiMedicines;
	JMenuItem jmiMedicineCategories,jmiRoom,jmiRoomCategories,jmiListofDoctors,jmiRoomOccupancy,jmiAboutUs,jmiViewHelp,jmiConsultancyCharges,
	jmiRoomCharges,jmiPrescriptionSlip,jmiPatientAdmission,jmiAddProcedure,jmiFinalBill,jmiAddMedicine,jmiHelp;
	Insets ins;
	Font f;
	JDesktopPane jdp;
	static FrmMainFrame mainframe;
	FrmMainFrame(String user)
	{
		FrmMainFrame.user=user;
		mainframe=this;
		f=new Font("Dialog",Font.PLAIN,12);
		ins=new Insets(0,0,0,0);
		
		panel_Top=new JPanel();
		panel_Top.setLayout(new BorderLayout());
     	panel_Top.add(createJToolBar(),BorderLayout.PAGE_START);
		
		panel_Bottom=new JPanel();
		panel_Bottom.setPreferredSize(new Dimension(10,35));
		panel_Bottom.setBackground(Color.gray);
		
		jdp=new MyDesktopPane();
		jdp.setBackground(Color.WHITE);
		
		    
		add(panel_Top,BorderLayout.NORTH);
     	add(jdp,BorderLayout.CENTER);
     	add(panel_Bottom,BorderLayout.SOUTH);

		setJMenuBar(createJMenu());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Welcome:"+user);
		setVisible(true);
	}
	JMenuBar createJMenu()
	{
	   //for File JMenu and its JMenuItems
		jmiLogOff=new JMenuItem("Log Off");
		jmiLogOff.setMnemonic('L');
	   	jmiLogOff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		jmiLogOff.setFont(f);
		jmiLogOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Log Off?","Log Off",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		     	if(JOptionPane.YES_OPTION==answer)
		     	{
		     		dispose();
		     		new FrmLogin();
		    	}
			}
		});
		 
		jmiChangePassword=new JMenuItem("Change Password");
		jmiChangePassword.setMnemonic('P');
	   	jmiChangePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		jmiChangePassword.setFont(f);
		jmiChangePassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			       jdp.add(new FrmChangePass(user,mainframe));   
			}
		});
		
		jmiAddUser=new JMenuItem("Add User");
		jmiAddUser.setFont(f);
		jmiAddUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			       new FrmCreateUser();   
			}
		});
		
		jmiSettings=new JMenuItem("Settings");
		jmiSettings.setFont(f);
		jmiSettings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			     //  jdp.add(new FrmSettings());   
			}
		});
		
		jmiExit=new JMenuItem("Exit");
		jmiExit.setMnemonic('E');
	   	jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		jmiExit.setFont(f);
		jmiExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		    	if(answer==JOptionPane.YES_OPTION)
		     		System.exit(1);
			}
		});
		
		mnuFile=new JMenu("File");
		mnuFile.setFont(f);
        mnuFile.setMnemonic('F');
        mnuFile.add(jmiChangePassword);mnuFile.add(jmiAddUser); /*mnuFile.add(jmiSettings);*/ mnuFile.addSeparator(); mnuFile.add(jmiLogOff); mnuFile.add(jmiExit);
        
        // For Masters JMenu and its JMenuItems
        jmiDoctors=new JMenuItem("Doctors");
		jmiDoctors.setMnemonic('D');
	   	jmiDoctors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		jmiDoctors.setFont(f);
		jmiDoctors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmDoctor(jdp));
			}
		});
        
        jmiPatients=new JMenuItem("Patients");
        jmiPatients.setFont(f);
		jmiPatients.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmPatient(jdp));
			}
		});
        
        jmiProcedure=new JMenuItem("Procedure");
		jmiProcedure.setFont(f);
		jmiProcedureCategories=new JMenuItem();
		jmiProcedure.setFont(f);
		jmiProcedure.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmPProcedure(jdp));
			}
		});
		
		jmiProcedureCategories=new JMenuItem("Procedure Categories");
		jmiProcedureCategories.setFont(f);
		jmiProcedureCategories.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmPCategories(jdp));
			}
		});
		
		jmiMedicines=new JMenuItem("Medicines");
		jmiMedicines.setMnemonic('M');
	   	jmiMedicines.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
		jmiMedicines.setFont(f);
		jmiMedicines.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmMedicines(jdp));
			}
		});
		
		jmiMedicineCategories=new JMenuItem("MedicineCategories");
		jmiMedicineCategories.setFont(f);
		jmiMedicineCategories.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmMCategory(jdp)); 
			}
		});
		
		jmiRoom=new JMenuItem("Room");
		jmiRoom.setMnemonic('R');
	   	jmiRoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		jmiRoom.setFont(f);
		jmiRoom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmRoom(jdp));
			}
		});
		
		jmiRoomCategories=new JMenuItem("RoomCategories");
		jmiRoomCategories.setFont(f);
		jmiRoomCategories.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{			
				jdp.add(new FrmRoomCategory(jdp));
			}
		});
		
		mnuMasters=new JMenu("Masters");
		mnuMasters.setFont(f);
        mnuMasters.setMnemonic('M');
        mnuMasters.add(jmiDoctors);
        mnuMasters.add(jmiPatients);
        mnuMasters.add(jmiProcedureCategories);
        mnuMasters.add(jmiProcedure);
        mnuMasters.addSeparator();
        mnuMasters.add(jmiMedicineCategories);
        mnuMasters.add(jmiMedicines);    
        mnuMasters.add(jmiRoomCategories);
        mnuMasters.add(jmiRoom);
  
        
        // for Reports JMenu and JMenuItems
        jmiListofDoctors=new JMenuItem("List of Doctors");
		jmiListofDoctors.setMnemonic('L');
	   	jmiListofDoctors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		jmiListofDoctors.setFont(f);
		jmiListofDoctors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmDocList());
			}
		});
		
		jmiRoomOccupancy=new JMenuItem("Room Occupancy");
		jmiRoomOccupancy.setMnemonic('O');
	   	jmiRoomOccupancy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		jmiRoomOccupancy.setFont(f);
		jmiRoomOccupancy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmRoomOccupancy());
			}
		});
		
		jmiConsultancyCharges=new JMenuItem("Consultancy Charges");
		jmiConsultancyCharges.setFont(f);
		jmiConsultancyCharges.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
		//		jdp.add(new FrmConsultancyCharges(jdp));
			}
		});
		
		jmiRoomCharges=new JMenuItem("RoomCharges");
		jmiRoomCharges.setMnemonic('C');
	   	jmiRoomCharges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		jmiRoomCharges.setFont(f);
		jmiRoomCharges.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmRoomCharges());
			}
		});
		
		mnuReports=new JMenu("Reports");
		mnuReports.setFont(f);
        mnuReports.setMnemonic('R');
        mnuReports.add(jmiListofDoctors);
		mnuReports.add(jmiRoomOccupancy);
        mnuReports.add(jmiRoomCharges);
        //mnuReports.add(jmiConsultancyCharges);
		
	  	// for OPD JMenu and JMenuItems
        jmiPrescriptionSlip=new JMenuItem("Prescription Slip");
		jmiPrescriptionSlip.setMnemonic('S');
	   	jmiPrescriptionSlip.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		jmiPrescriptionSlip.setFont(f);
		jmiPrescriptionSlip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmOPDAdmission(jdp));
			}
		});
		
		mnuOPD=new JMenu("OPD");
		mnuOPD.setFont(f);
        mnuOPD.setMnemonic('O');
        mnuOPD.add(jmiPrescriptionSlip); 
        
        //for IPD JMenu and JMenuItems   
             
        jmiPatientAdmission=new JMenuItem("Patient Admission");
		jmiPatientAdmission.setMnemonic('A');
	   	jmiPatientAdmission.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		jmiPatientAdmission.setFont(f);
		jmiPatientAdmission.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmIPDAdmission(jdp));
			}
		});
		
		jmiAddProcedure=new JMenuItem("Add Procedure");
		jmiAddProcedure.setMnemonic('P');
	   	jmiAddProcedure.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		jmiAddProcedure.setFont(f);
		jmiAddProcedure.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmAddProcedure());
			}
		});
		
		jmiFinalBill=new JMenuItem("Final Bill");
		jmiFinalBill.setMnemonic('F');
	   	jmiFinalBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		jmiFinalBill.setFont(f);
		jmiFinalBill.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmIPDAdmission(jdp));
			}
		});
		
		jmiAddMedicine=new JMenuItem("Add Medicine");
		jmiAddMedicine.setMnemonic('I');
	   	jmiAddMedicine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		jmiAddMedicine.setFont(f);
		jmiAddMedicine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmAddMedicines());				
			}
		});
		
		mnuIPD=new JMenu("IPD");
		mnuIPD.setFont(f);
        mnuIPD.setMnemonic('I');
        mnuIPD.add(jmiPatientAdmission);  mnuIPD.add(jmiAddProcedure);  mnuIPD.add(jmiAddMedicine);mnuIPD.add(jmiFinalBill);   
        	
        // for Help JMenu and JMenuItems
        jmiHelp=new JMenuItem("View Help");
        jmiHelp.setMnemonic('V');
	   	jmiHelp.setAccelerator(KeyStroke.getKeyStroke("F1"));
		jmiHelp.setFont(f);
		jmiHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmViewHelp());
			}
		});
        
        jmiAboutUs=new JMenuItem("About Us");
        jmiAboutUs.setMnemonic('U');
	   	jmiAboutUs.setAccelerator(KeyStroke.getKeyStroke("F2"));
		jmiAboutUs.setFont(f);
		jmiAboutUs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				new FrmAboutus();
			}
		});
		
        mnuHelp=new JMenu("Help");
		mnuHelp.setFont(f);
        mnuHelp.setMnemonic('H');
        mnuHelp.add(jmiHelp);
        mnuHelp.addSeparator();
        mnuHelp.add(jmiAboutUs);
        
		jmb=new JMenuBar();
		jmb.add(mnuFile);
		jmb.add(mnuMasters);
		jmb.add(mnuOPD);
		jmb.add(mnuIPD);
		jmb.add(mnuReports);
		jmb.add(mnuHelp);
		return jmb;
	}
	JToolBar createJToolBar()
	{
		btnChangePassword=new JButton(new ImageIcon(new ImageIcon("images/ChangePassword.png").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnChangePassword.setToolTipText("Change Your Password");
		btnChangePassword.setMargin(ins);
		btnChangePassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmChangePass(user,mainframe));
			}
		});
			
		btnAddUser=new JButton(new ImageIcon(new ImageIcon("images/AddUser.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnAddUser.setToolTipText("AddUser");
		btnAddUser.setMargin(ins);
		btnAddUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				new FrmCreateUser();
			}
		});
		
		btnDoctors=new JButton(new ImageIcon(new ImageIcon("images/Doctors.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnDoctors.setToolTipText("Docters");
		btnDoctors.setMargin(ins);
		btnDoctors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmDoctor(jdp));
			}
		});
		
		btnPatients=new JButton(new ImageIcon(new ImageIcon("images/Patients.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnPatients.setToolTipText("Patients");
		btnPatients.setMargin(ins);
		btnPatients.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmPatient(jdp));
			}
		});
		
		btnProcedure=new JButton(new ImageIcon(new ImageIcon("images/Procedure.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnProcedure.setToolTipText("Procedure");
		btnProcedure.setMargin(ins);
		btnProcedure.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmPProcedure(jdp));
			}
		});
		
		btnMedicines=new JButton(new ImageIcon(new ImageIcon("images/Medicines.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnMedicines.setToolTipText("Medicines");
		btnMedicines.setMargin(ins);
		btnMedicines.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmMedicines(jdp));	
			}
		});
		
		btnRoom=new JButton(new ImageIcon(new ImageIcon("images/Room.png").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnRoom.setToolTipText("Sales");
		btnRoom.setMargin(ins);
		btnRoom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmRoom(jdp));			
			}
		});
		
		btnPrescriptionSlip=new JButton(new ImageIcon(new ImageIcon("images/PrescriptionSlip.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnPrescriptionSlip.setToolTipText("Products Report");
		btnPrescriptionSlip.setMargin(ins);
		btnPrescriptionSlip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmOPDAdmission(jdp));
			}
		});
			
		btnPatientAdmission=new JButton(new ImageIcon(new ImageIcon("images/AddPatient.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnPatientAdmission.setToolTipText("Patient Admission");
		btnPatientAdmission.setMargin(ins);
		btnPatientAdmission.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmIPDAdmission(jdp));	
			}
		});
		
		btnFinalBill=new JButton(new ImageIcon(new ImageIcon("images/FinalBill.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnFinalBill.setToolTipText("Final Bill");
		btnFinalBill.setMargin(ins);
		btnFinalBill.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmIPDAdmission(jdp));	
			}
		});
		
		btnLogOff=new JButton(new ImageIcon(new ImageIcon("images/LogOff.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnLogOff.setToolTipText("Log Off");
		btnLogOff.setMargin(ins);
		btnLogOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
		  	{
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Log Off?","Log Off",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		        if(JOptionPane.YES_OPTION==answer)
		        {
		        	dispose();
		        	new FrmLogin();
		        }	    	
			}
		});
		
		btnViewHelp=new JButton(new ImageIcon(new ImageIcon("images/ViewHelp.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnViewHelp.setToolTipText("View Help");
		btnViewHelp.setMargin(ins);
		btnViewHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jdp.add(new FrmViewHelp());
			}
		});
	
		btnAboutUs=new JButton(new ImageIcon(new ImageIcon("images/AboutUs.png").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnAboutUs.setToolTipText("About Us");
		btnAboutUs.setMargin(ins);
		btnAboutUs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				new FrmAboutus();
			}
		});
		
		btnExit=new JButton(new ImageIcon(new ImageIcon("images/exit.jpg").getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT)));
		btnExit.setToolTipText("Exit");
		btnExit.setMargin(ins);
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		        if(JOptionPane.YES_OPTION==answer)
		        	System.exit(1);
			}
		});
		
		jtb=new JToolBar(JToolBar.HORIZONTAL);
		jtb.setMargin(ins);
		jtb.setBackground(Color.LIGHT_GRAY);

		jtb.add(btnAddUser);jtb.add(btnChangePassword);jtb.add(btnLogOff);jtb.add(btnExit);jtb.addSeparator();
		jtb.add(btnDoctors);jtb.add(btnPatients);jtb.add(btnProcedure);jtb.add(btnRoom);jtb.add(btnMedicines);jtb.addSeparator();jtb.addSeparator();
		jtb.add(btnPrescriptionSlip);jtb.add(btnPatientAdmission);jtb.add(btnFinalBill);jtb.addSeparator();
		jtb.add(btnViewHelp);jtb.add(btnAboutUs);jtb.addSeparator();      
		
		return jtb;
	}
//	public static void main(String [] args)
//	{
//		new FrmMainFrame("kb");
//	}
}
class MyDesktopPane extends JDesktopPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon ii=new ImageIcon("images/png_main.png");
		Dimension d=getSize();
		g.drawImage(ii.getImage(),0,60,d.width,d.height-100,null);
		revalidate();
	}
}
