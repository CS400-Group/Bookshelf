import java.util.LinkedList;
import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Logan O'Brien
// Email: lrobrien@wisc.edu
// Team: IF
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: I used a HashNode class to organize the key-value structure

public class TestHashTable<KeyType, ValueType> {

	/**
	 * Test that the constructors create the tables of expected length
	 * 
	 * @return Expected outcome (true or false)
	 */
	public boolean test1() {
		HashTableMap<KeyType, ValueType> table1 = new HashTableMap<KeyType, ValueType>(10);
		HashTableMap<KeyType, ValueType> table2 = new HashTableMap<KeyType, ValueType>();

		return (table1.getTable().length == 10 && table2.getTable().length == 10);
	}

	/**
	 * Test that the remove functionality works
	 * 
	 * @return Expected outcome (true or false)
	 */
	public boolean test2() {
		HashTableMap table1 = new HashTableMap(3);
		table1.put(12, 10);
		table1.put(13, 11);
		table1.remove(13);

		HashTableMap expected1 = new HashTableMap(3);
		expected1.put(12, 10);

		return tablesAreEqual(table1, expected1);
	}

	/**
	 * Test that the put functionality works
	 * 
	 * @return Expected outcome (true or false)
	 */
	public boolean test3() {
		HashTableMap table1 = new HashTableMap<KeyType, ValueType>(10);
		table1.put(12, 10);

		return !table1.put(12, 10) && table1.put(13, 10) && table1.put(23, 12);
	}

	/**
	 * Test that the get functionality works
	 * 
	 * @return Expected outcome (true or false)
	 */
	public boolean test4() {
		HashTableMap table1 = new HashTableMap<KeyType, ValueType>(10);
		table1.put(12, 10);

		try {
			table1.get(12);
			table1.get(13);
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	/**
	 * Tests the clear functionality
	 * 
	 * @return Expected outcome (true or false)
	 */
	public boolean test5() {
		HashTableMap table1 = new HashTableMap<KeyType, ValueType>(10);
		table1.put(12, 11);
		table1.clear();

		for (int i = 0; i < table1.getTable().length; i++) {
			if (!table1.getTable()[i].isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public static void main(String args[]) {
		TestHashTable test = new TestHashTable<>();

		if (test.test1()) {
			System.out.println("Test 1 passes!");
		} else {
			System.out.println("Test 1 fails.");
		}

		if (test.test2()) {
			System.out.println("Test 2 passes!");
		} else {
			System.out.println("Test 2 fails.");
		}

		if (test.test3()) {
			System.out.println("Test 3 passes!");
		} else {
			System.out.println("Test 3 fails.");
		}

		if (test.test4()) {
			System.out.println("Test 4 passes!");
		} else {
			System.out.println("Test 4 fails.");
		}

		if (test.test5()) {
			System.out.println("Test 5 passes!");
		} else {
			System.out.println("Test 5 fails.");
		}
	}

	/**
	 * Compare the tables to check if they are equal
	 * 
	 * @param table1
	 * @param table2
	 * @return If they are equal (true or false)
	 */
	public boolean tablesAreEqual(HashTableMap<KeyType, ValueType> table1, HashTableMap<KeyType, ValueType> table2) {
		for (int i = 0; i < table1.getTable().length; i++) {
			LinkedList<HashNode<KeyType, ValueType>> keyList1 = table1.getTable()[i];
			LinkedList<HashNode<KeyType, ValueType>> keyList2 = table2.getTable()[i];

			if (keyList1.isEmpty()) {
				if (!keyList2.isEmpty())
					return false;
			} else if (keyList2.isEmpty()) {
				if (!keyList1.isEmpty())
					return false;
			} else if (keyList1.size() != keyList2.size()) {
				return false;
			} else {
				for (int j = 0; j < keyList1.size(); j++) {
					if (keyList1.get(j).getKey() != keyList2.get(j).getKey())
						return false;
					if (keyList1.get(j).getValue() != keyList2.get(j).getValue())
						return false;
				}
			}
		}
		return true;
	}

}
