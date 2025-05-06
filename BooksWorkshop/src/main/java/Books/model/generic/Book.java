package Books.model.generic;


import Books.model.TypeBook;

import javax.swing.*;

public class Book {
    private static int idCounter = 1;

    protected int id;
    protected String title;
    protected String author;
    protected double price;
    protected TypeBook typeBook;


    public Book(String title, String author, double price) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.price = price;
        typeBook = TypeBook.BOOK;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Book.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void printData() {
        JOptionPane.showMessageDialog(null, toString(), "Book Data", JOptionPane.INFORMATION_MESSAGE);
    }

    public TypeBook getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(TypeBook typeBook) {
        this.typeBook = typeBook;
    }

    @Override
    public String toString() {
        return "Título: " + title + "\nAutor: " + author + "\nPrecio: $" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id;
    }


}
