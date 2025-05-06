package Books.model.generic;

import Books.model.TypeBook;

public class Textbook extends Book {
    private String course;

    public Textbook(String title, String author, double price, String course) {
        super(title, author, price);
        this.course = course;
        typeBook = TypeBook.TEXTBOOK;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


    @Override
    public String toString() {
        return "ID: " + id + "\nTítulo: " + title + "\nAutor: " + author +
                "\nPrecio: $" + price + "\nTipo: Libro de texto\nCurso: " + course;
    }
}


