package online_portal;
import java.util.*;
import java.sql.*;
class OnlinePortal{
	Scanner sc=new Scanner(System.in);
	String url="jdbc:mysql://localhost:3306/online_career_connection";
	String uname="root";
	String upass="170780";
	public void addCompany(String company)throws Exception {
		Connection con=DriverManager.getConnection(url,uname,upass);
		Class.forName("com.mysql.jdbc.Driver");
		String comquary="insert into companies values(?)";
		PreparedStatement comst=con.prepareStatement(comquary);
		comst.setNString(1,company);
		comst.executeUpdate();
		System.out.println("Added company Successfully..");
	}
	public void removeCompany(String company)throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String comquary="delete from companies where company_Name='"+company+"'";
		Statement comst=con.createStatement();
		int count=comst.executeUpdate(comquary);
		if(count==1) {
			System.out.println("Removed Company Sucessfully..");
		}
		else {
			System.out.println("The Company Name not Saved In Data Base");
		}
	}
	public void listOfCompanies() throws Exception {
		Connection con=DriverManager.getConnection(url,uname,upass);
		Class.forName("com.mysql.jdbc.Driver");
		String comquary="select * from companies";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(comquary);
		System.out.println("List of companies");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
	public void adminProfile() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		Statement st=con.createStatement();
		String adminquary="Select * from admin_1";
		ResultSet adrs=st.executeQuery(adminquary);
		System.out.println("Admin Profile");
		while(adrs.next()) {
			System.out.println("Admin Id: "+adrs.getString(1));
			System.out.println("Admin Name: "+adrs.getString(2));
			System.out.println("Admin Mail-Id: "+adrs.getString(3));
			System.out.println("Admin UserName: "+adrs.getString(4));
			System.out.println("Admin Password: "+adrs.getString(5));
		}
	}
	public boolean checkUserName(String userName)throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		Statement st1=con.createStatement();
		Statement st2=con.createStatement();
		Statement st3=con.createStatement();
		String erquary="select * from employer";
		String emquary="select * from emplyee";
		String adminquary="select * from admin_1";
		ResultSet errs=st1.executeQuery(erquary);
		ResultSet emrs=st2.executeQuery(emquary);
		ResultSet adrs=st3.executeQuery(adminquary);
		while(errs.next()){
			if(errs.getString(6).equals(userName)) {
				return true;
				
			}
		}
		while(emrs.next()){
				if(emrs.getString(7).equals(userName)) {
					return true;
					
				}
			}
		while(adrs.next()) {
			if(adrs.getString(4).equals(userName))
				return true;
		}
		st1.close();
		st2.close();
		st3.close();
		con.close();
		return false;
	}
	public int userVerification(String user,String password)throws Exception {
		int c=0,val=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		Statement st1=con.createStatement();
		Statement st2=con.createStatement();
		Statement st3=con.createStatement();
		String erquary="select * from employer";
		String emquary="select * from emplyee";
		String adminquary="select * from admin_1";
		ResultSet errs=st1.executeQuery(erquary);
		ResultSet emrs=st2.executeQuery(emquary);
		ResultSet adrs=st3.executeQuery(adminquary);
		while(errs.next()) {
			if(errs.getString(6).equals(user) && errs.getString(7).equals(password)) {
				c++;
				val=1;
			}
		}
		if(c==0) {
			while(emrs.next()) {
				if(emrs.getString(7).equals(user) && emrs.getString(8).equals(password)) {
					c++;
					val=2;
					}
				}
		}
		if(c==0) {
			while(adrs.next()) {
				if(adrs.getString(4).equals(user) && adrs.getString(5).equals(password)) {
					val=3;
					}
				}
		}
		st1.close();
		st2.close();
		st3.close();
		con.close();
		return val;
		
	}
	public void addJob(String job,int num)throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		String emprQuary="insert into jobs values(?,?)";
		PreparedStatement st=con.prepareStatement(emprQuary);
		st.setNString(1, job);
		st.setInt(2, num);
		st.executeUpdate();
		System.out.println("Added New Job Successfully");
	}
	public void removeJob(String job) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="delete from jobs where Job_Roll_Name='"+job+"'";
		Statement st=con.createStatement();
		int count=st.executeUpdate(quary);
		if(count==1) {
			System.out.println("Job Removed successfully");
		}
		else {
			System.out.println("Job Not Saved in Data Base");
		}
	}
	public void addEmployer(int rowCount,String erName,String erCompany,String erDesignation,String erMail,String erUserName,String erPass)throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		String emprQuary="insert into employer values(?,?,?,?,?,?,?)";
		PreparedStatement emprst=con.prepareStatement(emprQuary);
		emprst.setString(1,((rowCount+1)+"E"));
		emprst.setNString(2, erName);
		emprst.setString(3, erCompany);
		emprst.setString(4, erDesignation);
		emprst.setString(5, erMail);
		emprst.setString(6, erUserName);
		emprst.setString(7, erPass);
		emprst.executeUpdate();
		System.out.println("Employer Sign-In Completed Successfully");
	}
	public void addEmployee(int rowCount,String emName,String emQualification,String emMail,long emMobileNumber,String emYear,String emUserName,String emPass) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,upass);
		String empQuary="insert into emplyee values(?,?,?,?,?,?,?,?)";
		PreparedStatement empst=con.prepareStatement(empQuary);
		empst.setString(1,((rowCount+1)+"T"));
		empst.setNString(2, emName);
		empst.setString(3, emQualification);
		empst.setString(4, emMail);
		empst.setLong(5, emMobileNumber);
		empst.setString(6, emYear);
		empst.setString(7, emUserName);
		empst.setString(8, emPass);
		empst.executeUpdate();
		System.out.println("Employee Sign-In Completed Successfully");
	}
	public void listOfJobs()throws Exception {
		Connection con=DriverManager.getConnection(url,uname,upass);
		Class.forName("com.mysql.jdbc.Driver");
		Statement st=con.createStatement();
		String quary="select * from jobs";
		ResultSet rs=st.executeQuery(quary);
		while(rs.next()) {
			System.out.println(rs.getString(1)+"------->"+rs.getInt(2));
		}
	}
	public void erUpdateName(String name,String userName) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update employer set erName='"+name+"'where erUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void erUpdateCompany(String company,String userName)throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update employer set erCompany='"+company+"'where erUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void erUpdateDesignation(String designation,String userName) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update employer set erDesignation='"+designation+"'where erUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void erUpdateMail(String mail,String userName)throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update employer set erMail='"+mail+"'where erUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void empUpdateName(String name,String userName) throws Exception{
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update emplyee set emName='"+name+"'where emUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void emUpdateQualification(String qualification, String userName) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update emplyee set emQualification='"+qualification+"'where emUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void emUpdateNumber(long number,String userName) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update emplyee set emNumber='"+number+"'where emUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void emUpdateMail(String mail,String userName) throws Exception {
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="update emplyee set emMail='"+mail+"'where emUserName='"+userName+"'";
		Statement st=con.createStatement();
		st.executeUpdate(quary);
		System.out.println("Updation Completed Successfully..");
	}
	public void profileDetails(String userName) throws Exception{
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="Select * from emplyee where emUserName='"+userName+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(quary);
		rs.next();
		System.out.println();
		System.out.println("Job Seeker Id: "+rs.getString(1));
		System.out.println("Job Seeker Name: "+rs.getString(2));
		System.out.println("JOb Seeker Qualification: "+rs.getString(3));
		System.out.println("Job Seeker Mail-Id: "+rs.getString(4));
		System.out.println("Job Seeker Contact Number: "+rs.getLong(5));
		System.out.println("JOb Seeker Year of Passing: "+rs.getString(6));
		System.out.println("Job Seeker User Name: "+rs.getString(7));
	}
	public void erProfileDetails(String userName) throws Exception{
		Connection con=DriverManager.getConnection(url, uname, upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="Select * from employer where erUserName='"+userName+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(quary);
		rs.next();
		System.out.println();
		System.out.println("Employer Id: "+rs.getString(1));
		System.out.println("Employer Name: "+rs.getString(2));
		System.out.println("Employer Company: "+rs.getString(3));
		System.out.println("Employer Designation: "+rs.getString(4));
		System.out.println("Employer Mail-Id: "+rs.getString(5));
		System.out.println("Employer User Name: "+rs.getString(6));
	}
	public void applyForJob() throws Exception {
		Connection con=DriverManager.getConnection(url,uname,upass);
		Class.forName("com.mysql.jdbc.Driver");
		String quary="select * from jobs";
		PreparedStatement st=con.prepareStatement(quary);
		ResultSet rs1=st.executeQuery();
		while(rs1.next()) {
			System.out.println(rs1.getString(1));
		}
	    System.out.println("Type Job Role here to apply: ");
	    //sc.nextLine();
	    String emJobRole=sc.nextLine();
	    String quary1="select * from jobs where Job_Roll_Name='"+emJobRole+"'";
	    PreparedStatement st1=con.prepareStatement(quary1);
	    ResultSet rs2=st1.executeQuery();
	    rs2.next();
	    int application=rs2.getInt(2);
	    application+=1;
	    String quary2="update jobs set applications="+application+" where Job_Roll_Name='"+emJobRole+"'";
	    Statement st2=con.createStatement();
	    st2.executeUpdate(quary2);
	    System.out.println("Applied Successfully..");
	}
	
}
class InvalidMailId extends Exception{
	public InvalidMailId(String str) {
		super(str);
	}
}
class InvalidUserName extends Exception{
	public InvalidUserName(String str) {
		super(str);
	}
}
class PasswordNotMatching extends Exception{
	public PasswordNotMatching(String str) {
		super(str);
	}
}
class InvalidMobileNumber extends Exception{
	public InvalidMobileNumber(String str) {
		super(str);
	}
}
public class Online_Career_Connection {
	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://localhost:3306/online_career_connection";
		String uname="root";
		String pass="170780";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Scanner sc=new Scanner(System.in);
		OnlinePortal op=new OnlinePortal();
		while(true) {
			System.out.println("**Welcome to Online Career Connection**\n1.Sign In\n2.Login\n3.Close\nSelect one option");
			int choice1=sc.nextInt();
			
			switch(choice1){
			case 1:
				System.out.println();
				System.out.println("Sign-in As a...\n1.Employer\n2.Employee\n3.Back\nSelect One option");
				int choice2=sc.nextInt();
				switch(choice2){
				case 1:
					System.out.println();
					sc.nextLine();
					System.out.print("Enter Name: ");
					String erName=sc.nextLine();
					System.out.print("Enter Company Name: ");
					String erCompany=sc.nextLine();
					System.out.print("Designation: ");
					String erDesignation=sc.nextLine();
					System.out.println("Enter Mail Id: ");
					String erCheckMail=sc.nextLine();
					String erMail="";
					int erIndex=erCheckMail.indexOf("@");
					if(erIndex==-1) {
						try {
							throw new InvalidMailId("Invalid mail formate ! !");
						}catch(InvalidMailId e) {
							System.out.println(e.getMessage());
							System.out.println("Mail Must Contain @mail.com or @email.com");
							System.out.print("RE-Enter the Mail: ");
							erCheckMail=sc.nextLine();
							erIndex=erCheckMail.indexOf("@");
						}
					}
					try {
						String sub1=erCheckMail.substring(erIndex);
						if(validMail(sub1)) {
							erMail=erCheckMail;
						}
						else {
							throw new InvalidMailId("Invalid Mail Formate :@gmail.com or @email.com");
						}
					}catch(InvalidMailId e) {
						System.out.println(e.getMessage());
						System.out.println("Enter mail Again..");
						erMail=sc.nextLine();
					}
					System.out.println("User Name must Contais capital latter,small latter,Number and Special Charecter");
					System.out.print("Set User-Name: ");
					String erCheckUserName=sc.nextLine();
					String erUserName="";
					if(validUserName(erCheckUserName)) {
						erUserName=erCheckUserName;
					}
					else {
						try {
							throw new InvalidUserName("Invalid user Name Formate");
						}
						catch(InvalidUserName e) {
							System.out.println(e.getMessage());
							System.out.println("Enter User-name Again..");
							erUserName=sc.nextLine();
						}
					}
					while(op.checkUserName(erUserName)) {
						System.out.println("User Name Should Be Unic ! !\nRe-Enter User-Name");
						System.out.println("User Name must Contais capital latter,small latter,Number and Special Charecter");
						erUserName=sc.nextLine();
						if(validUserName(erUserName)) {
							erUserName=erUserName;
						}
						else {
							try {
								throw new InvalidUserName("Invalid user Name Formate");
							}catch(InvalidUserName e) {
								System.out.println(e.getMessage());
								System.out.println("User Name must Contais capital latter,small latter,Number and Special Charecter\nRe-Enter User-Name");
								erUserName=sc.nextLine();
							}
						}
					}
					String erPass="";
					System.out.print("Set New Password: ");
					String pass1=sc.nextLine();
					System.out.print("Enter Again New Password: ");
					String pass2=sc.nextLine();
					if(pass1.equals(pass2)) {
						erPass=pass1;
					}
					else {
						try {
							throw new PasswordNotMatching("Password Not Matching ! ! !");
						}
						catch(PasswordNotMatching e) {
							System.out.println(e.getMessage());
							System.out.println("Re-Enter New Password..");
							erPass=sc.nextLine();
						}
					}
					String countQuery="select count(erId) from employer";
					PreparedStatement st1=con.prepareStatement(countQuery);
					ResultSet rs1=st1.executeQuery();
					rs1.next();
					int rowCount=rs1.getInt(1);
					op.addEmployer(rowCount,erName,erCompany,erDesignation,erMail,erUserName,erPass);
					break;
				case 2:
					sc.nextLine();
					System.out.print("Enter Name: ");
					String emName=sc.nextLine();
					System.out.print("Enter Qualification: ");
					String emQualification=sc.nextLine();
					System.out.print("Enter Mail: ");
					String emMail="";
					String emCheckMail=sc.nextLine();
					int emIndex=emCheckMail.indexOf("@");
					if(emIndex==-1) {
						try {
							throw new InvalidMailId("Invalid mail formate ! !");
						}catch(InvalidMailId e) {
							System.out.println(e.getMessage());
							System.out.println("Mail Must Contain @mail.com or @email.com");
							System.out.print("RE-Enter the Mail: ");
							emCheckMail=sc.nextLine();
							emIndex=emCheckMail.indexOf("@");
						}
					}
					String emSub=emCheckMail.substring(emIndex);
					if(validMail(emSub)) {
						emMail=emCheckMail;
					}
					else {
						try {
							throw new InvalidMailId("Invalid Mail Formate: @gmail or @email");
						}catch(InvalidMailId e) {
							System.out.println(e.getMessage());
							System.out.print("Re-Enter the Mail: ");
							emMail=sc.nextLine();
						}
					}
					System.out.print("Enter Mobile Number: ");
					long emCheckMobileNumber=sc.nextLong();
					long emMobileNumber=0;
					if(validMobileNumber(emCheckMobileNumber)) {
						emMobileNumber=emCheckMobileNumber;
					}
					else {
						try {
							throw new InvalidMobileNumber("Invalid Mobile Number!!");
						}catch(InvalidMobileNumber e) {
							System.out.println(e.getMessage());
							System.out.print("Re-Enter Mobile Number: ");
							emMobileNumber=sc.nextLong();
						}
					}
					sc.nextLine();
					System.out.print("Year Of Complition: ");
					String emYear=sc.nextLine();
					System.out.print("Set New User Name: ");
					String emCheckUserName=sc.nextLine();
					String emUserName="";
					if(validUserName(emCheckUserName)) {
						emUserName=emCheckUserName;
					}
					else {
						try {
							throw new InvalidUserName("Invalid User  Name Formate ! !");
						}
						catch(InvalidUserName e) {
							System.out.println(e.getMessage());
							System.out.println("Re-Enter New User Name");
							emUserName=sc.nextLine();
						}
					}
					while(op.checkUserName(emUserName)) {
						System.out.println("User Name Should Be Unic ! !\nRe-Enter User-Name");
						System.out.println("User Name must Contais capital latter,small latter,Number and Special Charecter");
						emUserName=sc.nextLine();
						if(validUserName(emUserName)) {
							emUserName=emUserName;
						}
						else {
							try {
								throw new InvalidUserName("Invalid user Name Formate");
							}catch(InvalidUserName e) {
								System.out.println(e.getMessage());
								System.out.println("User Name must Contais capital latter,small latter,Number and Special Charecter\nRe-Enter User-Name");
								emUserName=sc.nextLine();
							}
						}
					}
					System.out.print("Set New Password: ");
					String emPass="";
					String emPass1=sc.nextLine();
					System.out.print("Re-Enter New Password: ");
					String emPass2=sc.nextLine();
					if(emPass1.equals(emPass2)) {
						emPass=emPass1;
					}
					else {
						try {
							throw new PasswordNotMatching("PassWord Miss Match ! !");
						}
						catch(PasswordNotMatching e) {
							System.out.println(e.getMessage());
							System.out.println("Re-Enter Password..");
							emPass=sc.nextLine();
						}
					}
					countQuery="select count(*) from emplyee";
					st1=con.prepareStatement(countQuery);
					rs1=st1.executeQuery();
					rs1.next();
					rowCount=rs1.getInt(1);
					rs1.close();
					op.addEmployee(rowCount,emName,emQualification,emMail,emMobileNumber,emYear,emUserName,emPass);
					break;
				default :
					break;
				}
				break;
			case 2:
				System.out.println("Log-in...");
				sc.nextLine();
					System.out.print("Enter User Name: ");
					String aUserName=sc.nextLine();
					System.out.print("Enter Password: ");
					String aPass=sc.nextLine();
					int check1=op.userVerification(aUserName, aPass);
					switch(check1) {
					case 0:
						System.out.println("Invalid User Name or Password");
						System.out.println("Re-LogIn...");
						break;
					case 1:
						boolean t1=true;
						while(t1) {
						System.out.println("**Welcome To Employer Section**\n1.List Of Job Notification\n2.Add Job Notifications\n3.Remove Job Notification\n4.Details Updation\n5.Profile\n6.Log-Out\nSelect One Option");
						int choice4=sc.nextInt();
						switch(choice4) {
						case 1:
							op.listOfJobs();
						    break;
						case 2:
							System.out.print("Enter New Job Title: ");
							sc.nextLine();
							String newJob=sc.nextLine();
							op.addJob(newJob,0);
							break;
						case 3:
							sc.nextLine();
							System.out.println("Enter Job Title: ");
							String rejob=sc.nextLine();
							op.removeJob(rejob);
							break;
						case 4:
							boolean b2=true;
							while(b2) {
							System.out.println("Which Data you want to Update...\n1.Name\n2.company\n3.Designation\n4.Mail\n5.Back\nSelect One Option");
							int choice5=sc.nextInt();
							switch(choice5) {
							case 1:
								sc.nextLine();
								System.out.print("Enter Name: ");
								String erNewName=sc.nextLine();
								op.erUpdateName(erNewName,aUserName);
								break;
							case 2:
								sc.nextLine();
								System.out.print("Enter Company: ");
								String erNewCompany=sc.nextLine();
								op.erUpdateCompany(erNewCompany, aUserName);
								break;
							case 3:
								sc.nextLine();
								System.out.print("Enter Designation: ");
								String erNewDesignation=sc.nextLine();
								op.erUpdateDesignation(erNewDesignation,aUserName);
								break;
							case 4:
								sc.nextLine();
								System.out.print("Enter Mail-Id");
								String erCheckNewMail=sc.nextLine();
								String erNewMail="";
								if(validMail(erCheckNewMail)) {
									erNewMail=erCheckNewMail;
								}
								else {
									try {
										throw new InvalidMailId("Invalid Mail Id Formate ! !");
									}catch(InvalidMailId e) {
										System.out.println(e.getMessage());
										System.out.print("Re-Enter Mail: ");
										erNewMail=sc.nextLine();
									}
								}
								op.erUpdateMail(erNewMail, aUserName);
								break;
							default:
								b2=false;
								break;
							}
							}
							break;
						case 5:
							op.erProfileDetails(aUserName);
							break;
						default:
							t1=false;
							break;
							}
						}
						break;
					case 2:
						boolean t2=true;
						while(t2) {
						System.out.println("**Welcome to Employee Section**\n1.Job Notifications\n2.Profile\n3.Detail Updation\n4.Log-Out\nSelect One Option");
						int choice5=sc.nextInt();
						switch(choice5) {
						case 1:
							op.applyForJob();
							break;
						case 2:
							System.out.println("Profile Details...");
							op.profileDetails(aUserName);
							break;
						case 3:
							boolean b1=true;
							while(b1){
							System.out.println("Which Data you want to Update...\n1.Name\n2.Qualification\n3.Mobile Number\n4.Mail\n5.Back\nSelect One Option");
							int choice6=sc.nextInt();
							switch(choice6) {
							case 1:
								System.out.print("Enter Name: ");
								sc.nextLine();
								String emNewName=sc.nextLine();
								op.empUpdateName(emNewName,aUserName);
								break;
							case 2:
								sc.nextLine();
								System.out.print("Enter Qualification: ");
								String emNewQualification=sc.nextLine();
								op.emUpdateQualification(emNewQualification, aUserName);
								break;
							case 3:
								System.out.print("Enter Mobile Number: ");
								long erNewNumber=0;
								long erCheckNewNumber=sc.nextLong();
								if(validMobileNumber(erCheckNewNumber)) {
									erNewNumber=erCheckNewNumber;
								}
								else {
									try {
										throw new InvalidMobileNumber("Invalid Mobile Number! ! ");
									}catch(InvalidMobileNumber e) {
										System.out.println(e.getMessage());
										System.out.print("Re-Enter Mobile Number: ");
										erNewNumber=sc.nextLong();
									}
								}
								op.emUpdateNumber(erNewNumber, aUserName);
								break;
							case 4:
								sc.nextLine();
								System.out.print("Enter Mail-Id");
								String emCheckNewMail=sc.nextLine();
								String emNewMail="";
								if(validMail(emCheckNewMail)) {
									emNewMail=emCheckNewMail;
								}
								else {
									try {
										throw new InvalidMailId("Invalid Mail Id Formate ! !");
									}catch(InvalidMailId e) {
										System.out.println(e.getMessage());
										System.out.print("Re-Enter Mail: ");
										emNewMail=sc.nextLine();
									}
								}
								op.emUpdateMail(emNewMail, aUserName);
								break;
							default:
								b1=false;
								break;
							}
							}
							break;
						default:
							t2=false;
							break;
							}
						}
						break;
					case 3:
						boolean t3=true;
						while(t3) {
							System.out.println("*Wlcome To ADMIN Section*\n1.Add Company\n2.Remove Company\n3.List Of Companies\n4.Profile\n5.Log-out\nSelect one option");
							int choice6=sc.nextInt();
							switch(choice6) {
							case 1:
								sc.nextLine();
								System.out.print("Enter New Comapany Name: ");
								String newCompany=sc.nextLine();
								op.addCompany(newCompany);
								break;
							case 2:
								sc.nextLine();
								System.out.print("Enter Company Name to remove: ");
								String oldCompany=sc.nextLine();
								op.removeCompany(oldCompany);
								break;
							case 3:
								op.listOfCompanies();
								break;
							case 4:
								op.adminProfile();
								break;
							default:
								t3=false;
								break;
							}
						}
					}break;
					
					default:
						System.out.println("Shut Down....");
						System.exit(0);
				}
				
			}
		}

	public  static boolean validUserName(String userName) {
		// TODO Auto-generated method stub
		int capitalCount=0,smallCount=0,numberCount=0,specialCount=0;
		for(int i=0;i<userName.length();i++) {
			char ch=userName.charAt(i);
			if(ch>='a'&&ch<='z') {
				smallCount++;
			}
			else if(ch>='A'&&ch<='Z') {
				capitalCount++;
			}
			else if(ch>='0'&& ch<='9') {
				numberCount++;
			}
			else {
				specialCount++;
			}
		}
		if(capitalCount!=0 && smallCount!=0 && numberCount!=0 && specialCount!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean validMail(String sub1){
		if(sub1.equals("@gmail.com")||sub1.equals("@email.com")) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean validMobileNumber(long number) {
		if(1000000000<=number && 9999999999l>=number) {
			return true;
		}
		else {
			return false;
		}
	}
	
}