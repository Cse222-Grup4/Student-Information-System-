import java.util.ArrayList;

/**
 * Course class for represent the courses.
 */
public class Course implements Comparable<Course> {
    /**
     * Term of course.
     */
    private int courseTerm;

    /**
     * Code of course.
     */
    private String courseCode;

    /**
     * Name of course.
     */
    private String courseName;

    /**
     * Credit of course.
     */
    private int credit;

    /**
     * Student list of course.
     */
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Total course hours.
     */
    private final int TotalCourses = 30;

    /**
     * Represent the linked courses.
     */
    private String linkedCourse = null;

    /**
     * Departmen of course.
     */
    private String department;

    /**
     * Constructor with 4 parameters.
     * @param courseTerm Term of course.
     * @param courseCode Code of course.
     * @param courseName Name of course.
     * @param credit Credit of course.
     */
    public Course(int courseTerm, String courseCode, String courseName, int credit)
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.courseTerm = courseTerm;
    }

    /**
     * Get total course hours.
     * @return Total course hours.
     */
    public int getTotalCourses()
    {
        return TotalCourses;
    }

    /**
     * Get students.
     * @return Students.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Get term of course.
     * @return Term of course.
     */
    public int getCourseTerm()
    {
        return courseTerm;
    }

    /**
     * Set term of course.
     * @param courseTerm Term of course.
     */
    public void setCourseTerm(int courseTerm)
    {
        this.courseTerm = courseTerm;
    }

    /**
     * Get code of course.
     * @return
     */
    public String getCourseCode()
    {
        return courseCode;
    }

    /**
     * Set code of course.
     * @param courseCode Code of course.
     */
    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * Get name of course.
     * @return Name of course.
     */
    public String getCourseName()
    {
        return courseName;
    }

    /**
     * Set name of course.
     * @param courseName Name of course.
     */
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    /**
     * Get credit of course.
     * @return Credit of course.
     */
    public int getCredit()
    {
        return credit;
    }

    /**
     * Set credit of course.
     * @param credit Credit of course.
     */
    public void setCredit(int credit)
    {
        this.credit = credit;
    }

    /**
     * Get linked courses.
     * @return String of linked courses.
     */
    public String getLinkedCourse()
    {
        return linkedCourse;
    }

    /**
     * Get department of course.
     * @return String of department.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Set department of course.
     * @param department Department of course.
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    /**
     * Set linked of course.
     * @param course Linked of course.
     */
    public void setLinkedCourse(String course)
    {
        linkedCourse = course;
    }
    
    /**
     * Add student method.
     * @param s A student object.
     */
    public void addStudent(Student s)
    {
        students.add(s);
    }

    /**
     * Print course students.
     */
    public void printCourseStudents()
    {
        System.out.println("size: " + students.size());
        for (int i= 0; i < students.size(); ++i)
            System.out.println(students.get(i));
    }

    /**
     * Get letter grade of student.
     * @param totalGrade Total grade of student.
     * @return String of total grade.
     */
    public String getLetterGrade(int totalGrade)
    {
        if (totalGrade < 55)
            return "FF";
        else if (55 <= totalGrade && totalGrade <= 60)
            return "DD";
        else if (60 < totalGrade && totalGrade <= 65)
            return "DC";
        else if (65 < totalGrade && totalGrade <= 70)
            return "CC";
        else if (70 < totalGrade && totalGrade <= 75)
            return "CB";
        else if (75 < totalGrade && totalGrade <= 80)
            return "BB";
        else if (80 < totalGrade && totalGrade <= 85)
            return "BA";
        else
            return "AA";
    }

    /**
     * Get letter grade of student.
     * @param totalGrade Total grade of student.
     * @param numOfAbsent Number of absent.
     * @return String of total grade.
     */
    public String getLetterGrade(int totalGrade, int numOfAbsent)
    {
        double ratio = (double)numOfAbsent / TotalCourses;

        if (ratio > 0.3)
            return "NA";

        if (totalGrade < 55)
            return "FF";
        else if (55 <= totalGrade && totalGrade <= 60)
            return "DD";
        else if (60 < totalGrade && totalGrade <= 65)
            return "DC";
        else if (65 < totalGrade && totalGrade <= 70)
            return "CC";
        else if (70 < totalGrade && totalGrade <= 75)
            return "CB";
        else if (75 < totalGrade && totalGrade <= 80)
            return "BB";
        else if (80 < totalGrade && totalGrade <= 85)
            return "BA";
        else
            return "AA";
    }

    /**
     * toString method of Course class.
     * @return String of course.
     */
    @Override
    public String toString() {
        return "CourseS{" +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit + '\'' +
                ",  courseTerm=" +  courseTerm +
                '}';
    }

    /**
     * compareTo method of course class.
     * @param o Other Course object.
     * @return Result of compare.
     */
    @Override
    public int compareTo(Course o)
    {
        return Integer.compare(getCredit(),o.getCredit());
    }
}
