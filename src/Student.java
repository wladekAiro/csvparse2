import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String grade;
    private Date registrationDate;

    public Student(Integer id, String firstName, String lastName, String grade, Date registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.registrationDate = registrationDate;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " 'ID' : '" + id + "' , 'FIRST NAME' : '" + firstName + "', 'LAST NAME' : '" + lastName + "', 'GRADE' : " +
                "'"+ grade + "', 'REG DATE' : '" + registrationDate +"'";
    }
}
