package hms;
import java.awt.*;
import java.sql.*;
class CommonMethods
{
	static void createAllTables()
	{
		DConnection dc=new DConnection();
		dc.executeOther("create table if not exists doctor(dr_id 		int auto_increment primary key,dr_name varchar(255),contact 		varchar(255),degree varchar(255),sp varchar(255),fees int,mail_id varchar(255),timings varchar(255))");
		
		dc.executeOther("create table if not exists users(user_id 		varchar(255) primary key,password varchar(255),usertype 		enum('admin','doctor','operator'))");
		
		dc.executeOther("create table if not exists pcat(pcat_id 			int auto_increment primary key,pcat_name varchar(255))");
	
		dc.executeOther("create table if not exists pprocedure(p_id 		int primary key auto_increment,p_name varchar(255),price 		int,description varchar(255),pcat_id int,foreign key 		(pcat_id) references pcat (pcat_id))");

		dc.executeOther("create table if not exists patient(pat_id 		int auto_increment primary key,pat_name varchar(255),age 		int,father_name varchar(255),gender enum		('Male','Female'),address varchar(255),contact varchar		(255),mail_id varchar(255))");	
		
		dc.executeOther("create table if not exists medcat		(medcat_id int primary key,medcat_name varchar		(255),med_count int)");
		
		dc.executeOther("create table if not exists med(med_id int 		primary key,med_name varchar(255),med_mrp int,medcat_id 		int,FOREIGN KEY(medcat_id) references medcat(medcat_id))");

		dc.executeOther("create table if not exists roomcat		(roomcat_id int auto_increment primary key,roomcat_name 		varchar(255),room_count int,room_charge int)");

		dc.executeOther("create table if not exists room(room_id 		int auto_increment primary key,room_number int 		unique,room_category varchar(255),room_count 		int,room_description varchar(255),room_charges int)");
		
		dc.executeOther("create table if not exists opd(reg_no int 		auto_increment primary key,reg_date datetime,pat_id 		int,amount int,dr_id int,FOREIGN KEY(dr_id) references doctor(dr_id),FOREIGN KEY(pat_id) references patient(pat_id))");
		
		dc.executeOther("create table if not exists ipdpatient		(ipd_no int primary key auto_increment,pat_id int,foreign 		key (pat_id) references patient (pat_id),doa varchar		(255),dod varchar(255),room_number int,procharges 		int,drcharges int,medcharges int,othercharges int,total 		int,adpay int,net int)");
		
		dc.executeOther("create table if not exists mcategory(ct_id 		int primary key auto_increment,ct_name varchar(255))");

		dc.executeOther("create table if not exists medicine(md_id 		int primary key auto_increment,md_name varchar(255),md_cat 		varchar(255),unit varchar(255),price int,ct_id int,foreign 		key(ct_id) references mcategory(ct_id))");
		dc.executeOther("create table if not exists ipdmedicine(date varchar(255),ipd_no int,md_cat varchar(255),md_name varchar(255),quantity int,amount int)");
		dc.executeOther("create table if not exists ipdprocedure(date varchar(255),ipd_no int,p_id varchar(255),p_name varchar(255),amount int)");
	}
	static Point getCenterPoint(Dimension frame)
	{
		Point p1=new Point();
		Dimension desktop=Toolkit.getDefaultToolkit().getScreenSize();
		p1.x=(desktop.width-frame.width)/2;
		p1.y=(desktop.height-frame.height)/2;
		return p1;
	}
	static String getPatientName(String id)
	{
		String name="";
		try
		{
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeQuery("select pat_name from patient where pat_id="+id);
			rst.next();
			name=rst.getString(1);
			dc.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return name;
	}
	static String getDoctorId(String name)
	{
		String id="";
		try
		{
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeQuery("select dr_id from doctor where dr_name='"+name+"'");
			rst.next();
			id=rst.getString(1);
			dc.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}

}
//setIconImage(new ImageIcon("HMS_logo.jpg").getImage());