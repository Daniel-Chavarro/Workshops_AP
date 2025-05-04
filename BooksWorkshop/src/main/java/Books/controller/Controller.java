package Books.controller;

import Books.model.Book;
import Books.model.TypeBook;
import Books.model.TypeNovel;
import Books.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Controller implements ActionListener {
    private MainFrame frame;
//    private BookSystem bookSystem;


    public Controller() {
        frame = new MainFrame();
//      bookSystem = new BookSystem();
        setListeners();
    }


    public void setListeners(){
        frame.getHomeButton().addActionListener(this);
        frame.getBooksButton().addActionListener(this);
        frame.getNewBookButton().addActionListener(this);
        frame.getFormBookPanel().getSaveButton().addActionListener(this);
        frame.getFormBookPanel().getCancelButton().addActionListener(this);
        frame.getManageBooksPanel().getDeleteButton().addActionListener(this);
        frame.getManageBooksPanel().getUpdateButton().addActionListener(this);
        frame.getManageBooksPanel().getSearchButton().addActionListener(this);
        frame.getManageBooksPanel().getAddButton().addActionListener(this);
        frame.getManageBooksPanel().getBackButton().addActionListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            switch (command) {
                case "BACK": {
                    frame.showPanel(frame.getHomePanel());
                    break;
                }
                case "MANAGE_BOOK":
                case "CANCEL": {
                    frame.showPanel(frame.getManageBooksPanel());
                    break;
                }
                case "ADD_BOOK": {
                    frame.showPanel(frame.getFormBookPanel());
                    break;
                }
                case "SAVE_BOOK": {
                    Book old, newBook;
                    newBook = getBookFromForm();
                    old = frame.getFormBookPanel().getOldBook();
                    if (old == null) {
                        // bookSystem.addBook(newBook);
                        JOptionPane.showMessageDialog(frame, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // bookSystem.updateBook(old, newBook);
                        JOptionPane.showMessageDialog(frame, "Book updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    frame.getFormBookPanel().setOldBook(null);

                    //ArrayList<Book> books = bookSystem.getAllBooks();
                    // frame.getManageBooksPanel().writeTable(arraylistBooksToVector(books));
                    frame.showPanel(frame.getManageBooksPanel());
                    break;
                }
                case "DELETE_BOOK": {
                    int selectedRow = frame.getManageBooksPanel().getBookTable().getSelectedRow();
                    if (selectedRow == -1) {
                        throw new IllegalArgumentException("Please select a book to delete.");
                    }
                    int bookId = getBookIdFromTable(selectedRow);
                    // bookSystem.deleteBook(bookId);
                    //ArrayList<Book> books = bookSystem.getAllBooks();
                    // frame.getManageBooksPanel().writeTable(arraylistBooksToVector(books));
                    JOptionPane.showMessageDialog(frame, "Book deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "UPDATE_BOOK": {
                    int selectedRow = frame.getManageBooksPanel().getBookTable().getSelectedRow();
                    if (selectedRow == -1) {
                        throw new IllegalArgumentException("Please select a book to update.");
                    }

                    int bookId = getBookIdFromTable(selectedRow);
                    /* Book old = bookSystem.findBook(bookId)
                    frame.getFormBookPanel().setOldBook(old);
                    frame.getFormBookPanel().setBookData(old);
                    frame.showPanel(frame.getFormBookPanel());
                    */
                    break;
                }
                case "SEARCH_BOOK": {
                    int idFilter = Integer.parseInt(frame.getManageBooksPanel().getIdFilterField().getText());
                    Vector<Vector<Object>> data = new Vector<>();
                    // Book book = bookSystem.findBook(idFilter);
                    // Vector<Object> row = bookToVector(book);
                    // data.add(row);
                    // frame.getManageBooksPanel().writeTable(data);
                }
                case "VIEW_DETAILS": {
                    int selectedRow = frame.getManageBooksPanel().getBookTable().getSelectedRow();
                    if (selectedRow == -1) {
                        throw new IllegalArgumentException("Please select a book to view details.");
                    }
                    int bookId = getBookIdFromTable(selectedRow);
                    // Book book = bookSystem.findBook(bookId);
                    // JOptionPane.showMessageDialog(frame, book.printData(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is used to get the book from the form.
     * @return Book object
     */

    private Book getBookFromForm() {
        // FactoryBook factoryBook = new BookCreator();
        // Get the book details from the form
        String title, author, course, faculty;
        double price;
        TypeBook typeBook;
        TypeNovel typeNovel;

        title = frame.getFormBookPanel().getBookTitleTextField().getText();
        author = frame.getFormBookPanel().getAuthorTextField().getText();
        price = Double.parseDouble(frame.getFormBookPanel().getPriceTextField().getText());
        typeBook = (TypeBook) frame.getFormBookPanel().getTypeBookComboBox().getSelectedItem();

        if (title.isEmpty() || author.isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Please fill all fields correctly.");
        }

        Book book = null;
        switch (typeBook) {
            case BOOK:
                // book = factoryBook.createBook(title, author, price);
                break;
            case TEXTBOOK:
                course = frame.getFormBookPanel().getCourseTextField().getText();
                if (course.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields correctly.");
                }
                // book = factoryBook.createTextBook(title, author, price, course);
                break;
            case NOVEL:
                typeNovel = (TypeNovel) frame.getFormBookPanel().getTypeNovelComboBox().getSelectedItem();
                if (typeNovel == null) {
                    throw new IllegalArgumentException("Please fill all fields correctly.");
                }
                // book = factoryBook.createNovel(title, author, price, typeNovel);
                break;
            case TEXTBOOKUNIVERSITY:
                course = frame.getFormBookPanel().getCourseTextField().getText();
                faculty = frame.getFormBookPanel().getFacultyTextField().getText();
                if (course.isEmpty() || faculty.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields correctly.");
                }
                // book = factoryBook.createTextBookUniversity(title, author, price, university);
                break;
            default:
                throw new IllegalArgumentException("Invalid book type: " + typeBook);
        }

        return book;
    }


    /**
     * This method is used to get the book id from the table.
     * @param row
     * @return int
     */
    private int getBookIdFromTable(int row) {
        int id;
        id = (int) frame.getManageBooksPanel().getBookTable().getValueAt(row, 0);
        return id;
    }

    private Vector<Object> bookToVector(Book book) {
        Vector<Object> row = new Vector<>();
//        row.add(book.getId());
//        row.add(book.getTitle());
//        row.add(book.getAuthor());
//        row.add(book.getPrice());
        return row;
    }

    private Vector<Vector<Object>> arraylistBooksToVector(ArrayList<Book> books) {
        Vector<Vector<Object>> data = new Vector<>();
        for (Book book : books) {
            Vector<Object> row = bookToVector(book);
            data.add(row);
        }
        return data;
    }

    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
