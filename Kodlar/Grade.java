/**
 * Grade class for represent the students grade.
 */
public class Grade {
    /**
     * Midterm grade of student.
     */
    private int midtermGrade = 0;

    /**
     * Final grade of student.
     */
    private int finalGrade = 0;

    /**
     * Project grade of student.
     */
    private int projectGrade = 0;

    /**
     * Total grade of student.
     */
    private int totalGrade = 0;

    /**
     * Default ctor.
     */
    Grade(){}

    /**
     * Constructor with parameter.
     * @param totalGrade Total grade of student.
     */
    Grade(int totalGrade)
    {
        this.totalGrade = totalGrade;
    }

    /**
     * Constructor with 3 parameter.
     * @param midtermGrade Midterm grade of student.
     * @param finalGrade Final grade of student.
     * @param projectGrade Project grade of student.
     */
    Grade(int midtermGrade, int finalGrade, int projectGrade)
    {
        this.midtermGrade = midtermGrade;
        this.finalGrade =  finalGrade;
        this.projectGrade = projectGrade;
    }

    /**
     * Get midterm grade of student.
     * @return Midterm grade of student.
     */
    public int getMidtermGrade()
    {
        return midtermGrade;
    }

    /**
     * Set midterm of student.
     * @param midtermGrade Midterm grade of student.
     */
    public void setMidtermGrade(int midtermGrade)
    {
        this.midtermGrade = midtermGrade;
    }

    /**
     * Get final grade of student.
     * @return Final grade of student.
     */
    public int getFinalGrade()
    {
        return finalGrade;
    }

    /**
     * Set final grade of student.
     * @param finalGrade Final grade of student.
     */
    public void setFinalGrade(int finalGrade)
    {
        this.finalGrade = finalGrade;
    }

    /**
     * Get project grade of student.
     * @return Project grade of student.
     */
    public int getProjectGrade()
    {
        return projectGrade;
    }

    /**
     * Set project grade of student.
     * @param projectGrade Project grade of student.
     */
    public void setProjectGrade(int projectGrade)
    {
        this.projectGrade = projectGrade;
    }

    /**
     * Get total grade of student.
     * @return Total grade of student.
     */
    public int getTotalGrade()
    {
        totalGrade = (int)((0.4 * midtermGrade) + (0.5 * finalGrade) + (0.1 * projectGrade));
        return totalGrade;
    }

    /**
     * Set total grade of student.
     * @param totalGrade Total grade of student.
     */
    public void setTotalGrade(int totalGrade)
    {
        this.totalGrade = totalGrade;
    }

    /**
     * Get past total grade of student.
     * @return Past total grade of student.
     */
    public int getPastTotalGrade()
    {
        return totalGrade;
    }

    /**
     * toString method of Grade class.
     * @return String of grade.
     */
    public String toString()
    {
        return "Midterm: " + midtermGrade + " Final: " + finalGrade + " Project: " + projectGrade +
                " Total grade: " + getTotalGrade() + "\n";
    }
}
