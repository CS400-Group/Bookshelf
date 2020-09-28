// Front End Interface
//   Bookshelf is a shelf, interface could be used to make other types of shelves

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

public interface Shelf<KeyType, ValueType> {
	public boolean add(ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public boolean replace(ValueType value1, ValueType value2);
	public ValueType remove(KeyType key); 
	public void clear();
	
	//In progress
	public String find(String str);
	public boolean update();
	
	

}
