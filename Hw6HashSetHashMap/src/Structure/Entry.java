package Structure;
/**
 * Represents a key-value pair entry for use in hash-based data structures.
 * This class stores a key, its associated value, and a deletion status flag.
 * 
 * @param <K> The type of the key
 * @param <V> The type of the value
 */
public class Entry<K, V>
{
    /** The key of this entry */
    private K key;
    
    /** The value associated with the key */
    private V value;
    
    /** Flag indicating whether this entry has been logically deleted */
    private boolean isDeleted;

    /**
     * Constructs a new Entry with the specified key and value.
     * The entry is initially marked as not deleted.
     * 
     * @param key The key for this entry
     * @param value The value associated with the key
     */
    public Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    /**
     * Returns the key of this entry.
     * 
     * @return The key
     */
    public K getKey()
        {return key;}
    
    /**
     * Returns the value associated with the key in this entry.
     * 
     * @return The value
     */
    public V getValue()
        {return value;}
    
    /**
     * Checks if this entry has been logically deleted.
     * 
     * @return true if this entry has been deleted, false otherwise
     */
    public boolean isDeleted()
        {return isDeleted;}
    
    /**
     * Sets the key of this entry.
     * 
     * @param key The new key
     */
    public void setKey(K key)
        {this.key = key;}
    
    /**
     * Sets the value associated with the key in this entry.
     * 
     * @param value The new value
     */
    public void setValue(V value)
        {this.value = value;}
    
    /**
     * Sets the deletion status of this entry.
     * Used for lazy deletion in hash tables.
     * 
     * @param isDeleted The new deletion status (true for deleted, false for active)
     */
    public void setDeleted(boolean isDeleted) 
        {this.isDeleted = isDeleted;}


}