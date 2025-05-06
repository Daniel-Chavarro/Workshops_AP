package Books.model.generic;

import Books.model.TypeBook;

public class UniversityTextbook extends Textbook {
    private String faculty;

    public UniversityTextbook(String title, String author, double price, String course, String faculty) {
        super(title, author, price, course);
        this.faculty = faculty;
        typeBook = TypeBook.TEXTBOOKUNIVERSITY;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    @Override
    public String toString() {
        return "ID: " + id + "\nTítulo: " + title + "\nAutor: " + author +
                "\nPrecio: $" + price + "\nTipo: Libro universitario\nCurso: " + getCourse() +
                "\nFacultad: " + faculty;
    }
}


