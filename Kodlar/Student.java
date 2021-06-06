import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Student extends Person implements Comparable<Student>{
    private int advisorTeacherID;
    private int entryYear;
    private int term;
    private String department;
    private boolean courseSelectionApprove;
    private ArrayList<Course> currentCourses;
    private ArrayList<ArrayList<Course>> pastCourses;
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
        entryYear = year;
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
                                                                                                         c.getLetterGrade());
            }
        }
    }

    public void viewGrades()
    {
        for (int i = 0; i < currentCourses.size(); ++i)
            System.out.print(currentCourses.get(i).getGrade().toString());
    }

    public void viewAttandance()
    {
        for (int i = 0; i < currentCourses.size(); ++i) {
            Course c = currentCourses.get(i);
            System.out.println("Course name: " + c.getCourseName());
            System.out.println("Total number of courses " + c.getNumOfAttendance());
            System.out.println("Number of absent day: " + c.getNumOfAbsent());

            for (int k = 0; k < c.getAbsent().size(); ++k)
                System.out.println("  Date: " + c.getAbsent().get(k).getDate());

            if (c.getLetterGrade().equals("NA"))
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

    public void addGrade(String courseCode,int grade,int type)
    {
        for (Course course : currentCourses)
            if (course.getCourseCode().equals(courseCode))
                course.addGrade(grade,type);
    }

    public void endOfSemester()
    {
        pastCourses.add(new ArrayList<Course>(currentCourses));
        currentCourses = new ArrayList<Course>();
        ++term;
        courseSelectionApprove = false;
    }
    
	@Override
	public int compareTo(Student o) {
		if(getUserID() == o.getUserID()) {
			return 0;
		}
		else if(getUserID() > o.getUserID()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
