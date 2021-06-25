/**
 * Attendance class for represent the attendance of student.
 */
public class Attendance {
    /**
     * Date of attendance.
     */
    private String date;

    /**
     * Id of course.
     */
    private String courseID;

    /**
     * Constructor with 2 parameter.
     * @param date Date of attendance.
     * @param courseID Id of course.
     */
    public Attendance(String date, String courseID)
    {
        this.date = date;
        this.courseID = courseID;
    }

    /**
     * Set date of attendance.
     * @param date Date of attendance.
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * Get date of attendance.
     * @return Date of attendance.
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Set id of course.
     * @param courseId Id of course.
     */
    public void setCourseID(String courseId)
    {
        this.courseID = courseId;
    }

    /**
     * Get id of course.
     * @return Id of course.
     */
    public String getCourseID()
    {
        return courseID;
    }

    /**
     * toString method of Attendance class.
     * @return String of empty.
     */
    public String toString()
    {
        return "";
    }
}

