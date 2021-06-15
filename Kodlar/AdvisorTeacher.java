import java.util.ArrayList;

public class AdvisorTeacher extends Teacher {

	private BinarySearchTree<Student> students;
	
	AdvisorTeacher(String mail, String password,DataBase db) {
		super(mail, password,db);
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

}
