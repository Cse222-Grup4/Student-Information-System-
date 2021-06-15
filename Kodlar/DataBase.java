import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DataBase {

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Officer> officers;
    private Queue<Event> events;
    private ArrayList<Curriculum> curriculums;
    private String teachersFilePath = "teachers.txt";
    private String officersFilePath = "officers.txt";


   // private HashMap <String,ArrayList<Student>> courseStudents;


    DataBase() throws IOException{
        try{
            students = new ArrayList<>();
            teachers = new ArrayList<>();
            officers = new ArrayList<>();
         //   courseStudents = new HashMap<>();
            events = new LinkedList<>();
            curriculums = new ArrayList<>();
            readUsersFile();
        }catch(IOException io){
            System.out.println("IOException occurred when reading 'teachers.txt' file.The exception is printing: ");
            io.printStackTrace();
            throw io;
        }
    }
    
    /**
     * Reads text file and adds officers into array list of officers.
     * @throws NumberFormatException If Officer's ID is not a valid number
     * @throws IOException If text file is not found
     */
    public void importOfficersFromFile() throws NumberFormatException, IOException {
    	String line;
    	BufferedReader br = new BufferedReader(new FileReader(officersFilePath));
    	
    	while ((line = br.readLine()) != null) {
    		officers.add(new Officer(
    				line.split(";")[0],
    				line.split(";")[1],
    				line.split(";")[2],
    				line.split(";")[3],
    				Integer.parseInt(line.split(";")[4])
    				));
    	}
    }
    
    /**
     * Appends an officer to end of text file which is storing officers.
     * @param o Officer object
     * @throws IOException If creating file is failed
     */
    public void exportOfficerToFile(Officer o) throws IOException {
    	// Appends end of file
        BufferedWriter writer = new BufferedWriter(new FileWriter(officersFilePath,true));
        writer.write(o.getUserMail() + ";");
        writer.write(o.getUserPassword() + ";");
        writer.write(o.getUserName() + ";");
        writer.write(o.getUserSurname() + ";");
        writer.write(o.getUserID() + "\n");
        writer.close();    	
    }
    
    /**
     * Writes all officers in list into the text file.
     * @throws IOException
     */
    public void exportOfficersList() throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter(officersFilePath));
    	
    	for(int i=0; i<officers.size(); i++)
    	{
    		writer.write(officers.get(i).getUserMail() + ";");
    		writer.write(officers.get(i).getUserPassword() + ";");
    		writer.write(officers.get(i).getUserName() + ";");
    		writer.write(officers.get(i).getUserSurname() + ";");
    		writer.write(officers.get(i).getUserID() + "\n");
    	}
    	writer.close();
    }
    
    /** 
     * Adds an officer to the list
     * @param o Officer object
     * @return Whether adding is successful
     */
    public boolean addOfficer(Officer o) {
    	boolean b;
    	b = officers.add(o);
    	return b;
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

            if(courses.size() == 0)
                readCourses("courses.txt");

            courseIDLine = line.split(";")[5];
            if(!courseIDLine.equals("null")) {
                courseIDs = courseIDLine.split(",");
                for (String c : courseIDs) {
                    teacher.addCourse(findCourseWID(c));
                }
            }
            teachers.add(teacher);
        }
        bufferedReader.close();
    }
    
    private void writeTeachersFile() throws IOException {
        // writer for read teachers file
        File usersFile = new File(System.getProperty("user.dir"),teachersFilePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(usersFile));

        bufferedWriter.write("id;mail;password;name;surname;courseIDs\n");
        ArrayList<String> courseIDs;

        for (Teacher teacher : teachers) {
            bufferedWriter.write(teacher.getUserID() + ";"
                    + teacher.getUserMail() + ";"
                    + teacher.getUserPassword() + ";"
                    + teacher.getUserName() + ";"
                    + teacher.getUserSurname() + ";");
            courseIDs = teacher.getCourseIDs();
            if (courseIDs == null)
                bufferedWriter.write("null\n");
            else{
                for(int i=0;i<courseIDs.size()-1;++i)
                    bufferedWriter.write(courseIDs.get(i) + ",");
                bufferedWriter.write(courseIDs.get(courseIDs.size()-1) +"\n");
            }
        }
        bufferedWriter.close();
    }
    
    private Course findCourseWID(String courseID) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseID))
                return course;
        }
        return null;
    }

    public boolean readCourses(String filename){
        try{
            FileReader myObj = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(myObj);
            String line;

            while((line = bufferedReader.readLine()) != null){
                String parts[] = line.split(";");
                courses.add(new Course(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3])));
            }
        }catch(IOException io){
            System.out.println("IOException occurred when reading 'teachers.txt' file.The exception is printing: ");
            io.printStackTrace();
        }
        return true;
    }

    public boolean readStudentFile(){
        // reader for read teachers file
        try{
            FileReader myObj = new FileReader("students.txt");
            BufferedReader bufferedReader = new BufferedReader(myObj);

            String line;

            while((line = bufferedReader.readLine()) != null){
                /*HashMap<String, int> tempAttendance = new HashMap();
                HashMap<String, Grade> tempGrades = new HashMap();*/

                String parts[] = line.split(";");
                Student tempStudent = new Student(parts[2], parts[3], parts[0], parts[1], Integer.parseInt(parts[4]), Integer.parseInt(parts[6]));
                tempStudent.setAdvisorTeacherID(Integer.parseInt(parts[5]));
                tempStudent.setTerm(Integer.parseInt(parts[7]));
                tempStudent.setDepartment(parts[8]);
                tempStudent.setCourseSelectionApprove(Boolean.parseBoolean(parts[9]));
                String tempCourses[] = parts[10].split(",");
                for(int i = 0;i < tempCourses.length;i++){
                    String tempCurrentCourse[] = tempCourses[i].split("\\.");
                    for(int j = 0;j < courses.size();j++){
                        if(courses.get(j).getCourseCode().equals(tempCurrentCourse[i])){
                            tempStudent.getCurrentCourses().add(courses.get(j));
                            break;
                        }
                    }
                    tempStudent.getAttendance().put(tempCurrentCourse[0], Integer.parseInt(tempCurrentCourse[1]));
                    tempStudent.getGrades().put(tempCurrentCourse[0], new Grade(Integer.parseInt(tempCurrentCourse[2]), Integer.parseInt(tempCurrentCourse[3]), Integer.parseInt(tempCurrentCourse[4])));

                    /*tempAttendance.put(tempCurrentCourse[0],tempCurrentCourse[1]);
                    tempGrades.put(tempCurrentCourse[0], new Grade(Integer.parseInt(tempCurrentCourse[2]), Integer.parseInt(tempCurrentCourse[3]), Integer.parseInt(tempCurrentCourse[4])));*/
                }

                tempCourses = parts[11].split(":");
                for(int i = 0;i < tempCourses.length;i++){
                    String tempCurrentCourses[] = tempCourses[i].split(",");

                    for(int t = 0;t < tempCurrentCourses.length;t++){
                        String tempCurrentCourse[] = tempCurrentCourses[t].split("\\.");
                        for(int j = 0;j < courses.size();j++){
                            if(courses.get(j).getCourseCode().equals(tempCurrentCourse[0])){
                                tempStudent.getPastCourses().get(i).add(courses.get(j));
                                tempStudent.getGrades().put(tempCurrentCourse[0], new Grade(Integer.parseInt(tempCurrentCourse[1])));
                                break;
                            }
                        }
                    }
                }
                students.add(tempStudent);
            }
            bufferedReader.close();
            myObj.close();
        }catch(IOException io){
        System.out.println("IOException occurred when reading 'teachers.txt' file.The exception is printing: ");
        io.printStackTrace();
        }
        return true;
    }
    
    public void displayTeachers(){
        for(Teacher t : teachers)
            System.out.println(t);
    }
    public Curriculum getCurriculum(String department) {
        for(int i=0;i<curriculums.size();i++) {
            if(curriculums.get(i).getDepartment().equals(department)) {
                 return curriculums.get(i);
            }
        }
        return null;
    }





}
