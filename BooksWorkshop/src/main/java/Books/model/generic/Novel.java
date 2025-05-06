package Books.model.generic;

import Books.model.TypeBook;
import Books.model.TypeNovel;

public class Novel extends Book {
    private TypeNovel typeNovel;

    public Novel(String title, String author, double price, TypeNovel typeNovel) {
        super(title, author, price);
        this.typeNovel = typeNovel;
        typeBook = TypeBook.NOVEL;
    }

    public TypeNovel getTypeNovel() {
        return typeNovel;
    }

    public void setTypeNovel(TypeNovel typeNovel) {
        this.typeNovel = typeNovel;
    }


    @Override
    public String toString() {
        return "ID: " + id + "\nTítulo: " + title + "\nAutor: " + author +
                "\nPrecio: $" + price + "\nTipo: Novela\nTipo de novela: " + typeNovel;
    }
}


