import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
// creating the consultation table

public class ConsulAbstTableModel extends AbstractTableModel {
    private final String[] tableColumnNames = {"Patient", "Patient ID", "Doctor", "Medical License", "Date"};
    private ArrayList<Consultation> consulList;

    public ConsulAbstTableModel(ArrayList<Consultation> consultations) {
        consulList = consultations;
    }



    public int getRowCount() {
        return consulList.size();
    }


    public int getColumnCount() {
        return tableColumnNames.length;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        Object index = null;
        if (columnIndex == 0) {
            index = consulList.get(rowIndex).getPatient().getName();
        } else if (columnIndex == 1) {
            index = consulList.get(rowIndex).getPatient().getPatientId();
        } else if (columnIndex == 2) {
            index = consulList.get(rowIndex).getDoctor().getName();
        } else if (columnIndex==3) {
            index=consulList.get(rowIndex).getDoctor().getMedicalLicenseNumber();

        } else if (columnIndex==4) {
            index=consulList.get(rowIndex).getDateTime();
        }
        return index;

    }

    // this will display the column names in the JTable
    public String getColumnName (int columnIndex){ return tableColumnNames[columnIndex];}
    public Class getColumnClass (int columnIndex){
        return String.class;
    }

}

