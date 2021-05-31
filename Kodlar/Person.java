public abstract class Person implements User {

    private String userName;
    private String userSurname;
    private String userMail;
    private String userPassword;
    private int userID;

    Person(String mail,String password){
        setUserMail(mail);
        setUserPassword(password);
    }
    Person(String mail,String password,String name,String surname,int id){
        this(mail,password);
        setUserName(name);
        setUserSurname(surname);
        setUserID(id);
    }

    @Override
    public boolean login(String mail, String password) {
        return (getUserMail().equals(mail) && getUserPassword().equals(password));
    }


    // getters and setters
    @Override
    public void setUserName(String name) {
        userName = name;
    }
    @Override
    public void setUserSurname(String surname) {
        userSurname = surname;
    }
    @Override
    public void setUserMail(String mail) {
        userMail = mail;
    }
    @Override
    public void setUserPassword(String password) {
        userPassword = password;
    }
    @Override
    public void setUserID(int id) {
        userID = id;
    }
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public String getUserSurname() {
        return userSurname;
    }
    @Override
    public String getUserMail() {
        return userMail;
    }
    @Override
    public String getUserPassword() {
        return userPassword;
    }
    @Override
    public int getUserID() {
        return userID;
    }


}
