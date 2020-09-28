// --== CS400 File Header Information ==--
// Name: Logan O'Brien
// Email: lrobrien@wisc.edu
// Team: IF
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: I used a HashNode class to organize the key-value structure

public class HashNode<KeyType, ValueType> {

	private KeyType key;
	private ValueType value;

	public HashNode(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}

	public KeyType getKey() {
		return key;
	}

	public ValueType getValue() {
		return value;
	}

	public void setKey(KeyType key) {
		this.key = key;
	}

	public void setValue(ValueType value) {
		this.value = value;
	}

}
