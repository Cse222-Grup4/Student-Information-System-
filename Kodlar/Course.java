import java.util.ArrayList;

public class Course implements Comparable<Course>  {
    //private Grade grade;
    // private ArrayList<Attendance> absent;
    private int courseTerm;
    private String courseCode;
    private String courseName;
    private int credit;
    private ArrayList<Student> students = new ArrayList<>();
    private final int TotalCourses = 30;

        /*private int numOfAttendance = 0;
    private int numOfAbsent = 0;
    private boolean ísPass = false;
    */

    public void printCourseStudents()
    {
        System.out.println("size: " + students.size());
        for (int i= 0; i < students.size(); ++i)
            System.out.println(students.get(i));
    }

    public int getTotalCourses()
    {
        return TotalCourses;
    }

    public Course(int courseTerm, String courseCode, String courseName, int credit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.courseTerm = courseTerm;
        /*absent = new ArrayList<>();
        grade = new Grade();*/
    }

    public void addStudent(Student s)
    {
        students.add(s);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    
/*
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }*/
    /*
    public ArrayList<Attendance> getAbsent() {
        return absent;
    }
    public void setAbsent(ArrayList<Attendance> absent) {
        this.absent = absent;
    }*/
    
    public int getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(int courseTerm) {
        this.courseTerm = courseTerm;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }/*
    public int getNumOfAttendance() {
        return numOfAttendance;
    }
    public void setNumOfAttendance(int numOfAttendance) {
        this.numOfAttendance = numOfAttendance;
    }
    public int getNumOfAbsent() {
        return numOfAbsent;
    }
    public void setNumOfAbsent(int numOfAbsent) {
        this.numOfAbsent = numOfAbsent;
    }*/

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

    /*// artık öğrenciyi bulup yapması lazım
    public void addGrade(int grade,int type){
        switch (type){
            case 1 -> this.grade.setMidtermGrade(grade);
            case 2 -> this.grade.setFinalGrade(grade);
            case 3 -> this.grade.setProjectGrade(grade);
        }
    }
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
    
    @Override
    public int compareTo(Course o) {
        return Integer.compare(getCredit(),o.getCredit());
    }
    
    
}
