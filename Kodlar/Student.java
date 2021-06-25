import java.io.*;
import java.util.*;

/**
 * Student class for represent the behaviors of student in our system.
 * This class extends Person abstract class
 */
public class Student extends Person implements Comparable<Student> {
    /**
     * Object of database.
     */
    private DataBase db;

    /**
     * Advisor teacher id of student.
     */
    private int advisorTeacherID;

    /**
     * Entry year of student.
     */
    private int entryYear;

    /**
     * Term of student.
     */
    private int term;

    /**
     * Department of student.
     */
    private String department;

    /**
     * Course selection approve of student.
     */
    private boolean courseSelectionApprove;

    /**
     * Current courses of student.
     */
    private ArrayList<Course> currentCourses;

    /**
     * Past courses of student.
     */
    private ArrayList<ArrayList<Course>> pastCourses;

    /**
     * Attendance of student.
     */
    private HashMap<String, Integer> Attendance;

    /**
     * Grades of student.
     */
    private TreeMap<String, Grade> Grades;

    /**
     * Constructor with 6 parameters.
     * @param mail Mail of student.
     * @param password Password of student.
     * @param name Name of student.
     * @param surname Surname of student.
     * @param id Id of student.
     * @param year Year of student.
     */
    Student(String mail,String password,String name,String surname,int id, int year)
    {
        super(mail, password, name, surname, id);
        currentCourses = new ArrayList<>();
        pastCourses = new ArrayList<ArrayList<Course>>();

        entryYear = year;
        Grades = new TreeMap<>();
        Attendance = new HashMap<String, Integer>();
        setDepartment("CSE");

    }

    /**
     * Get current courses of student.
     * @return Current courses of student.
     */
    public ArrayList<Course> getCurrentCourses()
    {
        return currentCourses;
    }

    /**
     * Get past courses of student.
     * @return Past courses of student.
     */
    public ArrayList<ArrayList<Course>> getPastCourses()
    {
        return pastCourses;
    }

    /**
     * Get attendance of student.
     * @return Attendance of student.
     */
    public HashMap<String,Integer> getAttendance()
    {
        return Attendance;
    }

    /**
     * Get grades of student.
     * @return Grades of student.
     */
    public TreeMap<String, Grade> getGrades()
    {
        return Grades;
    }

    /**
     * Set advisor teacher id of student.
     * @param id Advisor teacher id of student.
     */
    public void setAdvisorTeacherID(int id)
    {
        advisorTeacherID = id;
    }

    /**
     * Get advisor teacher id of student.
     * @return Advisor teacher id of student.
     */
    public int getAdvisorTeacherID()
    {
        return advisorTeacherID;
    }

    /**
     * Set term of student.
     * @param term Term of student.
     */
    public void setTerm(int term)
    {
        this.term = term;
    }

    /**
     * Get term of student.
     * @return Term of student.
     */
    public int getTerm()
    {
        return term;
    }

    /**
     * Set department of student.
     * @param department Department of student.
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    /**
     * Get department of student.
     * @return Department of student.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Is course selection approve the student.
     * @return Course selection approve of student.
     */
    public boolean getCourseSelectionApprove()
    {
        return courseSelectionApprove;
    }

    /**
     * Set course selection approve the student.
     * @param courseSelectionApprove Course selection approve of student.
     */
    public void setCourseSelectionApprove(boolean courseSelectionApprove)
    {
        this.courseSelectionApprove = courseSelectionApprove;
    }

    /**
     * Get courses of student.
     * @return Courses of student.
     */
    public ArrayList<Course> getCourses()
    {
        return currentCourses;
    }

    /**
     * Set courses of student.
     * @param currentCourses Courses of student.
     */
    public void setCourses(ArrayList<Course> currentCourses)
    {
        this.currentCourses = currentCourses;
    }

    /**
     * Get entry year of student.
     * @return Entry year of student.
     */
    public int getEntryYear()
    {
        return entryYear;
    }

    /**
     * Set database object of this.
     * @param db Database object.
     */
    public void setDb(DataBase db)
    {
        this.db = db;
    }
    /**
     * Course selection method of a student.
     */
    public void courseSelection()
    {
        if (courseSelectionApprove == true) {
            System.out.println("You cannot select courses.");
            return;
        }

        currentCourses.clear();

        Scanner kb = new Scanner(System.in);

        viewCurriculum();

        for (;;) {
            System.out.println("Please enter course id: | (Exit: q) ");
            String s = kb.nextLine();

            if (s.equals("q"))
                break;

            if (db.findCourseWID(s) != null) {
                currentCourses.add(db.findCourseWID(s));
                Attendance.put(s,0);
                Grades.put(s,new Grade(0,0,0));
                db.findCourseWID(s).addStudent(this);
            }
            
            	
            else
                System.out.println("There isn't any course with this id.");
        }
    }

    /**
     * Menu method for student class.
     */
    public void menu()
    {
        for (;;) {
            System.out.println("\n        WELCOME TO STUDENT MENU");
            System.out.println("0) Exit");
            System.out.println("1) View Transcript");
            System.out.println("2) View All Grades");
            System.out.println("3) View Course Grade");
            System.out.println("4) View Curriculum");
            System.out.println("5) View Attendances");
            System.out.println("6) Select Courses");
            System.out.println("7) Add Event");
            System.out.println("8) Show Event");

            Scanner kb = new Scanner(System.in);
            int choice = Integer.parseInt(kb.nextLine());

            switch (choice) {
                case 0:
                    return;
                case 1:
                    viewTranscript();
                    break;
                case 2:
                    viewGrades();
                    break;
                case 3:
                    System.out.println("Enter Course Code: ");
                    viewCourseGrades(kb.nextLine().toUpperCase());
                    break;
                case 4:
                    viewCurriculum();
                    break;
                case 5:
                    viewAttendance();
                    break;
                case 6:
                    courseSelection();
                    break;
                case 7:
                    System.out.println("Enter event name");
                    String name = kb.nextLine();
                    System.out.println("Enter event description");
                    String description = kb.nextLine();
                    System.out.println("Enter event date (ex: 25/11/2019):");
                    String date = kb.nextLine();
                    addEvent(name,description,date);
                    break;
                case 8:
                    showEvents();
                    break;
                default:
                    System.out.println("Try Again!");
            }
        }
    }

    /**
     * View transcript of student.
     */
    public void viewTranscript()
    {
        int year = entryYear;
        System.out.println("\n//////////////////////////////////////TRANSCRIPT//////////////////////////////////////\n");
        System.out.println("Name-Surname: " + this.getUserName() + ' ' + this.getUserSurname());
        System.out.println("ID: " + this.getUserID());
        System.out.println("Department: " + getDepartment());
        System.out.println("Register Date: " + entryYear);
        System.out.println("Advisor Teacher ID: " + getAdvisorTeacherID());
        System.out.println("\nCurrent Courses:");
        if (term % 2 == 1)
            System.out.printf("  %d Fall%n", year + term/2);
        else
            System.out.printf("  %d Spring%n", year + term/2);
        for (int i = 0; i < currentCourses.size(); ++i) {
            Course c = currentCourses.get(i);
            System.out.printf("      Course code: %s | Course name: %s | Course credit: %d | Grade: %s%n", c.getCourseCode(),
                    c.getCourseName(),
                    c.getCredit(),
                    c.getLetterGrade(Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade(),
                            Attendance.get(currentCourses.get(i).getCourseCode())));

        }

        System.out.println("\nPast Courses: ");
        for (int i = 0; i < pastCourses.size(); ++i) {
            if (i % 2 == 0)
                System.out.printf("  %d Fall%n", year);
            else
                System.out.printf("  %d Spring%n", year++);

            for (int k = 0; k < pastCourses.get(i).size(); ++k) {
                Course c = pastCourses.get(i).get(k);
                System.out.printf("      Course code: %s | Course name: %s | Course credit: %d | Grade: %s%n", c.getCourseCode(),
                        c.getCourseName(),
                        c.getCredit(),
                        c.getLetterGrade(Grades.get(currentCourses.get(i).getCourseCode()).getPastTotalGrade()));
            }
        }
        System.out.println("\n//////////////////////////////////////////////////////////////////////////////////////\n");
    }

    /**
     * View grades of student.
     */
    public void viewGrades()
    {
        System.out.println("\nCurrent Courses:");
        if (term % 2 == 1)
            System.out.printf("  %d Fall%n", entryYear + term/2);
        else
            System.out.printf("  %d Spring%n", entryYear + term/2);
        for (int i = 0; i < currentCourses.size(); ++i)
            System.out.println("      Code: " + currentCourses.get(i).getCourseCode() +
                    " | Midterm: " + Grades.get(currentCourses.get(i).getCourseCode()).getMidtermGrade() +
                    " | Final: " + Grades.get(currentCourses.get(i).getCourseCode()).getFinalGrade() +
                    " | Project: " + Grades.get(currentCourses.get(i).getCourseCode()).getProjectGrade() +
                    " | Total Grade: " + Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade() +
                    " | Name: " + currentCourses.get(i).getCourseName());
    }

    /**
     * View course grades of student.
      * @param courseCode Course code of course.
     */
    public void viewCourseGrades(String courseCode){
        if(Grades.get(courseCode) == null)
            System.out.println("No grade for student");
        else
            System.out.println("      " + Grades.get(courseCode));
    }

    /**
     * View attendance of student.
     */
    public void viewAttendance()
    {
        for (int i = 0; i < currentCourses.size(); ++i) {
            Course c = currentCourses.get(i);
            System.out.println("Course: " + c.getCourseCode() + " | " + c.getCourseName());
            System.out.println("      Total number of courses " + c.getTotalCourses());
            System.out.println("      Number of absent day: " + Attendance.get(c.getCourseCode()));

            if (c.getLetterGrade(Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade(),
                    Attendance.get(currentCourses.get(i).getCourseCode())).equals("NA"))
                System.out.println("        Currently your grade is NA. You should attend your classes if you want to pass..");
            System.out.printf("\n");
        }
    }

    /**
     * View curriculum of a student.
     */
    public void viewCurriculum()
    {
        try {
            FileReader myObj = new FileReader(getDepartment() + ".txt");
            BufferedReader myReader = new BufferedReader(myObj);

            while (myReader.ready())
                System.out.println(myReader.readLine());

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Add grade of student.
     * @param courseCode Course code of course.
     * @param grade Grade of student.
     * @param type Type of exam.
     */
    public void addGrade(String courseCode,int grade,int type)
    {
        if(Grades.get(courseCode) == null)
            Grades.put(courseCode,new Grade());
    switch (type) {
            case 1:
                Grades.get(courseCode).setMidtermGrade(grade);
                break;
            case 2:
                Grades.get(courseCode).setFinalGrade(grade);
                break;
            case 3:
                Grades.get(courseCode).setProjectGrade(grade);
                break;
        }
    }

    /**
     * Finish semester method.
     */
    public void endOfSemester()
    {
        pastCourses.add(new ArrayList<Course>(currentCourses));
        currentCourses = new ArrayList<Course>();
        ++term;
        courseSelectionApprove = false;
    }

    /**
     * Add an event method.
     * @param eventOrder Event order of event.
     */
    public void addEvent(String name,String description,String date)
    {   
        String [] parts = date.split("/");
        db.getEvents().offer(new Event(name,description,new Date(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]))));
    }

    /**
     * Show all events.
     */
    public void showEvents()
    {
        Iterator<Event> iter = db.getEvents().iterator();
        
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }

    /**
     * compareTo method of Student class.
     * @param o A student object.
     * @return Result of compare.
     */
    @Override
    public int compareTo(Student o)
    {
        return Integer.compare(getUserID(),o.getUserID());
    }

    /**
     * toString method of Student class.
     * @return String of student.
     */
    public String toString()
    {
        return "Name: " + getUserName() + " Surname: " + getUserSurname() + " Mail: " +
                getUserMail() + " Password: " + getUserPassword() + " User-id: " + getUserID()
                + " Advisor-id: " + getAdvisorTeacherID() + " Department: "  + getDepartment()
                + " Term: " + getTerm() + " Course-Selection: " + getCourseSelectionApprove();
    }

    /**
     * Import students to file.
     * @return String of student.
     */
    public String studentToFile()
    {

        String s =  getUserName() + ";" + getUserSurname() + ";" + getUserMail() + ";" + getUserPassword()
                + ";" + getUserID() + ";" + getAdvisorTeacherID() + ";" + getEntryYear() + ";" +
                getTerm() + ";" + getDepartment() + ";" + getCourseSelectionApprove() + ";";
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        for (int i = 0; i < currentCourses.size(); ++i) {
            sb.append(currentCourses.get(i).getCourseCode()).append('.');
            sb.append(Attendance.get(currentCourses.get(i).getCourseCode())).append('.');
            sb.append(Grades.get(currentCourses.get(i).getCourseCode()).getMidtermGrade()).append('.');
            sb.append(Grades.get(currentCourses.get(i).getCourseCode()).getFinalGrade()).append('.');
            sb.append(Grades.get(currentCourses.get(i).getCourseCode()).getProjectGrade());
            if (i == currentCourses.size() - 1)
                sb.append(';');
            else
                sb.append(',');
        }

        for (int i = 0; i < pastCourses.size(); ++i) {
            for (int k = 0; k < pastCourses.get(i).size(); ++k) {
                sb.append(pastCourses.get(i).get(k).getCourseCode()).append('.').append(Grades.get(pastCourses.get(i).get(k).getCourseCode()).getPastTotalGrade());

                if (k == pastCourses.get(i).size() - 1)
                    sb.append(':');
                else
                    sb.append(',');
            }

        }

        return sb.toString();
    }

    /**
     * Checks whether a course has been taken or not.
     * @param course Check the course.
     * @return True or false.
     */
    public boolean isTaken(Course course)
    {
        for(int i=0;i<pastCourses.size();i++)
            for(int j=0;j<pastCourses.get(i).size();j++)
                if(pastCourses.get(i).get(j).equals(course))
                    return true;

        return false;
    }
}
