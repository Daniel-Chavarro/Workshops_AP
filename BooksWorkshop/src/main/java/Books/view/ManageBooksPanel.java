package Books.view;

import Books.view.components.button.ButtonCreator;
import Books.view.components.button.FactoryButton;
import Books.view.components.table.TableCreator;
import Books.view.components.table.FactoryTable;

import javax.swing.*;
import java.awt.*;

public class ManageBooksPanel extends JPanel {
    // Factory for creating buttons
    private FactoryButton factoryButton;

    // Panels to hold the components
    private JPanel filterPanel;
    private JPanel bottomPanel;
    private JScrollPane tablePanel;

    // Text field for filtering products by ID
    private JTextField idFilterField;

    // Table to display product information
    private JTable bookTable;

    // Action buttons
    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton backButton;



    /**
     * Constructor for ManageProductsPanel.
     * Initializes the panel with a BorderLayout and sets up all UI components.
     */
    public ManageBooksPanel() {
        factoryButton = new ButtonCreator();
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        startComponents();
    }

    /**
     * Initializes and adds all UI components to the panel.
     * This method organizes the panel into three main sections:
     * - Top: Filter panel for searching books
     * - Center: Table displaying product information
     * - Bottom: Action buttons for product management
     */
    public void startComponents() {
        // Create and configure content pane
        createFilterPanel();
        createTablePanel();
        createBottomPanel();
        add(filterPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }


    /**
     * Creates and configures the filter panel with search fields and button.
     */
    private void createFilterPanel() {

        filterPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        filterPanel.setOpaque(false);

        // Initialize filter text fields
        idFilterField = new JTextField();

        // Create and configure search button
        searchButton = factoryButton.createButton("Search", new Color(0, 123, 255), "SEARCH_PRODUCT", new Dimension(100, 30));

        // Add components to filter panel
        filterPanel.add(new JLabel("ID:"));
        filterPanel.add(idFilterField);
        filterPanel.add(searchButton);
    }


    /**
     * Creates and configures the table panel for displaying books information.
     * The table shows product details with non-editable cells.
     */
    private void createTablePanel() {
        FactoryTable factoryTable = new TableCreator();
        // Define table column headers
        String[] columnNames = {"ID", "Title", "Author", "Price"};

        // Create a table model with the specified column names
        bookTable = factoryTable.createTable(columnNames, true);

        // Wrap table in scroll pane for scrolling capability
        tablePanel = new JScrollPane(bookTable);
    }

    /**
     * Creates and configures the bottom panel with action buttons.
     */
    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);

        // Create action buttons with appropriate colors
        addButton = factoryButton.createButton("Add New Product", new Color(0, 123, 255), "ADD_PRODUCT", new Dimension(150, 30));
        deleteButton = factoryButton.createButton("Delete Product", new Color(220, 53, 69), "DELETE_PRODUCT", new Dimension(150, 30));
        updateButton = factoryButton.createButton("Update Product", new Color(40, 167, 69), "UPDATE_PRODUCT", new Dimension(150, 30));
        backButton = factoryButton.createButton("Back", new Color(108, 117, 125), "BACK", new Dimension(150, 30));


        // Add buttons to panel in desired order
        bottomPanel.add(backButton);
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(updateButton);
    }
}
