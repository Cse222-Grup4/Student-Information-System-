import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Officer extends Person {

  /**
   * Constructor with five parameters.
   * @param name Officer's name.
   * @param surname Officer's surname.
   * @param mail Officer's e-mail address.
   * @param password Officer's account password.
   * @param ID Officer ID number.
   */
  private DataBase db;
  Officer(String name, String surname, String mail, String password, int ID,DataBase db) {
    super(mail, password, name, surname, ID);
    this.db=db;
    db.addOfficer(this);
    //TODO Auto-generated constructor stub
  }

  /**
   * Constructor with two parameters.
   * @param mail Officer's e-mail address.
   * @param password Officer's password.
   */
  Officer(String mail, String password) {
    super(mail, password);
  }

  /**
   * Prints student's certificate.
   * @param student Student object.
   */
  public void printCertificate(Student student) {
    student.viewTranscript();
  }

  

  /**
   * Inquire student information by Student ID
   * @param students Student List
   * @param studentID Student's ID to inquire
   * @return Student object
   */
  public Student inquireStudentInformation(int studentID) {
    
    for (int i = 0; i < db.getStudents().size(); i++) {
      if (db.getStudents().get(i).getUserID() == studentID)
        return students.get(i);
    }
    return null;
  }

  /**
   * Prints student's Transcript
   * @param students Student List
   * @param studentID Student's ID to print transcript
   */
  public void viewTranscript(int studentID) {
    for (int i = 0; i < db.getStudents().size(); i++) {
    	if (db.getStudents().get(i).getUserID() == studentID){
        students.get(i).viewTranscript();
        break;
      }
    }
  }

  /**
   * Add student in student list
   * @param students Student List
   * @param student Student object to be added
   * @return Whether student is registered
   */
  public boolean registerStudents( Student student) {
    // Registers student by using Array List's add method.
    boolean ret;
    ret = db.getStudents().add(student);
    return ret;
  }

  /**
   * Sets officer's surname
   * Inherits from Person class
   * @param surName Officer's surname 
   */
  public void setUserSurname(String surName) {
    super.setUserSurname(surName);
  }
  
  
    public void confirmCancelEvents(int situation,String orderEvent) throws IOException{

        File events = new File("events.txt");
        File temp = new File("deleted.txt");
        String line;
        temp.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader("events.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("deleted.txt"));

        br.readLine();

        while((line = br.readLine()) != null){

            if(situation == 1){
                if(line.contains(orderEvent)){
                    writer.write(line + "\n");
                    writer.write("Situation: CONFIRM ORDER "+ "\n");
                    br.readLine();
                
                }

                else {
                    writer.write(line + "\n");
                }
            }
        
            else if(situation == 0){
                if(line.contains(orderEvent)){

                    writer.write(line+"\n" );
                    writer.write("Situation: CANCELLED ORDER" + "\n");
                    br.readLine();
                }
            
                else{
                    writer.write(line + "\n");
                }
                
            }
        

        }
            br.close();
            writer.close();
            events.delete();
            temp.renameTo(events);
        
    }

    public void menu()throws Exception{
    	int choice,id;
    	boolean flag=true;
    	while(flag) {
    		System.out.println("0-) Exit");
    		System.out.println("1-) Inquire Student information with student id");
    		System.out.println("2-) View transcript");
    		System.out.println("3-) Register student");
    		System.out.println("4-) Confirm/cancel event");
    		System.out.println("5-) Show events");
    		System.out.println("6-) Add new event");
    		Scanner input = new Scanner(System.in);
        choice = Integer.parseInt(input.nextLine());
        switch (choice) {
          case 0:
            flag=false;
            break;
          case 1:
            System.out.println("Enter student id: ");
            id=Integer.parseInt(input.nextLine());
            inquireStudentInformation(id);
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
    public void showEvents()throws IOException{

        Queue<String>record = new LinkedList<String>();
        String line = "";

        BufferedReader br = new BufferedReader(new FileReader("events.txt"));

        br.readLine();

        while((line = br.readLine()) != null){
            record.add(line + "\n");
        }
    
        br.close();

        System.out.println(record);
    }


    public void addEvent(String eventOrder) throws IOException{
      
        BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt",true));
        writer.write("\n"+eventOrder+"\n");
        writer.write("NOT CHECKED");
        writer.close();
    
    }
  
  
}
