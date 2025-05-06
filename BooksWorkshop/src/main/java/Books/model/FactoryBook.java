package Books.model;

import Books.model.generic.Book;

public interface FactoryBook {
    Book createBook(String title, String author, double price);

    Book createTextBook(String title, String author, double price, String subject);

    Book createNovel(String title, String author, double price, TypeNovel typeNovel);

    Book createTextBookUniversity(String title, String author, double price, String subject, String faculty);
}
