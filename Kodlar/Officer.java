/**
 * Officer Class
 * @author aygun
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Officer extends Person{
	
	/**
	 * Constructor with five parameters.
	 * @param name Officer's name.
	 * @param surname Officer's surname.
 	 * @param mail Officer's e-mail address.
	 * @param password Officer's account password.
	 * @param ID Officer ID number.
	 */
    Officer(String name, String surname, String mail, String password,int ID) {
        super(name, surname, mail, password,ID);
        //TODO Auto-generated constructor stub
    }
    
    /**
     * Constructor with two parameters.
     * @param mail Officer's e-mail address.
     * @param password Officer's password.
     */
    Officer(String mail,String password){
        super(mail, password);
    }
    
    /**
     * Prints student's certificate.
     * @param student Student object.
     */
    public void printCertificate(Student student){
    	student.viewTranscript();
    }
    
    /**
     * Approves/disapproves event.
     * @param event Event object
     * @return Whether event is approved.
     */
    public boolean ApproveEvent(Event event){
    	Scanner sc = null;
    	String s;
    	boolean apprStatus = false;
    	
    	System.out.println("Event Name: " + event.getEventName());
    	System.out.println("Event Date: " + event.getDate());
    	for(int i=0; i<event.getParticipantsID().size(); i++)
    	{
    		System.out.print(event.getParticipantsID().get(i));
    		if(i<event.getParticipantsID().size()-1)
    			System.out.print(", ");
    		
    	}
    	System.out.println();
    	System.out.println("Do you approve this event? (Y:Yes, N:No)");
    	s = sc.next();
    	if(s.equals("Y") || s.equals("y"))
    	{
    		apprStatus = true;
    		System.out.println("You have approved this event.");
    	} else if(s.equals("N") || s.equals("n"))
    	{
    		apprStatus = false;
    		System.out.println("You did not approved this event.");
    	}
    	
    	event.setApproveStatus(apprStatus);
        return apprStatus;
    }
    
    /**
     * Inquire student information by Student ID
     * @param students Student List
     * @param studentID Student's ID to inquire
     * @return Student object
     */
    public Student inquireStudentInformation(ArrayList<Student> students, int studentID){
    	Student s = null;
    	for(int i=0; i<students.size(); i++)
    	{
    		if(students.get(i).getID() == studentID)
    			s = students.get(i);
    	}
        return s;
    }
    
    /**
     * Prints student's Transcript
     * @param students Student List
     * @param studentID Student's ID to print transcript
     */
    public void viewTranscript(ArrayList<Student> students, int studentID){
    	for(int i=0; i<students.size(); i++)
    	{
    		if(students.get(i).getID() == studentID)
        		students.get(i).viewTranscript();
    	}
    }
    	
    /**
     * Add student in student list
     * @param students Student List
     * @param student Student object to be added
     * @return Whether student is registered
     */
    public boolean registerStudents(ArrayList<Student> students, Student student){
    	// Registers student by using Array List's add method.
    	boolean ret;
    	ret = students.add(student);
    	return ret;
    }
    
    /**
     * Adds event by getting event information from user
     * @param events Event List
     * @return Whether event is added
     */
    public boolean addEvent(ArrayList<Event> events){
    	Scanner sc = null;
    	Event e = null;
    	String s;
    	boolean ret;
    	
    	// Get input from user
    	System.out.print("Enter event name: ");
    	s = sc.next();
    	e.setEventName(s);
    	System.out.print("Enter event date: ");
    	s = sc.next();
    	e.setDate(s);
    	
    	// Get participants student ID's one by one
    	do {
    		System.out.print("Enter participant's student ID or enter \"S\" to stop:");
    		s = sc.next();
    		if(s.equals("S") == false)
    			e.addParticipant( Integer.parseInt(s) );
    	} while (s.equals("S") == false);
    	
    	ret = events.add(e);
    	
    	// Print adding process status
    	if(ret)
    		System.out.println("Event has been added.");
    	else
    		System.out.println("An error occured and event has not been added.");
    	
    	// Return whether adding is successful
    	return ret;
    }
    
    /**
     * Removes an event from event list
     * @param events Event List
     * @param event Event to be removed
     * @return Whether event is removed
     */
    public boolean removeEvent(ArrayList<Event> events, Event event){
    	boolean isRemoved = false;
    	for(int i=0; i<events.size(); i++)
    	{
    		if( events.get(i).getEventName().equals(event.getEventName()) && events.get(i).getDate().equals(event.getDate()) )
    		{
    			events.remove(i);
    			isRemoved = true;
    		}
    	}	
        return isRemoved;
    }
    
	/**
	 * Sets officer's surname
	 * Inherits from Person class
	 * @param surName Officer's surname 
	 */
	public void setUserSurname(String surName) {
		super.setUserSurname(surName);		
	}
}
