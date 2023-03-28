import java.util.List;

public interface SkinConsultationManager {
    void addDoctor(Doctor doctor);

    void removeDoctor();



    void bookConsultation(Consultation consultation);

    void cancelConsultation(Consultation consultation);
    void printConsultation();

}
