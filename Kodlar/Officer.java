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
  Officer(String name, String surname, String mail, String password, int ID) {
    super(name, surname, mail, password, ID);
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
  public Student inquireStudentInformation(ArrayList < Student > students, int studentID) {
    
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getID() == studentID)
        return students.get(i);
    }
  }

  /**
   * Prints student's Transcript
   * @param students Student List
   * @param studentID Student's ID to print transcript
   */
  public void viewTranscript(ArrayList < Student > students, int studentID) {
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getID() == studentID){
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
  public boolean registerStudents(ArrayList < Student > students, Student student) {
    // Registers student by using Array List's add method.
    boolean ret;
    ret = students.add(student);
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
