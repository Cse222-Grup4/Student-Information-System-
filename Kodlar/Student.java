import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class Student extends Person implements Comparable<Studetnt>{
    private int advisorTeacherID;
    private int entryYear;
    private int term;
    private String department;
    private boolean courseSelectionApprove;
    private ArrayList<Course> currentCourses;
    private ArrayList<ArrayList<Course>> pastCourses;
    private HashMap<String, Integer> Attendance;
    private HashMap<String, Grade> Grades;
    private PriorityQueue<Event> eventsToJoin = new PriorityQueue(new Date.EventComparator());

    /*
    Student(String mail,String password)
    {
        super(mail,password);
        courses = new ArrayList<>();
    }
    */

    Student(String mail,String password,String name,String surname,int id, int year)
    {
        super(mail, password, name, surname, id);
        currentCourses = new ArrayList<>();
        pastCourses = new ArrayList<ArrayList<Course>>();
        entryYear = year;
        Grades = new HashMap();
        Attendance = new HashMap<String, Integer>();

    }

    public ArrayList<Course> getCurrentCourses(){
        return currentCourses;
    }

    public ArrayList<ArrayList<Course>> getPastCourses(){
        return pastCourses;
    }

    public HashMap<String,Integer> getAttendance(){
        return Attendance;
    }

    public HashMap<String, Grade> getGrades(){
        return Grades;
    }

    public void setAdvisorTeacherID(int id)
    {
        advisorTeacherID = id;
    }

    public int getAdvisorTeacherID()
    {
        return advisorTeacherID;
    }

    public void setTerm(int term)
    {
        this.term = term;
    }

    public int getTerm()
    {
        return term;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
    }

    public boolean getCourseSelectionApprove()
    {
        return courseSelectionApprove;
    }

    public void setCourseSelectionApprove(boolean courseSelectionApprove)
    {
        this.courseSelectionApprove = courseSelectionApprove;
    }

    public ArrayList<Course> getCourses()
    {
        return currentCourses;
    }

    public void setCourses(ArrayList<Course> currentCourses)
    {
        this.currentCourses = currentCourses;
    }
    public int getEntryYear(){
        return entryYear;
    }

    /*
    public void addEvent(Event e)
    {
        // Officier.approveEvent(e);
    }
    */

    public void joinEvent(Event e)
    {
        eventsToJoin.add(e);
    }


    public void viewTranscript()
    {
        int year = entryYear;

        System.out.println("Name-Surname: " + this.getUserName() + ' ' + this.getUserSurname());
        System.out.println("ID: " + this.getUserID());
        System.out.println("Department: " + getDepartment());
        System.out.println("Register Date: " + entryYear);
        System.out.println("Advisor Teacher: " + getAdvisorTeacherID());

        for (int i = 0; i < pastCourses.size(); ++i) {
            if (i % 2 == 0)
                System.out.printf("%d Fall%n", year);
            else
                System.out.printf("%d Spring%n", year++);

            for (int k = 0; k < pastCourses.get(i).size(); ++k) {
                Course c = pastCourses.get(i).get(k);
                System.out.printf("Course code: %d | Course name: %s | Course credit: %d | Grade: %s%n", c.getCourseCode(),
                                                                                                         c.getCourseName(),
                                                                                                         c.getCredit(),
                                                                                                         c.getLetterGrade(Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade(), Attendance.get(currentCourses.get(i).getCourseCode())));
            }
        }
    }

    public void viewGrades()
    {
        for (int i = 0; i < currentCourses.size(); ++i)
            System.out.print(Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade());
            //System.out.println(Grades.get(currentCourses.get(i));
    }
    
    // viewing the grade of single course, the course id is given with parameter
    public void viewCourseGrades(String courseCode){
        if(Grades.get(courseCode) == null)
            System.out.println("No grade for student");
        else
            System.out.println(Grades.get(courseCode));
    }

    public void viewAttandance()
    {
        for (int i = 0; i < currentCourses.size(); ++i) {
            Course c = currentCourses.get(i);
            System.out.println("Course name: " + c.getCourseName());
            //System.out.println("Total number of courses " + c.getNumOfAttendance());
            //System.out.println("Number of absent day: " + c.getNumOfAbsent());

           // for (int k = 0; k < c.getAbsent().size(); ++k)
           //     System.out.println("  Date: " + c.getAbsent().get(k).getDate());

            if (c.getLetterGrade(Grades.get(currentCourses.get(i).getCourseCode()).getTotalGrade(), Attendance.get(currentCourses.get(i).getCourseCode())).equals("NA"))
                System.out.println("Currently your grade is NA. You should attend your classes if you want to pass..");
        }
    }

    public void viewCurriculum()
    {
        try {
            FileReader myObj = new FileReader("src/plan.txt");
            BufferedReader myReader = new BufferedReader(myObj);
            while (myReader.ready()){
                System.out.println(myReader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void viewEvents(PriorityQueue<Event> events)
    {
        PriorityQueue<Event> copyEvents = new PriorityQueue<Event>();

        while (!events.isEmpty()) {
            Event e = events.poll();
            System.out.println(e);
            copyEvents.add(e);
        }

        while (!copyEvents.isEmpty())
            events.add(copyEvents.poll());
    }

    // pre: should be mapping for the keys(course codes)
    public void addGrade(String courseCode,int grade,int type)
    {
        if(Grades.get(courseCode) == null)
            Grades.put(courseCode,new Grade());
        switch (type){
            case 1 -> Grades.get(courseCode).setMidtermGrade(grade);
            case 2 -> Grades.get(courseCode).setFinalGrade(grade);
            case 3 -> Grades.get(courseCode).setProjectGrade(grade);
        }
    }
    

    public void endOfSemester()
    {
        pastCourses.add(new ArrayList<Course>(currentCourses));
        currentCourses = new ArrayList<Course>();
        ++term;
        courseSelectionApprove = false;
    }

    public void addEvent(String eventOrder) throws IOException{
      
        BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt",true));
        writer.write("\n"+eventOrder+"\n");
        writer.write("NOT CHECKED");
        writer.close();
    
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
        
    @Override
    public int compareTo(Student o) {
        return Integer.compare(getUserID(),other.getUserID());
    }

}
