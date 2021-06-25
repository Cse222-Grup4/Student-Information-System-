import java.util.*;

public class Admin extends Officer {
	
	Admin(String name, String surname, String mail, String password, int ID, DataBase db)
	{
		super(name, surname, mail, password, ID, db);
	}
	/**
	 * Constructor with two parameters.
	 * @param mail Admin's e-mail address.
	 * @param password Admin's password.
	 */
	Admin(String mail, String password)
	{
		super(mail, password);
	}

	public void print()
	{
		//...
	}
	/**
	 * Inquire teacher information by Teacher ID
	 * @param teachers Teacher List
	 * @param teacherID Teacher's ID to inquire
	 * @return Teacher object
	 */
	public Teacher inquireTeacherInformation(int teacherID)
	{
		
		for (int i = 0; i < db.getTeachers().size(); i++)
			if (db.getTeachers().get(i).getUserID() == teacherID)
				return db.getTeachers().get(i);

		return null;
	}

	/**
	 * Inquire officer information by Officer ID
	 * @param officers Officer List
	 * @param officerID Officer's ID to inquire
	 * @return Officer object
	 */
	public Officer inquireOfficerInformation(int officerID)
	{
		for (int i = 0; i < db.getOfficers().size(); i++)
			if (db.getOfficers().get(i).getUserID() == officerID)
				return db.getOfficers().get(i);

		return null;
	}

	public boolean addOfficer(Officer newOfficer)
	{
		// Registers officer by using Array List's add method.
		boolean success;
		success = db.getOfficers().add(newOfficer);
		return success;
	}

	public boolean addTeacher(Teacher newTeacher)
	{
		// Registers teacher by using Array List's add method.
		boolean success;
		success = db.getTeachers().add(newTeacher);
		return success;
	}

	public boolean addStudent(Student newStudent)
	{
		// Registers student by using Array List's add method.
		boolean success;
		success = db.getStudents().add(newStudent);
		return success;
	}

	public boolean removeOfficer(Officer oldOfficer)
	{
		// Registers teacher by using Array List's add method.
		boolean success;
		success = db.getOfficers().remove(oldOfficer);
		return success;
	}

	public boolean removeTeacher(Teacher oldTeacher)
	{
		// Registers teacher by using Array List's add method.
		boolean success;
		success = db.getTeachers().remove(oldTeacher);
		return success;
	}

	public boolean removeStudent(Student oldStudent)
	{
		// Registers student by using Array List's add method.
		boolean success;
		success = db.getStudents().remove(oldStudent);
		return success;
	}

	public void menu()//scanner.nexInt() ler sıkıntı cıkartıyordu sildim. Ugur
	{
		int choice, innerChoice, id;

		for (;;) {
			System.out.println("WELCOME TO ADMIN MENU");
			System.out.println("0) Exit");
			System.out.println("1) Add User");
			System.out.println("2) Remove User");
			System.out.println("3) Inquire User");

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 0:
					return;
				case 1:
					System.out.println("0) Exit");
					System.out.println("1) Add Officer");
					System.out.println("2) Add Student");
					System.out.println("3) Add Teacher");

					innerChoice =Integer.parseInt(scanner.nextLine());
					String mail,password, name, surname;
					int year;

					System.out.println("Enter name: ");
					name = scanner.nextLine();

					System.out.println("Enter surname: " );
					surname = scanner.nextLine();

					System.out.println("Enter id: ");
					id = Integer.parseInt(scanner.nextLine());

					System.out.println("Enter mail: ");
					mail = scanner.nextLine();

					System.out.println("Enter password: ");
					password = scanner.nextLine();

					switch (innerChoice) {
						case 0:
							break;
						case 1:
							addOfficer(new Officer(name, surname, mail, password, id, db));
							break;
						case 2:
							System.out.println("Enter year");
							year = Integer.parseInt(scanner.nextLine());
							addStudent(new Student(mail, password, name, surname, id, year));
							break;
						case 3:
							System.out.println("Enter department");
							String department=scanner.nextLine();
							addTeacher(new Teacher(mail,password,name,surname,id,department,db));
							break;
						default:
							break;
					}
					break;
				case 2:
					System.out.println("0) Exit");
					System.out.println("1) Remove User");
					innerChoice = scanner.nextInt();

					switch (innerChoice) {
						case 0:
							break;
						case 1:
							System.out.println("Enter id");
							id = scanner.nextInt();
							//remove func database
							break;
						default:
							break;
					}
					break;
				case 3:
					System.out.println("0) Exit");
					System.out.println("1) Inquire User");
					innerChoice = scanner.nextInt();

					switch (innerChoice) {
						case 0:
							break;
						case 1:
							System.out.println("Enter id");
							id = scanner.nextInt();

							//inquire func database
							break;
						default:
							break;
					}
					break;
				default:
					System.out.println("Tekrar giris");
			}
		}
	}
}
