package pkgLibrary;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;
import pkgExcpetion.BookException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public Book getBook(String id) throws BookException {
		boolean bookExists = false;
		try {
			for (Book b : this.getBooks()) {
				if (b.getId().equals(id)) {
					bookExists = true;
					return b;
				}
			}
			if (!bookExists) {
				throw new BookException();
			}
		}catch(BookException e) {
			throw e;
		}
		return null;
	}
	
	public void addBook(Book b) throws BookException {
		boolean bookExists = false;
		try {
			for (Book c : this.getBooks()) {
				if (c.getId().equals(b.getId())) {
					bookExists = true;
					throw new BookException();
				}
			}
		}catch(BookException c) {
			throw c;
		}
		if (!bookExists) {
			this.books.add(b);
		}
	}
	
}
