// Book object class

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

public class Book {
	
	public String title;
	public String author;
	public Long isbn;
	
	//Notes
	//	1) You should use 13 digit ISBNs, 10 digit ISBNs are deprecated
	//	2) We have to use long as 13 digits is out of the range of int
	//
	public Book(String title1, String author1, Long isbn1) {
		title = title1;
		author = author1;
		isbn = isbn1;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public long getIsbn() {
		return this.isbn;
	}
	
	//Notes
	//	1. This is kind of bad solution but made more sense then making a book object both the key and value
	//	   Could also make the key book.toString()
	//	2. The key is the book's hash code in an Integer(uppercase) wrapper
	//	3. Integer.hashCode() returns the int primitive that is wrapped, so when the HashTableMap calls key.hashCode() it returns the same value as book.hashCode()
	public Integer generateKey() {
		return Integer.valueOf(title.hashCode());
	}

}
