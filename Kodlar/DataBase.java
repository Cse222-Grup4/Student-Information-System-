import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private Queue<Event> events;

    private String teachersFilePath = "teachers.txt";


    private HashMap <String,ArrayList<Student>> courseStudents;


    DataBase() throws IOException{
        try{
            students = new ArrayList<>();
            teachers = new ArrayList<>();
            courseStudents = new HashMap<>();
            events = new LinkedList<>();
            readUsersFile();
        }catch(IOException io){
            System.out.println("IOException occurred when reading 'teachers.txt' file.The exception is printing: ");
            io.printStackTrace();
            throw io;
        }
    }

    private void readTeachersFile() throws IOException {
        // reader for read teachers file
        File usersFile = new File(System.getProperty("user.dir"),teachersFilePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(usersFile));
        bufferedReader.readLine(); // reading first line(since first line does not include any data).

        String line,userName,userPassword,userSurname,userMail,courseIDLine;
        String[] courseIDs;
        int userID;

        while((line = bufferedReader.readLine()) != null){
            // reading teachers properties
            userID = Integer.parseInt(line.split(";")[0]); userMail = line.split(";")[1];
            userPassword = line.split(";")[2]; userName = line.split(";")[3];
            userSurname = line.split(";")[4];

            Teacher teacher = new Teacher(userMail,userPassword,userName,userSurname,userID,this);

            // reading teacher's courses
            courseIDLine = line.split(";")[5];
            if(!courseIDLine.equals("null")) {
                courseIDs = courseIDLine.split(",");
                for (String c : courseIDs)
                    teacher.addCourse(c);
            }
            teachers.add(teacher);
        }
        bufferedReader.close();
    }

    public void displayTeachers(){
        for(Teacher t : teachers)
            System.out.println(t);
    }





}
