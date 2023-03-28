import java.io.*;
import java.time.LocalDate;
import java.util.*;




public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static ArrayList<Doctor> doctors;
    public static ArrayList<Consultation> consultations;
    public static ArrayList<ImageEncryption> ListOfEncryptedImages;
    private Scanner scanner ;
    public WestminsterSkinConsultationManager(){
        doctors = new ArrayList<Doctor>();
        consultations = new ArrayList<Consultation>();
        this.scanner = new Scanner(System.in);
        consoleMenu();
    }
    // console menu (this displays first when the program runs------------------------------------
    private void consoleMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\n---------------Menu---------------");
                System.out.println("1. Add a new Doctor");
                System.out.println("2. Remove a Doctor from the list");
                System.out.println("3. Display a list of doctors in the system");
                System.out.println("4. Save inserted data to a file");
                System.out.println("5. Load previously saved data from the file");
                System.out.println("6. Open GUI");
                System.out.println("7. Quit\n");
                System.out.print("Insert a value from 1-7: ");
                int inputValue = sc.nextInt();
                switch (inputValue) {
                    case 1:
                        Doctor doctor = new Doctor();
                        addDoctor(doctor);
                        break;
                    case 2:
                        DoctorRemove();
                        break;
                    case 3:
                        displayDoctors();
                        break;
                    case 4:
                        saveData();
                        break;
                    case 5:
                        loadData();
                        break;
                    case 6:
                        LaunchPageGUI launchPageGUI = new LaunchPageGUI();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a value from 1-7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a value from 1-7.");
                sc.nextLine();
            }
        }
    }

    // saving and loading from a dat file
    public static void loadData() { // loading data from a file
        try (ObjectInputStream toFile = new ObjectInputStream(new FileInputStream("doctorsInfo.dat"))) {
            doctors = (ArrayList<Doctor>) toFile.readObject();
            consultations = (ArrayList<Consultation>) toFile.readObject();
            System.out.println("Data loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

    }// ------------------------------------------------------------------------------------------
    public static void saveData(){
        try (ObjectOutputStream fromFile = new ObjectOutputStream(new FileOutputStream("doctorsInfo.dat"))){
            fromFile.writeObject(doctors);
            fromFile.writeObject(consultations);
            System.out.println("\nData successfully saved");

        } catch (IOException e) {
            System.out.println("Error saving data to the file "+ e.getMessage());;
        }
    }// ------------------------------------------------------------------------------------------

    public static void displayDoctors() {
        List<Doctor> doctorsSorted = new ArrayList<>(doctors);
        doctorsSorted.sort(Comparator.comparing(Doctor::getSurname)); // sorting according to the surname
        // printing the list of doctors and their information

        System.out.println("\tSKIN CONSULTATION CENTER : DOCTORS LIST\n");
        if (doctorsSorted.size()==0){
            System.out.println("\t|\tThere are no doctors in the system\t|\t");
        }
        // for loop to print doctors and their details one by one
        for (Doctor doctor : doctorsSorted){
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Name(surname|first name)\t: Dr."+ doctor.getSurname()+" "+doctor.getName());
            System.out.println("Date of Birth\t\t\t: "+ doctor.getDateOfBirth());
            System.out.println("Mobile Number\t\t\t: "+ doctor.getMobileNumber());
            System.out.println("Medical License\t\t\t: "+ doctor.getMedicalLicenseNumber());
            System.out.println("Specialisation\t\t\t: "+ doctor.getSpecialisation());
            System.out.println("---------------------------------------------------------------------------------");

        }
    }// ------------------------------------------------------------------------------------------




    // ------------------------------------------------------------------------------------------
    @Override
    public void addDoctor(Doctor doctor) {
        if (doctors.size() >= 10) { // checking whether the maximum number of doctors are already added or not
            System.out.println("Maximum number of 10 doctors are already in the system.\nRemove a doctor before adding more\n ");
            return;

        }
        else {

            doctor.setName(FirstName()); // setting first name through calling the method

            doctor.setSurname(Surname()); // setting surname through calling the method

            doctor.setDateOfBirth(DateOfBirthFormat()); // setting date of birth through calling the method

            doctor.setMobileNumber(MobileNumber()); // setting mobile number through calling the method

            doctor.setMedicalLicenseNumber(LicenseNumber()); // setting license through calling the method

            doctor.setSpecialisation(Specialisation()); // setting specialisation through calling the method

            doctors.add(doctor); // adding the doctor in to the list

        }

    }

    public void DoctorRemove (){
        for (Doctor doctor : doctors) {
            try {
                if (doctor == null) { // check whether there are doctors in the arraylist
                    System.out.println(" There are no Doctors in the system");
                } else {
                    System.out.println("\t\tName\t\t|\t\tLicense No\t\t");
                    System.out.println("Dr. "+doctor.getName() +" "+ doctor.getSurname() + "\t|\t" +"\t"+ doctor.getMedicalLicenseNumber()); // find doctor by the name
                    removeDoctor();
                    break;

                }
            }catch (Exception e){
                System.out.println("Invalid Input : please enter a valid license number ");
            }

        }
    }


    @Override
    public void removeDoctor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Remove Doctor by License Number \nInsert the License number of the doctor to remove : ");
        String removeByLicense = sc.next();
        Doctor doctor = null;
        for (Doctor doctor1 : doctors){ // checking the list for the doctor
            if (doctor1.getMedicalLicenseNumber().equals(removeByLicense)){
                doctor = doctor1;
                break;
            }
        }
        if (doctor == null){
            System.out.println("There are no doctors with that license number in the system");
            System.out.println("Insert a valid licence number from the list below");
            DoctorRemove();


        } else {
            doctors.remove(doctor);
            System.out.println("Successfully removed Doctor by the license number "+doctor.getMedicalLicenseNumber());

        }


    }




    @Override
    public void bookConsultation(Consultation consultation) {

    }

    @Override
    public void cancelConsultation(Consultation consultation) {

    }

    @Override
    public void printConsultation() {

    }


    // ============================= Methods used to add a Doctor =============================
    private String FirstName(){
        int nameType = 1;
        return NameValidation(nameType);
    }// ------------------------------------------------------------------------------------------

    private String Surname(){
        int nameType = 2;
        return NameValidation(nameType);
    }// ------------------------------------------------------------------------------------------
    private String NameValidation(int nameType) { // nameType is to identify whether lastname or firstname
        Scanner sc = new Scanner(System.in);
        String name = null;

        while (name == null) { // loop until valid input is given
            String nameTypeUpdate = null;
            if (nameType == 2){
                nameTypeUpdate = "Last";
            } else if (nameType==1) {
                nameTypeUpdate = "First";
            }
            System.out.print("Insert the "+nameTypeUpdate+" Name : ");
            name= sc.nextLine();

            try {
                if ((name.trim().length() > 0) && (name.matches("[a-zA-Z]+"))) { // check whether the user insert black spaces as the name
                    return name;

                } else {
                    System.out.println("Error: Invalid name Try again.");
                    name = null;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input, Please try again.");
            }
        }
        return name;
    }


    // to get the birthday
    private LocalDate DateOfBirthFormat() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the date of birth (in the format YYYY-MM-DD): ");
            String input = scanner.nextLine();

            try {
                LocalDate birthday = LocalDate.parse(input);
                return birthday;
            } catch (Exception e) {
                System.out.println("Error parsing birthday: " + e.getMessage() + "\nPlease try again.");
            }
        }
    }// ------------------------------------------------------------------------------------------

    // to get the doctors mobile number
    private String MobileNumber(){
        Scanner sc = new Scanner(System.in);
        String mobileNumber = null;

        while (mobileNumber == null) { // while loop is to ask user the input until the input is valid
            System.out.print("Insert the mobile number: ");
            mobileNumber = sc.nextLine();
            try {
                // checking whether the mobile number is numbers not letters and valid
                if ((mobileNumber.matches("[0-9]+") && (mobileNumber.length()==10))){
                    break;
                }
                else {
                    System.out.println("Invalid input : mobile number should contain 10 numbers");
                    mobileNumber=null;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input, Please insert a valid mobile number.");
                mobileNumber=null;
            }
        }

        return mobileNumber;
    }// ------------------------------------------------------------------------------------------
    // to get the medical license number
    private String LicenseNumber(){
        Scanner sc = new Scanner(System.in);
        String licenseNumber = null;



        while (licenseNumber == null) { // loop until valid input is given
            System.out.print("Insert the doctor license number: ");
            licenseNumber = sc.nextLine();
            try {
                // checking whether the mobile number is numbers not letters and valid

                if (licenseNumber.matches("[0-9]+")){
                    break;
                }
                else {
                    System.out.println("Invalid input : license must contain only numbers");
                    licenseNumber=null;}


            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input, Please enter a valid License number.");
                sc.nextLine();
            }
        }

        return licenseNumber;
    }// ------------------------------------------------------------------------------------------
    // to get the specialization
    private String Specialisation(){
        Scanner sc = new Scanner(System.in);
        String specialisation = null;

        while (specialisation == null) { // loop until valid input is given
            System.out.print("Insert the doctor's specialisation: ");
            try {
                specialisation = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Invalid input, Please try again.");

            }
        }
        return specialisation;
    }//  =======================================================================================







}




