package Structure;
import java.lang.Math;
/**
 * A custom implementation of a hash map using quadratic probing for collision resolution.
 * This class provides basic map operations such as put, get, and remove.
 * The map automatically resizes when the load factor exceeds 0.75.
 * 
 * @param <K> The type of keys maintained by this map
 * @param <V> The type of mapped values
 */
public class GTUHashMap<K, V>
{
    /** Tracks the number of collisions that have occurred during operations */
    private int collisions = 0;
    
    /** The initial capacity of the hash map */
    private final int INITIAL_CAPACITY = 17;
    
    /** The load factor threshold, above which the map will be resized */
    private final double LOAD_FACTOR = 0.75;
    
    /** The current capacity of the hash map */
    private int capacity;
    
    /** The array of entries that stores the key-value pairs */
    private Entry<K, V>[] table;
    
    /** The number of key-value mappings in this map */
    private int size;
    
    /**
     * Constructs an empty hash map with the initial capacity of 17.
     */
    @SuppressWarnings("unchecked")
    public GTUHashMap()
    {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        size = 0;
    }
    
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * 
     * @param key The key with which the specified value is to be associated
     * @param value The value to be associated with the specified key
     */
    public void put(K key, V value) 
    {
        if((double)size/capacity >= LOAD_FACTOR)
            reallocate();
        int indexValue = hashCalculater(key);
        put(key, value, indexValue, indexValue,0);
    }
    
    /**
     * Helper method for put operation that handles collisions using quadratic probing.
     * 
     * @param key The key with which the specified value is to be associated
     * @param value The value to be associated with the specified key
     * @param startIndex The initial hash index for the key
     * @param indexValue The current index being examined
     * @param probeNum The number of probes performed so far
     */
    private void put(K key,V value,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null || table[indexValue].isDeleted())
            {
                table[indexValue] = new Entry<>(key, value);
                size++;
                return;
            }
            else if(table[indexValue].getKey().equals(key))
            {
                table[indexValue].setKey(key);
                table[indexValue].setValue(value);
                return;
            }
            else
            {
                collisions++;
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
            }     
        }
    }

    /**
     * Increases the capacity of the hash map and rehashes all existing entries.
     * The new capacity is the next prime number after doubling the current capacity.
     */
    @SuppressWarnings("unchecked")
    private void reallocate()
    {
        Entry<K,V>[] previousTable = table;
        int oldCapacity = capacity;
        capacity *= 2;
        capacity = nextPrime(capacity);
        table = new Entry[capacity];
        size = 0;
        for(int i = 0;i<oldCapacity;i++)
        {
            Entry<K,V> element = previousTable[i];
            if (element != null && !element.isDeleted())
                put(element.getKey(),element.getValue());
        }
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     * 
     * @param key The key whose associated value is to be returned
     * @return The value to which the specified key is mapped, or null if no mapping
     */
    public V get(K key)
    {
        if(key == null)
            return null;
        else if(containsKey(key))
        {
            int indexValue = hashCalculater(key);
            return get(key,indexValue,indexValue,0);
        }
        else
            return null;
    }
    
    /**
     * Helper method for get operation that handles collisions using quadratic probing.
     * 
     * @param key The key whose associated value is to be returned
     * @param startIndex The initial hash index for the key
     * @param indexValue The current index being examined
     * @param probeNum The number of probes performed so far
     * @return The value to which the key is mapped, or null if not found
     */
    private V get(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null)
                return null;
            else if(table[indexValue].getKey().equals(key))
                return table[indexValue].getValue();
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
                if(probeNum >= capacity)
                    return null;
            }
        }
    }
 
    /**
     * Removes the mapping for the specified key from this map if present.
     * 
     * @param key The key whose mapping is to be removed from the map
     */
    public void remove(K key)
    {
        if(containsKey(key))
        {
            int indexValue = hashCalculater(key);
            remove(key, indexValue, indexValue, 0);
        }
        else
            System.out.println("We could not find element in table");
    }

    /**
     * Helper method for remove operation that handles collisions using quadratic probing.
     * 
     * @param key The key whose mapping is to be removed from the map
     * @param startIndex The initial hash index for the key
     * @param indexValue The current index being examined
     * @param probeNum The number of probes performed so far
     */
    private void remove(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null)
                return;
            if(table[indexValue].getKey().equals(key))
            {    
                table[indexValue].setDeleted(true);
                size--;
                return;
            }
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
                if(probeNum >= capacity)
                    return;
            }
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param key The key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    public boolean containsKey(K key)
    {
        int hashValue = hashCalculater(key);
        int indexValue = hashValue;
        return containsKey(key,indexValue,indexValue,0);
    }

    /**
     * Helper method for containsKey operation that handles collisions using quadratic probing.
     * 
     * @param key The key whose presence in this map is to be tested
     * @param startIndex The initial hash index for the key
     * @param indexValue The current index being examined
     * @param probeNum The number of probes performed so far
     * @return true if this map contains a mapping for the specified key
     */
    private boolean containsKey(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null)
                return false;
            else if(table[indexValue].getKey().equals(key))
                return true;
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
                if(probeNum >= capacity)
                    return false;
            }
        }
    }

    /**
     * Calculates the hash value for the given key.
     * 
     * @param key The key to hash
     * @return The hash value for the key
     */
    private int hashCalculater(K key)
    {
        if(key == null)
            return 0;
        int hashValue = 0;
    
        hashValue = Math.abs(key.hashCode() % capacity);
        
        return hashValue;
    }
    
    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return The number of key-value mappings in this map
     */
    public int size()
        { return size; }

    /**
     * Finds the next prime number greater than or equal to n.
     * 
     * @param n The starting number
     * @return The next prime number greater than or equal to n
     */
    private int nextPrime(int n)
    {
        while (!isPrime(n))
            n++;
        return n;
    }

    /**
     * Checks if a number is prime.
     * 
     * @param n The number to check
     * @return true if the number is prime, false otherwise
     */
    private boolean isPrime(int n)
    {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    /**
     * Returns a list containing all the keys in this map.
     * 
     * @return A list containing all the keys in this map
     */
    public GTUArrayList<K> keySet()
    {
        GTUArrayList<K> keys = new GTUArrayList<>();
        for (Entry<K, V> entry : table)
            if (entry != null && !entry.isDeleted())
                keys.add(entry.getKey());
        return keys;
    }

    /**
     * Returns the number of collisions that have occurred during operations.
     * 
     * @return The number of collisions
     */
    public int getCollisions()
    {
        return collisions;
    }
}