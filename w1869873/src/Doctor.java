import java.time.LocalDate;

// Doctor is a subclass of Person
public class Doctor extends Person {
    private String medicalLicenseNumber;
    private String specialisation;
    private int patientCount = 0;



    // methods of Doctor
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return this.specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public int getPatientCount() {return patientCount;}
    public void setPatientCount() {patientCount += 1; }

}





