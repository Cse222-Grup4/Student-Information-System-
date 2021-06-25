import java.util.Scanner;
import java.io.*;
import java.util.Iterator;

/**
 * Officer class for represent the behaviors of officers in our system.
 * This class extends Person abstract class.
 */
public class Officer extends Person {
	/**
	 * Database reference type.
	 */
	protected DataBase db;

	/**
	 * Constructor with five parameters.
	 * @param name Officer's name.
	 * @param surname Officer's surname.
	 * @param mail Officer's e-mail address.
	 * @param password Officer's account password.
	 * @param ID Officer ID number.
	 */
	Officer(String name, String surname, String mail, String password, int ID,DataBase db)
	{
		super(mail, password, name, surname, ID);
		this.db=db;
	}

  	/**
     	* Constructor with two parameters.
     	* @param mail Officer's e-mail address.
     	* @param password Officer's password.
     	*/
  	Officer(String mail, String password) 
	{
  		super(mail, password);
  	}

  	/**
  	 * Prints student's certificate.
  	 * @param student Student object.
  	 */
  	public void printCertificate(Student student) 
	{
  		student.viewTranscript();
  	}

  	/**
  	* Inquire student information by Student ID
  	* @param studentID Student's ID to inquire
  	* @return Student object
  	*/
  	public Student inquireStudentInformation(int studentID) 
	{
    
  		for (int i = 0; i < db.getStudents().size(); i++) 
  			if (db.getStudents().get(i).getUserID() == studentID)
  				return db.getStudents().get(i);
  			
  		return null;
  	}

  	/**
  	 * Prints student's Transcript
  	 * @param studentID Student's ID to print transcript
  	 */
  	public void viewTranscript(int studentID) 
	{
  		for (int i = 0; i < db.getStudents().size(); i++) 
  			if (db.getStudents().get(i).getUserID() == studentID) {
  				db.getStudents().get(i).viewTranscript();
  				break;
  			}
  		
  	}

  	/**
  	 * Add student in student list
  	 * @param student Student object to be added
  	 * @return Whether student is registered
  	 */
  	public boolean registerStudents( Student student) 
	{
  		boolean ret;
  		ret = db.getStudents().add(student);
  		return ret;
  	}
  	
  	/**
  	 * Sets officer's surname
  	 * Inherits from Person class
  	 * @param surName Officer's surname 
  	 */
  	public void setUserSurname(String surName) 
	{
  		super.setUserSurname(surName);
  	}
  
  	/**
  	 * takes an event name and cancels or confirms, depending on the situation. 
  	 * @param situation 1 or 0. 1 means confirm 0 means cancel
  	 * @param orderEvent event name
  	 * @throws IOException it throws an exception if the file cannot be opened.
  	 */
  	public void confirmCancelEvents(int situation,String eventName){
  		Event eventTemp;
  		Iterator<Event> iter = db.getEvents().iterator();
  		while(iter.hasNext()) {
  			eventTemp = iter.next();
  			if(eventTemp.getEventName().equals(eventName)){
  				eventTemp.setSituation(situation == 1);
  				eventTemp.setWaitSituation(situation != 1);
  				return;
  			}
  		}
  		System.out.println("Event couldn't found!");
    }

  	/**
  	 * menu for Officer
  	 * @throws Exception it throws an exception if the file cannot be opened.
  	 */
    public void menu() throws Exception
	{
    	int choice, id;
    	boolean flag=true;

    	while (flag) {
    		System.out.println("0-) Exit");
    		System.out.println("1-) Inquire Student information with student id");
    		System.out.println("2-) View transcript");
    		System.out.println("3-) Register student");
    		System.out.println("4-) Confirm/cancel event");
    		System.out.println("5-) Show events");
    		System.out.println("6-) Add new event");
    		@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
	        choice = Integer.parseInt(input.nextLine());

	        switch (choice) {
	        	case 0:
	        		flag=false;
	        		break;
	        	case 1:
	        		System.out.println("Enter student id: ");
	        		id=Integer.parseInt(input.nextLine());
	        		if(inquireStudentInformation(id) == null) {
	        			System.out.println("Student couldn't found!");
	        		}
	        		else {
	        			System.out.println(inquireStudentInformation(id));
	        		}
	        		break;
	        	case 2:
	        		System.out.println("Enter student id: ");
	        		id=Integer.parseInt(input.nextLine());
	        		viewTranscript(id);
	        		break;
	        	case 3:
	        		System.out.println("Enter student mail");
	        		String mail=input.nextLine();
	        		System.out.println("Enter student passwd");
	        		String passwd=input.nextLine();
	        		System.out.println("Enter student name");
	        		String name=input.nextLine();
	        		System.out.println("Enter student surname");
	        		String surname=input.nextLine();
	        		System.out.println("Enter student id");
	        		id=Integer.parseInt(input.nextLine());
	        		System.out.println("Enter year");
	        		int year=Integer.parseInt(input.nextLine());
	        		Student student=new Student(mail,passwd,name,surname,id,year);
	        		student.setDb(db);
	        		boolean b=registerStudents(student);
	        		if(!b)
	        			System.out.println("This student is already registered.");
	        		// STUDENTS.TXT
	        		break;
	        	case 4:
					System.out.println("Enter event name");
					String eventName=input.nextLine();
					System.out.println("Enter Event Sitition(ex: 1 or 0):");
					int sitition=Integer.parseInt(input.nextLine());
	        		confirmCancelEvents(sitition, eventName);
					break;
	        	case 5:
	        		showEvents();
	        		break;
	        	case 6:
                    System.out.println("Enter event name");
                    name = input.nextLine();
                    System.out.println("Enter event description");
                    String description = input.nextLine();
                    System.out.println("Enter event date (ex: 25/11/2019):");
                    String date = input.nextLine();
                    addEvent(name,description,date);
					break;
	        	default:
	        		System.out.println("Please choose from the menu");
	        		break;
	        }
	    }
    }

    /**
     * Shows the events in the event.txt file
     * @throws IOException it throws an exception if the file cannot be opened.
     */
    public void showEvents()throws IOException{
        Iterator<Event> iter = db.getEvents().iterator();
        
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    /**
     * Add an event method.
     * @param eventOrder Event order of event.
     */
    public void addEvent(String name,String description,String date)
    {   
        String [] parts = date.split("/");
        db.getEvents().offer(new Event(name,description,new Date(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]))));
    }
}
