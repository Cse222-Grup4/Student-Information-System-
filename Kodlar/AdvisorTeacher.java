import java.util.ArrayList;
import java.util.Scanner;
public class AdvisorTeacher extends Teacher {

	private BinarySearchTree<Student> students;
	
	AdvisorTeacher(String mail, String password,String department,DataBase db) {
		super(mail, password,department,db);
		students= new BinarySearchTree<>();
	}

	AdvisorTeacher(String mail,String password,String name,String surname,int id,String department,DataBase db) {
		super(mail, password, name, surname, id,department,db);
		students= new BinarySearchTree<>();
	}
	
    public void approveCourseSelection(int studentID,boolean confirmation){
    	getStudent(studentID,students).setCourseSelectionApprove(confirmation);
    }
    
    public void viewStudents() {
    	displayStudents(students);
    }
    
    public void displaySelectedCourses(int studentID) {
    	ArrayList<Course>  temp = getStudent(studentID,students).getCourses();
    	int totalCredit =0;
    	if(temp == null) {
    		System.out.println("There is no selected course from this student!");
    	}
    	else {
        	for(int i=0;i<temp.size();i++) {
        		totalCredit += temp.get(i).getCredit();
        		System.out.println(temp.get(i));
        	}  		
        	System.out.println("Total credit taken by student: "+ totalCredit);
    	}
    }
    
    public BinarySearchTree<Student> getStudents(){
    	return students;
    }
    
    private Student getStudent(int studentID,BinaryTree<Student> students) {
    	if(students.getData().getUserID() == studentID) {
    		return students.getData();
    	}
    	if(students.getData().getUserID() > studentID) {
    		getStudent(studentID,students.getLeftSubtree());
    	}
    	else {
    		getStudent(studentID,students.getRightSubtree());
    	}
    	return null;
    }

    private void displayStudents(BinaryTree<Student> students) {
    	if(students != null) {
    		System.out.println("Name: " + students.getData().getUserName() + " " + students.getData().getUserSurname()+ " ID: "+ students.getData().getUserID());
    		displayStudents(students.getLeftSubtree());
    		displayStudents(students.getRightSubtree());
    	}
    }
	private void showMenu() {
    	for (int i = 0; i < 45; i++) System.out.print("-");
			System.out.print("\n"+"   ");
		System.out.println("Welcome Teacher " + getUserName() + getUserSurname());
		System.out.println("What do you want to do?");
		System.out.println("1) View Students.\n2) View Student's Selected Course\n3) Approve Course Selection\n0) Exit");
    }
    public void performTasks() {
    	Scanner input = new Scanner(System.in);
    	int selection = 1,id,confirmation;
    	while(selection != 0) {
    		showMenu();
    		selection = input.nextInt();
    		switch (selection) {
    			case 1:
    				viewStudents();
    				break;
    			case 2:
    				System.out.println("Enter the ID of student: ");
    				id = input.nextInt();
    				if(getStudent(id,students) == null) {
    					System.out.println("Student couldn't found");
    				}
    				else {
    					displaySelectedCourses(id);
    				}
    				break;
    			case 3:
    				System.out.println("Enter the ID of student: ");
    				id = input.nextInt();
    				if(getStudent(id,students) == null) {
    					System.out.println("Student couldn't found");
    				}
    				else {
    					System.out.println("Do you want to approve?(1-Yes, 0-No)");
    					confirmation = input.nextInt();
    					if(confirmation != 0 || confirmation != 1) {
    						System.out.println("Wrong Input!");
    					}
    					else {
    						approveCourseSelection(id, confirmation == 1);
    					}
    				}
    				break;
    			case 0:
    				break;
    			default:
    				System.out.println("Wrong Selection");
    				break;
    		}
    	}
    }

}
