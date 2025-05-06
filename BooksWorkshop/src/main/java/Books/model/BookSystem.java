package Books.model;

import Books.model.generic.Book;
import Books.model.persistence.BookDAO;
import java.util.ArrayList;

public class BookSystem {
    private BookDAO bookDAO;

    public BookSystem(){
        bookDAO = new BookDAO();
    }

    public void addBook(Book book) {
        if (!bookDAO.create(book)){
         throw new IllegalArgumentException("Book already exists");}
    }

     public void deleteBook(int id) {
        if (!bookDAO.delete(id)){
        throw new IllegalArgumentException("Book wasn't deleted");}
    }

     public void updateBook(int id, Book book) {
        if (!bookDAO.update(id, book)){
        throw new IllegalArgumentException("Book wasn't updated");}
    }

    public Book findBook(int id) {
        return bookDAO.find(id);
    }

    public ArrayList<Book> getAllBooks() {
        return bookDAO.getAll();
    }

}
