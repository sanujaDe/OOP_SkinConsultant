import java.io.Serializable;
import java.time.LocalDate;
// defining the superclass Person to represent a person with name, surname, date of birth and a mobile number
public class Person implements Serializable {
    // Person has a name, surname, date of birth and mobile number
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    // defining methods of person
    public String getName(){
        return this.name;
    }
    public void setName (String name){
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }





}

