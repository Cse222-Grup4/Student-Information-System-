import java.util.ArrayList;

public class Course {
    private Grade grade;
    private ArrayList<Attendance> absent;
    private String courseCode;
    private String courseName;
    private int credit;
    private int numOfAttendance = 0;
    private int numOfAbsent = 0;
    private boolean ísPass = false;
    private int term;
    private ArrayList<Student> students;

    public Course(String courseCode, String courseName, int credit,int term) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.term = term;
        absent = new ArrayList<>();
        grade = new Grade();
    }

    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public ArrayList<Attendance> getAbsent() {
        return absent;
    }
    public void setAbsent(ArrayList<Attendance> absent) {
        this.absent = absent;
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
    }
    public int getTerm(){
        return term;
    }
    public int setTerm(int term){
        this.term = term;
    }
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
    }

    public String getLetterGrade()
    {
        double ratio = (double)numOfAbsent / numOfAttendance;

        if (ratio > 0.3)
            return "NA";

        int totalGrade = grade.getTotalGrade();

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

    public void addGrade(int grade,int type){
        switch (type){
            case 1 -> this.grade.setMidtermGrade(grade);
            case 2 -> this.grade.setFinalGrade(grade);
            case 3 -> this.grade.setProjectGrade(grade);
        }
    }



    @Override
    public String toString() {
        return "CourseS{" +
                "grade=" + grade +
                ", absent=" + absent +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", numOfAttendance=" + numOfAttendance +
                ", numOfAbsent=" + numOfAbsent +
                '}';
    }
}
