import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
// this class is used to create doctors list in a table and display it in the GUI

public class DoctorModel extends AbstractTableModel {

    private String[] columns = {"Name", "Medical License", "Specialisation" };
    public static ArrayList<Doctor> listDocs;
    public DoctorModel(ArrayList<Doctor> docListNew){
        listDocs=docListNew;
    }
    public String getColumnName (int column){return columns[column];}
    public Class getColumnClass(int column){return String.class;}

    @Override
    public int getRowCount() {
        return listDocs.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = listDocs.get(row).getName();
        } else if (col == 1) {
            temp = listDocs.get(row).getMedicalLicenseNumber();
        } else if (col == 2) {
            temp = listDocs.get(row).getSpecialisation();
        }
        return temp;

    }

}
