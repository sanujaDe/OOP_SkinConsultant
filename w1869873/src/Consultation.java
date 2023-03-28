import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

// defining the class Consultation
public class Consultation  {
    private String dateTime;
    private int cost;
    private byte[] notes;
    private Doctor doctor;
    private Patient patient;


    // methods of the class Consultation
    public String getDateTime() {return this.dateTime;}

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    //-------------------------------------------------------------

    public double getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    //-------------------------------------------------------------
    public byte[] getNotes() {
        return this.notes;
    }

    public void setNotes(byte [] notes) {this.notes = notes;}

    //-------------------------------------------------------------
    Consultation (String dateAndTime, byte[] notes){
        this.dateTime = dateAndTime;
        this.notes = notes;
        cost = 25;
    }//-------------------------------------------------------------
    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.doctor.setPatientCount();
    }//-------------------------------------------------------------


    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(String firstName, String surname, LocalDate dateOfBirth , String mobileNo , String patientID) {
        this.patient = new Patient();
        patient.setName(firstName);
        patient.setSurname(surname);
        patient.setDateOfBirth(dateOfBirth);
        patient.setMobileNumber(mobileNo);
        patient.setPatientId(patientID);

    }

}//-------------------------------------------------------------
