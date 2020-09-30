import java.util.NoSuchElementException;

public interface Shelf<KeyType, ValueType> {
	public boolean add(ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public boolean replace(String oldValue, ValueType value2);
	public ValueType remove(KeyType key); 
	public void clear();

}
