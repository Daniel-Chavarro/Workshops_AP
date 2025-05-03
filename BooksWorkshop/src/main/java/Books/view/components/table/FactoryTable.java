package Books.view.components.table;

import javax.swing.*;


public interface FactoryTable {

    JTable createTable(String[] columns, boolean isRowSelectable);
}
