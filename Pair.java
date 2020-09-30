//Helper class for HashTableMap

public class Pair<KeyType, ValueType> {
	
	private KeyType key;
	private ValueType value;
	
	public Pair(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyType getKey() {
		return key;
	}
	
	public ValueType getValue() {
		return value;
	}
	
	public String toString() {
		//return "Key: " + key.toString() + " Value: " + value.toString();
		return "Key: " + getKey() + " Value: " + getValue();
	}
}