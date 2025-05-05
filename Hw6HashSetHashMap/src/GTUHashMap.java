public class GTUHashMap<K, V>
{
    private Entry<K, V>[] table;
    private int size;
    public GTUHashMap()
        { /* constructor */ }
    
    public void put(K key, V value) 
        { } //use linear probing here. Quadratic for the bonus.
    
    public V get(K key)
        { return null; }
    
    public void remove(K key)
        { } // mark as deleted (tombstone)
    
    public boolean containsKey(K key)
        { return false; }
    
    public int size()
        { return 0; }
}