import java.util.ArrayList;

public class Teacher extends Person {
    private DataBase db;
    private ArrayList<String> coursesIDs;

    Teacher(String mail,String password)
    {
        super(mail,password);
    }

    Teacher(String mail,String password,String name,String surname,int id)
    {
        super(mail, password, name, surname, id);
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

}
