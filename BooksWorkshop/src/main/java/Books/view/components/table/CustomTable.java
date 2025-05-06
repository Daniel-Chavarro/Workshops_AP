package Books.view.components.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Vector;


public class CustomTable extends JTable {
    private DefaultTableModel model;

    public CustomTable(String[] columns, boolean isRowSelectable) {
        super();

        // Create a non-editable table model
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        setModel(model);
        setRowSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFillsViewportHeight(true);
        setFocusable(false);

        // Set some visual properties
        setShowGrid(true);
        setGridColor(Color.LIGHT_GRAY);
    }


    public void addRow(Vector<Object> rowData) {
        model.addRow(rowData);
    }

    public void clearRows() {
        model.setRowCount(0);
    }

    public void writeTable(Vector<Vector<Object>> data) {
        clearRows();
        for (Vector<Object> row : data) {
            addRow(row);
        }
    }
}
