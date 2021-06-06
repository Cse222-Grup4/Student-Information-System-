import java.util.ArrayList;

public class Teacher extends Person {
    private DataBase database;
    private ArrayList<CourseTeacher> courses;

    Teacher(String mail,String password,DataBase db)
    {
        super(mail,password);
        database = db;
        courses = new ArrayList<>();
    }

    Teacher(String mail,String password,String name,String surname,int id,DataBase db)
    {
        super(mail, password, name, surname, id);
        database = db;
        courses = new ArrayList<>();
    }

    public void viewGrades(Student[] students)
    {
        for (Student student : students) {
            System.out.println("The grades for student:" + student.getUserName() + " " + student.getUserSurname());
            student.viewGrades();
        }
    }

    public void addGrade(String courseCode,Student student,int grade,int type)
    {
        student.addGrade(courseCode,grade,type);
    }
    
    public void editGrade(String courseCode,Student student,int grade,int type){
        addGrade(courseCode, student, grade, type);
    }
    
    
    public void addCourse(CourseTeacher course){
        courses.add(course);
    }
    
}
