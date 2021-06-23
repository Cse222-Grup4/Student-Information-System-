import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Teacher class for represent the behaviors of teacher in our system. Since teacher is a user of this system,
 * this class extends Person class.
 */
public class Teacher extends Person {

    /**
     * DataBase object for communicate with database
     */
    private final DataBase database;
    /**
     * The courses of teacher. Using SkipList data structure, because Skip-List provide sorted access and fast operations.
     */
    private SkipList<Course> courses;
    /**
     * The department of teacher
     */
    private String department;
    /**
     * Flag to indicate whether the teacher is advisor teacher
     */
    private boolean isAdvisor;

    /**
     * Constructor with 4 parameters
     * @param mail The mail of teacher
     * @param password The password of teacher
     * @param department The department of teacher
     * @param db The database
     */
    Teacher(String mail,String password,String department,DataBase db) {
        super(mail,password);
        database = db;
        courses = new SkipList<>();
        this.department = department;
        isAdvisor = false;
    }
    /**
     * Constructor with 7 parameters
     * @param mail The mail of teacher
     * @param password The password of teacher
     * @param name The name of teacher
     * @param surname The surname of teacher
     * @param id The ID of teacher
     * @param department The department of teacher
     * @param db The database
     */
    Teacher(String mail,String password,String name,String surname,int id,String department,DataBase db) {
        super(mail, password, name, surname, id);
        database = db;
        courses = new SkipList<>();
        this.department = department;
        isAdvisor = false;
    }

    /**
     * Return the department of teacher
     * @return The department of teacher
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Set the department of teacher
     * @param department The department of teacher
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Set the advisor teacher flag
     * @param advisor true if this is a advisor teacher
     */
    public void setIsAdvisor(boolean advisor) {
        isAdvisor = advisor;
    }
    /**
     * Return whether this is a advisor teacher
     * @return true if this is a advisor teacher
     */
    public boolean getIsAdvisor() {
       return isAdvisor;
    }

    /**
     * Return the Course Codes of courses of teacher
     * @return Array(ArrayList) of course codes(String)
     */
    public ArrayList<String> getCourseIDs(){
        ArrayList<String> courseIDs = new ArrayList<>();
        for (Course course : courses) {
            courseIDs.add(course.getCourseCode());
        }
        return courseIDs;
    }


    /**
     * Viewing all the grades of given students in terms of given course.
     * @param students The students who take course
     * @param courseCode The code of course
     */
    public void viewAllGrades(ArrayList<Student> students,String courseCode) {

        if(students == null || students.size() == 0) {
            System.out.println("There is no student.");
            return;
        }
        for (Student student : students) {
            System.out.println("The grades for student:" + student.getUserName() + " " + student.getUserSurname() +" of course:" + courseCode);
            student.viewCourseGrades(courseCode);
        }
    }
    /**
     * Viewing the grades for single student.
     * @param student The student
     */
    public void viewSingleStudentGrades(Student student) {
            System.out.println("The grades for student:" + student.getUserName() + " " + student.getUserSurname());
            student.viewGrades();
    }
    /**
     * Add/Edit grade of student in terms of given course
     * @param courseCode The code of course
     * @param student The course
     * @param grade The grade out of 100
     * @param type The type of grade. 1 for midterm, 2 for final, 3 for project.
     */
    public void addGrade(String courseCode,Student student,int grade,int type) {
        student.addGrade(courseCode,grade,type);
    }
    /**
     * Add the given course to teachers courses
     * @param course The new course
     */
    public void addCourse(Course course){
        courses.add(course);
    }
    /**
     * Increasing the attendance of student attendance in terms of given course
     * @param student The student
     * @param courseCode The code of course
     */
    public void addAttendance(Student student,String courseCode){
        if(student.getAttendance().containsKey(courseCode)) {
            student.getAttendance().replace(courseCode, student.getAttendance().get(courseCode)+1);
        }
        student.getAttendance().put(courseCode, 1);
    }
    /**
     * Viewing the curriculum of department
     */
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
    /**
     * Displaying the transcript of the given student
     * @param student The student
     */
    public void inquireStudentInformation(Student student){
        student.viewTranscript();
    }

    /**
     * Main menu for Teacher
     */
    public void performTasks() {
        try {
            Scanner input = new Scanner(System.in);
            String inputLine;
            int choice;
            System.out.println("-------------Welcome To Teacher Menu-------------");
            System.out.println("ID:" + getUserID() +" --- Name:" + getUserName() +" --- Surname:" + getUserSurname()) ;
            do {
                System.out.println("\nPlease select from the Teacher Menu:");
                System.out.println("1-View Single Student Grades");
                System.out.println("2-View Course Grades");
                System.out.println("3-Add Grade");
                System.out.println("4-Edit Grade");
                System.out.println("5-Add Attendance");
                System.out.println("6-View Curriculum");
                System.out.println("7-Inquire Student Information's(Transcript of Student)");
                System.out.println("8-Display Teacher Information's");
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
                    case 8 -> System.out.println(this);
                    case 0 -> System.out.println("Returning main menu..\n");
                    default -> System.out.println("Please select from menu\n");
                }
            } while (choice != 0);
        }catch (NumberFormatException nfe){
            System.out.println("!You typed char instead of number!");
            nfe.printStackTrace();
        }
    }

    /**
     * Menu for View Single Grade Task
     */
    private void performViewSingleGradeTask(){
        Scanner input = new Scanner(System.in);
        String inputLine;
        int inputID;

        System.out.println("Please type the Student ID for print out all the grades of student.");
        inputLine = input.nextLine();
        inputID = Integer.parseInt(inputLine);
        Student student = database.findStudentWID(inputID);
        if(student == null)
            System.out.println("Student couldn't found.");
        else
            viewSingleStudentGrades(student);
    }
    /**
     * Menu for View Course Grade Task
     */
    private void performViewCourseGradeTask(){
        System.out.println("Please select from one of your courses to print out all the grades of students who taking the course.");
        if(courses.size() == 0)
            System.out.println("There is no course.");
        else {
            Course tempCourse = inputCourseSelection();
            if(tempCourse == null){
                 System.out.println("The course couldn't found.");
                 return;
             }
            if(tempCourse.getStudents() == null || tempCourse.getStudents().size() == 0)
                System.out.println("There is no student for this course.");
            else
                viewAllGrades(tempCourse.getStudents(), tempCourse.getCourseCode());
        }
    }
    /**
     * Takes the index of course from input, and finds it in the skip-list and returns the course
     * @return If index is correct, returns corresponding course. Else returns null.
     */
    private Course inputCourseSelection(){
        Scanner input = new Scanner(System.in);
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
    /**
     * Menu for Add Grade Task
     */
    private void performAddGradeTask(){
        Scanner input = new Scanner(System.in);
        String inputLine;

        System.out.println("------------------------------------Add/Edit Grade------------------------------------");

        int inputSelectStudent;
        int inputGrade;
        int inputType;

        Course inputCourse = inputCourseSelection();
        if(inputCourse == null){
            System.out.println("The course couldn't found.");
            return;
        }

        ArrayList<Student> tempStudents = inputCourse.getStudents();
        if(tempStudents == null || tempStudents.size() == 0) {
            System.out.println("There is no student who takes this course.");
            return;
        }
        System.out.println("The students who takes the course is listed below. Please type the index of the student you want to add/edit grade.");

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
        System.out.println("Grade is successfully added/edited.");

    }
    /**
     * Menu for Edit Grade Task
     */
    private void performEditGradeTask(){
        performAddGradeTask();
    }
    /**
     * Menu for Add Attendance Task
     */
    private void performAddAttendance(){
        Scanner input = new Scanner(System.in);
        String inputLine;
        int inputSelectStudent;

        System.out.println("------------------------------------Add Attendance------------------------------------");

        Course inputCourse = inputCourseSelection();
        if(inputCourse == null){
            System.out.println("The course couldn't found.");
            return;
        }

        ArrayList<Student> tempStudents = inputCourse.getStudents();
        if(tempStudents == null || tempStudents.size() == 0) {
            System.out.println("There is no student who takes this course.");
            return;
        }
        System.out.println("The students who takes the course is listed below. Please type the index of the student you want to increase attendance");

        int counter = 1;
        for (Student student : tempStudents) {
            System.out.println(counter + "-" + student.getUserID() + "--" + student.getUserName() + "--" + student.getUserSurname());
            counter++;
        }
        inputLine = input.nextLine();
        inputSelectStudent = Integer.parseInt(inputLine);

        addAttendance(tempStudents.get(inputSelectStudent-1),inputCourse.getCourseCode());
        System.out.println("Attendance is successfully increased.");
    }
    /**
     * Menu for Inquire Student Information Task
     */
    private void performInquireStudentInformation(){
        Scanner input = new Scanner(System.in);
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


    /**
     * Returns a string representation of the Teacher
     * @return The string representation of the Teacher
     */
    @Override
    public String toString() {
        if(!isAdvisor)
            System.out.println("Teacher");
        else
            System.out.println("Advisor Teacher");

        System.out.println("Name : " + getUserName());
        System.out.println("Surname : " + getUserSurname());
        System.out.println("Mail : " + getUserMail());
        System.out.println("Password : " + getUserPassword());
        System.out.println("Department : " + getDepartment());
        System.out.println("Courses:");
        if (courses.size() == 0)
            System.out.println("No course.");
        else {
            for (Course course : courses)
                System.out.println(course.getCourseCode());
        }
        return "";

/*
        StringBuilder sb = new StringBuilder();
        if(!isAdvisor)
            sb.append("Teacher\n");
        else
            sb.append("Advisor Teacher\n");
        sb.append("ID : ");
        sb.append(getUserID());
        sb.append("\n");
        sb.append("Name : ");
        sb.append(getUserName());
        sb.append("\n");
        sb.append("SurName : ");
        sb.append(getUserSurname());
        sb.append("\n");
        sb.append("Mail : ");
        sb.append(getUserMail());
        sb.append("\n");
        sb.append("Password : ");
        sb.append(getUserPassword());
        sb.append("\n");
        sb.append("Department : ");
        sb.append(getDepartment());
        sb.append("\n");
        sb.append("Courses : \n");

        if (courses.size() == 0)
            sb.append("No course.\n");
        else {
            for (Course course : courses) {
                sb.append(course.getCourseCode());
                sb.append("\n");
            }
        }
        return sb.toString();
*/

    }
}
