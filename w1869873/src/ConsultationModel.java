import javax.crypto.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

public class ConsultationModel extends JFrame {
    String patientID;
    String patientFirstName;
    String patientSurname;
    String patientDateOfBirth;
    String patientMobileNo;
    Doctor consultantDoc;
    String consultationDate;
    String consultationNotes;

    // defining menu bar
    JMenuBar menuBar = new JMenuBar();
    // defining the items of the menu bar
    JMenu menuFile = new JMenu("File");

    // defining what pops up when select File on menu bar
    JMenuItem consulTable = new JMenuItem("Consultation Table");
    JMenuItem saveMenuItem = new JMenuItem("Save");
    JMenuItem loadMenuItem = new JMenuItem("Load");
    JMenuItem exitMenuItem = new JMenuItem("Exit");

    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9;
    JTextField textField1, textField2, textField3, textField4;
    JTextArea textArea1, textArea2;
    JButton submitBtn, clearBtn, uploadImgBtn;
    JComboBox dobMonth, dobYear, dobYearBox, consulMonth, consulDate, consulYear;
    public static String consulDoc;
    public void disposeFrame(){
        dispose(); // this will be used later to dispose of the JFrame
    }

    private final String[] datesBox
            = {"01", "02", "03", "04", "05",
            "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"}; // cant be generated later due to LocalDate parsing error, has to have 0 in front of single digits

    private final String[] monthsBox
            = {"01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12"};

    private String[] yearsBox;

    private final String[] yearThis = {"2023"};


    // --------------Constructor----------------------------------------
    ConsultationModel(){
        // populating YearBox with years from 1930 to 2023
        yearsBox = new String[94];
        for (int i = 0; i < 94; i++) {
            yearsBox[i] = String.valueOf(1930 + i);
        }
        //===========================================================
        menuBar.setBackground(new Color(33,179,167));
        setVisible(true);
        setSize(720,780);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when user close the doctor table window it goes back to the launch page
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Create a new JFrame to visible when doctor window closed
                LaunchPageGUI launchPageGUI=new LaunchPageGUI();

            }
        });
        setTitle("Patients Registration Form");
        getContentPane().setBackground(new Color(181,227,222));
        // --------------J Labels----------------------------------------
        label1 = new JLabel("Registration form | Consultations");
        label1.setForeground(Color.darkGray);
        label1.setFont(new Font("Monospace", Font.BOLD, 40));
        label2 = new JLabel("First Name:");
        label3 = new JLabel("Last name");
        label4 = new JLabel("Mobile number");
        label5 = new JLabel("Date of Birth");
        label6 = new JLabel("Consultation Date");
        label7 = new JLabel("Consultation notes");
        label8 = new JLabel("Consultation Doctor");
        label9 = new JLabel("Patient ID");
        // --------------J text fields----------------------------------------
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textArea1 = new JTextArea();
        textField4 = new JTextField();
        textArea2 = new JTextArea();
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
        uploadImgBtn = new JButton("Upload image");
        // --------------buttons----------------------------------------
        // styling buttons
        Border lineBorder = new LineBorder(Color.BLACK);
        Border borderMargin = new EmptyBorder(5, 15, 5, 15);
        Border compoundBorder = new CompoundBorder(lineBorder, borderMargin);

        ListenerAction btn = new ListenerAction();
        submitBtn.addActionListener(btn);
        submitBtn.setBorder(compoundBorder);
        clearBtn.addActionListener(btn);
        clearBtn.setBorder(compoundBorder);
        uploadImgBtn.addActionListener(btn);
        uploadImgBtn.setBorder(compoundBorder);

        label1.setBounds(120, 62, 700, 70);
        label1.setFont(new Font("Monospace", Font.PLAIN, 30));

        label2.setBounds(13, 152, 193, 25);
        label2.setFont(new Font("Monospace", Font.PLAIN, 18));

        label3.setBounds(13, 196, 193, 25);
        label3.setFont(new Font("Monospace", Font.PLAIN, 18));

        label4.setBounds(13, 240, 193, 25);
        label4.setFont(new Font("Monospace", Font.PLAIN, 18));

        label5.setBounds(13, 284, 193, 25);
        label5.setFont(new Font("Monospace", Font.PLAIN, 18));

        label6.setBounds(13, 323, 193, 25);
        label6.setFont(new Font("Monospace", Font.PLAIN, 18));

        label7.setBounds(13, 367, 193, 25);
        label7.setFont(new Font("Monospace", Font.PLAIN, 18));

        label8.setBounds(13, 525, 193, 25);
        label8.setFont(new Font("Monospace", Font.PLAIN, 18));

        label9.setBounds(13, 571, 193, 25);
        label9.setFont(new Font("Monospace", Font.PLAIN, 18));

        textField1.setBounds(221, 152, 220, 30);
        textField1.setBackground(new Color(225,244,242));
        textField2.setBounds(221, 196, 220, 30);
        textField2.setBackground(new Color(225,244,242));
        textField3.setBackground(new Color(225,244,242));
        textField4.setBackground(new Color(225,244,242));
        textArea1.setBackground(new Color(225,244,242));
        textArea2.setBackground(new Color(225,244,242));
        textField1.setBorder(null);
        textField2.setBorder(null);
        textField3.setBorder(null);
        textField4.setBorder(null);
        textArea1.setBorder(null);
        textArea2.setBorder(null);


        textField3.setBounds(221, 241, 220, 30);
        textArea1.setBounds(221, 366, 220, 141);
        textArea1.setLineWrap(true);

        textField4.setBounds(221, 571, 220, 30);
        textArea2.setBounds(479, 152, 191, 242);
        textArea2.setVisible(false);
        textArea2.setLineWrap(true);

        submitBtn.setBounds(37, 625, 150, 30);
        clearBtn.setBounds(271, 625, 150, 30);
        uploadImgBtn.setBounds(479,625,150,30);

        dobYearBox = new JComboBox(datesBox);
        dobYearBox.setFont(new Font("Monospace", Font.PLAIN, 10));
        dobYearBox.setSize(70, 20);
        dobYearBox.setLocation(222, 282);
        dobYearBox.setBorder(null);
        dobYearBox.setBackground(new Color(225,244,242));
        add(dobYearBox);

        dobMonth = new JComboBox(monthsBox);
        dobMonth.setFont(new Font("Monospace", Font.PLAIN, 10));
        dobMonth.setSize(70, 20);
        dobMonth.setLocation(290, 282);
        dobMonth.setBorder(null);
        dobMonth.setBackground(new Color(225,244,242));
        add(dobMonth);

        dobYear = new JComboBox(yearsBox);
        dobYear.setFont(new Font("Monospace", Font.PLAIN, 10));
        dobYear.setSize(90, 20);
        dobYear.setLocation(360, 282);
        dobYear.setBorder(null);
        dobYear.setBackground(new Color(225,244,242));
        add(dobYear);
//-------------------------------------------------------------------------------
        consulDate = new JComboBox(datesBox);
        consulDate.setFont(new Font("Monospace", Font.PLAIN, 10));
        consulDate.setSize(70, 20);
        consulDate.setLocation(222, 325);
        consulDate.setBorder(null);
        consulDate.setBackground(new Color(225,244,242));
        add(consulDate);

        consulMonth = new JComboBox(monthsBox);
        consulMonth.setFont(new Font("Monospace", Font.PLAIN, 10));
        consulMonth.setSize(70, 20);
        consulMonth.setLocation(290, 325);
        consulMonth.setBorder(null);
        consulMonth.setBackground(new Color(225,244,242));
        add(consulMonth);

        consulYear = new JComboBox(yearThis);
        consulYear.setFont(new Font("Monospace", Font.PLAIN, 10));
        consulYear.setSize(90, 20);
        consulYear.setLocation(360, 325);
        consulYear.setBorder(null);
        consulYear.setBackground(new Color(225,244,242));
        add(consulYear);

        // Default combo box model class
        DefaultComboBoxModel<String> defModel = new DefaultComboBoxModel<>();
        for (Doctor doctor : WestminsterSkinConsultationManager.doctors){
            defModel.addElement(doctor.getName()); // adding the doctors names to the model
        }

        // JComboBox creation and setting it as defModel
        JComboBox<String> comboBox = new JComboBox<>(defModel);
        comboBox.setFont(new Font("Monospace",Font.PLAIN,10));
        comboBox.setSize(200,20);
        comboBox.setLocation(222,525);
        comboBox.setBorder(null);
        comboBox.setBackground(new Color(225,244,242));
        add(comboBox);

        // adding components to the form
        add(label1);
        add(label2);
        add(textField1);
        add(label3);
        add(textField2);
        add(label4);
        add(label5);
        add(label6);
        add(textField3);
        add(label7);
        add(textArea1);
        add(label8);
        add(label9);
        add(textField4);
        add(textArea2);
        add(submitBtn);
        add(clearBtn);
        add(uploadImgBtn);

        loadMenuItem.addActionListener(btn);
        saveMenuItem.addActionListener(btn);
        exitMenuItem.addActionListener(btn);
        consulTable.addActionListener(btn);

        // setting up the menu bar and making it visible
        menuBar.add(menuFile);


        this.setJMenuBar(menuBar);
        this.setVisible(true);

        menuFile.add(consulTable);
        menuFile.add(loadMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(exitMenuItem);

        setVisible(true);
        consulDoc = (String) comboBox.getSelectedItem();



    }

    private class ListenerAction implements ActionListener {

        // defining an error message to display when submitted with empty fields
        public void emptyError() {
            JOptionPane.showMessageDialog(null,
                    "Some fields are empty. Please fill all the fields",
                    "Westminster Skin Consultation", JOptionPane.ERROR_MESSAGE);
        }
        // defining error message to display when doctor is full of maximum 4 patients
        public void doctorNotAvailableError() {
            JOptionPane.showMessageDialog(null, "This doctor is currently unavailable or seeing maximum " +
                            "number of patients. A random doctor will be assigned instead ",
                    "Westminster Skin Consultation", JOptionPane.ERROR_MESSAGE);
        }

        // defining a method to assign a doctor randomly when the desired doctor is unavailable
        public Doctor selectRandomDoctor() {
            Doctor doc = new Doctor();
            int randomNo;
            Random ran = new Random();
            for (Consultation c : WestminsterSkinConsultationManager.consultations) {
                randomNo = ran.nextInt(WestminsterSkinConsultationManager.doctors.size()); // generating a random number based on the doctors count
                doc = WestminsterSkinConsultationManager.doctors.get(randomNo);
                if (c.getDoctor().getMedicalLicenseNumber() != doc.getMedicalLicenseNumber()) {
                    if (doc.getPatientCount() < 4) {
                        break;
                    }
                }
                //}
            }
            return doc;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (submitBtn == e.getSource()) { // check for empty fields
                if (textField1.getText().trim().isEmpty()) {
                    emptyError();
                } else if (textField2.getText().trim().isEmpty()) {
                    emptyError();
                } else if (textField3.getText().trim().isEmpty()) {
                    emptyError();
                } else if (textArea1.getText().trim().isEmpty()) {
                    emptyError();
                } else if (textField4.getText().trim().isEmpty()) {
                    emptyError();
                }
                else {
                    // ================= After pressing the submit button ======================
                    patientFirstName = textField1.getText();
                    patientSurname = textField2.getText();
                    patientDateOfBirth = yearsBox[dobYear.getSelectedIndex()] + "-" + monthsBox[dobMonth.getSelectedIndex()] +
                            "-" + datesBox[dobYearBox.getSelectedIndex()];
                    consultationDate = yearThis[consulYear.getSelectedIndex()] + "-" + monthsBox[consulMonth.getSelectedIndex()] +
                            "-" + datesBox[consulDate.getSelectedIndex()];
                    consultationNotes = textArea1.getText();
                    // ================= encryption using AES algorithm ======================
                    KeyGenerator keyGenerator; //
                    try{
                        keyGenerator = KeyGenerator.getInstance("AES");

                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                    keyGenerator.init(128); // bits
                    SecretKey secretKey = keyGenerator.generateKey();
                    Cipher cipher = null;
                    try{
                        cipher = Cipher.getInstance("AES");
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
                    } catch (InvalidKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    byte[] encryptedNote;
                    try {
                        encryptedNote = cipher.doFinal(consultationNotes.getBytes());
                    } catch (IllegalBlockSizeException | BadPaddingException ex) {
                        throw new RuntimeException(ex);
                    }

                    for (Doctor doc : WestminsterSkinConsultationManager.doctors){
                        if(consulDoc.equals(doc.getName())){
                            System.out.println(consulDoc);
                            consultantDoc = doc;
                            doc.setPatientCount();
                            System.out.println(doc.getPatientCount());
                        }
                    }
                    patientMobileNo = textField3.getText();
                    patientID = textField4.getText();

                    if (consultantDoc.getPatientCount()>=4){ // only one doctor can have 4 patients
                        doctorNotAvailableError();
                        consultantDoc = selectRandomDoctor();
                    }

                    Consultation consultation = new Consultation(consultationDate, encryptedNote);
                    try {
                        LocalDate birthday = LocalDate.parse(patientDateOfBirth);
                        consultation.setPatient(patientFirstName,patientSurname, birthday,patientMobileNo,patientID);
                        consultation.setDoctor(consultantDoc);
                        WestminsterSkinConsultationManager.consultations.add(consultation);
                        System.out.println(WestminsterSkinConsultationManager.consultations);
                        System.out.println(consultation);
                        System.out.println(WestminsterSkinConsultationManager.consultations);
                        // String a,b,c,d are only used for the purpose of displaying the patients details after they press submit button
                        textArea2.setEditable(false);
                        String a = "First Name\t:" + textField1.getText()+"\n";
                        String b = "Last Name\t:" + textField2.getText()+"\n";
                        String c = "Mobile\t:\t" + textField3.getText()+"\n";
                        String d = "Patient ID\t:" + textField4.getText()+"\n";
                        textArea2.setText(a+b+c+d);
                        textArea2.setVisible(true);

                    } catch (Exception exception) {
                        System.out.println("Error parsing birthday: " + exception.getMessage() + "\nPlease try again.");
                    }




                }
            }



            // ================= Clear button functionality ======================
            else if (clearBtn == e.getSource()) {
                String clear = ""; // all the strings will be replaced with a blank string if user presses clear button
                textField1.setText(clear);
                textField2.setText(clear);
                textField3.setText(clear);
                textArea1.setText(clear);
                textField4.setText(clear);
                textArea2.setText(clear);
                dobYearBox.setSelectedIndex(0); // all the indexes will set to zero
                dobMonth.setSelectedIndex(0);
                dobYear.setSelectedIndex(0);
                consulDate.setSelectedIndex(0);
                consulMonth.setSelectedIndex(0);
                consulYear.setSelectedIndex(0);

            }
            // ================= Image upload ======================
            else if (uploadImgBtn ==e.getSource()) { // button to upload skin images
                // object to store encrypted images byte code with their patient ID
                ImageEncryption encryptedImages = new ImageEncryption();

                JFileChooser fileChooser = new JFileChooser(); // file chooser that pops up to upload images
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int fileChooserResults = fileChooser.showOpenDialog(null);
                // encrypting the uploaded image========================================================
                if (fileChooserResults== JFileChooser.APPROVE_OPTION){
                    File fileSelected = fileChooser.getSelectedFile();
                    KeyGenerator keyGenerator = null;
                    try {
                        keyGenerator = KeyGenerator.getInstance("AES");

                    }catch (NoSuchAlgorithmException ex){
                        throw new RuntimeException(ex);
                    }
                    keyGenerator.init(128); //in bits
                    SecretKey secretKey = keyGenerator.generateKey();
                    byte[] imageDataToEncrypt = new byte[0];
                    try{
                        imageDataToEncrypt = Files.readAllBytes(fileSelected.toPath());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Cipher cipher;
                    try{
                        cipher = Cipher.getInstance("AES");
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
                    } catch (InvalidKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        byte[] encryptedImageData = cipher.doFinal(imageDataToEncrypt);
                        encryptedImages.setEncryptedDataImages(encryptedImageData);
                        encryptedImages.setPatientID(patientID= textField4.getText());
                        WestminsterSkinConsultationManager.ListOfEncryptedImages.add(encryptedImages); // storing data in ListOfEncryptedImages
                    } catch (IllegalBlockSizeException | BadPaddingException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            //=============================== loads the launch page ===============================
            else if (exitMenuItem==e.getSource()) {
                disposeFrame();
                LaunchPageGUI launchPageGUI = new LaunchPageGUI();

            }
            //=============================== view consultation table ===============================
            else if (consulTable==e.getSource()) {
                disposeFrame();
                ConsulAbstTableModel consulAbstTableModel = new ConsulAbstTableModel(WestminsterSkinConsultationManager.consultations);


                JTable table = new JTable(consulAbstTableModel); // adding to the container
                JScrollPane scrollPane = new JScrollPane(table);
                table.setGridColor(Color.black);

                JFrame frame = new JFrame("Consultation Table");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // if user closes Consultation table it returns to launch page without exiting the code
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        LaunchPageGUI launchPageGUI = new LaunchPageGUI();  // new JFrame

                    }
                });
                frame.add(scrollPane);
                frame.setSize(450,450);
                frame.getContentPane().setBackground(new Color(181,227,222));
                frame.setVisible(true); // new visible JFrame created
            }

            // ================= saving data ======================
            else if (e.getSource()==saveMenuItem) {
                WestminsterSkinConsultationManager.saveData();

            }
            // ================= loading data ======================
            else if (e.getSource()==loadMenuItem) {
                WestminsterSkinConsultationManager.loadData();
            }

        }


    }

}

