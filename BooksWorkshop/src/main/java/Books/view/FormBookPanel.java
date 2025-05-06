package Books.view;

import Books.model.generic.Book;
import Books.model.TypeBook;
import Books.model.TypeNovel;
import Books.view.components.button.ButtonCreator;
import Books.view.components.button.FactoryButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
    private JButton saveButton;
    private JButton cancelButton;

    // Data
    private Book oldBook;     // Original DTO for update operations
    private ActionListener modifyTypeBookListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TypeBook selectedType = (TypeBook) typeBookComboBox.getSelectedItem();
            if (selectedType == TypeBook.BOOK) {
                additionalPanel.removeAll();
            } else {
                modifyAdditionalPanel(selectedType);
            }
            additionalPanel.revalidate();
            additionalPanel.repaint();
        }
    };

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

    public void createAdditionalPanel() {
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
                break;
            }
            case TEXTBOOK: {
                courseLabel = new JLabel("Course Title:");
                courseTextField = new JTextField();
                labels.add(courseLabel);
                inputs.add(courseTextField);
                break;
            }
            case TEXTBOOKUNIVERSITY: {
                courseLabel = new JLabel("Course Title:");
                courseTextField = new JTextField();
                facultyLabel = new JLabel("Faculty:");
                facultyTextField = new JTextField();
                labels.add(courseLabel);
                labels.add(facultyLabel);
                inputs.add(courseTextField);
                inputs.add(facultyTextField);
                break;
            }
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
        saveButton = factoryButton.createButton("Save", new Color(0, 123, 255), "SAVE_BOOK", new Dimension(100, 30));
        cancelButton = factoryButton.createButton("Cancel", new Color(0, 123, 255), "CANCEL", new Dimension(100, 30));

        // Add buttons to the panel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
    }

    // TODO: uncomment the code when the Book class is available and modify the method accordingly
    public void setBookData(Book book) {
//        bookTitleTextField.setText(book.getTitle());
//        authorTextField.setText(book.getAuthor());
//        priceTextField.setText(String.valueOf(book.getPrice()));
//        typeBookComboBox.setSelectedItem(book.getTypeBook());
//
//        if (book.getTypeBook() == TypeBook.NOVEL) {
//            typeNovelComboBox.setSelectedItem(book.getTypeNovel());
//        } else if (book.getTypeBook() == TypeBook.TEXTBOOK) {
//            courseTextField.setText(book.getCourse());
//        } else if (book.getTypeBook() == TypeBook.TEXTBOOKUNIVERSITY) {
//            courseTextField.setText(book.getCourse());
//            facultyTextField.setText(book.getFaculty());
//        }
    }

    public FactoryButton getFactoryButton() {
        return factoryButton;
    }

    public void setFactoryButton(FactoryButton factoryButton) {
        this.factoryButton = factoryButton;
    }

    public JPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(JPanel formPanel) {
        this.formPanel = formPanel;
    }

    public JPanel getAdditionalPanel() {
        return additionalPanel;
    }

    public void setAdditionalPanel(JPanel additionalPanel) {
        this.additionalPanel = additionalPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JLabel getTypeBookLabel() {
        return typeBookLabel;
    }

    public void setTypeBookLabel(JLabel typeBookLabel) {
        this.typeBookLabel = typeBookLabel;
    }

    public JLabel getBookTitleLabel() {
        return bookTitleLabel;
    }

    public void setBookTitleLabel(JLabel bookTitleLabel) {
        this.bookTitleLabel = bookTitleLabel;
    }

    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    public void setAuthorLabel(JLabel authorLabel) {
        this.authorLabel = authorLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JLabel getCourseLabel() {
        return courseLabel;
    }

    public void setCourseLabel(JLabel courseLabel) {
        this.courseLabel = courseLabel;
    }

    public JLabel getFacultyLabel() {
        return facultyLabel;
    }

    public void setFacultyLabel(JLabel facultyLabel) {
        this.facultyLabel = facultyLabel;
    }

    public JLabel getTypeNovelLabel() {
        return typeNovelLabel;
    }

    public void setTypeNovelLabel(JLabel typeNovelLabel) {
        this.typeNovelLabel = typeNovelLabel;
    }

    public JComboBox<TypeBook> getTypeBookComboBox() {
        return typeBookComboBox;
    }

    public void setTypeBookComboBox(JComboBox<TypeBook> typeBookComboBox) {
        this.typeBookComboBox = typeBookComboBox;
    }

    public JComboBox<TypeNovel> getTypeNovelComboBox() {
        return typeNovelComboBox;
    }

    public void setTypeNovelComboBox(JComboBox<TypeNovel> typeNovelComboBox) {
        this.typeNovelComboBox = typeNovelComboBox;
    }

    public JTextField getBookTitleTextField() {
        return bookTitleTextField;
    }

    public void setBookTitleTextField(JTextField bookTitleTextField) {
        this.bookTitleTextField = bookTitleTextField;
    }

    public JTextField getAuthorTextField() {
        return authorTextField;
    }

    public void setAuthorTextField(JTextField authorTextField) {
        this.authorTextField = authorTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setPriceTextField(JTextField priceTextField) {
        this.priceTextField = priceTextField;
    }

    public JTextField getCourseTextField() {
        return courseTextField;
    }

    public void setCourseTextField(JTextField courseTextField) {
        this.courseTextField = courseTextField;
    }

    public JTextField getFacultyTextField() {
        return facultyTextField;
    }

    public void setFacultyTextField(JTextField facultyTextField) {
        this.facultyTextField = facultyTextField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public Book getOldBook() {
        return oldBook;
    }

    public void setOldBook(Book oldBook) {
        this.oldBook = oldBook;
    }

    public ActionListener getModifyTypeBookListener() {
        return modifyTypeBookListener;
    }

    public void setModifyTypeBookListener(ActionListener modifyTypeBookListener) {
        this.modifyTypeBookListener = modifyTypeBookListener;
    }
}

