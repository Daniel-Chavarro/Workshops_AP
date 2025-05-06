package Books.model.persistence;


import java.util.*;

import Books.model.generic.Book;

public class BookDAO {
	private ArrayList<Book> books;

	public BookDAO() {
		books = new ArrayList<>();
	}
	

	public boolean create(Book New) {
		if (books.contains(New)) {
			return false;
		} else {
			books.add(New);
			return true;
		}
	}


	public boolean update(int id, Book New) {
		Book book = find(id);
		if (book != null) {
			books.remove(book);
			books.add(New);
			return true;
		}

		return false;

	}


	public boolean delete(int id) {
		Book book = find(id);
		if (book != null) {
			books.remove(book);
			return true;
		}

		return false;
	}


	public Book find(int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}


	public ArrayList<Book> getAll() {
		return new ArrayList<>(books);
	}

}
