package Books.view;

import Books.view.components.button.ButtonCreator;
import Books.view.components.button.FactoryButton;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private FactoryButton factoryButton;

    // Panels for different views
    private HomePanel homePanel;
    private FormBookPanel formBookPanel;
    private ManageBooksPanel ManageBooksPanel;

    // UI Components
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel mainPanel;

    // Menu buttons
    private JButton homeButton;
    private JButton booksButton;
    private JButton newBookButton;

    /**
     * Constructor for MainFrame.
     * Initializes the main window and all its components.
     */
    public MainFrame() {
        setTitle("Books System Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        initComponents();
        add(mainPanel);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Initializes all components of the main frame.
     */
    private void initComponents() {
        // Initialize panels
        homePanel = new HomePanel();
        formBookPanel = new FormBookPanel();
        ManageBooksPanel = new ManageBooksPanel();

        // Create the factory for creating buttons
        factoryButton = new ButtonCreator();

        // Create main panel with BorderLayout to fill the entire frame
        mainPanel = new JPanel(new BorderLayout(0, 0));

        // Create sidebar panel
        sidebarPanel = createSidebarPanel();

        // Create content panel with BorderLayout to fill the available space
        contentPanel = new JPanel(new BorderLayout(0, 0));

        // Add initial panel to content panel
        contentPanel.add(homePanel, BorderLayout.CENTER);

        // Add sidebar and content panel to main panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER); // Use CENTER instead of EAST to fill available space
    }

    /**
     * Creates and configures the sidebar panel with navigation buttons.
     *
     * @return JPanel containing the sidebar components
     */
    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(51, 51, 51));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Add bakery logo or name
        JLabel titleLabel = new JLabel("Books System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Create menu buttons
        homeButton = factoryButton.createButton("Home", new Color(0, 123, 255), "BACK", new Dimension(180, 40));
        newBookButton = factoryButton.createButton("New Book", new Color(0, 123, 255), "ADD_BOOK", new Dimension(180, 40));
        booksButton = factoryButton.createButton("Manage Books", new Color(0, 123, 255), "MANAGE_BOOK", new Dimension(180, 40));

        // Add components to sidebar
        sidebar.add(titleLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(homeButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(booksButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(newBookButton);

        // Add spring to push everything to the top
        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }


    /**
     * Shows a specific panel in the content area.
     * Ensures the panel fills the entire content area.
     *
     * @param panel The panel to show
     */
    public void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public FactoryButton getFactoryButton() {
        return factoryButton;
    }

    public void setFactoryButton(FactoryButton factoryButton) {
        this.factoryButton = factoryButton;
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }

    public void setHomePanel(HomePanel homePanel) {
        this.homePanel = homePanel;
    }

    public FormBookPanel getFormBookPanel() {
        return formBookPanel;
    }

    public void setFormBookPanel(FormBookPanel formBookPanel) {
        this.formBookPanel = formBookPanel;
    }

    public ManageBooksPanel getManageBooksPanel() {
        return ManageBooksPanel;
    }

    public void setManageBooksPanel(ManageBooksPanel manageBooksPanel) {
        ManageBooksPanel = manageBooksPanel;
    }

    public JPanel getSidebarPanel() {
        return sidebarPanel;
    }

    public void setSidebarPanel(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton) {
        this.homeButton = homeButton;
    }

    public JButton getBooksButton() {
        return booksButton;
    }

    public void setBooksButton(JButton booksButton) {
        this.booksButton = booksButton;
    }

    public JButton getNewBookButton() {
        return newBookButton;
    }

    public void setNewBookButton(JButton newBookButton) {
        this.newBookButton = newBookButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
