import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
public class AdvisorTeacher extends Teacher {

	private BinarySearchTree<Student> students;
		/**
	 * Constructor for advisor teacher.
	 * @param mail
	 * @param password
	 * @param department
	 * @param db
	 */
	AdvisorTeacher(String mail, String password,String department,DataBase db) {
		super(mail, password,department,db);
		students= new BinarySearchTree<>();
		setIsAdvisor(true);
	}
	/**
	 * Constructor for advisor teacher.
	 * @param mail
	 * @param password
	 * @param department
	 * @param db
	 */
	AdvisorTeacher(String mail,String password,String name,String surname,int id,String department,DataBase db) {
		super(mail, password, name, surname, id,department,db);
		students= new BinarySearchTree<>();
		setIsAdvisor(true);
	}
	/**
	 * Approves student's course selection.
	 * @param studentID
	 * @param confirmation
	 */
    public void approveCourseSelection(int studentID,boolean confirmation){
    	getStudent(studentID,students).setCourseSelectionApprove(confirmation);
    }
     /**
     * Displays student's informations.
     */
    public void viewStudents() {
    	displayStudents(students);
    }
     /**
     * Displays student's selected courses and selected course's informations.
     * @param studentID
     */
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
    	if(isSatisfied(getStudent(studentID,students))) {
        	System.out.println("Student has satisfied requirements to take this courses");
    	}
    	else {
    		System.out.println("Student has not satisfied requirements to take this courses");
    	}
    }
     /**
     * Returns students which are consultant by advisor teacher.
     * @return
     */
    public BinarySearchTree<Student> getStudents(){
    	return students;
    }
    
    private Student getStudent(int studentID,BinaryTree<Student> students) {
    	try {
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
    		catch(Exception e) {
    		  return null;
    		}
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
	/**
	 * Menu function for advisor teacher.
	 */
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
    					if(confirmation != 0 && confirmation != 1) {
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
	    private boolean isSatisfied(Student student) {
    	ListGraph temp = database.getGraphOfDepartment(getDepartment());
    	for(int i=0;i<student.getCourses().size();i++) {
    		Iterator<Edge> iter = temp.edgeIterator(database.indexOfCourse(student.getCourses().get(i)));
    		while(iter.hasNext()) {
    			int index = iter.next().getDest();
    			if(index != -1) {
        			if(!student.isTaken(database.getCourse(index))) {
        				return false;
        			}
    			}
    		}
    	}
    	return true;
    }

}
