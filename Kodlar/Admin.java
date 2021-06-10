import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class Admin extends Officer{
	Admin(String name, String surname, String mail, String password, int ID) {
		super(name, surname, mail, password, ID);
		//TODO Auto-generated constructor stub
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
			if (teachers.get(i).getID() == teacherID)
				return teachers.get(i);
		}
	}

	/**
		* Inquire officer information by Officer ID
		* @param officers Officer List
		* @param officerID Officer's ID to inquire
		* @return Officer object
	*/
	public Officer inquireOfficerInformation(ArrayList < Officer > officers, int officerID) {
		for (int i = 0; i < officersofficersofficers.size(); i++) {
			if (officersofficers.get(i).getID() == teacherID)
				return officers.get(i);
		}
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

	public boolean removeTeacher(ArrayList < Teacher > teachers , Teacher newTeacher){
    // Registers teacher by using Array List's add method.
	    boolean success;
	    success = teachers.remove(newTeacher);
	    return success;
	}

	public boolean removeStudent(ArrayList < Student > students , Student newStudent){
    // Registers student by using Array List's add method.
	    boolean success;
	    success = students.remove(newStudent);
	    return success;
	}
}
