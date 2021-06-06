public class CourseTeacher {

    private String courseCode;
    private String courseName;
    private int credit;

    public CourseTeacher(String courseCode, String courseName, int credit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
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

}