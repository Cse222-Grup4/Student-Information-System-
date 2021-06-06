import java.util.ArrayList;

public class Curriculum {
	private ArrayList<Course> courseList;
	private String department;
	Curriculum(){
    	courseList = new ArrayList<>();
    }
    public void addCourse(Course newCourse){
    	courseList.add(newCourse);
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
    	this.department = department;
    }
	
    @Override
    public String toString() {
    	String temp = "";
    	temp += "Department: " + department + "\n" + "Term\tCourse Code\tCourse Name\t\t\tCredit\n" ;
    	for(int i=0;i<courseList.size();i++) {
    		temp += courseList.get(i).getTerm() + "\t" + courseList.get(i).getCourseCode()+ "\t" + courseList.get(i).getCourseName() +"\t\t" + courseList.get(i).getCredit() +"\n";
    	}
    	return temp;
    }	
}
