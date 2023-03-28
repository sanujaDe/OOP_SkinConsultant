import java.time.LocalDate;

// Patient is a subclass or Person
public class Patient extends Person {
    private String patientId;

    // methods of Patient
    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
