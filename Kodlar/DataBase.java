import java.io.*;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DataBase {

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Officer> officers;
    private ArrayList<Course> courses;
    private Queue<Event> events;
    private ArrayList<Curriculum> curriculums;
    private Admin admin;
    private String teachersFilePath = "teachers.txt";
    private String officersFilePath = "officers.txt";


   // private HashMap <String,ArrayList<Student>> courseStudents;

    public ArrayList<Student> getStudents(){return students;}
    DataBase() throws IOException
    {
        try{
            students = new ArrayList<>();
            teachers = new ArrayList<>();
            officers = new ArrayList<>();
            courses = new ArrayList<>();
            events = new LinkedList<>();
            curriculums = new ArrayList<>();
            admin = new Admin("admin","admin","admin@gtu.edu.tr","admin",1,this);

            readCourses("src/courses.txt");
            readTeachersFile();
            //readStudentFile();
            //readUsersFile(); silinmis

        } catch(IOException io){
            System.out.println("IOException occurred when reading 'teachers.txt' file.The exception is printing: ");
            io.printStackTrace();
            throw io;
        }
    }
    
    /**
     * The menu for Application
     */
    public void initialMenu(){

        try {
            String inputLine;
            int choice;

            do {
                System.out.println("\n---------------Welcome to the Student Information System Application---------------\n");
                System.out.println("Please select:");
                System.out.println("1-Sign In");
                System.out.println("0-Exit");

                inputLine = input.nextLine();
                choice = Integer.parseInt(inputLine);

                switch (choice) {
                    case 1 -> signIn();
                    case 0 -> System.out.println("Thanks for using Student Information System. Have a nice day!");
                    default -> System.out.println("Please select from menu\n");
                }
            } while (choice != 0);
        }catch (NumberFormatException nfe){
            System.out.println("!You typed char instead of number!");
            nfe.printStackTrace();
        }
    }

    /**
     * Log In to the system.
     * @throws NumberFormatException  If the userID does not contain a parsable int.
     */
    private void signIn() throws NumberFormatException{
        int inputUserID;

        System.out.print("UserID:");
        inputUserID = Integer.parseInt(input.nextLine());
        System.out.print("Password:");
        String inputPassword = input.nextLine();

        for (Student student : students) {
            if (student.getUserID() == inputUserID && student.getUserPassword().equals(inputPassword)){
                student.menu();
                return;
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getUserID() == inputUserID && teacher.getUserPassword().equals(inputPassword)) {
                teacher.performTasks();
                return;
            }
        }
        for (Officer officer : officers) {
            if (officer.getUserID() == inputUserID && officer.getUserPassword().equals(inputPassword)) {
                officer.menu();
                return;
            }
        }
        if (admin.getUserID() == inputUserID && admin.getUserPassword().equals(inputPassword)) {
            admin.menu();
            return;
        }

        System.out.println("Wrong ID or password.");

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
    				Integer.parseInt(line.split(";")[4]),
                    this
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

        String line,userName,userPassword,userSurname,userMail,courseIDLine,userDepartment;
        String[] courseIDs;
        int userID,userType;

        while((line = bufferedReader.readLine()) != null){
            // reading teachers properties
            userID = Integer.parseInt(line.split(";")[0]); userMail = line.split(";")[1];
            userPassword = line.split(";")[2]; userName = line.split(";")[3];
            userSurname = line.split(";")[4]; userDepartment = line.split(";")[5];
            userType = Integer.parseInt(line.split(";")[6]);


            if(courses.size() == 0)
                readCourses("courses.txt");

            if(userType == 0) {
                Teacher teacher = new Teacher(userMail, userPassword, userName, userSurname, userID, userDepartment, this);

                courseIDLine = line.split(";")[7];
                if(!courseIDLine.equals("null")) {
                    courseIDs = courseIDLine.split(",");
                    for (String c : courseIDs) {
                        teacher.addCourse(findCourseWID(c));
                    }
                }
                teachers.add(teacher);
            }
            else {
                AdvisorTeacher advisorTeacher = new AdvisorTeacher(userMail, userPassword, userName, userSurname, userID, userDepartment, this);
                courseIDLine = line.split(";")[7];
                if(!courseIDLine.equals("null")) {
                    courseIDs = courseIDLine.split(",");
                    for (String c : courseIDs) {
                        advisorTeacher.addCourse(findCourseWID(c));
                    }
                }
                teachers.add(advisorTeacher);
            }
        }
        bufferedReader.close();
    }
    
    private void writeTeachersFile() throws IOException {
        // writer for read teachers file
        File usersFile = new File(System.getProperty("user.dir"),teachersFilePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(usersFile));

        bufferedWriter.write("id;mail;password;name;surname;department;type;courseIDs\n");
        ArrayList<String> courseIDs;

        for (Teacher teacher : teachers) {
            bufferedWriter.write(teacher.getUserID() + ";"
                    + teacher.getUserMail() + ";"
                    + teacher.getUserPassword() + ";"
                    + teacher.getUserName() + ";"
                    + teacher.getUserSurname() + ";"
                    + teacher.getDepartment() + ";");
            if(teacher instanceof AdvisorTeacher)
                bufferedWriter.write("1;");
            else
                bufferedWriter.write("0;");
            courseIDs = teacher.getCourseIDs();
            if (courseIDs.size() == 0)
                bufferedWriter.write("null\n");
            else{
                for(int i=0;i<courseIDs.size()-1;++i) {
                    bufferedWriter.write(courseIDs.get(i) + ",");
                }
                bufferedWriter.write(courseIDs.get(courseIDs.size()-1) +"\n");
            }
        }
        bufferedWriter.close();
    }
    
    public Course findCourseWID(String courseID) {
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
    public void printStudents()
    {
        System.out.println(students.get(0).studentToFile());

        for (int i = 0; i < students.size(); ++i) {
            System.out.println(students.get(i).toString());
            for (int k = 0; k < students.get(i).getPastCourses().size(); ++k)
                for (int j = 0; j < students.get(i).getPastCourses().get(k).size(); ++j)
                    System.out.printf("%s\n",students.get(i).getPastCourses().get(k).get(j).toString());
        }
    }


    public boolean writeStudentFile()
    {
        try {
            FileWriter file = new FileWriter("src/students.txt");


            for (int i = 0; i < students.size(); ++i) {
                file.write(students.get(i).studentToFile());
                file.write('\n');
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    public boolean readStudentFile(){
        // reader for read teachers file
        try{
            FileReader myObj = new FileReader("src/students.txt");
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
                        if(courses.get(j).getCourseCode().equals(tempCurrentCourse[0])){
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
                    ArrayList<Course> t_arr = new ArrayList<Course>();
                    for(int t = 0;t < tempCurrentCourses.length;t++){
                        String tempCurrentCourse[] = tempCurrentCourses[t].split("\\.");

                        for(int j = 0;j < courses.size();j++){
                            if(courses.get(j).getCourseCode().equals(tempCurrentCourse[0])) {
                                t_arr.add(courses.get(j));
                                //tempStudent.getPastCourses().get(i).add(courses.get(j));
                                tempStudent.getGrades().put(tempCurrentCourse[0], new Grade(Integer.parseInt(tempCurrentCourse[1])));
                                break;
                            }
                        }
                    }
                    tempStudent.getPastCourses().add(t_arr);
                }
                tempStudent.setDb(this);
                students.add(tempStudent);
            }

            for (int i = 0; i < students.size(); ++i)
                for (int k = 0; k < students.get(i).getCurrentCourses().size(); ++k)
                    students.get(i).getCurrentCourses().get(k).addStudent(students.get(i));

            bufferedReader.close();
            myObj.close();

        } catch(IOException io) {
            System.out.println("IOException occurred when reading 'student.txt' file.The exception is printing: ");
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
    
    public Student findStudentWID(int ID){
        for (Student student : students) {
            if (student.getUserID() == ID)
                return student;
        }
        return null;
    }
    private void appendAdvisors() {
    	for(int i=0;i<students.size();i++) {
    		for(int j =0 ; j<teachers.size();j++) {
    			if(students.get(i).getDepartment().equals(teachers.get(j).getDepartment()) && teachers.get(j).getIsAdvisor()) {
    				AdvisorTeacher temp = (AdvisorTeacher)teachers.get(j);
    				temp.getStudents().add(students.get(i));
    				students.get(i).setAdvisorTeacherID(teachers.get(j).getUserID());
    			}
    		}
    	}
    }
    
}
