import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

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
  	public void confirmCancelEvents(int situation,String orderEvent) throws IOException
	{
        File events = new File("src/events.txt");
        File temp = new File("deleted.txt");
        String line;
        temp.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader("src/events.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("deleted.txt"));

        br.readLine();

        while ((line = br.readLine()) != null) {
            if (situation == 1) {
                if (line.contains(orderEvent)) {
                    writer.write(line + "\n");
                    writer.write("Situation: CONFIRM ORDER "+ "\n");
                    br.readLine();
                }
                else
                    writer.write(line + "\n");
            }
            else if (situation == 0) {
                if (line.contains(orderEvent)) {
                    writer.write(line+"\n" );
                    writer.write("Situation: CANCELLED ORDER" + "\n");
                    br.readLine();
                }
                else
                    writer.write(line + "\n");
            }
        }
            br.close();
            writer.close();
            events.delete();
            temp.renameTo(events);
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
	        	case 3://String mail,String password,String name,String surname,int id, int year
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
	        		System.out.println("Enter Event Sitition:");
	        		int sitition=Integer.parseInt(input.nextLine());
	        		System.out.println("Enter event name");
	        		String eventName=input.nextLine();
	        		confirmCancelEvents(sitition, eventName);
	        		break;
	        	case 5:
	        		showEvents();
	        		break;
	        	case 6:
	        		System.out.println("Enter event");
	        		String eventOrder=input.nextLine();
	        		addEvent(eventOrder);
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
    public void showEvents() throws IOException
	{
        Queue<String>record = new LinkedList<String>();
        String line = "";

        BufferedReader br = new BufferedReader(new FileReader("src/events.txt"));
        br.readLine();

        while ((line = br.readLine()) != null)
            record.add(line + "\n");
    
        br.close();
        System.out.println(record);
    }

    /**
     * takes a new event and adds it inside the events.txt.
     * @param eventOrder event name
     * @throws IOException it throws an exception if the file cannot be opened.
     */
    public void addEvent(String eventOrder) throws IOException
	{
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/events.txt",true));
        writer.write("\n"+eventOrder+"\n");
        writer.write("NOT CHECKED");
        writer.close();
    }
}
