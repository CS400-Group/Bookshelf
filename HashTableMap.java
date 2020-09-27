//NEEDS REVISION

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

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    
	//MAY NEED REVISION
	//	May need to be LinkedList<KeyType, ValueType>
	private LinkedList[] table;
    
	public static void main(String args[]) {}
	
	//default capacity is 10
	public HashTableMap() { 
		table = new LinkedList[10];
	}
	
	public HashTableMap(int capacity) {
		table = new LinkedList[capacity];
	}
	
	//NEEDS REVISION
	//	Only works when there are no duplicates
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (!containsKey(key)) {
			throw new NoSuchElementException();
		}
		int index = Math.abs(key.hashCode())%table.length;
		Pair<KeyType, ValueType> pair= (Pair<KeyType, ValueType>)table[index].get(0);
		return pair.getValue();
	}
	
	//MAY NEED REVISION
	public boolean put(KeyType key, ValueType value) {
		if (table.length*.80 < size()) {
			grow();
		}
		if (containsKey(key)) {
			return false;
		}

		Pair<KeyType, ValueType> pair = new Pair<KeyType, ValueType>(key, value);
		int index = (Math.abs(key.hashCode()))%table.length;
		if (table[index] == null) {
			table[index] = new LinkedList<Pair<KeyType, ValueType>>();
		}
		table[index].add(pair);
		return true;
		
	}
	
	//MAY NEED REVISION
	//	Does not count duplicates but it shouldn't need to, right?
	public int size() {
		int size = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) size++;
		}
		return size;
	}
	
	//NEEDS REVISION
	public boolean containsKey(KeyType key) {
		int index = Math.abs(key.hashCode())%(table.length);
		
		//NEEDS REVISION
		//  1) For loop should step through Pair objects not just Object
		//	2) Casting errors
		for (Object O: table[index]) {
			if ((((Pair<KeyType, ValueType>)O).getKey()).equals(key)) {
				
			}
		}
		if (table[index] != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	//MAY NEED REVISION
	public ValueType remove(KeyType key) {
		if (!containsKey(key)) {
			return null;
		} else {
			int index = Math.abs(key.hashCode())%table.length;
			//MAY NEED REVISION
			//	Had to cast Object to Pair
			Pair<KeyType, ValueType> pair = (Pair<KeyType, ValueType>)table[index].get(0);
			ValueType value = pair.getValue();
			table[index] = null;
			return value;
		}
	}
	
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}
	
	//NEEDS REVISION
	// For loop should be able to step through Pair objects not just Object
	public void print() {
		for (Object item: table) {
			System.out.println(item);
		}
	}

	//NEED REVISION
	// Relies on above methods that need revision
	public LinkedList[] grow() {
		LinkedList[] newTable = new LinkedList[table.length*2];
		int index = 0;
		// NEEDS REVISION
		//For loop should step through Pair objects not just Object
		for (Object i: table) {
			index++;			
		}

		return newTable;
	}
	
}
