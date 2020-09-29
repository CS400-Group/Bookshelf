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
		System.out.println("testSize(): "+ testSize());
		System.out.println("testGet(): "+ testGet());
		//System.out.println("testRemove(): " + testRemove());
		System.out.println("testClear(): " + testClear());
		//System.out.println("test3: "+ test3());
		//System.out.println("test4: "+ test4());
		//System.out.println("test5: "+ test5());*/
	}
	
	//Book book1 = new Book("The Odyssey", "Homer", 123456789012L);
			//Book book2 = new Book("The Count of Monte Cristo", "Alxander Dumas", 9780553213508L);
			//Book book3 = new Book("Oliver Twist",  "Charles Dickens", 9788172248963L);
			//Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", 
			//		9780446310789L );
			//Book book5 = new Book("Moby-Dick", "Herman Melville", 9780553213119L );
			//bookshelf.add(book1);
			//bookshelf.add(book2);
			//bookshelf.add(book3);
			//bookshelf.add(book4);
			//bookshelf.add(book5);
	
	public static boolean testSize() {
		BookShelf bookshelf = new BookShelf();
		bookshelf.add(new Book("The Odyssey", "Homer", 123456789012L));
		bookshelf.add(new Book("The Count of Monte Cristo", "Alxander Dumas", 9780553213508L));
		bookshelf.add(new Book("Oliver Twist",  "Charles Dickens", 9788172248963L));
		bookshelf.add(new Book("To Kill a Mockingbird", "Harper Lee", 9780446310789L));
		bookshelf.add(new Book("Moby-Dick", "Herman Melville", 9780553213119L ));
		if (bookshelf.size() == 5) {
			return true;
		}
		return false;
	}
	
	public static boolean testGet() {
		BookShelf bookshelf = new BookShelf();
		Book book1 = new Book("The Odyssey", "Homer", 123456789012L);
		Book book2 = new Book("The Count of Monte Cristo", "Alxander Dumas", 9780553213508L);
		Book book3 = new Book("Oliver Twist",  "Charles Dickens", 9788172248963L);
		Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", 
				9780446310789L );
		Book book5 = new Book("Moby-Dick", "Herman Melville", 9780553213119L );
		bookshelf.add(book1);
		bookshelf.add(book2);
		bookshelf.add(book3);
		bookshelf.add(book4);
		bookshelf.add(book5);
		if (bookshelf.get(book3.generateKey()).equals(book3)) {
			return true;
		}
		return false;
	}
	
	public static boolean testRemove() {
		BookShelf bookshelf3 = new BookShelf();
		Book book1 = new Book("The Odyssey", "Homer", 123456789012L);
		Book book2 = new Book("The Count of Monte Cristo", "Alxander Dumas", 9780553213508L);
		Book book3 = new Book("Oliver Twist",  "Charles Dickens", 9788172248963L);
		Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", 9780446310789L );
		Book book5 = new Book("Moby-Dick", "Herman Melville", 9780553213119L );
		bookshelf3.add(book1);
		bookshelf3.add(book2);
		bookshelf3.add(book3);
		bookshelf3.add(book4);
		bookshelf3.add(book5);
		System.out.println(bookshelf3.remove(book4.generateKey()));
		if (bookshelf3.remove(book2.generateKey()).equals(book2)) {
			System.out.println("0");
			return true;
		}
		return false;
	}
	
	public static boolean testClear() {
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
		bookshelf.clear();
		if (0 < bookshelf.size()) {
			System.out.println("0");
			System.out.println(bookshelf.size());
			return false;
		} else if (bookshelf.get(book1.generateKey()) != null) {
			System.out.println("1");
			return false;
		}
		return true;	
	}
}