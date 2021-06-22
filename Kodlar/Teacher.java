import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Teacher extends Person {
    private final DataBase database;
    private SkipList<Course> courses;
    private String department;
    private boolean isAdvisor;

    static final Scanner input = new Scanner(System.in);

    Teacher(String mail,String password,String department,DataBase db)
    {
        super(mail,password);
        database = db;
        courses = new SkipList<>();
        this.department = department;
        isAdvisor = false;
    }

    Teacher(String mail,String password,String name,String surname,int id,String department,DataBase db)
    {
        super(mail, password, name, surname, id);
        database = db;
        courses = new SkipList<>();
        this.department = department;
        isAdvisor = false;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setIsAdvisor(boolean advisor) {
        isAdvisor = advisor;
    }
    public boolean getIsAdvisor() {
       return isAdvisor;
    }

    
    

    public ArrayList<String> getCourseIDs(){
        ArrayList<String> courseIDs = new ArrayList<>();

        for (Course course : courses) {
            courseIDs.add(course.getCourseCode());
        }
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



    public void viewCurriculum() {
        try {
            FileReader myObj = new FileReader("src/" + getDepartment() + ".txt");
            BufferedReader myReader = new BufferedReader(myObj);
            while (myReader.ready()){
                System.out.println(myReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void inquireStudentInformation(Student student){
        student.viewTranscript();
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
                System.out.println("5-Add Attendance");
                System.out.println("6-View Curriculum");
                System.out.println("7-Inquire Student Information's(Transcript of Student)");
                System.out.println("0-Exit");
                inputLine = input.nextLine();
                choice = Integer.parseInt(inputLine);

                switch (choice) {
                    case 1 -> performViewSingleGradeTask();
                    case 2 -> performViewCourseGradeTask();
                    case 3 -> performAddGradeTask();
                    case 4 -> performEditGradeTask();
                    case 5 -> performAddAttendance();
                    case 6 -> viewCurriculum();
                    case 7 -> performInquireStudentInformation();
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
            viewAllGrades(tempCourse.getStudents(), tempCourse.getCourseCode());
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
        counter = 0;
        while (iterator.hasNext()){
            tempCourse = iterator.next();
            if(counter == inputSelect-1){
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

    private void performAddAttendance(){
        String inputLine;
        int inputSelectStudent;

        Course inputCourse = inputCourseSelection();
        if(inputCourse == null){
            System.out.println("The course couldn't found.");
            return;
        }

        ArrayList<Student> tempStudents = inputCourse.getStudents();
        System.out.println("The students who takes the course is listed below. Please type the index of the student you want to increase attendance");

        int counter = 1;
        for (Student student : tempStudents) {
            System.out.println(counter + "-" + student.getUserID() + "--" + student.getUserName() + "--" + student.getUserSurname());
            counter++;
        }
        inputLine = input.nextLine();
        inputSelectStudent = Integer.parseInt(inputLine);

        addAttendance(tempStudents.get(inputSelectStudent-1),inputCourse.getCourseCode());

    }
    private void performInquireStudentInformation(){
      /*  String inputLine;
        int inputSelectStudent;

        Course inputCourse = inputCourseSelection();
        if(inputCourse == null){
            System.out.println("The course couldn't found.");
            return;
        }
        ArrayList<Student> tempStudents = inputCourse.getStudents();
        System.out.println("The students who takes the course is listed below. Please type the index of the student you want to inquire information's");

        int counter = 1;
        for (Student student : tempStudents) {
            System.out.println(counter + "-" + student.getUserID() + "--" + student.getUserName() + "--" + student.getUserSurname());
            counter++;
        }
        inputLine = input.nextLine();
        inputSelectStudent = Integer.parseInt(inputLine);

       inquireStudentInformation(tempStudents.get(inputSelectStudent-1));

*/
        String inputLine;
        int inputID;

        System.out.println("Please type the Student ID for inquire the information's of student.");
        inputLine = input.nextLine();
        inputID = Integer.parseInt(inputLine);
        Student student = database.findStudentWID(inputID);
        if(student == null)
            System.out.println("Student couldn't found.");
        else
            inquireStudentInformation(student);

    }



    @Override
    public String toString() {
        System.out.println(super.toString());
        System.out.println("Department: "+ getDepartment());
        System.out.println("Teacher");
        for (Course course : courses) {
            System.out.println(course.getCourseCode());
        }
        return "";
    }
}
