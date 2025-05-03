package Books.view;

import Books.model.Book;
import Books.model.TypeBook;
import Books.model.TypeNovel;
import Books.view.components.button.ButtonCreator;
import Books.view.components.button.FactoryButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormBookPanel extends JPanel {
    private FactoryButton factoryButton;

    // UI Components - Panels
    private JPanel formPanel;
    private JPanel additionalPanel;
    private JPanel buttonPanel;

    // UI Components - Labels
    private JLabel typeBookLabel;
    private JLabel bookTitleLabel;
    private JLabel authorLabel;
    private JLabel priceLabel;
    private JLabel courseLabel;
    private JLabel facultyLabel;
    private JLabel typeNovelLabel;

    // UI Components - Input fields
    private JComboBox<TypeBook> typeBookComboBox;
    private JComboBox<TypeNovel> typeNovelComboBox;
    private JTextField bookTitleTextField;
    private JTextField authorTextField;
    private JTextField priceTextField;
    private JTextField courseTextField;
    private JTextField facultyTextField;

    // UI Components - Buttons
    private JButton saveBtn;
    private JButton cancelBtn;

    // Data
    private Book oldBook;     // Original DTO for update operations

    public FormBookPanel() {
        factoryButton = new ButtonCreator();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F5F5"));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setPreferredSize(new Dimension(500, 500));



        // Initialize components
        createFormPanel();
        createAdditionalPanel();
        createButtonPanel();

        add(formPanel, BorderLayout.NORTH);
        add(additionalPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        typeBookComboBox.addActionListener(modifyTypeBookListener);
    }

    public void createFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        typeBookLabel = new JLabel("Type of Book:");
        bookTitleLabel = new JLabel("Book Title:");
        authorLabel = new JLabel("Author:");
        priceLabel = new JLabel("Price:");

        // Initialize labels
        JLabel[] labels = {
            typeBookLabel,
            bookTitleLabel,
            authorLabel,
            priceLabel,
        };

        typeBookComboBox = new JComboBox<>(TypeBook.values());
        bookTitleTextField = new JTextField();
        authorTextField = new JTextField();
        priceTextField = new JTextField();

        JComponent[] inputs = {
            typeBookComboBox,
            bookTitleTextField,
            authorTextField,
            priceTextField,
        };

        // Add labels and input fields to the form panel
        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i;

            gbc.gridx = 0;
            JLabel label = labels[i];
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            inputs[i].setPreferredSize(new Dimension(200, 25));
            formPanel.add(inputs[i], gbc);
        }
    }

    public void createAdditionalPanel(){
        additionalPanel = new JPanel();
        additionalPanel.setLayout(new GridBagLayout());
        additionalPanel.setBackground(Color.WHITE);
        additionalPanel.setOpaque(false);
    }

    public void modifyAdditionalPanel(TypeBook option) {
        // Clear the previous additional panel
        additionalPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JComponent> inputs = new ArrayList<>();

        switch (option) {
            case NOVEL: {
                typeNovelLabel = new JLabel("Type of Novel:");
                typeNovelComboBox = new JComboBox<>(TypeNovel.values());
                labels.add(typeNovelLabel);
                inputs.add(typeNovelComboBox);
                break;}
            case TEXTBOOK: {
                courseLabel = new JLabel("Course Title:");
                courseTextField = new JTextField();
                labels.add(courseLabel);
                inputs.add(courseTextField);
                break;}
            case TEXTBOOKUNIVERSITY: {
                courseLabel = new JLabel("Course Title:");
                courseTextField = new JTextField();
                facultyLabel = new JLabel("Faculty:");
                facultyTextField = new JTextField();
                labels.add(courseLabel);
                labels.add(facultyLabel);
                inputs.add(courseTextField);
                inputs.add(facultyTextField);
                break;}
            }

        // Add labels and input fields to the form panel
        for (int i = 0; i < labels.size(); i++) {
            gbc.gridy = i;

            gbc.gridx = 0;
            JLabel label = labels.get(i);
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            additionalPanel.add(label, gbc);

            gbc.gridx = 1;
            inputs.get(i).setPreferredSize(new Dimension(200, 25));
            additionalPanel.add(inputs.get(i), gbc);
        }

        // Repaint and revalidate the additional panel to reflect changes
        additionalPanel.revalidate();
        additionalPanel.repaint();
    }

    public void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setOpaque(false);

        // Initialize buttons
        saveBtn = factoryButton.createButton("Save", new Color(0, 123, 255), "SAVE_BOOK", new Dimension(100, 30));
        cancelBtn = factoryButton.createButton("Cancel", new Color(0, 123, 255), "CANCEL", new Dimension(100, 30));

        // Add buttons to the panel
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
    }

    private ActionListener modifyTypeBookListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TypeBook selectedType = (TypeBook) typeBookComboBox.getSelectedItem();
            if (selectedType == TypeBook.BOOK) {
                additionalPanel.removeAll();
            } else{
                modifyAdditionalPanel(selectedType);
            }
            additionalPanel.revalidate();
            additionalPanel.repaint();
        }
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form Book Panel");
        FormBookPanel formBookPanel = new FormBookPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(formBookPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

