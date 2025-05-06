package Books.model;

import Books.model.generic.Book;
import Books.model.generic.Novel;
import Books.model.generic.Textbook;
import Books.model.generic.UniversityTextbook;

public class BookCreator implements FactoryBook {
    @Override
    public Book createBook(String title, String author, double price) {
        return new Book(title, author, price);
    }

    @Override
    public Book createTextBook(String title, String author, double price, String subject) {
        return new Textbook(title, author, price, subject);
    }

    @Override
    public Book createNovel(String title, String author, double price, TypeNovel typeNovel) {
        return new Novel(title, author, price, typeNovel);
    }

    @Override
    public Book createTextBookUniversity(String title, String author, double price, String subject, String faculty) {
        return new UniversityTextbook(title, author, price, subject, faculty);
    }
}
