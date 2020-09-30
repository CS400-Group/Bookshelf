/////////////////////////////////////////
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
	
	/**
     * Book constructor
     * 
     * @param title1 title of book
     * @param author1 author of book 
     * @param isbn1 book's ISBN number
     * @return added book
     */
	public Book(String title1, String author1, Long isbn1) {
		title = title1;
		author = author1;
		isbn = isbn1;
	}
	
	/**
     * Retrieves title of book
     * 
     * @return title of book
     */
	public String getTitle() {
		return this.title;
	}
	
	/**
     * Retrieves author of book
     * 
     * @param book book to add to bookshelf
     * @return author of book
     */
	public String getAuthor() {
		return this.author;
	}
	
	/**
     * Retrieves ISBN of book
     * 
     * @return ISBN of book
     */
	public long getIsbn() {
		return this.isbn;
	}
	
	/**
     * Generates a books key for the hashtable data structure
     * 
     * @return key of book in hashtable
     */
	public Integer generateKey() {
		return Integer.valueOf(title.hashCode());
	}

}
