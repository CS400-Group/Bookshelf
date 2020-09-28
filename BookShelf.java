//STILL ADDING FUNCTIONALITY
// Back end BookShelf object class

//////////////////////////////////////////
// --== CS400 File Header Information ==--
// Name: <your full name>
// Email: <your @wisc.edu email address>
// Team: IF
// Role: <your role in your team>
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
//////////////////////////////////////////

import java.util.NoSuchElementException;

public class BookShelf implements Shelf<Integer, Book> {
	
	private HashTableMap<Integer, Book> bookshelf;
	
	public BookShelf() {
		bookshelf = new HashTableMap<Integer, Book>();
	}
	
	public BookShelf(int capacity) {
		bookshelf = new HashTableMap<Integer, Book>(capacity);
	}
	
	public boolean add(Book book) {
		return  bookshelf.put(book.generateKey(), book);
	}
	
	public Book get(Integer key) throws NoSuchElementException {
		return bookshelf.get(key);
	}
	
	public int size() {
		return bookshelf.size();
	}
	public boolean containsKey(Integer key) {
		return bookshelf.containsKey(key);
	}
	
	//Replaces first Book parameter with second
	public boolean replace(Book oldBook, Book newBook) {
		remove(oldBook.generateKey());
		return  bookshelf.put(newBook.generateKey(), newBook);
	}
	
	public Book remove(Integer key) {
		return bookshelf.remove(key);
	}
	public void clear() {
		bookshelf.clear();
	}

	//In Progress
	
	//Method that will print any books matching an entered String's keywords
	//	In Progress
	public String find(String str) {
		return "";
	}
	//Updates an existing book's information
	public boolean update() {
		return true;
	}
}
