import java.util.*;
import java.io.*;

public class Admin extends Officer{
	private DataBase db;
	Admin(String name, String surname, String mail, String password, int ID,DataBase db) {
		this.name=name;
		this.surname=surname;
		this.mail=mail;
		this.password=password;
		this.ID=ID;
		this.db=db;
	}
	/**
	* Constructor with two parameters.
	* @param mail Admin's e-mail address.
	* @param password Admin's password.
	*/
	Admin(String mail, String password) {
		super(mail, password);
	}
	public void print(){
	}
	/**
	* Inquire teacher information by Teacher ID
	* @param teachers Teacher List
	* @param teacherID Teacher's ID to inquire
	* @return Teacher object
	*/
	public Teacher inquireTeacherInformation(ArrayList <Teacher> teachers, int teacherID) {
		for (int i = 0; i < teachers.size(); i++) {
		if (teachers.get(i).getUserID() == teacherID)
		return teachers.get(i);
		}
		return null;
	}

	/**
	* Inquire officer information by Officer ID
	* @param officers Officer List
	* @param officerID Officer's ID to inquire
	* @return Officer object
	*/
	public Officer inquireOfficerInformation(ArrayList < Officer > officers, int officerID) {
		for (int i = 0; i < officers.size(); i++) {
		if (officers.get(i).getUserID() == officerID)
		return officers.get(i);
		}
		return null;
	}

	public boolean addOfficer(ArrayList < Officer > officers , Officer newOfficer){
		// Registers officer by using Array List's add method.
		boolean success;
		success = officers.add(newOfficer);
		return success;
	}

	public boolean addTeacher(ArrayList < Teacher > teachers , Teacher newTeacher){
		// Registers teacher by using Array List's add method.
		boolean success;
		success = teachers.add(newTeacher);
		return success;
	}

	public boolean addStudent(ArrayList < Student > students , Student newStudent){
		// Registers student by using Array List's add method.
		boolean success;
		success = students.add(newStudent);
		return success;
	}

	public boolean removeOfficer(ArrayList < Officer > officers , Officer oldOfficer){
		// Registers teacher by using Array List's add method.
		boolean success;
		success = officers.remove(oldOfficer);
		return success;
	}

	public boolean removeTeacher(ArrayList < Teacher > teachers , Teacher oldTeacher){
		// Registers teacher by using Array List's add method.
		boolean success;
		success = teachers.remove(oldTeacher);
		return success;
	}

	public boolean removeStudent(ArrayList < Student > students , Student oldStudent){
		// Registers student by using Array List's add method.
		boolean success;
		success = students.remove(oldStudent);
		return success;
	}

	public void menu(){
		int choice , innerChoice,id;
		for (;;) {
			System.out.println("WELCOME TO ADMIN MENU");
			System.out.println("0) Exit");
			System.out.println("1) Add User");
			System.out.println("2) Remove User");
			System.out.println("3) Inquire User");

			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();

			switch (choice) {

				case 0:
				return;
				case 1:
					System.out.println("0) Exit");
					System.out.println("1) Add Officer");
					System.out.println("2) Add Student");
					System.out.println("3) Add Teacher");
					innerChoice = scanner.nextInt();
					String mail,password, name, surname;
					int year;
					System.out.println("Enter name");
					name; = scanner.nextLine();
					System.out.println("Enter surname");
					surname = scanner.nextLine();
					System.out.println("Enter id");
					id = scanner.nextInt();
					System.out.println("Enter mail");
					mail = scanner.nextLine();
					System.out.println("Enter password");
					password = scanner.nextLine();
					switch (innerChoice){
						case 0:
						break;
						case 1:
							Officer tempO = new Officer(name, surname, mail, password, id,db);
							//add database the Officer.
						break;
						case 2:
							System.out.println("Enter year");
							year = Integer.parseInt(scanner.nextLine());
							Student tempO = new Student(mail, password, name, surname, id, year);
							//add database the student.
						break;
						case 3:
							Admin tempO = new Admin(name, surname, mail, password, id,db);
							//add database the Admin.
						break;
						default:
						break;
					}
				break;
				case 2:
					
					System.out.println("0) Exit");
					System.out.println("1) Remove User");
					innerChoice = scanner.nextInt();
					switch (innerChoice){
						case 0:
						break;
						case 1:
							System.out.println("Enter id");
							id = scanner.nextInt();
							//remove func database
						break;
						default:
						break;
					}
				break;
				case 3:
					
					System.out.println("0) Exit");
					System.out.println("1) Inquire User");
					innerChoice = scanner.nextInt();
					switch (innerChoice){
						case 0:
						break;
						case 1:
							System.out.println("Enter id");
							id = scanner.nextInt();
							
							//inquire func database
						break;
						default:
						break;
					}
				break;
				default:
				System.out.println("Tekrar giris");
			}
		}
	}

}
