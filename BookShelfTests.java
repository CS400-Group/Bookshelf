// Tests for the BookShelf class's methods

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
public class BookShelfTests {
	
	public static void main(String args[]) {
		System.out.println("testAdd(): "+ testAdd());
		System.out.println("testGet(): "+ testGet());
		System.out.println("testSize(): "+ testSize());
		System.out.println("testContainsKey: "+ testContainsKey());
		System.out.println("testRemove(): " + testRemove());
		System.out.println("testClear(): " + testClear());
	}
	
	 /**
     * tests that BookShelf.add() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testAdd() {
		BookShelf bookshelf = new BookShelf();
		Book book = new Book("Catcher in the Rye",  "JD Salinger", 9788172248963L);
		bookshelf.add(book);
		if (bookshelf.size() == 1 && bookshelf.get(book.generateKey()).equals(book)) {
			return true;
		}
		return false;
	}
	
	/**
     * tests that BookShelf.get() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testGet() {
		BookShelf bookshelf = createShelf();
		Book book = new Book("Don Quixote",  "Miguel de Cervantes", 9780060934347L);
		bookshelf.add(book);
		if (bookshelf.get(book.generateKey()).equals(book)) {
			return true;
		}
		return false;
	}
	
	/**
     * tests that BookShelf.size() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testSize() {
		BookShelf bookshelf = createShelf();
		Book book = new Book("Crime and Punishment", "Fyodor Dostoyevsky", 9780486415871L);
		bookshelf.add(book);
		if (bookshelf.size() == 6) {
			return true;
		}
		return false;
	}
	
	/**
     * tests that BookShelf.containsKey() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testContainsKey() {
		BookShelf bookshelf = new BookShelf();
		Book book = new Book("Nineteen Eighty-Four", "George Orwell", 9780451524935L);
		bookshelf.add(book);
		if (bookshelf.containsKey(book.generateKey())) {
			return true;
		}
		return false;
	}
	
	/**
     * tests that BookShelf.remove() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testRemove() {
		BookShelf bookshelf = new BookShelf();
		Book book = new Book("Crime and Punoshment", "Fyodor Dostoyevsky", 9780486415871L);
		bookshelf.add(book);
		bookshelf.remove(book.generateKey());
		if (bookshelf.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
     * tests that BookShelf.clear() functions properly
     * 
     * @return true when functionality is verified
     */
	public static boolean testClear() {
		BookShelf bookshelf = createShelf();
		bookshelf.clear();
		if (0 < bookshelf.size()) {
			System.out.println(bookshelf.size());
			return false;
		}
		return true;	
	}
	
	/**
     * Helper methods that creates a bookShelf with 
     * 
     * @return bookshelf holding 5 books and with capacity 10
     */
	public static BookShelf createShelf() {
		BookShelf bookshelf = new BookShelf();
		Book book1 = new Book("The Odyssey", "Homer", 123456789012L);
		Book book2 = new Book("The Count of Monte Cristo", "Alxander Dumas", 9780553213508L);
		Book book3 = new Book("Oliver Twist",  "Charles Dickens", 9788172248963L);
		Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", 9780446310789L );
		Book book5 = new Book("Moby-Dick", "Herman Melville", 9780553213119L );
		bookshelf.add(book1);
		bookshelf.add(book2);
		bookshelf.add(book3);
		bookshelf.add(book4);
		bookshelf.add(book5);
		return bookshelf;
	}
}