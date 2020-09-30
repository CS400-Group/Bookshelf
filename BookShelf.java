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
	
	 /**
     * Constructs a bookshelf with size of 10 books
     */
	public BookShelf() {
		bookshelf = new HashTableMap<Integer, Book>();
	}
	
	 /**
     * Constructs a bookshelf with specified size
     */
	public BookShelf(int capacity) {
		bookshelf = new HashTableMap<Integer, Book>(capacity);
	}
	
	/**
     * Adds book to bookshelf
     * 
     * @param book book to add to bookshelf
     * @return added book
     */
	public boolean add(Book book) {
		return  bookshelf.put(book.generateKey(), book);
	}
	
	/**
     * Gets book with specified key from bookshelf
     * 
     * @param book book to add to bookshelf
     * @return added book
     */
	public Book get(Integer key) throws NoSuchElementException {
		return bookshelf.get(key);
	}
	
	/**
     * Returns size of the bookshelf
     * 
     * @return size
     */
	public int size() {
		return bookshelf.size();
	}
	public boolean containsKey(Integer key) {
		return bookshelf.containsKey(key);
	}
	
	/**
     * Replaces existing
     * 
     * @param book book to add to bookshelf
     * @return added book
     */
	public boolean replace(String oldBookTitle, Book newBook) {
		remove(Integer.valueOf(oldBookTitle.hashCode()));
		return  bookshelf.put(newBook.generateKey(), newBook);
	}
	
	/**
     * tests that BookShelf.remove() functions properly
     * 
     * @return true when functionality is verified
     */
	public Book remove(Integer key) {
		Book removeBook = bookshelf.get(key);
		bookshelf.remove(key);
		return removeBook;
	}
	
	/**
     * tests that BookShelf.clear() functions properly
     * 
     * @return true when functionality is verified
     */
	public void clear() {
		bookshelf.clear();
	}
	
}
