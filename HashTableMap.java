// --== CS400 File Header Information ==--
// Name: Logan O'Brien
// Email: lrobrien@wisc.edu
// Team: IF
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: I used a HashNode class to organize the key-value structure

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	private LinkedList<HashNode<KeyType, ValueType>>[] hashTable; // Let's keep track of our HashTable in a array of
																	// lists of hashnodes (my class to keep track of
																	// key-value pairs)
	private int size; // Every time we add a new key-value pair, increase the size

	public HashTableMap() {
		// Create an empty HashTable of size 10, with empty lists
		hashTable = new LinkedList[10];

		for (int i = 0; i < 10; i++) {
			hashTable[i] = new LinkedList<HashNode<KeyType, ValueType>>();
		}
	}

	public HashTableMap(int capacity) {
		// Create an empty HashTable of size capacity, with empty lists
		hashTable = new LinkedList[capacity];

		for (int i = 0; i < capacity; i++) {
			hashTable[i] = new LinkedList<HashNode<KeyType, ValueType>>();
		}
	}

	@Override
	public boolean put(KeyType key, ValueType value) {
		HashNode<KeyType, ValueType> node = new HashNode<KeyType, ValueType>(key, value); // Create a new node with the
																							// key-value pair

		if (containsKey(key)) {
			// If the key is already in our table
			// Don't do anything, return false

			return false;
		} else if (containsValue(value)) {
			// If the value is already in our table
			// We have a collision!
			size++;
			LinkedList<HashNode<KeyType, ValueType>> list = hashTable[getIndexValue(value)]; // Get the previous list of
																								// key, values
			list.add(node); // Add our node to the list

			return true;
		} else {
			size++;
			checkLoadCapacity();
			LinkedList<HashNode<KeyType, ValueType>> list = hashTable[Math.abs(key.hashCode() % hashTable.length)];
			list.add(node); // Add the node to our list at an index

			return true;
		}
	}

	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		ValueType value = null;

		if (containsKey(key)) {
			// If the key is in the list
			for (int i = 0; i < hashTable.length; i++) {
				// Get the list of pairs for each index
				LinkedList<HashNode<KeyType, ValueType>> list = hashTable[i];

				for (int j = 0; j < list.size(); j++) {
					// Look at each node in our list to get the key
					HashNode<KeyType, ValueType> node = list.get(j);

					if (node.getKey().equals(key))
						value = node.getValue();
				}
			}
			return value;
		} else {
			// If the key isn't in the list, throw the error
			throw new NoSuchElementException();
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean containsKey(KeyType key) {
		boolean foundKey = false;

		for (int i = 0; i < hashTable.length; i++) {
			// Get the list of pairs for each index
			LinkedList<HashNode<KeyType, ValueType>> list = hashTable[i];

			for (int j = 0; j < list.size(); j++) {
				// Check each list if we can find the key
				HashNode<KeyType, ValueType> node = list.get(j);

				if (node.getKey().equals(key))
					foundKey = true;
			}
		}

		return foundKey;
	}

	@Override
	public ValueType remove(KeyType key) {
		ValueType value = null;

		for (int i = 0; i < hashTable.length; i++) {
			// Get the list of pairs for each index
			LinkedList<HashNode<KeyType, ValueType>> currList = hashTable[i];

			for (int j = 0; j < currList.size(); j++) {
				// Let's see if we can find the key we want to remove
				if (currList != null && currList.get(j).getKey().equals(key)) {
					// Found the key, get the value
					value = currList.get(j).getValue();

					if (currList.size() > 1) {
						// If the list of pairs is greater than one, remove the pair from the list
						currList.remove(j);
						hashTable[Math.abs(key.hashCode() % hashTable.length)] = currList;
					} else {
						// If the list of pairs is equal to one, then create a new empty list of pairs
						hashTable[Math.abs(
								key.hashCode() % hashTable.length)] = new LinkedList<HashNode<KeyType, ValueType>>();
					}
					break;
				}
			}
		}
		size--;
		rehash();

		return value;
	}

	@Override
	public void clear() {
		// To clear the table, loop through and set each list of pairs to an empty list
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new LinkedList<HashNode<KeyType, ValueType>>();
		}
		size = 0;
	}

	// Helper Methods ------------------------------

	/**
	 * Loop through the HashTable to see if the value already exists
	 * 
	 * @param value - The value we are looking to see if already is in our hashtable
	 * @return true or false - if we can find the value
	 */
	private boolean containsValue(ValueType value) {
		boolean foundValue = false;

		for (int i = 0; i < hashTable.length; i++) {
			// Get the list of pairs for each index
			LinkedList<HashNode<KeyType, ValueType>> list = hashTable[i];

			for (int j = 0; j < list.size(); j++) {
				// Look at each node in the list to see if it has the value
				HashNode<KeyType, ValueType> node = list.get(j);

				if (node.getValue().equals(value))
					foundValue = true;
			}
		}

		return foundValue;
	}

	/**
	 * Find the index of a value in our HashTable
	 * 
	 * @param value - the value who's index we are looking for
	 * @return the index of the value in the array
	 */
	private int getIndexValue(ValueType value) {
		int valueIndex = -1;

		for (int i = 0; i < hashTable.length; i++) {
			// Get the list of pairs for each index
			LinkedList<HashNode<KeyType, ValueType>> list = hashTable[i];

			for (int j = 0; j < list.size(); j++) {
				// Look at each node in the list to find the value
				HashNode<KeyType, ValueType> node = list.get(j);

				if (node.getValue().equals(value))
					valueIndex = i;
			}
		}

		return valueIndex;
	}

	/**
	 * Check to see if the HashTable is 80% filled or more
	 */
	private void checkLoadCapacity() {
		if (((size * 1.0) / (hashTable.length * 1.0)) >= .8) {
			// If the load capacity is greater than 80% we need to
			// increase the size of the table, and rehash
			increaseTableCapacityAndRehash();
		}
	}

	/**
	 * We want to double the size of the table and rehash all of its pairs to the
	 * correct index
	 */
	private void increaseTableCapacityAndRehash() {
		LinkedList<HashNode<KeyType, ValueType>>[] newTable = new LinkedList[hashTable.length * 2]; // Create a new
																									// table double the
																									// size
		for (int i = 0; i < newTable.length; i++) {
			// Initialize with empty lists
			newTable[i] = new LinkedList<>();
		}

		for (int i = 0; i < hashTable.length; i++) {
			// Loop through the old table to get it's pairs
			LinkedList<HashNode<KeyType, ValueType>> currList = hashTable[i];

			if (!currList.isEmpty()) {
				// If we find a list of pairs that isn't empty, add the values to our new table
				LinkedList<HashNode<KeyType, ValueType>> keyList = new LinkedList<>();
				keyList.addAll(currList);
				newTable[Math.abs(currList.hashCode() % newTable.length)] = keyList;
			}
		}

		hashTable = newTable;
	}

	private void rehash() {
		LinkedList<HashNode<KeyType, ValueType>>[] newTable = new LinkedList[hashTable.length * 2]; // Create a new

		for (int i = 0; i < hashTable.length; i++) {
			// Loop through the old table to get it's pairs
			LinkedList<HashNode<KeyType, ValueType>> currList = hashTable[i];

			if (!currList.isEmpty()) {
				// If we find a list of pairs that isn't empty, add the values to our new table
				LinkedList<HashNode<KeyType, ValueType>> keyList = new LinkedList<>();
				keyList.addAll(currList);
				newTable[Math.abs(keyList.getFirst().getKey().hashCode() % newTable.length)] = keyList;
			}
		}
	}

	/**
	 * Used in this class and the test class to visualize the table
	 */
	public void printTable(LinkedList<HashNode<KeyType, ValueType>>[] table) {
		System.out.println("\nTABLE -");
		for (int i = 0; i < table.length; i++) {
			LinkedList<HashNode<KeyType, ValueType>> list = table[i];
			System.out.println("List " + i);
			for (int j = 0; j < list.size(); j++) {
				System.out.println("k: " + table[i].get(j).getKey() + " v: " + table[i].get(j).getValue());
			}
		}
		System.out.println("");
	}

	/**
	 * Acessor for the test class
	 * 
	 * @return our private table
	 */
	public LinkedList<HashNode<KeyType, ValueType>>[] getTable() {
		return hashTable;
	}

}