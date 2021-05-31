public class Attendance {
    private String date;
    private String courseID;

    public Attendance(String date, String courseID)
    {
        this.date = date;
        this.courseID = courseID;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setCourseID(String courseId)
    {
        this.courseID = courseId;
    }

    public String getCourseID()
    {
        return courseID;
    }

    public String toString()
    {
        return "";
    }
}

