import java.io.*;
import java.util.*;

/*
do:
    CSE.TXT dosyasi => BILAL
    students.txt dosyasi => berkcan
    officier menu database - student ctor
*/

public class Student extends Person implements Comparable<Student> {
    private DataBase db;
    private int advisorTeacherID;
    private int entryYear;
    private int term;
    private String department;
    private boolean courseSelectionApprove;
    private ArrayList<Course> currentCourses;
    private ArrayList<ArrayList<Course>> pastCourses;
    private HashMap<String, Integer> Attendance;        // treeset treemap kullan
    private TreeMap<String, Grade> Grades;
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
        Grades = new TreeMap<>();
        Attendance = new HashMap<String, Integer>();

    }

    public ArrayList<Course> getCurrentCourses()
    {
        return currentCourses;
    }

    public ArrayList<ArrayList<Course>> getPastCourses()
    {
        return pastCourses;
    }

    public HashMap<String,Integer> getAttendance()
    {
        return Attendance;
    }

    public TreeMap<String, Grade> getGrades()
    {
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

    public int getEntryYear()
    {
        return entryYear;
    }

    public void setDb(DataBase db)
    {
        this.db = db;
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

            if (db.findCourseWID(s) != null)
                currentCourses.add(db.findCourseWID(s));
            else
                System.out.println("There isn't any course with this id.");
        }
    }

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
            // etkinlik ekle
            // etkinlik goruntule


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
                    System.out.println("Enter event");
                    String eventOrder = kb.nextLine();
                    // enter description
                    // enter date
                    // Event = new Event(name, description, date);
                    addEvent(eventOrder);
                    break;
                case 8:
                    showEvents();
                    break;
                default:
                    System.out.println("Try Again!");
            }
        }
    }

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
        //System.out.println(Grades.get(currentCourses.get(i));
    }

    // viewing the grade of single course, the course id is given with parameter
    public void viewCourseGrades(String courseCode){
        if(Grades.get(courseCode) == null)
            System.out.println("No grade for student");
        else
            System.out.println("      " + Grades.get(courseCode));
    }

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

    public void viewCurriculum()
    {
        try {
            FileReader myObj = new FileReader("src/" + getDepartment() + ".txt");
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

    public void addEvent(String eventOrder)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/events.txt",true));
            writer.write("\n"+eventOrder+"\n");
            writer.write("NOT CHECKED");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void showEvents()
    {

        Queue<String>record = new LinkedList<String>();
        String line = "";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/events.txt"));
            br.readLine();

            while((line = br.readLine()) != null)
                record.add(line + "\n");

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(record);
    }

    @Override
    public int compareTo(Student o)
    {
        return Integer.compare(getUserID(),o.getUserID());
    }

    public String toString()
    {
        return "isim: " + getUserName() + " soyisim: " + getUserSurname() + " mail: " +
                getUserMail() + " sifre: " + getUserPassword() + " user id: " + getUserID()
                + " advisor id: " + getAdvisorTeacherID() + " department: "  + getDepartment()
                + " term: " + getTerm() + " ders durumu: " + getCourseSelectionApprove();
    }

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

                // sondaki iki nokta iyilestirilecek
                if (k == pastCourses.get(i).size() - 1)
                    sb.append(':');
                else
                    sb.append(',');
            }

        }

        return sb.toString();
    }

}
