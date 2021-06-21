import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Teacher extends Person {
    private final DataBase database;
    private SkipList<Course> courses;

    static final Scanner input = new Scanner(System.in);

    Teacher(String mail,String password,DataBase db)
    {
        super(mail,password);
        database = db;
        courses = new SkipList<>();
    }

    Teacher(String mail,String password,String name,String surname,int id,DataBase db)
    {
        super(mail, password, name, surname, id);
        database = db;
        courses = new SkipList<>();
    }
    
    public ArrayList<String> getCourseIDs(){
        ArrayList<String> courseIDs = new ArrayList<>();

        for (Course course : courses) {
            courseIDs.add(course.getCourseCode());
        }

       /*
        Iterator<Course> iterator = courses.iterator();
        while(iterator.hasNext()){
            courseIDs.add(iterator.next().getCourseCode());
        }
        */

        return courseIDs;
    }
    
    

    public void viewAllGrades(ArrayList<Student> students,String courseCode) {
        for (Student student : students) {
            System.out.println("The grades for student:" + student.getUserName() + " " + student.getUserSurname() +" of course:" + courseCode);
            student.viewCourseGrades(courseCode);
        }
    }
    public void viewSingleStudentGrades(Student student) {
            System.out.println("The grades for student:" + student.getUserName() + " " + student.getUserSurname());
            student.viewGrades();
    }


    public void addGrade(String courseCode,Student student,int grade,int type) {
        student.addGrade(courseCode,grade,type);
    }
   /*
   public void editGrade(String courseCode,Student student,int grade,int type){
        addGrade(courseCode, student, grade, type);
    }*/

    public void addCourse(Course course){
        courses.add(course);
    }


    public void addAttendance(Student student,String courseCode){
        if(student.getAttendance().containsKey(courseCode)) {
            student.getAttendance().replace(courseCode, student.getAttendance().get(courseCode)+1);
        }
        student.getAttendance().put(courseCode, 1);
    }



    public void performTasks() {
        try {
            String inputLine;
            int choice;
            System.out.println("ID:" + getUserID() +" ---Name:" + getUserName()) ;
            do {
                System.out.println("\nPlease select from the Teacher Menu:");
                System.out.println("1-View Single Student Grades");
                System.out.println("2-View Course Grades");
                System.out.println("3-Add Grade");
                System.out.println("4-Edit Grade");
                System.out.println("0-Exit");
                inputLine = input.nextLine();
                choice = Integer.parseInt(inputLine);

                switch (choice) {
                    case 1 -> performViewSingleGradeTask();
                    case 2 -> performViewCourseGradeTask();
                    case 3 -> performAddGradeTask();
                    case 4 -> performEditGradeTask();
                    case 0 -> System.out.println("Returning main menu..\n");
                    default -> System.out.println("Please select from menu\n");
                }
            } while (choice != 0);
        }catch (NumberFormatException nfe){
            System.out.println("!You typed char instead of number!");
            nfe.printStackTrace();
        }
    }

    private void performViewSingleGradeTask(){
        String inputLine;
        int inputID;

        System.out.println("Please type the Student ID for print out the all grades of student.");
        inputLine = input.nextLine();
        inputID = Integer.parseInt(inputLine);
        Student student = database.findStudentWID(inputID);
        if(student == null)
            System.out.println("Student couldn't found.");
        else
            viewSingleStudentGrades(student);
    }
    private void performViewCourseGradeTask(){
        String inputLine;
        int inputSelect;

        System.out.println("Please select form one of your courses to print out the all grades of student taking the course.");
        if(courses.size() == 0)
            System.out.println("There is no course.");
        else {


            Course tempCourse = inputCourseSelection();
            if(tempCourse == null){
                 System.out.println("The course couldn't found.");
                 return;
             }
            ArrayList<Student> tempStudents =  tempCourse.getStudents();
            for (Student tempStudent : tempStudents)
                tempStudent.viewCourseGrades(tempCourse.getCourseCode());
        }
    }

    private Course inputCourseSelection(){

        int counter = 1;
        System.out.println("Your courses are listed in below. Please type the index of the course you want to select");
        for (Course course : courses) {
            System.out.println(counter + "-" + course.getCourseCode());
            counter++;
        }
        String inputLine = input.nextLine();
        int inputSelect = Integer.parseInt(inputLine);

        Iterator<Course> iterator = courses.iterator();
        Course tempCourse;

        while (iterator.hasNext()){
            tempCourse = iterator.next();
            if(counter == inputSelect){
                return tempCourse;
            }
            counter++;
        }
        return null;
    }

    private void performAddGradeTask(){
        String inputLine;

        int inputSelectStudent;
        int inputGrade;
        int inputType;

        Course inputCourse = inputCourseSelection();
        if(inputCourse == null){
            System.out.println("The course couldn't found.");
            return;
        }

        ArrayList<Student> tempStudents = inputCourse.getStudents();
        System.out.println("The students who takes the course is listed below. Please type the index of the student you want to select");

        int counter = 1;
        for (Student student : tempStudents) {
            System.out.println(counter + "-" + student.getUserID() + "--" + student.getUserName() + "--" + student.getUserSurname());
            counter++;
        }
        inputLine = input.nextLine();
        inputSelectStudent = Integer.parseInt(inputLine);

        System.out.println("Please select the grade type");
        System.out.println("1-Midterm");
        System.out.println("2-Final");
        System.out.println("3-Project");

        inputLine = input.nextLine();
        inputType = Integer.parseInt(inputLine);

        System.out.println("Please type the grade out of 100");
        inputLine = input.nextLine();
        inputGrade = Integer.parseInt(inputLine);

        addGrade(inputCourse.getCourseCode(), tempStudents.get(inputSelectStudent-1),inputGrade,inputType);

    }
    private void performEditGradeTask(){
        System.out.println("Edit Grade Menu");
        performAddGradeTask();
    }



    @Override
    public String toString() {
        System.out.println(super.toString());
        for (Course course : courses) {
            System.out.println(course.getCourseCode());
        }
        return "";
    }
}
