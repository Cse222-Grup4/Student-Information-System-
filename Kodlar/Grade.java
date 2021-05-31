public class Grade {
    private int midtermGrade = 0;
    private int finalGrade = 0;
    private int projectGrade = 0;
    private int totalGrade = 0;

    Grade(){}

    // getters and setters
    public int getMidtermGrade() {
        return midtermGrade;
    }
    public void setMidtermGrade(int midtermGrade) {
        this.midtermGrade = midtermGrade;
    }
    public int getFinalGrade() {
        return finalGrade;
    }
    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }
    public int getProjectGrade() {
        return projectGrade;
    }
    public void setProjectGrade(int projectGrade) {
        this.projectGrade = projectGrade;
    }
    public int getTotalGrade(){
        totalGrade = (int)((0.3 * midtermGrade) + (0.5 * finalGrade) + (0.1 * projectGrade));
        return totalGrade;
    }
    public void setTotalGrade(int totalGrade){
        this.totalGrade = totalGrade;
    }

    public String toString()
    {
        return "Midterm: " + midtermGrade + " Final: " + finalGrade + " Project: " + projectGrade +
                " Total grade: " + totalGrade + "\n";
    }
}
