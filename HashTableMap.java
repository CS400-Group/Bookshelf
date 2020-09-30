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
    
	private LinkedList<Pair<KeyType, ValueType>>[] table;
    private int size;
	
	public static void main(String args[]) {}
	
	//default capacity is 10
	public HashTableMap() { 
		table = new LinkedList[10];
		for (int i = 0; i < 10; i++) {
			table[i] = new LinkedList<Pair<KeyType, ValueType>>();
			
		}
	}
	
	public HashTableMap(int capacity) {
		table = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			table[i] = new LinkedList<Pair<KeyType, ValueType>> ();
		}
	}
	
	@Override
	public boolean put(KeyType key, ValueType value) {
		Pair<KeyType, ValueType> pair = new Pair<KeyType, ValueType>(key, value);
		if (table.length*.80 < size()) {
			grow();
		}
		if (containsKey(key)) {
			return false;
		} else if (containsValue(value)) {
			if (0.8 <= ((double)size) / ((double)table.length)) {
				grow();
			}
			LinkedList<Pair<KeyType, ValueType>> list = table[getIndex(value)]; 
			list.add(pair);
			size++;
			return true;
		} else {
			LinkedList<Pair<KeyType, ValueType>> list = table[Math.abs(key.hashCode() % table.length)];
			list.add(pair);
			size++;
			return true;
		}
	}

	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (!containsKey(key)) {
			throw new NoSuchElementException();
		}
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			for(int j = 0; j < list.size(); j++) {
				Pair<KeyType, ValueType> pair = list.get(j);
				if (pair.getKey().equals(key))
					return pair.getValue();
			}
		}
		return null;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean containsKey(KeyType key) {
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			for(int j = 0; j < list.size(); j++) {
				Pair<KeyType, ValueType> pair = list.get(j);
				if (pair.getKey().equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsValue(ValueType value) {
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			for (int j = 0; j < list.size(); j++) {
				Pair<KeyType, ValueType> pair = list.get(j);
				if (pair.getValue().equals(value))
					return true;
			}
		}
		return false;
	}
	
	@Override
	public ValueType remove(KeyType key) {
		ValueType valueRemoved = null;
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			for (int j = 0; j < list.size(); j++) {
				if (list != null && list.get(j).getKey().equals(key)) {                                                                    
					valueRemoved = list.get(j).getValue();
					if (list.size() > 1) {
						list.remove(j);
						table[(Math.abs(key.hashCode()) % table.length)] = list;
					} else {
						table[(Math.abs(key.hashCode()) % table.length)] = new LinkedList<Pair<KeyType, ValueType>>();
					}
					break;
				}
			}
		}
		size--;
		rehash();
		return valueRemoved;
	}
	
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Pair<KeyType, ValueType>>();
		}
		size = 0;
	}

	private void grow() {
		LinkedList<Pair<KeyType, ValueType>>[] newTable = new LinkedList[table.length * 2];
		for (int i = 0; i < newTable.length; i++) {
			newTable[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			if (!list.isEmpty()) {
				LinkedList<Pair<KeyType, ValueType>> keyList = new LinkedList<>();
				keyList.addAll(list);
				newTable[Math.abs(list.hashCode() % newTable.length)] = keyList;
			}
		}
	}
	public int getIndex(ValueType value) {
		int index = -1;
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			for (int j = 0; j < list.size(); j++) { 
				Pair<KeyType, ValueType> pair = list.get(j);
				if (pair.getValue().equals(value)) {
					return index;
				}
				index++;
			}
		}
		return index;
	}
	
	private void rehash() {
		LinkedList<Pair<KeyType, ValueType>>[] newTable = new LinkedList[table.length * 2]; // Create a new
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> currList = table[i];
			if (!currList.isEmpty()) {
				LinkedList<Pair<KeyType, ValueType>> keyList = new LinkedList<>();
				keyList.addAll(currList);
				newTable[Math.abs(keyList.getFirst().getKey().hashCode() % newTable.length)] = keyList;
			}
		}
	}
	
	public void printTable(LinkedList<Pair<KeyType, ValueType>>[] table) {
		for (int i = 0; i < table.length; i++) {
			LinkedList<Pair<KeyType, ValueType>> list = table[i];
			System.out.println("List " + i);
			for (int j = 0; j < list.size(); j++) {
				System.out.println("k: " + table[i].get(j).getKey() + " v: " + table[i].get(j).getValue());
			}
		}
		System.out.println("");
	}
	
}
