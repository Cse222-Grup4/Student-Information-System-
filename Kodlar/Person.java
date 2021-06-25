/**
 * Person abstract class for represent the behaviours of persons.
 * This class implements User interface.
 */
public abstract class Person implements User {
    /**
     * Name of user.
     */
    private String userName;
    /**
     * Surname of user.
     */
    private String userSurname;
    /**
     * Mail of user.
     */
    private String userMail;
    /**
     * Password of user.
     */
    private String userPassword;
    /**
     * ID of user.
     */
    private int userID;

    /**
     * Constructor with 2 parameters.
     * @param mail The mail of person.
     * @param password The password of person.
     */
    Person(String mail,String password)
    {
        setUserMail(mail);
        setUserPassword(password);
    }

    /**
     * Constructor with 5 parameters.
     * @param mail The mail of person.
     * @param password The password of person.
     * @param name The name of person.
     * @param surname The surname of person.
     * @param id The id of person.
     */
    Person(String mail,String password,String name,String surname,int id)
    {
        this(mail,password);
        setUserName(name);
        setUserSurname(surname);
        setUserID(id);
    }

    /**
     * Login method of system.
     * @param mail The mail of person.
     * @param password The password of person.
     * @return
     */
    @Override
    public boolean login(String mail, String password)
    {
        return (getUserMail().equals(mail) && getUserPassword().equals(password));
    }

    /**
     * Set name of person.
     * @param name Name of person.
     */
    @Override
    public void setUserName(String name)
    {
        userName = name;
    }

    /**
     * Set surname of person.
     * @param surname Surname of person.
     */
    @Override
    public void setUserSurname(String surname)
    {
        userSurname = surname;
    }

    /**
     * Set mail of person.
     * @param mail Mail of person.
     */
    @Override
    public void setUserMail(String mail)
    {
        userMail = mail;
    }

    /**
     * Set password of person.
     * @param password Password of person.
     */
    @Override
    public void setUserPassword(String password)
    {
        userPassword = password;
    }

    /**
     * Set id of person.
     * @param id Id of person.
     */
    @Override
    public void setUserID(int id)
    {
        userID = id;
    }

    /**
     * Get name of person.
     * @return Name of person.
     */
    @Override
    public String getUserName()
    {
        return userName;
    }

    /**
     * Get surname of person.
     * @return Surname of person.
     */
    @Override
    public String getUserSurname()
    {
        return userSurname;
    }

    /**
     * Get mail of person.
     * @return Mail of person.
     */
    @Override
    public String getUserMail()
    {
        return userMail;
    }

    /**
     * Get password of person.
     * @return Password of person.
     */
    @Override
    public String getUserPassword()
    {
        return userPassword;
    }

    /**
     * Get id of person.
     * @return Id of person.
     */
    @Override
    public int getUserID()
    {
        return userID;
    }
}
