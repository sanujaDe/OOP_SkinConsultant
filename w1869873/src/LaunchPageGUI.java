import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Set;

public class LaunchPageGUI implements ActionListener {

    JFrame jFrame = new JFrame();
    JButton doctorBtn = new JButton("View Doctors");
    JButton addConsulBtn =  new JButton("Add a Consultation");


    LaunchPageGUI(){
        // texts----------------------------------------------------------
        JLabel label1 = new JLabel("Welcome To Westminster Skin Consultations");
        label1.setForeground(Color.darkGray);
        label1.setFont(new Font("Monospace", Font.BOLD, 22));
        label1.setBounds(50, 40, 520, 30);

        //--------------------------------------------------------------------
        JLabel label2 = new JLabel("Select from the below options to continue");
        label2.setForeground(Color.black);
        label2.setFont(new Font("Monospace", Font.PLAIN, 16));
        label2.setBounds(140, 90, 480, 30);
        // buttons ------------------------------------------------------------
        // styling buttons
        Border lineBorder = new LineBorder(Color.BLACK);
        Border borderMargin = new EmptyBorder(5, 15, 5, 15);
        Border compoundBorder = new CompoundBorder(lineBorder, borderMargin);
        //-----------------------------------
        doctorBtn.setBounds(70,150,200,60);
        doctorBtn.setFocusable(false);
        doctorBtn.addActionListener(this);
        doctorBtn.setBorder(compoundBorder);
        doctorBtn.setFont(new Font("Monospace",Font.ROMAN_BASELINE,15));


        addConsulBtn.setBounds(300,150,200,60);
        addConsulBtn.setFocusable(false);
        addConsulBtn.addActionListener(this);
        addConsulBtn.setBorder(compoundBorder);
        addConsulBtn.setFont(new Font("Monospace",Font.ROMAN_BASELINE,15));


        // adding to the frame-----------------------------------------------------

        jFrame.setTitle("Westminster Skin Consultations");
        jFrame.getContentPane().setBackground(new Color(181,227,222));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600,300);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.add(label1);
        jFrame.add(label2);
        jFrame.add(doctorBtn);
        jFrame.add(addConsulBtn);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //========== view Doctors table when clicked the button ========================
        if(e.getSource() == doctorBtn){
            jFrame.dispose();
            DoctorModel tableModel = new DoctorModel(WestminsterSkinConsultationManager.doctors);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setGridColor(Color.black);;
            table.setBackground(new Color(181,227,222));
            JFrame frame= new JFrame("Today's Doctors List");


            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when user close the doctor table window it goes back to the launch page
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    // Create a new JFrame to visible when doctor window closed
                    LaunchPageGUI launchPageGUI=new LaunchPageGUI();

                }
            });
            frame.add(scrollPane);
            frame.setSize(500,500);
            frame.setVisible(true);
            frame.setBackground(new Color(181,227,222));
        }
        // ======= View add Consultation form when clicked the button===========
        if(e.getSource() == addConsulBtn){
            jFrame.dispose();
            ConsultationModel consultationModel = new ConsultationModel();
        }
    }
}
