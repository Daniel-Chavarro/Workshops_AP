package Books.view.components.table;

import javax.swing.*;


public class TableCreator implements FactoryTable {

    @Override
    public JTable createTable(String[] columns, boolean isRowSelectable) {
        // Create and return a CustomTable instance
        return new CustomTable(columns, isRowSelectable);
    }
}
